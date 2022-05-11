package Minesweeper.UI;

import Minesweeper.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Block extends JLabel implements MouseListener {

    private int widthLength = UI.FILESIZE/Minesweeper.getWidth()/4 * 3;
    private int heightLength = UI.FILESIZE/Minesweeper.getHeight()/4 * 3;
    public int adjacentMines = 0;
    private boolean revealed = false;
    private boolean flagged = false;
    private UI ongoingGame;
    private String type;
    private boolean isMine;
    private int y;
    private int x;



    public Block(String s, boolean isMine, int y, int x, UI game){
        setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/block.png").getImage().getScaledInstance(heightLength,widthLength,Image.SCALE_DEFAULT)));
        type = s;
        ongoingGame = game;
        this.isMine = isMine;
        this.y = y;
        this.x = x;
        addMouseListener(this);
    }

    public boolean isMine(){
        return this.isMine;
    }

    public boolean isRevealed(){
        return revealed;
    }

    public void setAdjacentMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }

    public void changeValues(String s, boolean isMine, int y,int x){
        this.type = s;
        this.isMine = isMine;
        this.y = y;
        this.x = x;
    }
    public void setIcon(int adjacentMines, String type){
        if(adjacentMines != 0 && !isMine)
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/E" + adjacentMines + ".png").getImage().getScaledInstance(heightLength,widthLength,Image.SCALE_DEFAULT)));
        else{
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/" + type + ".png").getImage().getScaledInstance(heightLength,widthLength,Image.SCALE_DEFAULT)));
        }
        revealed = true;
    }

    private void flag(){
        flagged = !flagged;
        if(flagged){
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/flag.png").getImage().getScaledInstance(heightLength,widthLength,Image.SCALE_DEFAULT)));
        }
        else{
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/block.png").getImage().getScaledInstance(heightLength,widthLength,Image.SCALE_DEFAULT)));
        }
    }

    public String toString(){
        return isMine + " " + y + " " + x;
    }

    private void openAdjacent(int y, int x){
        int[] rowOffSets = {-1,-1,-1,0,0,1,1,1};
        int[] columnOffSets = {-1,0,1,-1,1,-1,0,1};
        for(int i = 0; i < 8; i ++) {
            int currentRow = y + rowOffSets[i];
            int currentColumn = x + columnOffSets[i];
            if (Position.validX(currentRow) && Position.validY(currentColumn)) {
                Block b = ongoingGame.getBlocks()[currentRow][currentColumn];
                if(!b.isRevealed() && !b.flagged){
                    b.setIcon(b.adjacentMines,b.type);
                    if(b.adjacentMines == 0){
                        openAdjacent(b.y,b.x);
                    }
                }
            }
        }
    }

    private int[] opening(){
        ArrayList<Integer> reservedCoordinates= new ArrayList<Integer>();
        int[] rowOffSets = {-1,-1,-1,0,0,1,1,1};
        int[] columnOffSets = {-1,0,1,-1,1,-1,0,1};
        for(int i = 0; i < 8; i ++) {
            int currentRow = y + rowOffSets[i];
            int currentColumn = x + columnOffSets[i];
            if (Position.validX(currentRow) && Position.validY(currentColumn)) {
                reservedCoordinates.add((currentRow * Minesweeper.getWidth()) + currentColumn);
            }
            reservedCoordinates.add(y * Minesweeper.getWidth() + currentColumn);
        }
        return (reservedCoordinates.stream().mapToInt(i->i).toArray());

    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!Minesweeper.isLost()) {
            if(Minesweeper.isFirstMove() && e.getButton() == MouseEvent.BUTTON1){
                ongoingGame.adjustField(opening());
                Minesweeper.setFirstMove(false);
                openAdjacent(y,x);
                setIcon(adjacentMines,type);
            }
            else if (e.getButton() == MouseEvent.BUTTON1 && !flagged) {
                setIcon(adjacentMines, type);
                if (isMine) {
                    Minesweeper.setLost(true);
                    ongoingGame.loseScreen();
                }
                if (adjacentMines == 0 && !isMine)
                    openAdjacent(y, x);
            } else if (e.getButton() == MouseEvent.BUTTON3 && !revealed && !Minesweeper.isFirstMove()) {
                flag();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
