package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import javax.swing.*;
import java.awt.*;

public class test extends Item implements OnItemPickup{

    public test(){

    }

    public test(UI game){
        super(game);
        setIcon(new ImageIcon(new ImageIcon("test.png").getImage().getScaledInstance(ICONSIZE,ICONSIZE,Image.SCALE_DEFAULT)));
        setDescription(getDescription());
    }

    public String getDescription(){
        return "this item is just a test<br/><br/>and this is a break<br/><br/>and this is a text";
    }

    @Override
    public void onItemPickup(UI game) {

    }

    public String toString(){
        return "test";
    }
}
