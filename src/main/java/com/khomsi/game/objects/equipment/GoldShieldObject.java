package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class GoldShieldObject extends Entity {
    public static final String OBJ_NAME = "Gold Shield";
    public GoldShieldObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_SHIELD;
        name = OBJ_NAME;
        down = setup("/objects/equipment/shield_gold");
        defenseValue = 3;
        itemDescription = "[" + name + "]\n" + "Gold shield with\n" + defenseValue + " defense.";
        price = 35;
    }
}
