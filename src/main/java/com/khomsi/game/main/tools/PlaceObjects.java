package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.mobs.MOB_Slime;
import main.java.com.khomsi.game.entity.npc.NPC_Woman3;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.*;
import main.java.com.khomsi.game.objects.equipment.AxeObject;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.spells.PotionObject;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
        int index = 0;
        gameManager.object[index] = new KeyObject(gameManager);
        gameManager.object[index].worldX = GameManager.TILE_SIZE * 25;
        gameManager.object[index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[index] = new KeyObject(gameManager);
        gameManager.object[index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.object[index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[index] = new AxeObject(gameManager);
        gameManager.object[index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.object[index].worldY = GameManager.TILE_SIZE * 19;
        index++;

        gameManager.object[index] = new GoldShieldObject(gameManager);
        gameManager.object[index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.object[index].worldY = GameManager.TILE_SIZE * 20;
        index++;


        gameManager.object[index] = new PotionObject(gameManager);
        gameManager.object[index].worldX = GameManager.TILE_SIZE * 34;
        gameManager.object[index].worldY = GameManager.TILE_SIZE * 29;
    }

    public void setNpc() {
        int index = 0;
        gameManager.npcList[index] = new NPC_Woman3(gameManager);
        gameManager.npcList[index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.npcList[index].worldY = GameManager.TILE_SIZE * 21;
    }

    public void setMobs() {
        int index = 0;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 31;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 31;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 33;
        index++;
        gameManager.mobs[index] = new MOB_Slime(gameManager);
        gameManager.mobs[index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.mobs[index].worldY = GameManager.TILE_SIZE * 33;
    }
}
