package main.java.com.khomsi.game.entity.npc.regular;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;
import java.util.Random;

public class NpcWomanW extends Entity {

    public NpcWomanW(GameManager gameManager) {
        super(gameManager);
        direction = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 31;
        solidArea.height = 32;

        getImage();
        setDialog();
    }

    public void getImage() {
        up = setup("/npc/woman3_up");
        up1 = setup("/npc/woman3_up_1");
        up2 = setup("/npc/woman3_up_2");
        up3 = setup("/npc/woman3_up_3");
        down = setup("/npc/woman3_down");
        down1 = setup("/npc/woman3_down_1");
        down2 = setup("/npc/woman3_down_2");
        down3 = setup("/npc/woman3_down_3");
        left = setup("/npc/woman3_left");
        left1 = setup("/npc/woman3_left_1");
        left2 = setup("/npc/woman3_left_2");
        left3 = setup("/npc/woman3_left_3");
        right = setup("/npc/woman3_right");
        right1 = setup("/npc/woman3_right_1");
        right2 = setup("/npc/woman3_right_2");
        right3 = setup("/npc/woman3_right_3");
    }

    private void setDialog() {
        dialogues[0] = "Hello young man!";
        dialogues[1] = "How are you today?\nIt's a nice weather, isn't it?";
        dialogues[2] = "Are you a adventure traveler?";
        dialogues[3] = "Well, I wish you good luck to\nfind something interesting!";
        dialogues[4] = "If you need something,\nI'll be happy to help you!";
    }

    //set npc movement
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

    //Maybe add special stuff, different custom text for this character
    public void speak() {
        super.speak();
    }
}
