package com.khomsi.game.objects.light;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

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
