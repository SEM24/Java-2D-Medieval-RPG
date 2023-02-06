package main.java.com.khomsi.game.objects.outside;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class BedObject extends Entity {
    public BedObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_OBSTACLE;
        name = "Bed";

        collision = true;
        solidArea.x = 15;
        solidArea.y = 15;
        solidArea.width = 41;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        down = setup("/objects/bed_blue",
                (int) (GameManager.TILE_SIZE * 1.4), GameManager.TILE_SIZE * 2 + 10);
    }

    @Override
    public void interact() {
        gameManager.gameState = GameManager.SLEEP_STATE;
        gameManager.playSE(15);
        gameManager.player.hp = gameManager.player.maxHp;
        gameManager.player.mana = gameManager.player.maxMana;
    }
}
