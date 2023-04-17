package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class CoinBObject extends Entity {
    public static final String OBJ_NAME = "Coin Bronze";
    public CoinBObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_PICK_UP_ONLY;
        down = setup("/objects/coins/coins_bronze");
        value = 5;
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.playSE(6);
        gameManager.ui.addMessage("Coin +" + value);
        gameManager.player.coin += value;
        return true;
    }
}
