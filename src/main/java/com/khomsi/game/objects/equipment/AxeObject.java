package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class AxeObject extends Entity {
    public AxeObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_AXE;
        name = "Axe";
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
