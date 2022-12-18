package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.mobs.MOB_Slime;
import main.java.com.khomsi.game.entity.npc.NPC_Woman3;
import main.java.com.khomsi.game.main.GameManager;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
    }

    public void setNpc() {
        int index = 0;
        gameManager.npcList[index] = new NPC_Woman3(gameManager);
        gameManager.npcList[index].worldX = GameManager.TILE_SIZE * 21;
        gameManager.npcList[index].worldY = GameManager.TILE_SIZE * 21;
    }

    public void setMobs() {
        int index = 0;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 21;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 21;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 23;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 22;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 20;
    }
}
