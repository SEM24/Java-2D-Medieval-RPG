package com.khomsi.game.objects.inside.castle;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.outside.DoorObject;

public class BigDoorOpenedObject extends DoorObject {
    public static final String OBJ_NAME = "Big Door Opened";

    public BigDoorOpenedObject(GameManager gameManager) {
        super(gameManager);
    }

    public BigDoorOpenedObject(GameManager gameManager, DoorType doorType) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        collision = false;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
        getImage(doorType);
    }

    public void getImage(DoorType doorType) {
        String basePath = "/objects/castle/big_door/opened/";
        switch (doorType) {
            case OPENED -> basePath += "Big_doors_opened";
            case OPENED_INSIDE -> basePath += "Big_doors_opened_1";
            default -> throw new IllegalArgumentException("Invalid type: " + doorType);
        }
        down = setup(basePath, (GameManager.TILE_SIZE * 3),
                GameManager.TILE_SIZE * 3);
    }
}
