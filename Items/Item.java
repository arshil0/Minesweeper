package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public abstract class Item extends JLabel implements MouseListener {

    public static final int ICONSIZE = 75;
    private static ArrayList<Item> itemList = new ArrayList<>();
    private JDialog description = new JDialog();
    private JLabel benefit = new JLabel();
    private JLabel sabotage = new JLabel();
    private JLabel text = new JLabel();

    private static ArrayList<Item> allItems;
    private UI ongoingGame;

    public Item(){

    }
    public Item(UI game){
        this.ongoingGame = game;
        description.add(benefit, BorderLayout.NORTH);
        description.add(sabotage, BorderLayout.CENTER);
        description.add(text, BorderLayout.SOUTH);
        description.setLocationRelativeTo(this);
        description.getContentPane().setBackground(Color.GRAY);
        addMouseListener(this);
    }

    private void description(){
        if(!Minesweeper.isLost()){
            description.setVisible(true);
        }
    }

    public void setDescription(String text){
        String[] lines = text.split("<br/>");
        this.benefit.setForeground(Color.GREEN);
        this.sabotage.setForeground(Color.RED);
        this.text.setForeground(Color.DARK_GRAY);
        description.setSize(400,(lines.length * 20) + 50);
        description.revalidate();
        this.benefit.setText(lines[0]);
        this.sabotage.setText(lines[2]);
        if(lines.length >= 4)
            this.text.setText(lines[4]);
    }

    public static Item[] getItemList(){
        if(!itemList.isEmpty())
            return itemList.toArray(new Item[0]);
        return null;
    }

    public static void removeFromItemList(Item item){
        for(int i = 0; i < itemList.size(); i ++){
            if (itemList.get(i).getClass() == item.getClass()){
                itemList.remove(i);
            }
        }
    }

    public static void refreshItemList(UI ongoingGame){
        System.out.println("refreshed");
        itemList.clear();
        itemList.add(new test(ongoingGame));
    }

    public static void print(){
        for(Item i: itemList){
            System.out.println(i);
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
        description.setVisible(false);
    }
}
