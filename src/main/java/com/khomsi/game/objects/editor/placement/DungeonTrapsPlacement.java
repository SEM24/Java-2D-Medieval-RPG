package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.entity.npc.dungeon.NpcRockMovable;
import com.khomsi.game.main.GameManager;

import static com.khomsi.game.objects.editor.PlaceObjects.MAP_DUNGEON;

public class DungeonTrapsPlacement {
    private GameManager gameManager;

    public DungeonTrapsPlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void trapsOnDungeonMap1() {
        int mapNum = MAP_DUNGEON;
        int index = 0;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 61;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 79;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 60;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 78;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 79;
    }
}
