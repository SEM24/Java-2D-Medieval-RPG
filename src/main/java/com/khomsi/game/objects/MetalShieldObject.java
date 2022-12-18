package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class MetalShieldObject extends Entity {
    public MetalShieldObject(GameManager gameManager) {
        super(gameManager);
        name = "Metal Shield";
        down = setup("/objects/shield", GameManager.TILE_SIZE, GameManager.TILE_SIZE);
        defenseValue = 2;
        itemDescription = "[" + name + "]\n" + "Usual metal shield with\n" + defenseValue + " defense.";
    }
}
