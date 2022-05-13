package Minesweeper.File;

import java.io.*;
import java.util.Scanner;
import Minesweeper.game.*;

import javax.security.auth.login.AccountLockedException;

public abstract class Open {

    public static int openHighScore(){
        Scanner inputStream = null;
        try{
            inputStream = new Scanner(new FileInputStream("src/Minesweeper/File/highscore.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("couldn't open highscore.txt");
            System.exit(0);
        }

        int highScore = inputStream.nextInt();
        inputStream.close();
        return highScore;
    }
}
