package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

public class RedDice extends Item implements OnItemPickup{

    public RedDice(UI ongoingGame){
        super(ongoingGame);
    }
    @Override
    public String getDescription() {
        return "Gives you a random Item!";
    }

    @Override
    public String toString() {
        return "RedDice";
    }

    @Override
    public void onItemPickup(UI game) {
        removeFromItemList(this);
        Item randomItem = getItemList()[(int) (Math.random() * getItemList().length)];
        Minesweeper.addItem(randomItem);
        if(randomItem instanceof OnItemPickup){
            ((OnItemPickup) randomItem).onItemPickup(game);
        }
        Minesweeper.removeItem(this);
        Item.removeFromItemList(randomItem);
        if(getItemList().length > 2)
            Item.addOnItemList(this);
    }
}
