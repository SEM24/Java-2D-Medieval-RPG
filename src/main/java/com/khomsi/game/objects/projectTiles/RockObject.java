package main.java.com.khomsi.game.objects.projectTiles;

import main.java.com.khomsi.game.entity.ProjectTile;
import main.java.com.khomsi.game.main.GameManager;

public class RockObject extends ProjectTile {

    public RockObject(GameManager gameManager) {
        super(gameManager);
        name = "Rock";
        speed = 8;
        maxHp = 80;
        hp = maxHp;
        //TODO bug when attack gives not 2 damage, but 0, so need to multiply the num to 2
        attack = 2 * 2;
        useCost = 1;
        // if the previous tile is still on the screen,
        // you can't shoot next one
        alive = false;
        getImage();
    }

    public void getImage() {
        up = setup("/projectiles/rock_down_1");
        up1 = setup("/projectiles/rock_down_1");
        up2 = setup("/projectiles/rock_down_1");
        up3 = setup("/projectiles/rock_down_1");

        down = setup("/projectiles/rock_down_1");
        down1 = setup("/projectiles/rock_down_1");
        down2 = setup("/projectiles/rock_down_1");
        down3 = setup("/projectiles/rock_down_1");

        left = setup("/projectiles/rock_down_1");
        left1 = setup("/projectiles/rock_down_1");
        left2 = setup("/projectiles/rock_down_1");
        left3 = setup("/projectiles/rock_down_1");

        right = setup("/projectiles/rock_down_1");
        right1 = setup("/projectiles/rock_down_1");
        right2 = setup("/projectiles/rock_down_1");
        right3 = setup("/projectiles/rock_down_1");
    }
}
