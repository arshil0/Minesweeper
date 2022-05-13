package Minesweeper.game;

public abstract class Position {
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
