package Minesweeper.game;

public class InvalidPosition extends Exception{

    public InvalidPosition(){
        super("Invalid Position");
    }
    public InvalidPosition(String e){
        super(e);
    }
}
