package com.khomsi.game.main.logic;

import com.khomsi.game.entity.mobs.MobDungeonBoss;
import com.khomsi.game.entity.player.PlayerDummy;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.dungeon.DungeonDoorClosedObject;

import java.awt.*;
import java.util.Objects;

public class CutSceneManager {
    GameManager gameManager;
    Graphics2D g2d;
    public int sceneNumber;
    public int scenePhase;
    float alpha = 0.0F;
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

  /*  public void scene_opening() {
        int pressEnterY = 520;
        String text;
        if (this.scenePhase == 0) {
            this.drawBlackBackground(1.0F);
            this.alpha += 0.005F;
            if (this.alpha > 1.0F) {
                this.alpha = 1.0F;
            }

            text = "This is an island somewhere far away.\n\nBlue Boy, an aspiring adventurer, \ncomes to this island because he hears \nthat it holds a legendary treasure.\n\n\n\n\n";
            this.drawString(this.alpha, 35.0F, 170, text, 40);
            this.drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
            if (this.gameManager.keyHandler.isEnterPressed) {
                this.gameManager.keyHandler.isEnterPressed = false;
                ++this.scenePhase;
            }
        }

        if (this.scenePhase == 1) {
            this.drawBlackBackground(1.0F);
            this.alpha -= 0.02F;
            if (this.alpha < 0.0F) {
                this.alpha = 0.0F;
                ++this.scenePhase;
            }

            text = "This is an island somewhere far away.\n\nBlue Boy, an aspiring adventurer, \ncomes to this island because he hears \nthat it holds a legendary treasure.\n\n\n\n\n";
            this.drawString(this.alpha, 35.0F, 170, text, 40);
            this.drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
        }

        if (this.scenePhase == 2) {
            this.drawBlackBackground(1.0F);
            this.alpha += 0.01F;
            if (this.alpha > 1.0F) {
                this.alpha = 1.0F;
            }

            text = "Can he safely find the treasure on this island,\nwhere dangerous monsters roam?\n\nIt all depends on you.\n\n\n\n\n\n";
            this.drawString(this.alpha, 35.0F, 200, text, 40);
            this.drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
            if (this.gameManager.keyHandler.isEnterPressed) {
                this.gameManager.keyHandler.isEnterPressed = false;
                ++this.scenePhase;
            }
        }

        if (this.scenePhase == 3) {
            this.drawBlackBackground(1.0F);
            this.alpha -= 0.02F;
            if (this.alpha < 0.0F) {
                this.alpha = 0.0F;
                ++this.scenePhase;
            }

            text = "Can he safely find the treasure on this island,\nwhere dangerous monsters roam?\n\nIt all depends on you.\n\n\n\n\n";
            this.drawString(this.alpha, 35.0F, 200, text, 40);
            this.drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to continue)", 40);
        }

        if (this.scenePhase == 4) {
            this.drawBlackBackground(1.0F);
            this.alpha += 0.005F;
            if (this.alpha > 1.0F) {
                this.alpha = 1.0F;
            }

            this.drawString(this.alpha, 35.0F, 50, "<How to Play>", 40);
            text = "Move: [W/A/S/D]\nAttack/Interact/Confirm: [ENTER]\nMagic: [F]\nGuard/Parry: [SPACE]\nInventory/Status: [C]\nMap: [M]   Mini Map: [X]\nPause: [P]\nOptions: [ESC]\n\n";
            this.drawString(this.alpha, 35.0F, 120, text, 45);
            this.drawString(this.alpha, 35.0F, pressEnterY, "(Press Enter to start the adventure)", 40);
            if (this.gameManager.keyHandler.isEnterPressed) {
                this.gameManager.keyHandler.isEnterPressed = false;
                ++this.scenePhase;
            }
        }

        if (this.scenePhase == 5) {
            this.gameManager.keyHandler.isEnterPressed = false;
            this.sceneNumber = 0;
            this.scenePhase = 0;
            GameManager var10000 = this.gameManager;
            this.gameManager.getClass();
            var10000.gameState = 1;
            this.gameManager.playMusic(0);
        }

    }

    public void drawBlackBackground(float alpha) {
        this.g2d.setComposite(AlphaComposite.getInstance(3, alpha));
        this.g2d.setColor(Color.black);
        Graphics2D var10000 = this.g2d;
        this.gameManager.getClass();
        this.gameManager.getClass();
        var10000.fillRect(0, 0, 960, 576);
        this.g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        this.g2d.setComposite(AlphaComposite.getInstance(3, alpha));
        this.g2d.setColor(Color.white);
        this.g2d.setFont(this.g2d.getFont().deriveFont(fontSize));
        String[] var9;
        int var8 = (var9 = text.split("\n")).length;

        for (int var7 = 0; var7 < var8; ++var7) {
            String line = var9[var7];
            int x = this.gameManager.ui.getXCenterText(line);
            this.g2d.drawString(line, x, y);
            y += lineHeight;
        }

        this.g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
    }*/

    public void sceneDungeonBoss() {
        switch (scenePhase) {
            case 0 -> {
                gameManager.bossBattleOn = true;
                //Close the door
                for (int i = 0; i < gameManager.object[1].length; i++) {
                    if (gameManager.object[gameManager.currentMap][i] == null) {
                        gameManager.object[gameManager.currentMap][i] = new DungeonDoorClosedObject(gameManager);
                        gameManager.object[gameManager.currentMap][i].worldX = GameManager.TILE_SIZE * 25;
                        gameManager.object[gameManager.currentMap][i].worldY = GameManager.TILE_SIZE * 8;
                        gameManager.object[gameManager.currentMap][i].markered = true;
                        i++;
                        gameManager.object[gameManager.currentMap][i] = new DungeonDoorClosedObject(gameManager);
                        gameManager.object[gameManager.currentMap][i].worldX = GameManager.TILE_SIZE * 25;
                        gameManager.object[gameManager.currentMap][i].worldY = GameManager.TILE_SIZE * 33;
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
