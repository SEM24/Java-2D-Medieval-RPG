package main.java.com.khomsi.game.objects.projectTiles;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.entity.ProjectTile;
import main.java.com.khomsi.game.main.GameManager;

public class MagicArrowObject extends ProjectTile {
    public MagicArrowObject(GameManager gameManager) {
        super(gameManager);
        name = "Blue Magic Arrow";
        speed = 7;
        maxHp = 90;
        hp = maxHp;
        attack = 3;
        useCost = 2;
        alive = false;
        itemDescription = "[" + name + "]\n" + "Magic arrow with\n" + attack + " attack.";
        getImage();
    }

    public void getImage() {
        up = setup("/projectiles/arrows/blue/up_arrow_blue_1");
        up1 = setup("/projectiles/arrows/blue/up_arrow_blue_2");
        up2 = setup("/projectiles/arrows/blue/up_arrow_blue_3");
        up3 = setup("/projectiles/arrows/blue/up_arrow_blue_4");

        down = setup("/projectiles/arrows/blue/down_arrow_blue_1");
        down1 = setup("/projectiles/arrows/blue/down_arrow_blue_2");
        down2 = setup("/projectiles/arrows/blue/down_arrow_blue_3");
        down3 = setup("/projectiles/arrows/blue/down_arrow_blue_4");

        left = setup("/projectiles/arrows/blue/left_arrow_blue_1");
        left1 = setup("/projectiles/arrows/blue/left_arrow_blue_2");
        left2 = setup("/projectiles/arrows/blue/left_arrow_blue_3");
        left3 = setup("/projectiles/arrows/blue/left_arrow_blue_4");

        right = setup("/projectiles/arrows/blue/right_arrow_blue_1");
        right1 = setup("/projectiles/arrows/blue/right_arrow_blue_2");
        right2 = setup("/projectiles/arrows/blue/right_arrow_blue_3");
        right3 = setup("/projectiles/arrows/blue/right_arrow_blue_4");
    }

    //TODO #30 use this method when the bow shooting will be added
    @Override
    public boolean haveResource(Entity entity) {
        return entity.ammo >= useCost;
    }

    @Override
    public void subtractResource(Entity entity) {
        entity.ammo -= useCost;
    }
}
