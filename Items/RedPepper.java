package Minesweeper.Items;

import Minesweeper.UI.Block;
import Minesweeper.UI.UI;
import Minesweeper.game.Minesweeper;

import java.awt.event.MouseEvent;

public class RedPepper extends Item implements OnMoveItem{

    private UI ongoingGame;
    public RedPepper(UI ongoingGame){
        super(ongoingGame);
        this.ongoingGame = ongoingGame;
    }
    @Override
    public String getDescription() {
        return "Every 6 moves opens up a random tile 2 or above";
    }

    @Override
    public String toString() {
        return "RedPepper";
    }

    @Override
    public void onMove(Minesweeper game) {
        if(game.getMoves() % 6 == 0){
            Block[][] blocks = ongoingGame.getBlocks();
            for(int y = 0; y < blocks.length; y++){
                for(int x = 0; x < blocks.length; x++){
                    Block b = blocks[y][x];
                    if(b.getAdjacentMines() >= 2 && !b.isRevealed() && !b.isMine()){
                        b.openTile();
                        return;
                    }
                }
            }
        }
    }
}
