package com.khomsi.game.objects.equipment;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

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
