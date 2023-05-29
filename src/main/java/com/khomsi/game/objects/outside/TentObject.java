package com.khomsi.game.objects.outside;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class TentObject extends Entity {
    public static final String OBJ_NAME = "Tent";
    public TentObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = OBJ_NAME;
        down = setup("/objects/tent");
        itemDescription = "[" + name + "]" + "\nYou can sleep until\nnext morning.";
        price = 300;
        stackable = true;
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.gameState = GameManager.SLEEP_STATE;
        gameManager.playSE(15);
        gameManager.player.hp = gameManager.player.maxHp;
        gameManager.player.mana = gameManager.player.maxMana;
        gameManager.player.getSleepingImage(down);
        //Disappear after using
        return true;
    }
}
