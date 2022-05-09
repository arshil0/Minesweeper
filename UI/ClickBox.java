package Minesweeper.UI;

import javax.swing.JButton;

public class ClickBox extends JButton {

    private boolean isMine;
    private int y;
    private int x;


    public ClickBox(boolean isMine, int y, int x){
        super();
        this.isMine = isMine;
        this.y = y;
        this.x = x;
        setVisible(true);
    }

    public boolean isMine(){
        return this.isMine;
    }


}
