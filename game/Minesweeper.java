package Minesweeper.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Minesweeper {

    private static int width;
    private static int height;
    //the number of bombs = size * size / BOMBSCALE
    private static final int BOMBSCALE = 3;

    private Tile[][] field;


    public Minesweeper(){
        width = 5;
        height = 5;
        setBoard();
    }

    public Tile[][] getField(){
        return field;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
    public static int getSize(){
        return width * height;
    }

    private void setBoard(){
        int numberOfMines = width * height / BOMBSCALE;
        int[] bombCoordinates = generateMineCoordinates(numberOfMines);
        Arrays.sort(bombCoordinates);
        setBoard(bombCoordinates);
    }

    private void setBoard(int[] bombCoordinates){
        field = new Tile[height][width];
        for(int row = 0; row < height; row ++){
            for(int column = 0; column < width; column ++){
                if(hasValue(bombCoordinates,(row * width) + column)) {
                    field[row][column] = new Mine();
                }
                else{
                    field[row][column] = new EmptyTile();
                }
            }
        }
    }

    private int[] generateMineCoordinates(int numberOfMines){
        int[] bombCoords = new int[numberOfMines];
        //this is used to not always return true, as 0 exists in the array.
        fillMinusOne(bombCoords);
        for(int i = 0; i < numberOfMines; i ++){
            int coordinate = (int) (Math.random() * (getSize()));
            if( hasValue(bombCoords,coordinate) == false){
                bombCoords[i] = coordinate;
            }
            else{
                i --;
            }
        }
        return bombCoords;
    }

    private boolean hasValue(int[] coordinates, int value){
        for(int number: coordinates){
            if(number == value){
                return true;
            }
        }
        return false;
    }
    //this is to avoid having problems when the random coodinate is 0.
    private void fillMinusOne(int[] arr){
        for(int i = 0; i < arr.length; i ++){
            arr[i] = -1;
        }
    }

    public static void main(String[] args){
        Minesweeper m = new Minesweeper();
    }
}
