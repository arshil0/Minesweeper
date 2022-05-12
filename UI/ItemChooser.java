package Minesweeper.UI;

import javax.swing.*;
import Minesweeper.Items.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ItemChooser extends JDialog {

    private int itemsToChooseFrom = 3;
    public ItemChooser(UI ongoingGame){
        super();
        setLayout(new BorderLayout());
        add(new JLabel("Choose your item!"), BorderLayout.NORTH);
        setSize(UI.FILESIZE, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JPanel itemPanel = new JPanel(new FlowLayout());
        itemPanel.setBackground(new Color(34, 34, 35));
        add(itemPanel,BorderLayout.CENTER);
        Item[] itemList = Item.getItemList();

        if(itemList.length < itemsToChooseFrom){
            for(Item item: itemList){
                itemPanel.add(item);
            }
        }
    }


}
