package main.java.com.khomsi.game.objects.interact;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.MetalSwordObject;

public class KeyObject extends Entity {
    public static final String OBJ_NAME = "Key";

    public KeyObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = OBJ_NAME;
        down = setup("/objects/key");
        itemDescription = "[" + name + "]\n" + "Key opens the door.";
        stackable = true;
    }

    @Override
    public boolean use(Entity entity) {
        gameManager.gameState = GameManager.DIALOGUE_STATE;

        int objIndex = getDetected(entity, gameManager.object, "door");
        if (objIndex != 999) {
            gameManager.ui.currentDialog = "You used the " + name + " and opened the door";
            gameManager.playSE(4);
            gameManager.object[gameManager.currentMap][objIndex] = null;
            return true;
        } else {
            gameManager.ui.currentDialog = "Uhh, where do i need to use it?";
            return false;
        }
    }

}
