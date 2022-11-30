package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.NPC_Woman3;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.DoorObject;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
        gameManager.object[0] = new DoorObject(gameManager);
        gameManager.object[0].worldX = GameManager.TILE_SIZE * 21;
        gameManager.object[0].worldY = GameManager.TILE_SIZE * 24;

        gameManager.object[1] = new DoorObject(gameManager);
        gameManager.object[1].worldX = GameManager.TILE_SIZE * 23;
        gameManager.object[1].worldY = GameManager.TILE_SIZE * 24;
    }

    public void setNpc() {
        gameManager.npc[0] = new NPC_Woman3(gameManager);
        gameManager.npc[0].worldX = GameManager.TILE_SIZE * 21;
        gameManager.npc[0].worldY = GameManager.TILE_SIZE * 21;

        gameManager.npc[1] = new NPC_Woman3(gameManager);
        gameManager.npc[1].worldX = GameManager.TILE_SIZE * 23;
        gameManager.npc[1].worldY = GameManager.TILE_SIZE * 23;

    }
}
