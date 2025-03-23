package com.khomsi.game.objects.inside.castle;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.outside.DoorObject;

public class BigDoorClosedObject extends DoorObject {
    public static final String OBJ_NAME = "Big Door Closed";

    public BigDoorClosedObject(GameManager gameManager) {
        super(gameManager);
    }

    public BigDoorClosedObject(GameManager gameManager, DoorType doorType) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        collision = true;
        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 140;
        solidArea.height = 48 * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
        getImage(doorType);
    }

    public void getImage(DoorType doorType) {
        String basePath = "/objects/castle/big_door/closed/";
        switch (doorType) {
            case CLOSED -> basePath += "Big_doors_closed_1";
            case SPECIAL_KEY_CLOSED -> basePath += "Big_doors_closed_2";
            case KEY_CLOSED -> basePath += "Big_doors_closed_3";
            default -> throw new IllegalArgumentException("Invalid type: " + doorType);
        }
        down = setup(basePath, (GameManager.TILE_SIZE * 3),
                GameManager.TILE_SIZE * 3);
    }
}
