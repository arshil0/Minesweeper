package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.Items.*;
import Minesweeper.game.Minesweeper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItemChooser extends JDialog {

    private static int itemsToChooseFrom = 3;
    public ItemChooser(UI ongoingGame){
        super();
        setLayout(new BorderLayout());
        setModal(true);
        setResizable(false);
        add(new JLabel("Choose your item!"), BorderLayout.NORTH);
        setSize(UI.FILESIZE, 200);
        setLocation(ongoingGame.getX() + 200,ongoingGame.getY() + 100);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel itemPanel = new JPanel(new FlowLayout());
        itemPanel.setBackground(new Color(34, 34, 35));
        add(itemPanel,BorderLayout.CENTER);
        Item[] itemList = Item.getItemList();

        if(itemList == null){
            //this is to not get errors while trying to make a new jDialog with no items left.
            //it didn't work with && in the upcoming if
        }

        else if(itemList.length <= itemsToChooseFrom){
            for(Item item: itemList){
                itemPanel.add(item);
            }
        }
        else{
            int[] reservedItemCoordinates = new int[itemsToChooseFrom];
            //this is to avoid matching the first indexed number, which is 0;
            fillArrayWithMinusOne(reservedItemCoordinates);
            for(int i = 0; i < itemsToChooseFrom ; i ++){
                int randomItemNumber = (int) (Math.random() * itemList.length);
                boolean success = true;
                for(int r: reservedItemCoordinates){
                    if(r == randomItemNumber){
                        i --;
                        success = false;
                        break;
                    }
                }
                if(success){
                    reservedItemCoordinates[i] = randomItemNumber;
                    itemPanel.add(itemList[randomItemNumber]);
                }
            }
        }
    }

    public static void addExtraItemToChoose(){
        itemsToChooseFrom += 1;
    }

    public static void resetItemsToChooseFrom(){
        itemsToChooseFrom = 3;
    }

    private void fillArrayWithMinusOne(int[] arr){
        for(int i = 0; i < arr.length; i ++){
            arr[i] = -1;
        }
    }


}
