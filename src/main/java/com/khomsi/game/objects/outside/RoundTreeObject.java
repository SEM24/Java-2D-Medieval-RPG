package com.khomsi.game.objects.outside;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class RoundTreeObject extends Entity {

    public static final String OBJ_NAME = "Round Tree";

    public RoundTreeObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/outside/round_tree",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 86;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}