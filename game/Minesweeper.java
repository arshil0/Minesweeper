package Minesweeper.game;

import Minesweeper.Items.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Minesweeper {

    public static final int STARTWIDTH = 4;
    public static final int STARTHEIGHT = 4;
    private static boolean lost = false;
    private boolean firstMove = true;
    private static int health = 1;
    private static int shield = 0;
    private int moves = 0;
    private int openedTiles = 0;
    private static int width;
    private static int height;
    //the number of bombs = size * size / BOMBSCALE
    private static final int BOMBSCALE = 5;
    private static int scale = 1;
    private static ArrayList<Item> items = new ArrayList<Item>();
    private Tile[][] field;


    public Minesweeper(){
        this(4,4);
    }

    public Minesweeper(int w, int h){
        if(w <= STARTWIDTH && h <= STARTHEIGHT)
            items.clear();
        health = 1;
        shield = 0;
        moves = 0;
        openedTiles = 0;
        width = w;
        height = h;
        lost = false;
        firstMove = true;
        setField(null);
    }

    public Minesweeper(int w, int h, int[] reservedCoordinates){
        moves = 0;
        width = w;
        height = h;
        lost = false;
        firstMove = true;
        setField(reservedCoordinates);
    }

    public int getMoves(){
        return moves;
    }
    public void moved(){
        moves += 1;
    }

    public int getOpenedTiles(){
        return openedTiles;
    }

    public void openedTile(){
        openedTiles += 1;
    }
    public int getHealth(){
        return health;
    }

    public void takeDamage(){
        if(shield > 0)
            shield-= 1;
        else{
            health -=1;
        }
    }

    public int getShield(){
        return shield;
    }
    public int getNumberOfTotalMines(){
        return (int) ((width * height / BOMBSCALE) * scale);
    }
    public void setFirstMove(boolean f){
        firstMove = f;
        moved();
    }
    public boolean isFirstMove(){
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

    public static void addItem(Item item){
        items.add(item);
        System.out.println(items.size());
        System.out.println(getItemList().length);
    }

    public static Item[] getItemList(){
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
