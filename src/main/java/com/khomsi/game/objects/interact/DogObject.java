package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class DogObject extends Entity {
    public DogObject(GameManager gameManager) {
        super(gameManager);
        name = "Dog";
        down = setup("/objects/dog");
    }
}
