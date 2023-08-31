package com.khomsi.game.objects;

import com.khomsi.game.data.GameProgress;
import com.khomsi.game.entity.mobs.MobBat;
import com.khomsi.game.entity.mobs.MobChestMimic;
import com.khomsi.game.entity.mobs.MobDungeonBoss;
import com.khomsi.game.entity.npc.beach.NpcCrabs;
import com.khomsi.game.entity.npc.beach.NpcRock;
import com.khomsi.game.entity.npc.dungeon.NpcRockMovable;
import com.khomsi.game.entity.npc.dungeon.NpcTutorialMan;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.dungeon.DungeonDoorClosedObject;
import com.khomsi.game.objects.equipment.GoldShieldObject;
import com.khomsi.game.objects.equipment.HookObject;
import com.khomsi.game.objects.equipment.MetalSwordObject;
import com.khomsi.game.objects.inside.*;
import com.khomsi.game.objects.interact.ChestObject;
import com.khomsi.game.objects.outside.DoorObject;
import com.khomsi.game.objects.outside.RoundTreeObject;
import com.khomsi.game.objects.spells.PotionObject;
import com.khomsi.game.tiles.animated.water.WaterBubbles;
import com.khomsi.game.tiles.animated.water.WaterHurricane;
import com.khomsi.game.tiles.animated.water.dungeon.lava.LavaGreenDown;
import com.khomsi.game.tiles.animated.water.dungeon.lava.LavaRedDown;
import com.khomsi.game.tiles.animated.water.dungeon.lava.LavaRedUp;
import com.khomsi.game.tiles.animated.water.wave.center.WaveCenterDown;
import com.khomsi.game.tiles.interactive.Bush;
import com.khomsi.game.tiles.interactive.dungeon.DestructibleWall;
import com.khomsi.game.tiles.interactive.dungeon.SwitchPress;

import static com.khomsi.game.entity.npc.beach.Color.*;

public class PlaceObjects {
    GameManager gameManager;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObjects() {
        objectsOnMap1();
        objectsOnMapHouseBeach1();
//        objectsOnMap1();
//        objectsOnMap2();
        objectsOnDungeonMap1();
    }

    private void objectsOnMap1() {
        int index = 0;
        int mapNum = 0;
        gameManager.object[mapNum][index] = new DoorObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 35;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;

        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new PotionObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 83;
        index++;
        placeTreeObjectOnMap1(index, mapNum);
    }

    private void placeTreeObjectOnMap1(int index, int mapNum) {
        gameManager.object[mapNum][index] = new RoundTreeObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 36;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 76;
        index++;
    }

    private void objectsOnMapHouseBeach1() {
        //Map 1
        int index = 0;
        int mapNum = 2;
        gameManager.object[mapNum][index] = new WindowObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 49;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
        index++;
        gameManager.object[mapNum][index] = new WindowObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 45;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
        index++;

        gameManager.object[mapNum][index] = new PictureObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 50;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 66;
        index++;

        gameManager.object[mapNum][index] = new MapObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 45;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 66;
        index++;
        gameManager.object[mapNum][index] = new StairsDownObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 57;

        index++;
        gameManager.object[mapNum][index] = new BedObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 60;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 59;
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

    private void objectsOnDungeonMap1() {
        int index = 0;
        int mapNum = 3;
        gameManager.object[mapNum][index] = new ChestObject(gameManager);
        gameManager.object[mapNum][index].setLoot(new PotionObject(gameManager));
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 61;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;
        gameManager.object[mapNum][index] = new MetalSwordObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 74;
        index++;
        gameManager.object[mapNum][index] = new PotionObject(gameManager);
        gameManager.object[mapNum][index].worldX = GameManager.TILE_SIZE * 58;
        gameManager.object[mapNum][index].worldY = GameManager.TILE_SIZE * 74;
    }

    public void setInteractiveTiles() {
        //TODO change the tiles
        interactiveTilesOnMap1();
//        interactiveTilesOnMap2();
    }

    private void interactiveTilesOnMap1() {
        int index = 0;
        int mapNum = 0;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 26, 62);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 27, 62);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 23, 65);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 22, 78);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 23, 78);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 24, 65);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 28, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 28, 85);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 29, 85);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 29, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 30, 76);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 35, 87);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 36, 87);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 18, 84);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 48, 66);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 51, 68);
        index++;
        gameManager.interactTile[mapNum][index] = new Bush(gameManager, 52, 66);
        setAnimatedTiles();
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
        npcOnMap1();
        npcOnDungeonMap1();
//        npcOnMap2();
    }

    private void npcOnMap1() {
        int index = 0;
        int mapNum = 0;
        //Map 0

        npcOnBeachMap1(index, mapNum);
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

    public void setAnimatedTiles() {
        drawWaterAnimationOnMap0();
//        drawWaterAnimationOnMap2();
    }

    private void drawWaterAnimationOnMap2() {
        int index = 0;
        int mapNum = 2;
        gameManager.animatedTiles[mapNum][index] = new LavaGreenDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 24;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 34;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedUp(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 37;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 26;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 38;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedUp(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 33;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 37;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 33;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 38;
        index++;

        gameManager.animatedTiles[mapNum][index] = new LavaRedUp(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 39;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 38;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 39;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 39;
        index++;

        gameManager.animatedTiles[mapNum][index] = new LavaRedUp(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 10;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 12;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 10;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;

        gameManager.animatedTiles[mapNum][index] = new LavaRedUp(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 7;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 12;
        index++;
        gameManager.animatedTiles[mapNum][index] = new LavaRedDown(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 7;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 13;
        index++;
    }

    private void drawWaterAnimationOnMap0() {
        int index = 0;
        int mapNum = 0;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 38;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 23;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 98;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterHurricane(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 18;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 59;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 96;
        index++;
        gameManager.animatedTiles[mapNum][index] = new WaterBubbles(gameManager);
        gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * 64;
        gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 97;
        index++;
        drawWaterWavesAnimationOnMap0(index);
    }

    private void drawWaterWavesAnimationOnMap0(int index) {
        int mapNum = 0;
        int[][] ranges = {{16, 50}, {55, 64}};  // Define the ranges

        for (int[] range : ranges) {
            int startingWorldX = range[0];
            int endingWorldX = range[1];

            for (int worldX = startingWorldX; worldX <= endingWorldX; worldX++) {
                gameManager.animatedTiles[mapNum][index] = new WaveCenterDown(gameManager);
                gameManager.animatedTiles[mapNum][index].worldX = GameManager.TILE_SIZE * worldX;
                gameManager.animatedTiles[mapNum][index].worldY = GameManager.TILE_SIZE * 94;
                index++;
            }
        }
    }

    public void trapsOnDungeonMap1() {
        int mapNum = 3;
        int index = 0;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 61;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 79;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 60;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 78;
        index++;
        gameManager.npcList[mapNum][index] = new NpcRockMovable(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 62;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 79;
    }

    public void npcOnDungeonMap1() {
        int mapNum = 3;
        int index = 3;
        gameManager.npcList[mapNum][index] = new NpcTutorialMan(gameManager);
        gameManager.npcList[mapNum][index].worldX = GameManager.TILE_SIZE * 57;
        gameManager.npcList[mapNum][index].worldY = GameManager.TILE_SIZE * 75;
    }

    public void setMobs() {
        //TODO change the coords of mobs
        mobsOnMap0();
        mobsOnDungeonMap1();
//        mobsOnMap2();
//        mobsOnMap3();
    }

    private void mobsOnMap0() {
        int index = 0;
        int mapNum = 0;
        gameManager.mobs[mapNum][index] = new MobChestMimic(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 15;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 84;
        index++;
    }

    private void mobsOnDungeonMap1() {
        int index = 0;
        int mapNum = 3;
        gameManager.mobs[mapNum][index] = new MobChestMimic(gameManager);
        gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 54;
        gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 72;
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
        if (!GameProgress.DUNGEON_BOSS_DEFEATED) {
            //Set mobs on different map
            gameManager.mobs[mapNum][index] = new MobDungeonBoss(gameManager);
            gameManager.mobs[mapNum][index].worldX = GameManager.TILE_SIZE * 23;
            gameManager.mobs[mapNum][index].worldY = GameManager.TILE_SIZE * 24;
            index++;
        }
    }
}
