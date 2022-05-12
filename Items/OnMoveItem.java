package Minesweeper.Items;

//items that implement this interface have a method, which gets called every move.

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

public interface OnMoveItem {
    public void onMove(Minesweeper game);
}
