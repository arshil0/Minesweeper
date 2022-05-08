package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.game.*;

import java.awt.*;

public class UI extends JFrame {

    private static final int FILESIZE = 200;
    private Minesweeper game;
    private Block[][] blocks;
    private BlockHider[][] blockHiders;

    public UI(){
        game = new Minesweeper();
        blocks = new Block[game.getHeight()][game.getWidth()];
        setBlocks(game);

        //make the window
        JFrame window = new JFrame("Minesweeper");
        window.setSize(game.getWidth() * FILESIZE, game.getHeight() * FILESIZE);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);

        //set the blocks on the field
        JPanel field = new JPanel();
        field.setLayout(new GridLayout(game.getWidth(),game.getHeight()));
        addBlocksOnField(field);

        window.add(field);
        window.setVisible(true);

    }


    private void setBlocks(Minesweeper game){
        Tile[][] field = game.getField();
        for(int row = 0; row < game.getHeight(); row ++){
            for(int column = 0; column < game.getWidth(); column++ ){
                Tile currentTile = field[row][column];
                blocks[row][column] = new Block(currentTile.toString(),currentTile instanceof Mine, row, column);
            }
        }
    }

    private void addBlocksOnField(JPanel field){
        for(Block[] blockArr: blocks){
            for(Block block: blockArr){
                field.add(block);
            }
        }
    }

    public static void main(String[] args){
        UI u = new UI();
    }


}
