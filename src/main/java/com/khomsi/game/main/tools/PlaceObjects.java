package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.mobs.MobChestMimic;
import main.java.com.khomsi.game.entity.mobs.MobOrc;
import main.java.com.khomsi.game.entity.mobs.MobSlime;
import main.java.com.khomsi.game.entity.mobs.MobSlimeBlue;
import main.java.com.khomsi.game.entity.npc.regular.NpcWomanW;
import main.java.com.khomsi.game.entity.npc.sellers.NpcSeller1;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.AxeObject;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.interact.ChestObject;
import main.java.com.khomsi.game.objects.interact.CoinBObject;
import main.java.com.khomsi.game.objects.interact.KeyObject;
import main.java.com.khomsi.game.objects.light.LanternObject;
import main.java.com.khomsi.game.objects.outside.BedObject;
import main.java.com.khomsi.game.objects.outside.DoorObject;
import main.java.com.khomsi.game.objects.outside.StairsDownObject;
import main.java.com.khomsi.game.objects.outside.TentObject;
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

        gameManager.object[mapNum][index] = new LanternObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 22;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;
        gameManager.object[mapNum][index] = new TentObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 21;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;

        gameManager.object[mapNum][index] = new AxeObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 20;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;

        gameManager.object[mapNum][index] = new GoldShieldObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 19;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;


        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 18;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;
        gameManager.object[mapNum][index] = new DoorObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;
        gameManager.object[mapNum][index] = new DoorObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 18;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 25;
        index++;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new KeyObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 17;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;
        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 15;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;
        index++;
        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 16;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 11;

        //Map 1
        index = 0;
        mapNum = 1;
        gameManager.object[mapNum][index] = new BedObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 29;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;

        gameManager.object[mapNum][index] = new StairsDownObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
    }

    public void setInteractiveTiles() {
        int index = 0;
        int mapNum = 0;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 17, 26);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 18, 26);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 19, 26);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 21, 28);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 28, 28);
    }

    public void setNpc() {
        int index = 0;
        int mapNum = 0;
        //Map 0
        gameManager.npcList[mapNum][index] = new NpcWomanW(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 21;
        index++;

        //Map 1
        index = 0;
        mapNum = 1;
        gameManager.npcList[mapNum][index] = new NpcSeller1(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 20;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 16;
        index++;
    }

    public void setMobs() {
        int index = 0;
        int mapNum = 0;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 36;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 30;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 30;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 38;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 30;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 39;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 30;
        index++;
        gameManager.mobs[mapNum][index] = new MobSlimeBlue(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 40;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 30;
        index++;
        gameManager.mobs[mapNum][index] = new MobOrc(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 37;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 31;
        index++;

        gameManager.mobs[mapNum][index] = new MobChestMimic(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;
        //Set mobs on different map
        mapNum = 1;
//        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
//        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
//        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 14;
    }
}
