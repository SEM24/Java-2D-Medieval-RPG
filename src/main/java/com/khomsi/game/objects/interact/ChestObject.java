package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class ChestObject extends Entity {
    public static final String OBJ_NAME = "Chest";

    public ChestObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_OBSTACLE;
        name = OBJ_NAME;
        image = setup("/objects/chest");
        image2 = setup("/objects/chest_opened");
        down = image;
        collision = true;
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }


    public void setLoot(Entity loot) {
        this.loot = loot;
    }

    @Override
    public void interact() {
        gameManager.gameState = GameManager.DIALOGUE_STATE;
        if (!opened) {
            gameManager.playSE(4);
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and found a ").append(loot.name).append("!");
            if (!gameManager.player.canObtainItem(loot)) {
                sb.append("\n...But you can't carry any more!");
            } else {
                sb.append("\nYou obtain the ").append(loot.name).append("!");
                down = image2;
                opened = true;
            }
            gameManager.ui.currentDialog = sb.toString();
        } else {
            gameManager.ui.currentDialog = "This chest is empty!";
        }
    }
}
