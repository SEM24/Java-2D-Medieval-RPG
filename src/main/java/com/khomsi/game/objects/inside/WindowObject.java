package com.khomsi.game.objects.inside;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class WindowObject extends Entity {

    public static final String OBJ_NAME = "Window";

    public WindowObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/inside/Window");

        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}