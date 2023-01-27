package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class BootsObject extends Entity {

    public BootsObject(GameManager gameManager) {
        super(gameManager);
        name = "Boots";
        down = setup("/objects/boots");
    }
}
