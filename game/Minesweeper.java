package Minesweeper.game;

import java.util.ArrayList;
import java.util.Arrays;

public class Minesweeper {

    private static int size;
    //the number of bombs = size * size / BOMBSCALE
    private static final int BOMBSCALE = 3;

    private Mine[][] board;


    public Minesweeper(){
        size = 5;
        setBoard();
    }

    public static int getSize(){
        return size;
    }

    private void setBoard(){
        int numberOfMines = size * size / BOMBSCALE;
        int[] bombCoordinates = generateMineCoordinates(numberOfMines);
        Arrays.sort(bombCoordinates);
        setBoard(bombCoordinates);
    }

    private void setBoard(int[] bombCoordinates){
        board = new Mine[size][size];
        for(int row = 0; row < size; row ++){
            for(int column = 0; column < size; column ++){
                if(hasValue(bombCoordinates,(row * size) + column)) {
                    board[row][column] = new Mine();
                }
                else{
                    board[row][column] = new EmptyMine();
                }
            }
        }
    }

    private int[] generateMineCoordinates(int numberOfMines){
        int[] bombCoords = new int[numberOfMines];
        //this is used to not always return true, as 0 exists in the array.
        fillMinusOne(bombCoords);
        for(int i = 0; i < numberOfMines; i ++){
            int coordinate = (int) (Math.random() * (size * size));
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
