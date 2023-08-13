package com.khomsi.game.objects.interact;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class MagicNecklaceObject extends Entity {
    public static final String OBJ_NAME = "Magic Necklace";

    public MagicNecklaceObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_CONSUMABLE;
        name = OBJ_NAME;
        down = setup("/objects/special_necklace");
        itemDescription = "[" + name + "]\n" + "Does something magic.";
        price = 500;
        stackable = false;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "Try to move the rocks with this " + name + ".";
    }

    @Override
    public boolean use(Entity entity) {
        startDialogue(this, 0);
        return false;
    }
}
