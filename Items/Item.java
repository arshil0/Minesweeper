package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Item extends JLabel implements MouseListener {

    private JDialog description = new JDialog();
    private JLabel text = new JLabel();

    private static ArrayList<Item> allItems;
    private UI ongoingGame;

    public Item(UI game){
        this.ongoingGame = game;
        description.add(text);
        description.setLocationRelativeTo(this);
        setDescription("<html>test<br/>tests<br/>aa<br/>bb<br/>cc</html>");
        setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/UI/Sprites/placeholder.png").getImage().getScaledInstance(UI.FILESIZE/ Minesweeper.getHeight()/2,UI.FILESIZE/Minesweeper.getWidth()/2, Image.SCALE_DEFAULT)));
        addMouseListener(this);
    }

    private void description(){
        if(!Minesweeper.isLost()){
            description.setVisible(true);
        }
    }

    public void setDescription(String text){
        int lines = text.split("<br/>").length;
        System.out.println(lines);
        this.text.setForeground(Color.BLUE);
        description.setSize(400,(lines * 20) + 50);
        description.revalidate();
        this.text.setText(text);
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
        description.setVisible(false);
    }
}
