package com.khomsi.game.objects.dungeon;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class DungeonDoorOpenedObject extends Entity {

    public static final String OBJ_NAME = "Dungeon Door Opened";

    public DungeonDoorOpenedObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/dungeon/door_opened",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        collision = false;
    }
}