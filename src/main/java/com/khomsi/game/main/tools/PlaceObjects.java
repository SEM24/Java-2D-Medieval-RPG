package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.npc.NPC_Woman3;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.entity.mobs.MOB_Slime;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
    }

    public void setNpc() {
        gameManager.npcList[0] = new NPC_Woman3(gameManager);
        gameManager.npcList[0].worldX = GameManager.TILE_SIZE * 21;
        gameManager.npcList[0].worldY = GameManager.TILE_SIZE * 21;
    }

    public void setMobs() {
        gameManager.mobs[0] = new MOB_Slime(gameManager);
        gameManager.mobs[0].worldX = GameManager.TILE_SIZE * 24;
        gameManager.mobs[0].worldY = GameManager.TILE_SIZE * 21;
    }
}
