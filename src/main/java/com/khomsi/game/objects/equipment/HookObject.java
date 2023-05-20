package main.java.com.khomsi.game.objects.equipment;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

public class HookObject extends Entity {
    public static final String OBJ_NAME = "Hook";

    public HookObject(GameManager gameManager) {
        super(gameManager);
        type = TYPE_HOOK;
        name = OBJ_NAME;
        down = setup("/objects/equipment/hook");
        attackValue = 1;
        attackArea.width = 30;
        attackArea.height = 30;
        itemDescription = "[" + name + "]\n" + "You can break the walls \nwith it.";
        price = 100;
        knockBackPower = 10;
        motion1Duration = 5;
        motion2Duration = 7;
    }
}
