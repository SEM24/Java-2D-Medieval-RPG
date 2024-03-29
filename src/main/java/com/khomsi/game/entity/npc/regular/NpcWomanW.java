package com.khomsi.game.entity.npc.regular;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

import java.awt.*;
import java.util.Random;

public class NpcWomanW extends Entity {

    public NpcWomanW(GameManager gameManager) {
        super(gameManager);
        direction = "down";
        speed = 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 31;
        solidArea.height = 32;
        dialogueSet = -1;

        getImage();
        setDialog();
    }

    @Override
    public void update() {
        super.update();
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
        dialogues[0][0] = "Hello young man!";
        dialogues[0][1] = "How are you today?\nIt's a nice weather, isn't it?";
        dialogues[0][2] = "Are you a adventure traveler?";
        dialogues[0][3] = "Well, I wish you good luck to\nfind something interesting!";
        dialogues[0][4] = "If you need something,\nI'll be happy to help you!";

        dialogues[1][0] = "You can save the game,\nif you through the coin into water.";
        dialogues[1][1] = "The tent or bed is good way to skip the night.";
        dialogues[1][2] = "Hey, be careful with chests!";

        dialogues[2][0] = "I'm wondering how to open the doors...";
    }

    //set npc movement
    public void setAction() {
        if (onPath) {
            int goalCol = 14;
            int goalRow = 28;
//            int goalCol = (gameManager.player.worldX + gameManager.player.solidArea.x) / GameManager.TILE_SIZE;
//            int goalRow = (gameManager.player.worldY + gameManager.player.solidArea.y) / GameManager.TILE_SIZE;
            searchPath(goalCol, goalRow, true);
        } else {
            lockCounter++;
            if (lockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
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

    //Maybe add special stuff, different custom text for this character
    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
//            dialogueSet--;
        }
        onPath = true;
    }
}
