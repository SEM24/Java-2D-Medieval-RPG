package com.khomsi.game.objects.inside.castle;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class ThroneObject extends Entity {
    public static final String OBJ_NAME = "Bed";

    public ThroneObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_OBSTACLE;
        name = OBJ_NAME;

        collision = true;
        solidArea.x = 4 * 2;
        solidArea.y = 16 * 2;
        solidArea.width = 46 * 2;
        solidArea.height = 32 * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down = setup("/objects/castle/Throne_Red", (GameManager.TILE_SIZE * 2 + 16),
                GameManager.TILE_SIZE * 3);
    }

    @Override
    public void interact() {
        gameManager.gameState = GameManager.SLEEP_STATE;
        gameManager.playSE(15);
        gameManager.player.hp = gameManager.player.maxHp;
        gameManager.player.mana = gameManager.player.maxMana;
    }
}
