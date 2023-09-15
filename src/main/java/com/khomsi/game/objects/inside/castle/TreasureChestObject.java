package com.khomsi.game.objects.inside.castle;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.interact.ChestObject;

public class TreasureChestObject extends ChestObject {
    public static final String OBJ_NAME = "Treasure Chest";

    public TreasureChestObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_OBSTACLE;
        name = OBJ_NAME;
        image = setup("/objects/castle/chest_red/BigTreasurechest_01", GameManager.TILE_SIZE * 2,
                GameManager.TILE_SIZE * 2);

        image2 = setup("/objects/castle/chest_red/BigTreasurechest_04",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        down = image;
        collision = true;
        solidArea.x = 4 * 2;
        solidArea.y = 16 * 2;
        solidArea.width = 40 * 2;
        solidArea.height = 32 * 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void setDialogue() {
        dialogues[0][0] = "You opened the treasure chest and found a\n" + loot.name + "!" +
                "\n...But you can't carry any more!";
        dialogues[1][0] = "You opened the treasure chest and found a\n" + loot.name + "!" +
                "\nYou obtain the " + loot.name + "!";
        dialogues[2][0] = "This treasure chest is empty!";
    }
}
