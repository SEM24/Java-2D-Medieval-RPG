package com.khomsi.game.objects.spells;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class HeartObject extends Entity {
    public static final String OBJ_NAME = "Heart";

    public HeartObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_PICK_UP_ONLY;
        value = 2;
        down = setup("/objects/ui/heart_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);

        image = setup("/objects/ui/heart_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image2 = setup("/objects/ui/heart_half",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image3 = setup("/objects/ui/heart_empty",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.playSE(6);
        gameManager.ui.addMessage("Life +" + value);
        entity.hp += value;
        return true;
    }
}
