package com.khomsi.game.main.logic;

import com.khomsi.game.data.GameProgress;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;

public class EventHandler {
    GameManager gameManager;
    Entity eventMaster;
    EventRect[][][] eventRect;
    public int previousEventX;
    public int previousEventY;
    boolean canTouchEvent = true;
    public int tempMap;
    public int tempCol;
    public int tempRow;

    public EventHandler(GameManager gameManager) {
        this.gameManager = gameManager;
        eventRect = new EventRect[gameManager.maxMap][GameManager.maxWorldCol][GameManager.maxWorldRow];
        eventMaster = new Entity(gameManager);

        for (int map = 0; map < gameManager.maxMap; map++) {
            for (int col = 0; col < GameManager.maxWorldCol; col++) {
                for (int row = 0; row < GameManager.maxWorldRow; row++) {
                    eventRect[map][col][row] = new EventRect();
                    eventRect[map][col][row].x = 23;
                    eventRect[map][col][row].y = 23;
                    eventRect[map][col][row].width = 2;
                    eventRect[map][col][row].height = 2;
                    eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
                    eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
                }
            }
        }
        setDialogue();
    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You felt into a pit!";
        eventMaster.dialogues[1][0] = "You thrown the coin!\nYour Hp and Mana were recovered!\nFile saved.";
        eventMaster.dialogues[2][0] = "You need coins to interact!\nCome again later!";
        eventMaster.dialogues[3][0] = "Ouch! That hurt!";
    }

    public void checkEvent() {
        //Check if the player is still on this tile or not
        int xDistance = Math.abs(gameManager.player.worldX - previousEventX);
        int yDistance = Math.abs(gameManager.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > GameManager.TILE_SIZE) {
            canTouchEvent = true;
        }
        if (canTouchEvent) {
            handleInteractions();
        }
    }

    private void handleInteractions() {
        int currentMap = gameManager.currentMap;
        switch (currentMap) {
            case 0 -> { // Main Map
                if (interact(0, 13, 91, "any")) {
                    changeLocation(3, 54, 86, GameManager.LIGHT_DUNGEON);
                    gameManager.playSE(4);
//                    gameManager.playSE(28);
                }
                //Sea urchin interact
                else if (shouldInteractSeaUrchin(0)) {
                    damageAnyThing(GameManager.DIALOGUE_STATE);
                } else if (shouldInteractHealingPool(0)) {
                    healingPool(GameManager.DIALOGUE_STATE);
                }
                //Seller house on beach
                else if (interact(0, 35, 84, "any")) {
                    changeLocation(2, 47, 71, GameManager.INDOOR);
                    gameManager.playSE(28);
                }
            }
            case 1 -> { // Dungeon
                //back to main map from Dungeon
                if (interact(3, 57, 72, "any")
                        || interact(3, 53, 86, "any")) {
                    changeLocation(0, 13, 90, GameManager.OUTSIDE);
                    gameManager.playSE(5);
                }
            }

            //Seller house on beach
            case 2 -> {
//                back to main map from seller
                if (interact(2, 47, 72, "any")) {
                    changeLocation(0, 35, 85, GameManager.OUTSIDE);
                    gameManager.playSE(28);
                }
                //                //Talk to seller in house
//                else if (interact(1, 20, 18, "up")) {
//                    speak(gameManager.npcList[1][0]);
//                }
//                                //Enter Dungeon in seller house
//                else if (interact(1, 25, 15, "up", 15)) {
//                    changeLocation(2, 16, 39, GameManager.DUNGEON);
//                    gameManager.playSE(4);
//                }
            }

//            case 2 -> { // Dungeon
//                //Back to seller house from dungeon
//                if (interact(2, 15, 39, "any")) {
//                    changeLocation(1, 25, 15, GameManager.INDOOR);
//                    gameManager.playSE(5);
//                }
//                //Enter the boss dungeon
//                else if (interact(2, 9, 14, "any")) {
//                    changeLocation(3, 25, 1, GameManager.BOSS_DUNGEON);
//                    gameManager.playSE(4);
//                }
//            }
//            case 3 -> { // Boss Dungeon
//                //Exit the boss dungeon
//                if (interact(3, 26, 1, "any")) {
//                    changeLocation(2, 9, 15, GameManager.BOSS_DUNGEON);
//                    gameManager.playSE(4);
//                }
//                //Start cutscene with the boss
//                if (interact(3, 25, 10, "any") ||
//                        interact(3, 26, 10, "any", 10)) {
//                    dungeonBoss();
//                }
//            }
        }
    }

    private boolean shouldInteractHealingPool(int currentMap) {
        // Initialize with an empty array
        int[][] interactRanges = new int[][]{};
        if (currentMap == 0) {
            interactRanges = new int[][]{
                    {0, 21, 85}
            };
        } else if (currentMap == 1) {
            //TODO Add items to map 1
//            interactRanges = new int[][]{
//                    {1, 33, 91},
//                    {1, 36, 90},
//                    {1, 38, 82}
//            };
        }
        for (int[] range : interactRanges) {
            if (interact(currentMap, range[1], range[2], "any")) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldInteractSeaUrchin(int currentMap) {
        // Initialize with an empty array
        int[][] interactRanges = new int[][]{};
        if (currentMap == 0) {
            interactRanges = new int[][]{
                    //Sea urchin locations
                    {0, 33, 91},
                    {0, 36, 90},
                    {0, 38, 82},
                    {0, 54, 85},
                    {0, 60, 82},
                    {0, 59, 92},
            };
        }
        for (int[] range : interactRanges) {
            if (interact(currentMap, range[1], range[2], "any")) {
                return true;
            }
        }
        return false;
    }

    public boolean interact(int map, int col, int row, String direction) {
        return interact(map, col, row, direction, 0);
    }

    public boolean interact(int map, int col, int row, String direction, int tolerance) {
        boolean interact = false;

        if (map == gameManager.currentMap) {
            gameManager.player.solidArea.x = gameManager.player.worldX + gameManager.player.solidArea.x;
            gameManager.player.solidArea.y = gameManager.player.worldY + gameManager.player.solidArea.y;

            eventRect[map][col][row].x = col * GameManager.TILE_SIZE + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * GameManager.TILE_SIZE + eventRect[map][col][row].y;
            // Define a smaller rectangle for player's collision area with tolerance
            //This way we can adjust the solid area of interaction, so if player collide on solid area, it will interact
            //any way
            Rectangle playerCollisionArea = new Rectangle(
                    gameManager.player.solidArea.x,
                    gameManager.player.solidArea.y,
                    gameManager.player.solidArea.width + tolerance,
                    gameManager.player.solidArea.height
            );
            // If player's collision area intersects with event's solidArea, event can be played only 1 time
            if (playerCollisionArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gameManager.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
                    interact = true;
                    previousEventX = gameManager.player.worldX;
                    previousEventY = gameManager.player.worldY;
                }
            }
            gameManager.player.solidArea.x = gameManager.player.solidAreaDefaultX;
            gameManager.player.solidArea.y = gameManager.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return interact;
    }

    private void speak(Entity entity) {
        if (gameManager.keyHandler.isEnterPressed) {
            gameManager.gameState = GameManager.DIALOGUE_STATE;
            gameManager.player.attackCanceled = true;
            entity.speak();
        }
    }

    private void changeLocation(int map, int col, int row, int area) {
        gameManager.gameState = GameManager.TRANSITION_STATE;
        gameManager.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
//      TODO  gameManager.playSE(4);
    }

    public void damagePit(int gameState) {
        //TODO gameManager.playerSE();
        gameManager.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        //TODO draw different sprites from character char_one char_pit_fall
        gameManager.player.hp -= 1;
        teleport(gameState);
        canTouchEvent = false;
    }

    public void damageAnyThing(int gameState) {
//       TODO  gameManager.playSE();
        gameManager.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 3);
        gameManager.player.hp -= 1;
        canTouchEvent = false;
    }

    private void healingPool(int gameState) {
        if (gameManager.keyHandler.isEnterPressed) {
            gameManager.gameState = gameState;
            gameManager.player.attackCanceled = true;
            if (gameManager.player.coin > 0) {
                //TODO gameManager.playerSE();
                eventMaster.startDialogue(eventMaster, 1);
                gameManager.player.hp = gameManager.player.maxHp;
                gameManager.player.mana = gameManager.player.maxMana;
                gameManager.player.coin--;
                gameManager.placeObjects.setMobs();
                //TODO make a new place to save the player (unique)
                gameManager.saveLoad.save();
            } else {
                //TODO gameManager.playerSE();
                eventMaster.startDialogue(eventMaster, 2);
            }
        }
    }

    private void teleport(int gameState) {
        gameManager.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        gameManager.player.worldX = GameManager.TILE_SIZE * 13;
        gameManager.player.worldY = GameManager.TILE_SIZE * 71;
        gameManager.player.fallIntoPit = false;
    }

    public void dungeonBoss() {
        if (!gameManager.bossBattleOn && !GameProgress.DUNGEON_BOSS_DEFEATED) {
            gameManager.gameState = GameManager.CUTSCENE_STATE;
            gameManager.cutSceneManager.sceneNumber = CutSceneManager.DUNGEON_BOSS;
        }
    }
}
