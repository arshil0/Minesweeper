package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

public class ScoreMultiplier extends Item implements OnStartItem, OnItemPickup{

    public ScoreMultiplier(UI ongoingGame){
        super(ongoingGame);
    }
    @Override
    public String getDescription() {
        return "When having this item you gain 2x the score from any source<br/><br/>" +
                "By the time of picking up this item, your score gets set to 0";
    }

    @Override
    public String toString() {
        return "ScoreMultiplier";
    }

    @Override
    public void onItemPickup(UI game) {
        Minesweeper.resetScore();
    }

    @Override
    public void onStart(UI game) {
        Minesweeper.multiplyScoreMultiplier(2);
    }
}
