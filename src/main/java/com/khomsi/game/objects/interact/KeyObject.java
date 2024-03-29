package com.khomsi.game.objects.interact;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class KeyObject extends Entity {
    public static final String OBJ_NAME = "Key";

    public KeyObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = OBJ_NAME;
        down = setup("/objects/key");
        itemDescription = "[" + name + "]\n" + "Key opens the door.";
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You used the " + name + " and opened the door";
        dialogues[1][0] = "Uhh, where do I need to use it?";
    }

    public boolean use(Entity entity) {
        return checkKeyUsage(entity, "Door");
    }

    protected boolean checkKeyUsage(Entity entity, String targetName) {
        int objIndex = getDetected(entity, gameManager.object, targetName);
        if (objIndex != 999) {
            startDialogue(this, 0);
            gameManager.playSE(27);
            gameManager.object[gameManager.currentMap][objIndex] = null;
            return true;
        } else {
            startDialogue(this, 1);
            return false;
        }
    }
}
