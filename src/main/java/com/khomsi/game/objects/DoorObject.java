package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class DoorObject extends Entity {

    public DoorObject(GameManager gameManager) {
        super(gameManager);
        name = "Door";
        down = setup("/objects/door");
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
