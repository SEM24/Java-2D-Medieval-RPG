package com.khomsi.game.entity.mobs;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class MobChestMimic extends Entity {
    public MobChestMimic(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
        getAttackImage();
    }

    private void setDefaultValues() {
        name = "ChestMimic";
        type = TYPE_MOB;
        defaultSpeed = 0;
        speed = defaultSpeed;
        direction = "down";
        //4 = 2 hearts
        maxHp = 2;
        hp = maxHp;
        attack = 10;
        defense = 0;
        xp = 10;
        //Boundaries
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 20;
        solidArea.height = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 40;
        attackArea.height = 40;
        motion1Duration = 15;
        motion2Duration = 20;
    }

    private void getImage() {
        up = setup("/mobs/mimics/mimic_chest_down");
        up1 = setup("/mobs/mimics/mimic_chest_down");
        up2 = setup("/mobs/mimics/mimic_chest_down");
        up3 = setup("/mobs/mimics/mimic_chest_down");

        down = setup("/mobs/mimics/mimic_chest_down");
        down1 = setup("/mobs/mimics/mimic_chest_down");
        down2 = setup("/mobs/mimics/mimic_chest_down");
        down3 = setup("/mobs/mimics/mimic_chest_down");

        left = setup("/mobs/mimics/mimic_chest_down");
        left1 = setup("/mobs/mimics/mimic_chest_down");
        left2 = setup("/mobs/mimics/mimic_chest_down");
        left3 = setup("/mobs/mimics/mimic_chest_down");

        right = setup("/mobs/mimics/mimic_chest_down");
        right1 = setup("/mobs/mimics/mimic_chest_down");
        right2 = setup("/mobs/mimics/mimic_chest_down");
        right3 = setup("/mobs/mimics/mimic_chest_down");
    }

    public void getAttackImage() {
        attackUp = setup("/mobs/mimics/attack/mimic_chest_attack_down");
        attackUp1 = setup("/mobs/mimics/attack/mimic_chest_attack_down_1");
        attackUp2 = setup("/mobs/mimics/attack/mimic_chest_attack_down_2");
        attackUp3 = setup("/mobs/mimics/attack/mimic_chest_attack_down_3");

        attackDown = setup("/mobs/mimics/attack/mimic_chest_attack_down");
        attackDown1 = setup("/mobs/mimics/attack/mimic_chest_attack_down_1");
        attackDown2 = setup("/mobs/mimics/attack/mimic_chest_attack_down_2");
        attackDown3 = setup("/mobs/mimics/attack/mimic_chest_attack_down_3");

        attackLeft = setup("/mobs/mimics/attack/mimic_chest_attack_down");
        attackLeft1 = setup("/mobs/mimics/attack/mimic_chest_attack_down_1");
        attackLeft2 = setup("/mobs/mimics/attack/mimic_chest_attack_down_2");
        attackLeft3 = setup("/mobs/mimics/attack/mimic_chest_attack_down_3");

        attackRight = setup("/mobs/mimics/attack/mimic_chest_attack_down");
        attackRight1 = setup("/mobs/mimics/attack/mimic_chest_attack_down_1");
        attackRight2 = setup("/mobs/mimics/attack/mimic_chest_attack_down_2");
        attackRight3 = setup("/mobs/mimics/attack/mimic_chest_attack_down_3");
    }

    @Override
    public void setAction() {
        if (onPath) {
            //Check if it stops chasing
            checkStopChasing(gameManager.player, 1, 100);
            //Search the direction to go
            searchPath(getGoalCol(gameManager.player), getGoalRow(gameManager.player), false);
        } else {
            //Check if it starts chasing
            checkStartChasing(gameManager.player, 1, 100);
        }
        //Check if attacking
        if (!attacking) {
            checkAttacking(20, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
        }
    }

    @Override
    public void damageReaction() {
        lockCounter = 0;
        //Monster moves away
//        direction = gameManager.player.direction;
        onPath = true;
    }
}
