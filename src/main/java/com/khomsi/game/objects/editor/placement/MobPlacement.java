package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.entity.mobs.MobChestMimic;
import com.khomsi.game.main.GameManager;

import static com.khomsi.game.objects.editor.PlaceObjects.MAP_MAIN;

public class MobPlacement {
    private GameManager gameManager;

    public MobPlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void setMobsOnMap0() {
        int index = 0;
        int mapNum = MAP_MAIN;
        gameManager.mobs[mapNum][index] = new MobChestMimic(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 15;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;
    }
    public void setMobsOnDungeonMap1() {
        int index = 0;
        int mapNum = 3;
        gameManager.mobs[mapNum][index] = new MobChestMimic(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 54;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
    }
}
