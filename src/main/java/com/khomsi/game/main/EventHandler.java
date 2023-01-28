package main.java.com.khomsi.game.main;

public class EventHandler {
    GameManager gameManager;
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
        int col = 0;
        int row = 0;
        int map = 0;

        while (map < gameManager.maxMap && col < GameManager.maxWorldCol &&
                row < GameManager.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col == GameManager.maxWorldCol) {
                col = 0;
                row++;
                if (row == GameManager.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
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
            if (interact(0, 29, 19, "any")) damagePit(gameManager.dialogueState);
            else if (interact(0, 30, 21, "right")) healingPool(gameManager.dialogueState);
                //Col and row of enter location is the place where
                //the player will be spawned on new map
            else if (interact(0, 12, 17, "any")) {
                changeLocation(1, 21, 21);
                gameManager.playSE(4);
            } else if (interact(1, 21, 23, "any")) {
                changeLocation(0, 12, 18);
                gameManager.playSE(5);
            }
        }
    }

    private void changeLocation(int map, int col, int row) {
        gameManager.gameState = gameManager.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
//        gameManager.playSE(4);
    }

    private void damagePit(int gameState) {
        //TODO gameManager.playerSE();
        gameManager.gameState = gameState;
        gameManager.ui.currentDialog = "You felt into a pit!";
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
                gameManager.ui.currentDialog = "You thrown the coin!\nYour Hp and Mana were recovered!";
                gameManager.player.hp = gameManager.player.maxHp;
                gameManager.player.mana = gameManager.player.maxMana;
                gameManager.player.coin--;
                gameManager.placeObjects.setMobs();
            } else {
                //TODO gameManager.playerSE();
                gameManager.ui.currentDialog = "You need coins to interact!\nCome again later!";
            }
        }
    }

    private void teleport(int gameState) {
        gameManager.gameState = gameState;
        gameManager.ui.currentDialog = "You felt into a pit!";
        gameManager.player.worldX = GameManager.TILE_SIZE * 37;
        gameManager.player.worldY = GameManager.TILE_SIZE * 10;
        gameManager.player.fallIntoPit = false;
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
}
