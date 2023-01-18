package main.java.com.khomsi.game.objects.gui;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class HeartObject extends Entity {
    public HeartObject(GameManager gameManager) {
        super(gameManager);
        name = "Heart";
        image = setup("/objects/heart_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image2 = setup("/objects/heart_half",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image3 = setup("/objects/heart_empty",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
    }
}
