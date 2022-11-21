package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GamePanel;

public class NPC_Woman3 extends Entity {

    public NPC_Woman3(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 2;
        getImage();
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
}
