package Minesweeper.Items;

import Minesweeper.UI.ItemChooser;
import Minesweeper.UI.UI;

public class ExtraItem extends Item implements OnItemPickup{

    public ExtraItem(UI game){
        super(game);
    }
    @Override
    public String getDescription() {
        return "Gives you an extra option while picking an item!";
    }

    @Override
    public String toString() {
        return "ExtraItem";
    }

    @Override
    public void onItemPickup(UI game) {
        ItemChooser.addExtraItemToChoose();
    }
}
