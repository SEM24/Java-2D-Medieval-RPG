package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class KeyObject extends Entity {


    public KeyObject(GameManager gameManager) {
        super(gameManager);
        name = "Key";
        down = setup("/objects/key");
    }
}
