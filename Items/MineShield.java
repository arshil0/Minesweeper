package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

public class MineShield extends Item implements OnStartItem{

    public MineShield(UI ongoingGame){
        super(ongoingGame);
    }
    @Override
    public String getDescription() {
        return "Every new level you enter, this item gives you a shield (if not already broken). <br/><br/>" +
                "This item will cause the field to have more mines, scaled by 1.5 to be exact! <br/><br/>" +
                "Shield: when touching a mine, the shield is removed first (if you have one) and the shield refreshes every level!";
    }

    @Override
    public String toString() {
        return "MineShield";
    }

    @Override
    public void onStart(UI game) {
        Minesweeper.setShield(1);
        Minesweeper.multiplyScale(1.5);
        game.addShield();
    }
}
