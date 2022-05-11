package Minesweeper.game;

import Minesweeper.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class Minesweeper {
    private static boolean firstMove = true;
    private static boolean lost = false;
    private int moves = 0;
    private static int width;
    private static int height;
    //the number of bombs = size * size / BOMBSCALE
    private static final int BOMBSCALE = 5;
    private static int scale = 1;
    private ArrayList<Item> items = new ArrayList<Item>();

    private Tile[][] field;


    public Minesweeper(){
        moves = 0;
        width = 7;
        height = 7;
        lost = false;
        firstMove = true;
        setField(null);
    }

    public Minesweeper(int width, int height, int[] reservedCoordinates){
        moves = 0;
        this.width = width;
        this.height = height;
        lost = false;
        firstMove = true;
        setField(reservedCoordinates);
    }

    public int getMoves(){
        return moves;
    }
    public void moved(){
        moves += 1;
        System.out.println(moves);
    }

    public int getNumberOfMines(){
        return (int) ((width * height / BOMBSCALE) * scale);
    }
    public static void setFirstMove(boolean f){
        firstMove = f;
    }
    public static boolean isFirstMove(){
        return firstMove;
    }
    public static boolean isLost(){
        return lost;
    }
    public static void setLost(boolean lose){
        lost = lose;
    }

    public Tile[][] getField(){
        return field;
    }

    public static int getWidth(){
        return width;
    }

    public static int getHeight(){
        return height;
    }
    public static int getSize(){
        return width * height;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item[] getItemList(){
        return items.toArray(new Item[0]);
    }
    public static void multiplyScale(double i){
        if(scale * i < BOMBSCALE - 2)
            scale *= i;
    }

    public void setField(int[] reservedCoordinates){
        int numberOfMines = (int) (width * height / BOMBSCALE * scale);
        int[] bombCoordinates = generateMineCoordinates(numberOfMines, reservedCoordinates);
        Arrays.sort(bombCoordinates);
        setField(bombCoordinates,reservedCoordinates);
    }

    public void setField(int[] bombCoordinates, int[] reservedCoordinates){
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

    private int[] generateMineCoordinates(int numberOfMines, int[] reservedCoordinates){
        int[] reservedCoords = new int[numberOfMines];
        //this is used to not always return true, as 0 exists in the array.
        fillMinusOne(reservedCoords);
        for(int i = 0; i < numberOfMines; i ++){
            int coordinate = (int) (Math.random() * (getSize()));
            if( !hasValue(reservedCoords,coordinate) && !hasValue(reservedCoordinates,coordinate)){
                reservedCoords[i] = coordinate;
            }
            else{
                i --;
            }
        }
        return reservedCoords;
    }

    private boolean hasValue(int[] coordinates, int value){
        if(coordinates != null)
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

}
