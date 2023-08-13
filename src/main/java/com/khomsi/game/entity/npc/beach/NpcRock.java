package com.khomsi.game.entity.npc.beach;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;

public class NpcRock extends Entity {
    public static final String NPC_NAME = "Rock";

    public NpcRock(GameManager gameManager) {
        super(gameManager);
        direction = "down";
        speed = 3;
        name = NPC_NAME;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 44;
        solidArea.height = 40;
        dialogueSet = -1;

        getImage();
        setDialog();
    }

    @Override
    public void update() {
    }

    public void getImage() {
        up = setup("/objects/Rock");
        up1 = setup("/objects/Rock");
        up2 = setup("/objects/Rock");
        up3 = setup("/objects/Rock");
        down = setup("/objects/Rock");
        down1 = setup("/objects/Rock");
        down2 = setup("/objects/Rock");
        down3 = setup("/objects/Rock");
        left = setup("/objects/Rock");
        left1 = setup("/objects/Rock");
        left2 = setup("/objects/Rock");
        left3 = setup("/objects/Rock");
        right = setup("/objects/Rock");
        right1 = setup("/objects/Rock");
        right2 = setup("/objects/Rock");
        right3 = setup("/objects/Rock");
    }


    private void setDialog() {
        dialogues[0][0] = "I'm just a rock.\nWhat do you want from me?";

        dialogues[1][0] = "Uhh, how do I supposed to move it?\nIt's better come back later...";
    }

    @Override
    public void setAction() {

    }

    @Override
    public void speak() {
        facePlayer();
        startDialogue(this, 0);
    }

    @Override
    public void moveObj(String direction) {
        this.direction = direction;
        int itemIndex = gameManager.player.searchItemInventory("Magic Necklace");
        if (itemIndex != 999) {
            changeDirection(direction);
        } else {
            startDialogue(this, 1);
        }
    }
}
