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

    @Override
    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You opened the chest and found a " + loot.name + "!" +
                "\n...But you can't carry any more!";
        dialogues[1][0] = "You opened the chest and found a " + loot.name + "!" +
                "\nYou obtain the " + loot.name + "!";
        dialogues[2][0] = "This chest is empty!";
    }

    @Override
    public void interact() {
        if (!opened) {
            gameManager.playSE(4);
            if (!gameManager.player.canObtainItem(loot)) {
                startDialogue(this, 0);
            } else {
                startDialogue(this, 1);
                down = image2;
                opened = true;
            }
        } else {
            startDialogue(this, 2);
        }
    }
}
