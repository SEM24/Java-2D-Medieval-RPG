package main.java.com.khomsi.game.objects.light;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.interact.KeyObject;

public class LanternObject extends Entity {
    public static final String OBJ_NAME = "Lantern";
    public LanternObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_LIGHT;
        name = OBJ_NAME;
        down = setup("/objects/light/lantern");
        itemDescription = "[" + name + "]\n" + "Illuminates your\nsurroundings.";
        price = 350;
        lightRadius = 350;
    }
}
