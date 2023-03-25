package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class MetalSwordObject extends Entity {
    public static final String OBJ_NAME = "Metal Sword";

    public MetalSwordObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_SWORD;
        name = OBJ_NAME;
        down = setup("/objects/equipment/sword");
        attackValue = 1;
        itemDescription = "[" + name + "]\n" + "Usual metal sword with\n" + attackValue + " attack.";
        attackArea.width = 36;
        attackArea.height = 36;
        price = 22;
        knockBackPower = 2;
        motion1Duration = 5;
        motion2Duration = 7;
    }
}
