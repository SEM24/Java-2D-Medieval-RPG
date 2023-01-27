package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ChestObject extends Entity {

    public ChestObject(GameManager gameManager) {
        super(gameManager);
        name = "Chest";
        down = setup("/objects/chest");
    }
}
