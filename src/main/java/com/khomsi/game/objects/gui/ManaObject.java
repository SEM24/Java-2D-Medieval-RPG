package main.java.com.khomsi.game.objects.gui;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class ManaObject extends Entity {
    public ManaObject(GameManager gameManager) {
        super(gameManager);
        name = "Mana";
        image = setup("/objects/mana_full",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        image2 = setup("/objects/mana_empty",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
    }
}
