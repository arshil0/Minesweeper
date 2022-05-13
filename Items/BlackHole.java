package Minesweeper.Items;

import Minesweeper.UI.UI;
import Minesweeper.game.Mine;
import Minesweeper.game.Minesweeper;
import Minesweeper.game.Tile;

public class BlackHole extends Item implements OnStartItem{
    //WIP

    public BlackHole(UI ongoingGame){
        super(ongoingGame);
    }
    @Override
    public String getDescription() {
        return "Reveals some mines at the start of each round<br/><br/>" +
                "Some tiles become corrupted, leaving a question mark instead of a number";
    }

    @Override
    public String toString() {
        return "BlackHole";
    }

    @Override
    public void onStart(UI game) {
        int numberOfMinesLeftToScan = game.getGame().getNumberOfTotalMines();
        int numberOfMinesToOpen = (int) ((Math.random() * 3) + 4);
        int numberOfEmptyTilesLeftToScan = (Minesweeper.getWidth() * Minesweeper.getHeight()) - numberOfMinesLeftToScan;
        int numberOfEmptyTilesToCurse = numberOfMinesToOpen;
        Tile[][] field = game.getGame().getField();
        for(int y = 0; y < Minesweeper.getHeight(); y ++){
            for(int x = 0; x < Minesweeper.getWidth(); x ++){
                if(field[y][x] instanceof Mine){
                    if(numberOfMinesLeftToScan > numberOfMinesToOpen){
                        int random = (int) (Math.random() * 2);
                        if(random == 0){
                            game.getBlocks()[y][x].updateIcon(0,"M");
                            numberOfMinesLeftToScan -= 1;
                            numberOfMinesToOpen -= 1;
                        }
                        else{
                            numberOfMinesLeftToScan -= 1;
                        }
                    }
                    else{
                        game.getBlocks()[y][x].updateIcon(0,"M");
                        numberOfMinesLeftToScan -= 1;
                        numberOfMinesToOpen -= 1;
                    }
                }
            }

        }
    }
}
