package com.khomsi.game.objects.outside;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class StairsDownObject extends Entity {

    public static final String OBJ_NAME = "Stairs down";
    public StairsDownObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        collision = true;
        solidArea.x = 15;
        solidArea.y = 40;
        solidArea.width = 41;
        solidArea.height = 50;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down = setup("/objects/stairs_down",
                (int) (GameManager.TILE_SIZE * 2.3), GameManager.TILE_SIZE * 2 + 20);
    }
}
