package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class MetalShieldObject extends Entity {
    public MetalShieldObject(GameManager gameManager) {
        super(gameManager);
        type = typeShield;
        name = "Metal Shield";
        down = setup("/objects/shield_metal");
        defenseValue = 2;
        itemDescription = "[" + name + "]\n" + "Usual metal shield with\n" + defenseValue + " defense.";
    }
}
