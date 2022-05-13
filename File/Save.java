package Minesweeper.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public abstract class Save {

    public static void saveHighScore(int highScore){
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(new FileOutputStream("src/Minesweeper/File/highscore.txt"));
        }catch(FileNotFoundException e){
            System.out.println("couldn't find highscore.txt");
        }

        outputStream.println(highScore);
        outputStream.close();
    }
}
