package Minesweeper.UI;

import Minesweeper.game.Minesweeper;
import Minesweeper.game.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Block extends JLabel implements MouseListener {

    private int adjacentMines = 0;
    private boolean revealed = false;
    private UI ongoingGame;
    private String type;
    private boolean isMine;
    private int y;
    private int x;



    public Block(String s, boolean isMine, int y, int x, UI game){
        setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/placeholder.png").getImage().getScaledInstance(UI.FILESIZE/Minesweeper.getHeight()/2,UI.FILESIZE/Minesweeper.getWidth()/2,Image.SCALE_DEFAULT)));
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
    public void setIcon(int adjacentMines, String type){
        if(adjacentMines != 0)
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/E" + adjacentMines + ".png").getImage().getScaledInstance(UI.FILESIZE/Minesweeper.getHeight()/2,UI.FILESIZE/Minesweeper.getWidth()/2,Image.SCALE_DEFAULT)));
        else{
            setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/" + type + ".png").getImage().getScaledInstance(UI.FILESIZE/Minesweeper.getHeight()/2,UI.FILESIZE/Minesweeper.getWidth()/2,Image.SCALE_DEFAULT)));
        }
        revealed = true;
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
                if(!b.isRevealed()){
                    b.setIcon(b.adjacentMines,b.type);
                    if(b.adjacentMines == 0){
                        openAdjacent(b.y,b.x);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            if (!Minesweeper.lost) {
                setIcon(adjacentMines, type);
                if (isMine)
                    Minesweeper.lost = true;
                if(adjacentMines == 0 && !isMine)
                    openAdjacent(y,x);
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("a");
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
