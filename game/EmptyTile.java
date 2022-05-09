package Minesweeper.game;

public class EmptyTile extends Tile{

    private int adjacentMines;


    public void setAdjacentMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }
    public String toString(){
        return "E";
    }

}
