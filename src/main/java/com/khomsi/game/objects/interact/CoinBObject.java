package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class CoinBObject extends Entity {
    public CoinBObject(GameManager gameManager) {
        super(gameManager);
        type = typePickUpOnly;
        down = setup("/objects/coins/coins_bronze");
        value = 1;
    }

    @Override
    public void use(Entity entity) {
        gameManager.playSE(6);
        gameManager.ui.addMessage("Coin +" + value);
        gameManager.player.coin += value;
    }
}
