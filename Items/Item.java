package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Item extends JLabel implements MouseListener {

    private static ArrayList<Item> allItems;
    private UI ongoingGame;

    public Item(UI game){
        Class derivedClass = this.getClass();
        if(!allItems.contains(derivedClass))
        this.ongoingGame = game;
        setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/placeholder.png").getImage().getScaledInstance(UI.FILESIZE/ Minesweeper.getHeight()/2,UI.FILESIZE/Minesweeper.getWidth()/2, Image.SCALE_DEFAULT)));
        addMouseListener(this);
    }

    private void description(){
        if(!Minesweeper.isLost()){
            JDialog a = new JDialog();
            a.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        description();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
