package com.khomsi.game.objects.equipment;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class MetalShieldObject extends Entity {
    public static final String OBJ_NAME = "Metal Shield";
    public MetalShieldObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_SHIELD;
        name = OBJ_NAME;
        down = setup("/objects/equipment/shield_metal");
        defenseValue = 2;
        itemDescription = "[" + name + "]\n" + "Usual metal shield with\n" + defenseValue + " defense.";
        price = 21;
    }
}
