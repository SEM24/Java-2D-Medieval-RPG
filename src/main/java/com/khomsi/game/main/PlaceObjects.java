package main.java.com.khomsi.game.main;

import main.java.com.khomsi.game.objects.*;

public class PlaceObjects {
    GamePanel gamePanel;

    public PlaceObjects(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.object[0] = new KnifeObject();
        gamePanel.object[0].worldX = GamePanel.TILE_SIZE * 23;
        gamePanel.object[0].worldY = GamePanel.TILE_SIZE * 7;

        gamePanel.object[1] = new KeyObject();
        gamePanel.object[1].worldX = GamePanel.TILE_SIZE * 23;
        gamePanel.object[1].worldY = GamePanel.TILE_SIZE * 40;

        gamePanel.object[2] = new DogObject();
        gamePanel.object[2].worldX = GamePanel.TILE_SIZE * 38;
        gamePanel.object[2].worldY = GamePanel.TILE_SIZE * 8;

        gamePanel.object[3] = new DoorObject();
        gamePanel.object[3].worldX = GamePanel.TILE_SIZE * 10;
        gamePanel.object[3].worldY = GamePanel.TILE_SIZE * 9;

        gamePanel.object[4] = new DoorObject();
        gamePanel.object[4].worldX = GamePanel.TILE_SIZE * 8;
        gamePanel.object[4].worldY = GamePanel.TILE_SIZE * 28;

        gamePanel.object[5] = new DoorObject();
        gamePanel.object[5].worldX = GamePanel.TILE_SIZE * 12;
        gamePanel.object[5].worldY = GamePanel.TILE_SIZE * 2;

        gamePanel.object[6] = new ChestObject();
        gamePanel.object[6].worldX = GamePanel.TILE_SIZE * 10;
        gamePanel.object[6].worldY = GamePanel.TILE_SIZE * 7;

        gamePanel.object[7] = new KeyObject();
        gamePanel.object[7].worldX = GamePanel.TILE_SIZE * 40;
        gamePanel.object[7].worldY = GamePanel.TILE_SIZE * 8;
    }
}
