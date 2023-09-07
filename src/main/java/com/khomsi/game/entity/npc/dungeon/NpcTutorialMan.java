package com.khomsi.game.entity.npc.dungeon;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;

public class NpcTutorialMan extends Entity {

    public NpcTutorialMan(GameManager gameManager) {
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
        dialogueSet = -1;

        getImage();
        setDialog();
    }

    @Override
    public void update() {
        if (onPath)
            super.update();
        else {
            direction = "down";
            spriteNum = 0;
        }
    }

    public void getImage() {
        down = setup("/npc/tutorial/old_man/npc_old_man_00");
        down1 = setup("/npc/tutorial/old_man/npc_old_man_01");
        down2 = setup("/npc/tutorial/old_man/npc_old_man_02");
        down3 = setup("/npc/tutorial/old_man/npc_old_man_03");

        right = setup("/npc/tutorial/old_man/npc_old_man_04");
        right1 = setup("/npc/tutorial/old_man/npc_old_man_05");
        right2 = setup("/npc/tutorial/old_man/npc_old_man_06");
        right3 = setup("/npc/tutorial/old_man/npc_old_man_07");

        up = setup("/npc/tutorial/old_man/npc_old_man_08");
        up1 = setup("/npc/tutorial/old_man/npc_old_man_09");
        up2 = setup("/npc/tutorial/old_man/npc_old_man_10");
        up3 = setup("/npc/tutorial/old_man/npc_old_man_11");

        left = setup("/npc/tutorial/old_man/npc_old_man_12");
        left1 = setup("/npc/tutorial/old_man/npc_old_man_13");
        left2 = setup("/npc/tutorial/old_man/npc_old_man_14");
        left3 = setup("/npc/tutorial/old_man/npc_old_man_15");

    }

    private void setDialog() {
        dialogues[0][0] = "So you are finally here?";
        dialogues[0][1] = "Happy to see you again.\nYou probably don't remember me...";
        dialogues[0][2] = "But I remember you.";
        dialogues[0][3] = "But don't bother your head with this now.";
        dialogues[0][4] = "Grab this sword and go outside\nYou will need to find your shield now!";
        dialogues[1][0] = "Are you still here?\n There's nothing to do here, go outside!";
    }

    //TODO the npc doesn't save the position on which he moved after loading the game + the text appears again.
    // Somehow needs to be fixed or just not to move the npc

    //TODO maybe it's better to remove the npc when player speaks to him by moving him to exit and use flag to indicate
    // in NpcPlacement with that flag that the player had already spoken to him (and store the flag in SaveLoad class)
    //set npc movement
    public void setAction() {
        if (onPath) {
            int goalCol = 60;
            int goalRow = 73;
            searchPath(goalCol, goalRow, true);
        }
    }

    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
        if (!hasSpoken) {
            hasSpoken = true;
            onPath = true;
        }
    }
}
