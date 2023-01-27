package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class MetalSwordObject extends Entity {
    public MetalSwordObject(GameManager gameManager) {
        super(gameManager);
        type = typeSword;
        name = "Metal Sword";
        down = setup("/objects/equipment/sword");
        attackValue = 1;
        itemDescription = "[" + name + "]\n" + "Usual metal sword with\n" + attackValue + " attack.";
        attackArea.width = 36;
        attackArea.height = 36;
    }
}
