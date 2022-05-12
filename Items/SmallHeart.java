package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

public class SmallHeart extends Item implements OnItemPickup{

    public SmallHeart(){

    }
    public SmallHeart(UI ongoingGame){
        super(ongoingGame);
    }
    @Override
    public String getDescription() {
        return "Gives you an extra heart to survive a mine<br/><br/>The field gets expanded by 2 instead of 1 by the time of picking up this item" +
                "<br/><br/>The heart is so small that it breaks, this item is destroyed upon taking damage!";
    }

    @Override
    public void onItemPickup(UI game) {
        Minesweeper.increaseHealth();
        Minesweeper.increaseHeight(1);
        Minesweeper.increaseWidth(1);
    }

    public String toString(){
        return "SmallHeart";
    }

}
