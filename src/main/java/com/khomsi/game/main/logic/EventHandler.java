package main.java.com.khomsi.game.main.logic;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

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
            if (interact(0, 27, 20, "any")) {
                damagePit(GameManager.DIALOGUE_STATE);
            } else {
                handleInteractions();
            }
        }
    }

    private void handleInteractions() {
        int currentMap = gameManager.currentMap;
        switch (currentMap) {
            case 0 -> { // Main Map
                if (interact(0, 18, 35, "down") || interact(0, 19, 35, "down")
                        || interact(0, 20, 35, "down")) {
                    healingPool(GameManager.DIALOGUE_STATE);
                } else if (interact(0, 18, 25, "any")) {
                    changeLocation(1, 20, 22, GameManager.INDOOR);
                    gameManager.playSE(4);
                }
            }
            case 1 -> { // Seller House
                //back to main map from seller
                if (interact(1, 20, 23, "any")) {
                    changeLocation(0, 18, 26, GameManager.OUTSIDE);
                    gameManager.playSE(5);
                }
                //Enter Dungeon in seller house
                else if (interact(1, 25, 15, "up") || interact(1, 24, 15, "up")) {
                    changeLocation(2, 16, 39, GameManager.DUNGEON);
                    gameManager.playSE(4);
                }
                //Talk to seller in house
                else if (interact(1, 20, 18, "up")) {
                    speak(gameManager.npcList[1][0]);
                }
            }
            case 2 -> { // Dungeon
                //Back to seller house from dungeon
                if (interact(2, 15, 39, "any")) {
                    changeLocation(1, 25, 15, GameManager.INDOOR);
                    gameManager.playSE(5);
                }
                //Enter the boss dungeon
                else if (interact(2, 9, 14, "any")) {
                    changeLocation(3, 25, 1, GameManager.BOSS_DUNGEON);
                    gameManager.playSE(4);
                }
            }
            case 3 -> { // Boss Dungeon
                //Exit the boss dungeon
                if (interact(3, 26, 1, "any")) {
                    changeLocation(2, 9, 15, GameManager.BOSS_DUNGEON);
                    gameManager.playSE(4);
                }
            }
        }
    }

    public boolean interact(int map, int col, int row, String direction) {
        boolean interact = false;

        if (map == gameManager.currentMap) {

            gameManager.player.solidArea.x = gameManager.player.worldX + gameManager.player.solidArea.x;
            gameManager.player.solidArea.y = gameManager.player.worldY + gameManager.player.solidArea.y;

            eventRect[map][col][row].x = col * GameManager.TILE_SIZE + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * GameManager.TILE_SIZE + eventRect[map][col][row].y;
            //if player's solidArea is colliding with event's solidArea, event can be played only 1 time
            if (gameManager.player.solidArea.intersects(eventRect[map][col][row]) &&
                    !eventRect[map][col][row].eventDone) {
                if (gameManager.player.direction.contentEquals(direction) ||
                        direction.contentEquals("any")) {
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
        if (gameManager.keyHandler.enterPressed) {
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
//        gameManager.playSE(4);
    }

    private void damagePit(int gameState) {
        //TODO gameManager.playerSE();
        gameManager.gameState = gameState;
        eventMaster.startDialogue(eventMaster, 0);
        //TODO draw different sprites from character char_one char_pit_fall
        gameManager.player.hp -= 1;
        teleport(gameState);
        canTouchEvent = false;
    }

    private void healingPool(int gameState) {
        if (gameManager.keyHandler.enterPressed) {
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
        gameManager.player.worldX = GameManager.TILE_SIZE * 24;
        gameManager.player.worldY = GameManager.TILE_SIZE * 11;
        gameManager.player.fallIntoPit = false;
    }
}
