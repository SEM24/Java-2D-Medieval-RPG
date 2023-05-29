package com.khomsi.game.entity.mobs;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.gui.ManaObject;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.objects.gui.HeartObject;

import java.util.Random;

public class MobDungeonBoss extends Entity {
    public static final String MOB_NAME = "Dungeon Boss";
    private static final int MULTIPLIER = 5;

    public MobDungeonBoss(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
        getAttackImage();
    }

    private void setDefaultValues() {
        name = MOB_NAME;
        type = TYPE_MOB;
        defaultSpeed = 1;
        speed = defaultSpeed;
        isBoss = true;
        //4 = 2 hearts
        maxHp = 50;
        hp = maxHp;
        attack = 10;
        defense = 2;
        xp = 50;
        knockBackPower = 5;

        int size = GameManager.TILE_SIZE * MULTIPLIER;
        //Boundaries
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - GameManager.TILE_SIZE * 2;
        solidArea.height = size - GameManager.TILE_SIZE;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 170;
        attackArea.height = 170;
        motion1Duration = 25;
        motion2Duration = 50;
    }

    private void getImage() {
        if (!inRage) {
            up = setup("/mobs/boss1/phase1/walk/skeletonlord_up_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up1 = setup("/mobs/boss1/phase1/walk/skeletonlord_up_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up2 = setup("/mobs/boss1/phase1/walk/skeletonlord_up_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up3 = setup("/mobs/boss1/phase1/walk/skeletonlord_up_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            down = setup("/mobs/boss1/phase1/walk/skeletonlord_down_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down1 = setup("/mobs/boss1/phase1/walk/skeletonlord_down_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down2 = setup("/mobs/boss1/phase1/walk/skeletonlord_down_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down3 = setup("/mobs/boss1/phase1/walk/skeletonlord_down_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            left = setup("/mobs/boss1/phase1/walk/skeletonlord_left_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left1 = setup("/mobs/boss1/phase1/walk/skeletonlord_left_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left2 = setup("/mobs/boss1/phase1/walk/skeletonlord_left_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left3 = setup("/mobs/boss1/phase1/walk/skeletonlord_left_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            right = setup("/mobs/boss1/phase1/walk/skeletonlord_right_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right1 = setup("/mobs/boss1/phase1/walk/skeletonlord_right_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right2 = setup("/mobs/boss1/phase1/walk/skeletonlord_right_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right3 = setup("/mobs/boss1/phase1/walk/skeletonlord_right_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
        }
        if (inRage) {
            up = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_up_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up1 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_up_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up2 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_up_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            up3 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_up_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            down = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_down_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down1 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_down_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down2 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_down_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            down3 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_down_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            left = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_left_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left1 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_left_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left2 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_left_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            left3 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_left_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);

            right = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_right_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right1 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_right_1",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right2 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_right_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
            right3 = setup("/mobs/boss1/phase2/walk/skeletonlord_phase2_right_2",
                    GameManager.TILE_SIZE * MULTIPLIER, GameManager.TILE_SIZE * MULTIPLIER);
        }
    }

    public void getAttackImage() {
        //Adjust image size due to not standard size
        int attackUpDownW = GameManager.TILE_SIZE * MULTIPLIER;
        int attackUpDownH = GameManager.TILE_SIZE * MULTIPLIER * 2;
        int attackLRW = GameManager.TILE_SIZE * MULTIPLIER * 2;
        int attackLRH = GameManager.TILE_SIZE * MULTIPLIER;
        if (!inRage) {
            attackUp = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_up_1", attackUpDownW, attackUpDownH);
            attackUp1 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_up_1", attackUpDownW, attackUpDownH);
            attackUp2 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_up_2", attackUpDownW, attackUpDownH);
            attackUp3 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_up_2", attackUpDownW, attackUpDownH);

            attackDown = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_down_1", attackUpDownW, attackUpDownH);
            attackDown1 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_down_1", attackUpDownW, attackUpDownH);
            attackDown2 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_down_2", attackUpDownW, attackUpDownH);
            attackDown3 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_down_2", attackUpDownW, attackUpDownH);

            attackLeft = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_left_1", attackLRW, attackLRH);
            attackLeft1 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_left_1", attackLRW, attackLRH);
            attackLeft2 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_left_2", attackLRW, attackLRH);
            attackLeft3 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_left_2", attackLRW, attackLRH);

            attackRight = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_right_1", attackLRW, attackLRH);
            attackRight1 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_right_1", attackLRW, attackLRH);
            attackRight2 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_right_2", attackLRW, attackLRH);
            attackRight3 = setup("/mobs/boss1/phase1/attack/skeletonlord_attack_right_2", attackLRW, attackLRH);
        }
        if (inRage) {
            attackUp = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_up_1", attackUpDownW, attackUpDownH);
            attackUp1 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_up_1", attackUpDownW, attackUpDownH);
            attackUp2 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_up_2", attackUpDownW, attackUpDownH);
            attackUp3 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_up_2", attackUpDownW, attackUpDownH);

            attackDown = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_down_1", attackUpDownW, attackUpDownH);
            attackDown1 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_down_1", attackUpDownW, attackUpDownH);
            attackDown2 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_down_2", attackUpDownW, attackUpDownH);
            attackDown3 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_down_2", attackUpDownW, attackUpDownH);

            attackLeft = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_left_1", attackLRW, attackLRH);
            attackLeft1 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_left_1", attackLRW, attackLRH);
            attackLeft2 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_left_2", attackLRW, attackLRH);
            attackLeft3 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_left_2", attackLRW, attackLRH);

            attackRight = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_right_1", attackLRW, attackLRH);
            attackRight1 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_right_1", attackLRW, attackLRH);
            attackRight2 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_right_2", attackLRW, attackLRH);
            attackRight3 = setup("/mobs/boss1/phase2/attack/skeletonlord_phase2_attack_right_2", attackLRW, attackLRH);
        }
    }

    @Override
    public void setAction() {
        //TODO change stats
        if (!inRage && hp < maxHp / 2) {
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
        //Distance to player
        if (getTileDistance(gameManager.player) < 10) {
            //every 60 frame check player's position
            moveTowardPlayer(60);
        } else {
            //Get random direction
            getRandomDirection(120);
        }
        //Check if attacking
        if (!attacking) {
            checkAttacking(60, GameManager.TILE_SIZE * 7, GameManager.TILE_SIZE * MULTIPLIER);
        }
    }

    @Override
    public void damageReaction() {
        lockCounter = 0;
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
