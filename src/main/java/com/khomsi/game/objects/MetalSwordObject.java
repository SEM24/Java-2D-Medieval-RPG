package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class MetalSwordObject extends Entity {
    public MetalSwordObject(GameManager gameManager) {
        super(gameManager);
        name = "Metal Sword";
        down = setup("/objects/sword", GameManager.TILE_SIZE, GameManager.TILE_SIZE);
        attackValue = 1;
        itemDescription = "[" + name + "]\n" + "Usual metal sword with\n" + attackValue + " attack.";
    }
}
