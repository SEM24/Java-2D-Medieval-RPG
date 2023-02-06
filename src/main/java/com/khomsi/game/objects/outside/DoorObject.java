package main.java.com.khomsi.game.objects.outside;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class DoorObject extends Entity {

    public DoorObject(GameManager gameManager) {
        super(gameManager);
        name = "door";
        type = TYPE_OBSTACLE;
        down = setup("/objects/door");
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void interact() {
        gameManager.gameState = GameManager.DIALOGUE_STATE;
        gameManager.ui.currentDialog = "You need a key to open the door!";
    }
}
