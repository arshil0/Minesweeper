package Minesweeper.game;

public class Position {

    private int x;
    private int y;

    public Position(){
        x = 0;
        y = 0;
    }

    public Position(int x, int y) throws InvalidPosition{
        if(validX(x) && validY(y)){
            this.x = x;
            this.y = y;
        }
        else{
            throw new InvalidPosition();
        }
    }


    public static boolean validX(int x){
        if(x >= 0 && x < Minesweeper.getWidth())
            return true;
        return false;
    }

    public static boolean validY(int y){
        if(y >= 0 && y < Minesweeper.getHeight())
            return true;
        return false;
    }

}
