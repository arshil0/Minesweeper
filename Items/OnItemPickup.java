package Minesweeper.Items;

import Minesweeper.UI.UI;

//items that implement this interface have a method that gets called only once, when picking up the item.
public interface OnItemPickup {
    public void onItemPickup(UI game);
}
