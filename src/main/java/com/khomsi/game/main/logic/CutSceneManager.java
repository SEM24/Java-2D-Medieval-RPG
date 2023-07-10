package com.khomsi.game.main.logic;

import com.khomsi.game.entity.mobs.MobDungeonBoss;
import com.khomsi.game.entity.player.PlayerDummy;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.outside.DungeonDoorClosedObject;

import java.awt.*;
import java.util.Objects;

public class CutSceneManager {
    GameManager gameManager;
    Graphics2D g2d;
    public int sceneNumber;
    public int scenePhase;
    //Scene number
    public static final int NA = 0;
    public static final int DUNGEON_BOSS = 1;

    public CutSceneManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        switch (sceneNumber) {
            case DUNGEON_BOSS -> sceneDungeonBoss();
        }
    }

    public void sceneDungeonBoss() {
        switch (scenePhase) {
            case 0 -> {
                gameManager.bossBattleOn = true;
                //Close the door
                for (int i = 0; i < gameManager.object[1].length; i++) {
                    if (gameManager.object[gameManager.currentMap][i] == null) {
                        gameManager.object[gameManager.currentMap][i] = new DungeonDoorClosedObject(gameManager);
                        //TODO change the location of door and the map of dungeon 2
                        gameManager.object[gameManager.currentMap][i].worldX = GameManager.TILE_SIZE * 25;
                        gameManager.object[gameManager.currentMap][i].worldY = GameManager.TILE_SIZE * 8;
                        gameManager.object[gameManager.currentMap][i].markered = true;
                        gameManager.playSE(5);
                        break;
                    }
                }
                //Search the vacant slot for dummy
                for (int i = 0; i < gameManager.npcList[1].length; i++) {
                    if (gameManager.npcList[gameManager.currentMap][i] == null) {
                        gameManager.npcList[gameManager.currentMap][i] = new PlayerDummy(gameManager);
                        gameManager.npcList[gameManager.currentMap][i].worldX = gameManager.player.worldX;
                        gameManager.npcList[gameManager.currentMap][i].worldY = gameManager.player.worldY;
                        gameManager.npcList[gameManager.currentMap][i].direction = gameManager.player.direction;
                        break;
                    }
                }
                gameManager.player.drawing = false;
                scenePhase++;
            }
            //Move the camera to boss
            case 1 -> {
                gameManager.player.worldY += 2;
                if (gameManager.player.worldY > GameManager.TILE_SIZE * 28) {
                    scenePhase++;
                }
            }
            case 2 -> {
                for (int i = 0; i < gameManager.mobs[1].length; i++) {
                    if (gameManager.mobs[gameManager.currentMap][i] != null
                            && Objects.equals(gameManager.mobs[gameManager.currentMap][i].name, MobDungeonBoss.MOB_NAME)) {
                        gameManager.mobs[gameManager.currentMap][i].bossSleep = false;
                        gameManager.ui.npc = gameManager.mobs[gameManager.currentMap][i];
                        scenePhase++;
                        break;
                    }
                }
            }
            //Boss speaks
            case 3 -> gameManager.ui.showDialog();
            //Return camera to player
            case 4 -> {
                for (int i = 0; i < gameManager.npcList[1].length; i++) {
                    if (gameManager.npcList[gameManager.currentMap][i] != null
                            && Objects.equals(gameManager.npcList[gameManager.currentMap][i].name, PlayerDummy.NPC_NAME)) {
                        //Restore player's position
                        gameManager.player.worldX = gameManager.npcList[gameManager.currentMap][i].worldX;
                        gameManager.player.worldY = gameManager.npcList[gameManager.currentMap][i].worldY;
                        //Delete dummy
                        gameManager.npcList[gameManager.currentMap][i] = null;
                        break;
                    }
                }
                //Draw the player again
                gameManager.player.drawing = true;
                //Restore counters
                scenePhase = 0;
                sceneNumber = NA;
                gameManager.gameState = GameManager.PLAY_STATE;
                //Change music
                gameManager.stopMusic();
                gameManager.playMusic(22);
            }
        }
    }
}
