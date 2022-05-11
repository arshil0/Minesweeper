package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import javax.swing.*;
import java.awt.*;

public class test extends Item{

    public test(UI game){
        super(game);
        setIcon(new ImageIcon(new ImageIcon("test.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
    }
}
