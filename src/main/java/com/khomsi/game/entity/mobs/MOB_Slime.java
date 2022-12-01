package main.java.com.khomsi.game.entity.mobs;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.util.Random;

public class MOB_Slime extends Entity {
    public MOB_Slime(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
    }

    private void setDefaultValues() {
        name = "Slime";
        type = 2;
        speed = 1;
        direction = "down";
        //4 = 2 hearts
        maxHp = 4;
        hp = maxHp;
        //Boundaries
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {
        up = setup("/mobs/slime_down");
        up1 = setup("/mobs/slime_down_1");
        up2 = setup("/mobs/slime_down");
        up3 = setup("/mobs/slime_down_1");

        down = setup("/mobs/slime_down");
        down1 = setup("/mobs/slime_down_1");
        down2 = setup("/mobs/slime_down");
        down3 = setup("/mobs/slime_down_1");

        left = setup("/mobs/slime_down");
        left1 = setup("/mobs/slime_down_1");
        left2 = setup("/mobs/slime_down");
        left3 = setup("/mobs/slime_down_1");

        right = setup("/mobs/slime_down");
        right1 = setup("/mobs/slime_down_1");
        right2 = setup("/mobs/slime_down");
        right3 = setup("/mobs/slime_down_1");
    }

    public void setAction() {
        lockCounter++;
        if (lockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 30) {
                direction = "up";
            }
            if (i <= 30) {
                direction = "up";
            }
            if (i > 30 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            lockCounter = 0;
        }
    }
}
