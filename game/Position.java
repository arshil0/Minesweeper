package Minesweeper.game;

public class Position {

    private int x;
    private int y;

    public Position(){
        x = 0;
        y = 0;
    }

    public Position(int x, int y) throws InvalidPosition{
        if(valid(x) && valid(y)){
            this.x = x;
            this.y = y;
        }
        else{
            throw new InvalidPosition();
        }
    }


    private boolean valid(int coordinate){
        if(coordinate >= 0 && coordinate < Minesweeper.getSize())
            return true;
        return false;
    }

}
