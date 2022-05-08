package Minesweeper.game;

public class Mine extends Tile{

    private boolean isEmpty;


    public Mine(){
        isEmpty = true;
    }

    public Mine(boolean empty){
        isEmpty = empty;
    }

    public String toString(){
        return "M";
    }
}
