package main.java.com.khomsi.game.objects.outside;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class DungeonDoorClosedObject extends Entity {

    public static final String OBJ_NAME = "Dungeon Door Closed";

    public DungeonDoorClosedObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        type = TYPE_OBSTACLE;
        down = setup("/objects/dungeon/door_closed",
                GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        collision = true;
        solidArea.x = 0;
        solidArea.y = 32;
        solidArea.width = 86;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You can't just open it.";
    }

    @Override
    public void interact() {
        startDialogue(this, 0);
    }
}