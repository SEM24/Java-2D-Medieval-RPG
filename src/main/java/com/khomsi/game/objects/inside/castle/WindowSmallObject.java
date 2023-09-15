package com.khomsi.game.objects.inside.castle;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class WindowSmallObject extends Entity {

    public static final String OBJ_NAME = "Window Small";

    public WindowSmallObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
//        down = setup("/objects/castle/windows_small");
        down = setup("/objects/castle/windows_small",
                GameManager.TILE_SIZE - 15, (int) (GameManager.TILE_SIZE));

        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}