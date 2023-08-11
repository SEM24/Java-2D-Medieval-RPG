package com.khomsi.game.objects.inside;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class PictureObject extends Entity {

    public static final String OBJ_NAME = "Picture";

    public PictureObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/inside/Picture");

        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}