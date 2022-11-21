package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.NPC_Woman3;
import main.java.com.khomsi.game.main.GamePanel;

public class PlaceObjects {
    GamePanel gamePanel;

    public PlaceObjects(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {

    }

    public void setNpc() {
        gamePanel.npc[0] = new NPC_Woman3(gamePanel);
        gamePanel.npc[0].worldX = GamePanel.TILE_SIZE * 21;
        gamePanel.npc[0].worldY = GamePanel.TILE_SIZE * 21;

    }
}
