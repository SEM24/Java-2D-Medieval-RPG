package main.java.com.khomsi.game.objects.spells;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class PotionObject extends Entity {


    public PotionObject(GameManager gameManager) {
        super(gameManager);
        type = typeConsumable;
        name = "Simple potion";
        down = setup("/objects/potions/potion");
        value = 6;

        itemDescription = "[" + name + "]\n" + "Simple potion\nthat heals " + value + " hp.";
    }

    @Override
    public void use(Entity entity) {
        gameManager.gameState = gameManager.dialogueState;
        gameManager.ui.currentDialog = "You used the " + name + "!\n" +
                "Your HP is recovered by " + value + ".";
        entity.hp += value;
        gameManager.playSE(6);
    }
}
