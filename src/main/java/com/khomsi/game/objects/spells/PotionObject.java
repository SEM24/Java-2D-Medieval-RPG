package main.java.com.khomsi.game.objects.spells;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class PotionObject extends Entity {

    public static final String OBJ_NAME = "Potion";

    public PotionObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = OBJ_NAME;
        down = setup("/objects/potions/potion");
        value = 6;
        price = 20;
        itemDescription = "[" + name + "]\n" + "Simple potion\nthat recovers " + value + " hp.";
        stackable = true;
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.gameState = GameManager.DIALOGUE_STATE;
        gameManager.ui.currentDialog = "You used the " + name + "!\n" +
                "Your HP is recovered by " + value + ".";
        entity.hp += value;
        gameManager.playSE(6);
        return true;
    }
}
