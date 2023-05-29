package com.khomsi.game.entity.mobs;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.gui.ManaObject;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.objects.gui.HeartObject;

import java.util.Random;

public class MobOrc extends Entity {
    public MobOrc(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
        getAttackImage();
    }

    private void setDefaultValues() {
        name = "Orc";
        type = TYPE_MOB;
        defaultSpeed = 1;
        speed = defaultSpeed;
        direction = "down";
        //4 = 2 hearts
        maxHp = 10;
        hp = maxHp;
        attack = 8;
        defense = 2;
        xp = 9;
        knockBackPower = 5;
        //Boundaries
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1Duration = 40;
        motion2Duration = 85;
    }

    private void getImage() {
        up = setup("/mobs/orc/walk/orc_up_1");
        up1 = setup("/mobs/orc/walk/orc_up_1");
        up2 = setup("/mobs/orc/walk/orc_up_2");
        up3 = setup("/mobs/orc/walk/orc_up_2");

        down = setup("/mobs/orc/walk/orc_down_1");
        down1 = setup("/mobs/orc/walk/orc_down_1");
        down2 = setup("/mobs/orc/walk/orc_down_2");
        down3 = setup("/mobs/orc/walk/orc_down_2");

        left = setup("/mobs/orc/walk/orc_left_1");
        left1 = setup("/mobs/orc/walk/orc_left_1");
        left2 = setup("/mobs/orc/walk/orc_left_2");
        left3 = setup("/mobs/orc/walk/orc_left_2");

        right = setup("/mobs/orc/walk/orc_right_1");
        right1 = setup("/mobs/orc/walk/orc_right_1");
        right2 = setup("/mobs/orc/walk/orc_right_2");
        right3 = setup("/mobs/orc/walk/orc_right_2");
    }

    public void getAttackImage() {
        //Adjust image size due to not standard size
        int attackUpDownW = GameManager.TILE_SIZE;
        int attackUpDownH = GameManager.TILE_SIZE * 2;
        int attackLRW = GameManager.TILE_SIZE * 2;
        int attackLRH = GameManager.TILE_SIZE;
        attackUp = setup("/mobs/orc/attack/orc_attack_up_1", attackUpDownW, attackUpDownH);
        attackUp1 = setup("/mobs/orc/attack/orc_attack_up_1", attackUpDownW, attackUpDownH);
        attackUp2 = setup("/mobs/orc/attack/orc_attack_up_2", attackUpDownW, attackUpDownH);
        attackUp3 = setup("/mobs/orc/attack/orc_attack_up_2", attackUpDownW, attackUpDownH);

        attackDown = setup("/mobs/orc/attack/orc_attack_down_1", attackUpDownW, attackUpDownH);
        attackDown1 = setup("/mobs/orc/attack/orc_attack_down_1", attackUpDownW, attackUpDownH);
        attackDown2 = setup("/mobs/orc/attack/orc_attack_down_2", attackUpDownW, attackUpDownH);
        attackDown3 = setup("/mobs/orc/attack/orc_attack_down_2", attackUpDownW, attackUpDownH);

        attackLeft = setup("/mobs/orc/attack/orc_attack_left_1", attackLRW, attackLRH);
        attackLeft1 = setup("/mobs/orc/attack/orc_attack_left_1", attackLRW, attackLRH);
        attackLeft2 = setup("/mobs/orc/attack/orc_attack_left_2", attackLRW, attackLRH);
        attackLeft3 = setup("/mobs/orc/attack/orc_attack_left_2", attackLRW, attackLRH);

        attackRight = setup("/mobs/orc/attack/orc_attack_right_1", attackLRW, attackLRH);
        attackRight1 = setup("/mobs/orc/attack/orc_attack_right_1", attackLRW, attackLRH);
        attackRight2 = setup("/mobs/orc/attack/orc_attack_right_2", attackLRW, attackLRH);
        attackRight3 = setup("/mobs/orc/attack/orc_attack_right_2", attackLRW, attackLRH);
    }

    @Override
    public void setAction() {
        if (onPath) {
            //Check if it stops chasing
            checkStopChasing(gameManager.player, 10, 100);
            //Search the direction to go
            searchPath(getGoalCol(gameManager.player), getGoalRow(gameManager.player), false);
        } else {
            //Check if it starts chasing
            checkStartChasing(gameManager.player, 5, 100);
            //Get random direction
            getRandomDirection(120);
        }
        //Check if attacking
        if (!attacking) {
            checkAttacking(30, GameManager.TILE_SIZE * 4, GameManager.TILE_SIZE);
        }
    }

    @Override
    public void damageReaction() {
        lockCounter = 0;
        //Monster moves away
//        direction = gameManager.player.direction;
        onPath = true;
    }

    @Override
    public void checkDrop() {
        int drop = new Random().nextInt(100) + 1;

        //Set the mob's drop, 50% chance of coin, 30 of heart and mana
        if (drop < 50) dropItem(new CoinBObject(gameManager));
        if (drop >= 50 && drop < 80) dropItem(new HeartObject(gameManager));
        if (drop >= 80 && drop < 100) dropItem(new ManaObject(gameManager));
    }
}
