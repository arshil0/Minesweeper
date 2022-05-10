package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.game.*;
import Minesweeper.Items.*;

import java.awt.*;

public class UI extends JFrame {

    public static final int FILESIZE = 800;
    private Minesweeper game;
    private Block[][] blocks;
    private ClickBox[][] clickBoxes;

    public UI(){
        super("Minesweeper");
        game = new Minesweeper();
        blocks = new Block[game.getHeight()][game.getWidth()];
        setBlocks(game);

        //adjust the window
        setSize(FILESIZE + 428,FILESIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);


        //set the blocks on the field
        JPanel field = new JPanel(new GridLayout(game.getWidth(),game.getHeight()));
        addBlocksOnField(field);

        JPanel itemList = new JPanel();
        itemList.setVisible(true);
        itemList.setBackground(Color.DARK_GRAY);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setVisible(true);
        //fieldPanel.setBackground(Color.CYAN);

        itemList.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
        fieldPanel.add(field);
        add(itemList, BorderLayout.NORTH);
        add(fieldPanel,BorderLayout.CENTER);
        itemList.add(new test(this));
        //add(field,BorderLayout.CENTER);
        //to open the frame in the middle of the screen
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void setBlocks(Minesweeper game){
        Tile[][] field = game.getField();
        for(int row = 0; row < game.getHeight(); row ++){
            for(int column = 0; column < game.getWidth(); column++ ){
                Tile currentTile = field[row][column];
                blocks[row][column] = new Block(currentTile.toString(),currentTile instanceof Mine, row, column,this);
                if(currentTile instanceof EmptyTile)
                    blocks[row][column].setAdjacentMines(checkForMines(row,column));
            }
        }
    }

    private int checkForMines(int row, int column){
        Tile[][] field = game.getField();
        int[] rowOffSets = {-1,-1,-1,0,0,1,1,1};
        int[] columnOffSets = {-1,0,1,-1,1,-1,0,1};
        int adjacentMines = 0;
        for(int i = 0; i < 8; i ++){
            int currentRow = row + rowOffSets[i];
            int currentColumn = column + columnOffSets[i];
            if(Position.validX(currentRow) && Position.validY(currentColumn)){
                if(field[currentRow][currentColumn] instanceof Mine){
                    adjacentMines += 1;
                }
            }
        }
        return adjacentMines;
    }

    public Block[][] getBlocks(){
        return blocks;
    }
    private void addBlocksOnField(JPanel field){
        for(Block[] blockArr: blocks){
            for(Block block: blockArr){
                field.add(block);
            }
        }
    }

    public void loseScreen(){
        JDialog loseScreen = new JDialog();
        loseScreen.setLayout(new BorderLayout());
        loseScreen.setResizable(false);
        loseScreen.setSize(200,150);
        loseScreen.add(new JLabel("you lost:"),BorderLayout.NORTH);
        loseScreen.setVisible(true);
        loseScreen.setLocationRelativeTo(null);

    }


    public static void main(String[] args){
        UI u = new UI();
    }


}
