package com.khomsi.game.objects.inside;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class StairsUpObject extends Entity {

    public static final String OBJ_NAME = "Stairs Up";

    public StairsUpObject(GameManager gameManager) {
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

        down = setup("/objects/inside/stairs_up",
                (int) (GameManager.TILE_SIZE * 2.3), GameManager.TILE_SIZE * 2 + 20);
    }
}
