package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.water.WaterBubbles;
import com.khomsi.game.tiles.animated.water.WaterHurricane;
import com.khomsi.game.tiles.animated.water.wave.center.WaveCenterDown;

import static com.khomsi.game.objects.editor.PlaceObjects.MAP_MAIN;

public class AnimatedTilePlacement {
    private GameManager gameManager;

    public AnimatedTilePlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void placeWaterAnimationOnMap0() {
        int index = 0;
        int mapNum = MAP_MAIN;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 38;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 23;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 98;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterHurricane(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 18;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 59;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 96;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 64;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        drawWaterWavesAnimationOnMap0(index);
    }

    private void drawWaterWavesAnimationOnMap0(int index) {
        int mapNum = MAP_MAIN;
        int[][] ranges = {{16, 50}, {55, 64}};  // Define the ranges

        for (int[] range : ranges) {
            int startingWorldX = range[0];
            int endingWorldX = range[1];

            for (int worldX = startingWorldX; worldX <= endingWorldX; worldX++) {
                gameManager.animatedTiles[mapNum][index] = new WaveCenterDown(gameManager);
                gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * worldX;
                gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 94;
                index++;
            }
        }
    }
}
