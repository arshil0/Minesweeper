package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.game.*;
import Minesweeper.Items.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private JPanel itemList;
    private JPanel field;
    public static final int FILESIZE = 800;
    private Minesweeper game;
    private Block[][] blocks;

    public UI(){
        super("Minesweeper");
        game = new Minesweeper();
        setBlocks(game);
        //adjust the window
        setSize(FILESIZE + 428,FILESIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        field = new JPanel(new GridLayout(game.getWidth(),game.getHeight()));

        itemList = new JPanel();
        itemList.setVisible(true);
        itemList.setBackground(Color.DARK_GRAY);
        itemList.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
        add(itemList, BorderLayout.NORTH);

        adjustField(null);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setVisible(true);
        //fieldPanel.setBackground(Color.CYAN);

        fieldPanel.add(field);
        add(fieldPanel,BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        System.out.println(blocks[1][1].adjacentMines);



        //itemList.add(new test(this));
        //add(field,BorderLayout.CENTER);
        //to open the frame in the middle of the screen

    }

    public Minesweeper getGame(){
        return game;
    }

    public void adjustField(int[] reservedCoordinates){
        field.removeAll();
        game = new Minesweeper(Minesweeper.getWidth(), Minesweeper.getHeight(), reservedCoordinates);
        adjustBlocks(game);

        //set the blocks on the field
        addBlocksOnField(field);




    }

    private void setBlocks(Minesweeper game){
        blocks = new Block[game.getHeight()][game.getWidth()];
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

    private void adjustBlocks(Minesweeper game) {
        Tile[][] field = game.getField();
        for (int row = 0; row < game.getHeight(); row++) {
            for (int column = 0; column < game.getWidth(); column++) {
                Tile currentTile = field[row][column];
                blocks[row][column].changeValues(currentTile.toString(), currentTile instanceof  Mine, row, column);
                if (currentTile instanceof EmptyTile)
                    blocks[row][column].setAdjacentMines(checkForMines(row, column));
            }
        }
        System.out.println(blocks[1][1].adjacentMines);
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
        loseScreen.setLocationRelativeTo(null);

        JPanel youLost = new JPanel(new FlowLayout());
        youLost.add(new JLabel("you Lost!"));

        JPanel buttons = new JPanel(new FlowLayout());
        JButton retry = new JButton("retry");
        retry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loseScreen.dispose();
                new UI();
            }
        });
        JButton exit = new JButton("exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttons.add(retry);
        buttons.add(exit);
        loseScreen.add(youLost,BorderLayout.NORTH);
        loseScreen.add(buttons,BorderLayout.CENTER);
        loseScreen.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        loseScreen.setVisible(true);

    }

    public void updateBlock(Block b,int y,int x){
        blocks[y][x] = b;
    }

    public static void main(String[] args){
        new UI();
    }


}
