package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.mobs.MobSlime;
import main.java.com.khomsi.game.entity.npc.NpcWoman3;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.AxeObject;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.gui.HeartObject;
import main.java.com.khomsi.game.objects.gui.ManaObject;
import main.java.com.khomsi.game.objects.interact.CoinBObject;
import main.java.com.khomsi.game.objects.spells.PotionObject;
import main.java.com.khomsi.game.tilesinteractive.Bush;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
        int index = 0;
        int mapNum = 0;
        gameManager.object[mapNum][index] = new CoinBObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 25;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[mapNum][index] = new CoinBObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[mapNum][index] = new AxeObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[mapNum][index] = new GoldShieldObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 20;
        index++;


        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 34;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 29;
        index++;
        gameManager.object[mapNum][index] = new HeartObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 32;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 19;
        index++;
        gameManager.object[mapNum][index] = new HeartObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 32;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 14;
        index++;
        gameManager.object[mapNum][index] = new ManaObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 34;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 14;
    }

    public void setInteractiveTiles() {
        int index = 0;
        int mapNum = 0;
        gameManager.interactiveTile[mapNum][index] = new Bush(gameManager, 21, 16);
        index++;
        gameManager.interactiveTile[mapNum][index] = new Bush(gameManager, 25, 16);
        index++;
        gameManager.interactiveTile[mapNum][index] = new Bush(gameManager, 12, 18);
        index++;
        gameManager.interactiveTile[mapNum][index] = new Bush(gameManager, 13, 18);
        index++;
        gameManager.interactiveTile[mapNum][index] = new Bush(gameManager, 11, 18);
    }

    public void setNpc() {
        int index = 0;
        int mapNum = 0;
        gameManager.npcList[mapNum][index] = new NpcWoman3(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 21;
    }

    public void setMobs() {
        int index = 0;
        int mapNum = 0;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 31;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 31;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 33;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 33;
        index++;
        //Set mobs on different map
        mapNum = 1;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 14;
    }
}
