package Minesweeper.UI;

import javax.swing.*;

public class Block extends JButton {

    private boolean isMine;
    private int y;
    private int x;



    public Block(String s, boolean isMine, int y, int x){
        setIcon(new ImageIcon("src/Minesweeper/UI/Sprites/" + s + ".png"));
        this.isMine = isMine;
        this.y = y;
        this.x = x;
    }

    public boolean isMine(){
        return this.isMine;
    }

    public String toString(){
        return isMine + " " + y + " " + x;
    }

}
