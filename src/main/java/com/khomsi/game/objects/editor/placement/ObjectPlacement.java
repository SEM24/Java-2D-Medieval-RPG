package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.equipment.MetalSwordObject;
import com.khomsi.game.objects.inside.*;
import com.khomsi.game.objects.interact.ChestObject;
import com.khomsi.game.objects.outside.DoorObject;
import com.khomsi.game.objects.outside.RoundTreeObject;
import com.khomsi.game.objects.spells.PotionObject;

import static com.khomsi.game.objects.editor.PlaceObjects.*;

public class ObjectPlacement {
    private GameManager gameManager;

    public ObjectPlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void placeObjectsOnMap0() {
        int index = 0;
        int mapNum = MAP_MAIN;

        gameManager.object[mapNum][index] = new DoorObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;

        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new PotionObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 83;
        index++;

        placeTreeObjectOnMap1(index, mapNum);
    }

    public void placeObjectsOnMapHouseBeach1() {
        int index = 0;
        int mapNum = MAP_HOUSE_BEACH;

        gameManager.object[mapNum][index] = new WindowObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 49;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
        index++;

        gameManager.object[mapNum][index] = new WindowObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 45;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
        index++;

        gameManager.object[mapNum][index] = new PictureObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 50;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 66;
        index++;

        gameManager.object[mapNum][index] = new MapObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 45;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 66;
        index++;

        gameManager.object[mapNum][index] = new StairsDownObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 57;
        index++;

        gameManager.object[mapNum][index] = new BedObject(gameManager);
        gameManager.object[mapNum][index].worldX = (int) (GameManager.TILE_SIZE * 60.5);
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 59;
    }

    private void placeTreeObjectOnMap1(int index, int mapNum) {
        gameManager.object[mapNum][index] = new RoundTreeObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 36;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 76;
        index++;
    }

    public void objectsOnDungeonMap1() {
        int index = 0;
        int mapNum = MAP_DUNGEON;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new PotionObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 61;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;
        gameManager.object[mapNum][index] = new MetalSwordObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 74;
        index++;
        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 58;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 74;
    }
}
