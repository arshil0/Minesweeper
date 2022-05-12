package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.game.*;
import Minesweeper.Items.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private JPanel itemList;
    private JPanel fieldPanel;
    private JPanel field;
    private JPanel gameStats;
    private JLabel mineCount = new JLabel("Mines: ");
    private int currentNumberOfMines;
    private JPanel playerStats;
    private JLabel health;
    private JLabel shield = new JLabel("Shield: ");

    private static boolean firstTimeOpeningProgram = true;
    private static boolean choosingItem = false;

    public static final int FILESIZE = 800;
    private Minesweeper game;
    private Block[][] blocks;

    public UI(){
        //this also sets the width and height of Minesweeper to the given values;
        this(Minesweeper.STARTWIDTH,Minesweeper.STARTHEIGHT);
    }

    public UI(int width, int height){
        super("Minesweeper");
        if(firstTimeOpeningProgram){
            Item.refreshItemList(this);
            firstTimeOpeningProgram = false;
        }
        game = new Minesweeper(width,height);
        setBlocks(game);
        //adjust the window
        setSize(FILESIZE + 428,FILESIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        field = new JPanel(new GridLayout(game.getWidth(),game.getHeight()));

        itemList = new JPanel(new FlowLayout());
        itemList.setVisible(true);
        itemList.setBackground(new Color(34, 34, 35));
        itemList.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
        add(itemList, BorderLayout.NORTH);

        adjustField(null);

        fieldPanel = new JPanel();
        fieldPanel.setVisible(true);

        fieldPanel.add(field);
        add(fieldPanel,BorderLayout.CENTER);

        currentNumberOfMines = game.getNumberOfTotalMines();
        mineCount.setText("Mines: " + currentNumberOfMines);
        gameStats = new JPanel(new BorderLayout());
        gameStats.add(mineCount,BorderLayout.NORTH);
        gameStats.setBorder(BorderFactory.createEmptyBorder(0,0,0,100));
        add(gameStats,BorderLayout.EAST);

        health = new JLabel("Health: " + game.getHealth());
        playerStats = new JPanel();
        playerStats.add(health);
        playerStats.setBorder(BorderFactory.createEmptyBorder(0,100,0,0));
        add(playerStats,BorderLayout.WEST);

        updateItemList();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void minusMineCount(){
        mineCount.setText("Mines: " + --currentNumberOfMines );
    }

    public void plusMineCount(){
        mineCount.setText("Mines: " + ++currentNumberOfMines);
    }

    public void updateHealth(){
        health.setText("Health: " + game.getHealth());
    }

    public void updateShield(){
        shield.setText("Shield: " + game.getShield());
    }

    public boolean isChoosingItem(){
        return choosingItem;
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
        firstTimeOpeningProgram = true;
        game.setLost(true);
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

    public void win() {
        if (!game.isLost()) {
            System.out.println("you won");
            ItemChooser itemChooser = new ItemChooser(this);
            itemChooser.setVisible(true);
            choosingItem = true;

        }
    }

    public void nextLevel(){
        choosingItem = false;
        new UI(Minesweeper.getWidth() + 1, Minesweeper.getHeight() + 1);
        dispose();
    }
    private void updateItemList(){
        itemList.removeAll();
        Item[] items = game.getItemList();
        for(Item item: items){
            itemList.add(item);
        }
        revalidate();
    }

    public static void main(String[] args){
        new UI();
    }


}
