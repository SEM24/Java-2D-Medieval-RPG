package com.khomsi.game.objects.editor.placement;

import com.khomsi.game.entity.npc.beach.NpcCrabs;
import com.khomsi.game.entity.npc.beach.NpcRock;
import com.khomsi.game.entity.npc.castle.NpcHeavyCastleRock;
import com.khomsi.game.entity.npc.dungeon.NpcTutorialMan;
import com.khomsi.game.main.GameManager;

import static com.khomsi.game.entity.npc.beach.Color.*;
import static com.khomsi.game.objects.editor.PlaceObjects.MAP_DUNGEON;
import static com.khomsi.game.objects.editor.PlaceObjects.MAP_MAIN;

public class NpcPlacement {
    private GameManager gameManager;

    public NpcPlacement(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void placeNpcOnMap0() {
        int index = 0;
        int mapNum = MAP_MAIN;
        npcOnBeachMap1(index, mapNum);
        rocksOnMap1(index, mapNum);
    }

    private void npcOnBeachMap1(int index, int mapNum) {
        gameManager.npcList[mapNum][index] = new NpcCrabs(gameManager, RED);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 93;
        index++;
        gameManager.npcList[mapNum][index] = new NpcCrabs(gameManager, BLUE);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 36;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 93;
        index++;
        gameManager.npcList[mapNum][index] = new NpcCrabs(gameManager, YELLOW);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 90;
        index++;
        gameManager.npcList[mapNum][index] = new NpcCrabs(gameManager, RED);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 22;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 85;
        index++;


        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 85;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 63;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 83;
        index++;
    }

    private void rocksOnMap1(int index, int mapNum) {
        gameManager.npcList[mapNum][index] = new NpcHeavyCastleRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 78;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 56;
        index++;
    }

    public void placeNpcOnDungeonMap1() {
        int mapNum = MAP_DUNGEON;
        int index = 3;
        gameManager.npcList[mapNum][index] = new NpcTutorialMan(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 75;
    }
}
