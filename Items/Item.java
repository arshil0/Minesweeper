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
    private JDialog description;
    private JLabel benefit = new JLabel();
    private JLabel sabotage = new JLabel();
    private JLabel text = new JLabel();
    private static UI ongoingGame;

    public Item(){

    }
    public Item(UI game){
        this.ongoingGame = game;
        description = new JDialog();
        description.add(benefit, BorderLayout.NORTH);
        description.add(sabotage, BorderLayout.CENTER);
        description.add(text, BorderLayout.SOUTH);
        description.setLocation(ongoingGame.getX() + ongoingGame.FILESIZE/4,ongoingGame.getY() + ongoingGame.FILESIZE/2);
        description.getContentPane().setBackground(Color.GRAY);
        addMouseListener(this);
        putIcon();
        setDescription(getDescription());
    }

    public abstract String getDescription();

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

        description.setSize(800,(lines.length * 20) + 50);
        description.revalidate();
        this.benefit.setText(lines[0]);
        if(lines.length >= 2)
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

    public static void addOnItemList(Item item){
        itemList.add(item);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //IMPORTANT: add any new items here in this method. ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void refreshItemList(UI ongoingGame){
        itemList.clear();
        itemList.add(new SmallHeart(ongoingGame));
        itemList.add(new MineShield(ongoingGame));
        itemList.add(new ScoreMultiplier(ongoingGame));
        itemList.add(new RedDice(ongoingGame));
        itemList.add(new ExtraItem(ongoingGame));
        itemList.add(new RedPepper(ongoingGame));
    }

    public void putIcon(){
        setIcon(new ImageIcon(new ImageIcon("src/Minesweeper/Items/Sprites/" + this + ".png").getImage().getScaledInstance(ICONSIZE,ICONSIZE,Image.SCALE_DEFAULT)));
    }

    public static void setOngoingGame(UI o){
        ongoingGame = o;
    }
    public static void print(){
        for(Item i: itemList){
            System.out.println(i);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(UI.isChoosingItem()){
            Minesweeper.addItem(this);
            if(this instanceof OnItemPickup){
                ((OnItemPickup) this).onItemPickup(ongoingGame);
            }
            this.mouseExited(e);
            removeFromItemList(this);
            ongoingGame.closeItemChooser();
        }
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

    public abstract String toString();
}
