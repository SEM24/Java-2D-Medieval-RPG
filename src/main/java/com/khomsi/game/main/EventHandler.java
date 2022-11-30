package main.java.com.khomsi.game.main;

public class EventHandler {
    GameManager gameManager;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GameManager gameManager) {
        this.gameManager = gameManager;
        eventRect = new EventRect[GameManager.maxWorldCol][GameManager.maxWorldRow];
        int col = 0;
        int row = 0;

        while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == GameManager.maxWorldCol) {
                col = 0;
                row++;
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
            if (interact(28, 16, "any")) damageEvent(28, 16, gameManager.dialogueState);
            if (interact(34, 33, "up")) healingPool(34, 33, gameManager.dialogueState);
        }

    }

    private void damageEvent(int col, int row, int gameState) {
        gameManager.gameState = gameState;
        gameManager.ui.currentDialog = "You fall into a pit!";
        gameManager.player.hp -= 1;
//        teleport(gameState);
        canTouchEvent = false;
    }

    private void healingPool(int col, int row, int gameState) {
        if (gameManager.keyHandler.enterPressed) {
            gameManager.gameState = gameState;
            gameManager.ui.currentDialog = "You thrown the coin!\nYour Hp was recovered!";
            gameManager.player.hp = gameManager.player.maxHp;
        }
    }

//    private void teleport(int gameState) {
//        gameManager.gameState = gameState;
//        gameManager.ui.currentDialog = "Teleport!";
//        gameManager.player.worldX = GameManager.TILE_SIZE * 37;
//        gameManager.player.worldY = GameManager.TILE_SIZE * 10;
//    }

    public boolean interact(int col, int row, String direction) {
        boolean interact = false;

        gameManager.player.solidArea.x = gameManager.player.worldX + gameManager.player.solidArea.x;
        gameManager.player.solidArea.y = gameManager.player.worldY + gameManager.player.solidArea.y;

        eventRect[col][row].x = col * GameManager.TILE_SIZE + eventRect[col][row].x;
        eventRect[col][row].y = row * GameManager.TILE_SIZE + eventRect[col][row].y;
        //if player's solidArea is colliding with event's solidArea, event can be played only 1 time
        if (gameManager.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gameManager.player.direction.contentEquals(direction) ||
                    direction.contentEquals("any")) {
                interact = true;
                previousEventX = gameManager.player.worldX;
                previousEventY = gameManager.player.worldY;
            }
        }
        gameManager.player.solidArea.x = gameManager.player.solidAreaDefaultX;
        gameManager.player.solidArea.y = gameManager.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;


        return interact;
    }
}
