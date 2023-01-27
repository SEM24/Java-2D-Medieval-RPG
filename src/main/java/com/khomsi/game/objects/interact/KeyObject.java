package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class KeyObject extends Entity {


    public KeyObject(GameManager gameManager) {
        super(gameManager);
        name = "Key";
        down = setup("/objects/key");
        itemDescription = "[" + name + "]\n" + "Key opens the door.";
    }
}
