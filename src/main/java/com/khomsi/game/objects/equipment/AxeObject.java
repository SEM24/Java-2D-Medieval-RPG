package com.khomsi.game.objects.equipment;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

public class AxeObject extends Entity {
    public static final String OBJ_NAME = "Axe";

    public AxeObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_AXE;
        name = OBJ_NAME;
        down = setup("/objects/equipment/axe");
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        itemDescription = "[" + name + "]\n" + "Axe with\n" + attackValue + " attack.";
        price = 13;
        knockBackPower = 10;
        motion1Duration = 15;
        motion2Duration = 20;
    }
}
