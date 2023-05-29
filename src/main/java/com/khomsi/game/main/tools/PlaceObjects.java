package com.khomsi.game.main.tools;

import com.khomsi.game.entity.mobs.*;
import com.khomsi.game.entity.npc.object.NpcRock;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.interact.ChestObject;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.objects.outside.*;
import com.khomsi.game.entity.npc.regular.NpcWomanW;
import com.khomsi.game.entity.npc.sellers.NpcSeller1;
import com.khomsi.game.objects.equipment.AxeObject;
import com.khomsi.game.objects.equipment.GoldShieldObject;
import com.khomsi.game.objects.equipment.HookObject;
import com.khomsi.game.objects.interact.KeyObject;
import com.khomsi.game.objects.light.LanternObject;
import com.khomsi.game.objects.spells.PotionObject;
import com.khomsi.game.tilesinteractive.Bush;
import com.khomsi.game.tilesinteractive.DestructibleWall;
import com.khomsi.game.tilesinteractive.SwitchPress;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObject() {
        objectsOnMap0();
        objectsOnMap1();
        objectsOnMap2();
    }

    private void objectsOnMap0() {
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
    }

    private void objectsOnMap1() {
        //Map 1
        int index = 0;
        int mapNum = 1;
        gameManager.object[mapNum][index] = new BedObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 29;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;

        gameManager.object[mapNum][index] = new StairsDownObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
    }

    private void objectsOnMap2() {
        int index = 0;
        int mapNum = 2;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new HookObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 42;
        index++;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new GoldShieldObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 40;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new PotionObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 41;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 29;
        index++;

        gameManager.object[mapNum][index] = new DungeonDoorClosedObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 29;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 19;

    }

    public void setInteractiveTiles() {
        interactiveTilesOnMap0();
        interactiveTilesOnMap2();
    }

    private void interactiveTilesOnMap0() {
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

    private void interactiveTilesOnMap2() {
        int index = 0;
        int mapNum = 2;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 29, 37);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 30, 37);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 37, 35);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 35, 25);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 24, 26);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 35, 17);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 14, 19);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 13, 19);
        index++;
        gameManager.interactTile[mapNum][index] = new DestructibleWall(gameManager, 12, 19);
        index++;

        gameManager.interactTile[mapNum][index] = new SwitchPress(gameManager, 39, 39);
        index++;
        gameManager.interactTile[mapNum][index] = new SwitchPress(gameManager, 40, 25);
        index++;
        gameManager.interactTile[mapNum][index] = new SwitchPress(gameManager, 23, 26);
        index++;
    }

    public void setNpc() {
        npcOnMap0();
        npcOnMap1();
        npcOnMap2();
    }

    private void npcOnMap0() {
        int index = 0;
        int mapNum = 0;
        //Map 0
        gameManager.npcList[mapNum][index] = new NpcWomanW(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 21;
        index++;
    }

    private void npcOnMap1() {
        int mapNum;
        int index;
        //Map 1
        index = 0;
        mapNum = 1;
        gameManager.npcList[mapNum][index] = new NpcSeller1(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 20;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 16;
        index++;
    }

    public void npcOnMap2() {
        int index = 0;
        int mapNum = 2;
        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 38;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 33;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 26;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRock(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 23;
        index++;
    }

    public void setMobs() {
        mobsOnMap0();
        mobsOnMap1();
        mobsOnMap2();
        mobsOnMap3();
    }

    private void mobsOnMap0() {
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
    }

    private void mobsOnMap1() {
        int index = 0;
        int mapNum = 1;
//        gameManager.mobs[mapNum][index] = new MobSlime(gameManager);
//        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 28;
//        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 14;
    }
    private void mobsOnMap2() {
        int index = 0;
        int mapNum = 2;
        //Set mobs on different map
        gameManager.mobs[mapNum][index] = new MobBat(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 36;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 39;
        index++;
        gameManager.mobs[mapNum][index] = new MobBat(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 30;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 25;
        index++;
        gameManager.mobs[mapNum][index] = new MobBat(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 9;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 26;
    }
    private void mobsOnMap3() {
        int index = 0;
        int mapNum = 3;
        //Set mobs on different map
        gameManager.mobs[mapNum][index] = new MobDungeonBoss(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 23;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 24;
        index++;
    }
}
