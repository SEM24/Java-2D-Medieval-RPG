package com.khomsi.game.entity.npc.beach;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;
import java.util.Random;

public class CrabEntity extends Entity {

    public CrabEntity(GameManager gameManager) {
        super(gameManager);
        direction = "up";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 31;
        solidArea.height = 32;
        dialogueSet = -1;
        setDialog();
    }

    @Override
    public void update() {
        super.update();
    }

    public void getImage(String basePath) {
        // Loop from 0 to 15 to set up the images
        for (int i = 0; i < 16; i++) {
            String filename = basePath + String.format("%02d", i);
            switch (i) {
                case 0 -> down = setup(filename);
                case 1 -> down1 = setup(filename);
                case 2 -> down2 = setup(filename);
                case 3 -> down3 = setup(filename);

                case 4 -> right = setup(filename);
                case 5 -> right1 = setup(filename);
                case 6 -> right2 = setup(filename);
                case 7 -> right3 = setup(filename);

                case 8 -> up = setup(filename);
                case 9 -> up1 = setup(filename);
                case 10 -> up2 = setup(filename);
                case 11 -> up3 = setup(filename);

                case 12 -> left = setup(filename);
                case 13 -> left1 = setup(filename);
                case 14 -> left2 = setup(filename);
                case 15 -> left3 = setup(filename);
            }
        }
    }


    private void setDialog() {
        dialogues[0][0] = "Scuttle-scuffle!";
        dialogues[1][0] = "(Don't touch me, little human!)";
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
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }
    }
}
