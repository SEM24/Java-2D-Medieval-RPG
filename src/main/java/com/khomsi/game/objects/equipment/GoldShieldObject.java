package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class GoldShieldObject extends Entity {
    public GoldShieldObject(GameManager gameManager) {
        super(gameManager);
        type = typeShield;
        name = "Gold Shield";
        down = setup("/objects/equipment/shield_gold");
        defenseValue = 3;
        itemDescription = "[" + name + "]\n" + "Gold shield with\n" + defenseValue + " defense.";
    }
}
