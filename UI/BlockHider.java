package Minesweeper.UI;

import javax.swing.JButton;

public class BlockHider extends JButton {

    private boolean isMine;
    private int y;
    private int x;


    public BlockHider(boolean isMine, int y, int x){
        this.isMine = isMine;
        this.y = y;
        this.x = x;
    }

    public boolean isMine(){
        return this.isMine;
    }


}
