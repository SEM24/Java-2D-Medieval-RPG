package main.java.com.khomsi.game.objects.light;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class LanternObject extends Entity {
    public LanternObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_LIGHT;
        name = "Lantern";
        down = setup("/objects/light/lantern");
        itemDescription = "[" + name + "]\n" + "Illuminates your\nsurroundings.";
        price = 350;
        lightRadius = 350;
    }
}
