package com.khomsi.game.objects.outside;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class DoorObject extends Entity {
    public static final String OBJ_NAME = "Door";
    public DoorObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/door");
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You need a key to open the door!";
    }

    @Override
    public void interact() {
        startDialogue(this, 0);
    }
}
