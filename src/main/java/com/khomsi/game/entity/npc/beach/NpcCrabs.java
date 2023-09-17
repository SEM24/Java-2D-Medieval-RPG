package com.khomsi.game.entity.npc.beach;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;

public class NpcCrabs extends Entity {

    public NpcCrabs(GameManager gameManager, Color color) {
        super(gameManager);
        direction = getRandomDirection();
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
        getImage(color);
    }

    @Override
    public void update() {
        super.update();
    }

    public void getImage(Color color) {
        String basePath = "/npc/beach/crab/";
        switch (color) {
            case RED -> basePath += "red/crab_red_";
            case YELLOW -> basePath += "yellow/crab_yellow_";
            case BLUE -> basePath += "blue/crab_blue_";
            default -> throw new IllegalArgumentException("Invalid color: " + color);
        }
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

    //Set random npc movement
    public void setAction() {
        lockCounter++;
        if (lockCounter == 120) {
            int i = random.nextInt(100) + 1;
            if (i <= 30) {
                direction = directions[0];
            }
            if (i > 30 && i <= 50) {
                direction = directions[1];
            }
            if (i > 50 && i <= 75) {
                direction = directions[2];
            }
            if (i > 75) {
                direction = directions[3];
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
