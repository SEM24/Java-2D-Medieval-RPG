package com.khomsi.game.objects.spells;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class ManaObject extends Entity {
    public static final String OBJ_NAME = "Mana";
    public ManaObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_PICK_UP_ONLY;
        value = 1;
        down = setup("/objects/ui/mana_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);

        image = setup("/objects/ui/mana_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image2 = setup("/objects/ui/mana_empty",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.playSE(6);
        gameManager.ui.addMessage("Mana +" + value);
        entity.mana += value;
        return true;
    }
}
