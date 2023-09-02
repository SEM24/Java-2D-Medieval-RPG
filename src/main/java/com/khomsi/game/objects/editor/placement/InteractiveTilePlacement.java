package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.interactive.Bush;

import static com.khomsi.game.objects.editor.PlaceObjects.MAP_MAIN;

public class InteractiveTilePlacement {
    private GameManager gameManager;

    public InteractiveTilePlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void placeInteractiveTilesOnMap0() {
        int index = 0;
        int mapNum = MAP_MAIN;

        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 26, 62);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 27, 62);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 23, 65);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 22, 78);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 23, 78);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 24, 65);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 28, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 28, 85);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 29, 85);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 29, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 30, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 35, 87);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 36, 87);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 18, 84);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 48, 66);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 51, 68);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 52, 66);
    }
}