package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class HeartObject extends Entity {
    public HeartObject(GameManager gameManager) {
        super(gameManager);
        name = "Heart";
        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_empty");

        //Resize the hearts FIXME hardcode
        image = tools.scaledImage(image, GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image2 = tools.scaledImage(image2, GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image3 = tools.scaledImage(image3, GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
    }
}
