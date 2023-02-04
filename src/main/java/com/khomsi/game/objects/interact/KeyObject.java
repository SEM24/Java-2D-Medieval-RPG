package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class KeyObject extends Entity {


    public KeyObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = "key";
        down = setup("/objects/key");
        itemDescription = "[" + name + "]\n" + "Key opens the door.";
        stackable = true;
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.gameState = gameManager.dialogueState;

        int objIndex = getDetected(entity, gameManager.object, "door");
        if (objIndex != 999) {
            gameManager.ui.currentDialog = "You used the " + name + " and opened the door";
            gameManager.playSE(4);
            gameManager.object[gameManager.currentMap][objIndex] = null;
            return true;
        } else {
            gameManager.ui.currentDialog = "Uhh, where do i need to use it?";
            return false;
        }
    }

    private int getDetected(Entity entity, Entity[][] target, String targetName) {
        int index = 999;
        int nextWorldX = entity.getLeftX();
        int nextWorldY = entity.getTopY();

        switch (entity.direction) {
            case "up" -> nextWorldY = entity.getTopY() - entity.speed;
            case "down" -> nextWorldY = entity.getBottomY() + entity.speed;
            case "left" -> nextWorldX = entity.getLeftX() - entity.speed;
            case "right" -> nextWorldX = entity.getRightX() + entity.speed;
        }
        int col = nextWorldX / GameManager.TILE_SIZE;
        int row = nextWorldY / GameManager.TILE_SIZE;
        for (int i = 0; i < target[1].length; i++) {
            if (target[gameManager.currentMap][i] != null) {
                if (target[gameManager.currentMap][i].getCol() == col &&
                        target[gameManager.currentMap][i].getRow() == row &&
                        target[gameManager.currentMap][i].name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
