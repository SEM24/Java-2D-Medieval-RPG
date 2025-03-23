package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.entity.mobs.MobChestMimic;
import com.khomsi.game.entity.mobs.MobMushroom;
import com.khomsi.game.main.GameManager;

import static com.khomsi.game.entity.mobs.Color.BROWN;
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
        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 32;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 87;
        index++;
        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 16;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 90;
        index++;
        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 23;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 83;
        index++;

        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 46;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 89;
        index++;

        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 40;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;

        gameManager.mobs[mapNum][index] = new MobMushroom(gameManager, BROWN);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 12;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 86;
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
