package com.khomsi.game.entity.mobs;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import java.awt.*;
import java.util.Random;

public class MobMushroom extends Entity {
    public MobMushroom(GameManager gameManager, Color color) {
        super(gameManager);
        setDefaultValues();
        getImage(color);
        getAttackImage(color);
    }

    private void setDefaultValues() {
        name = "Mushroom";
        type = TYPE_MOB;
        defaultSpeed = 1;
        speed = defaultSpeed;
        direction = getRandomDirection();
        maxHp = 1;
        hp = maxHp;
        attack = 6;
        defense = 1;
        xp = 2;

        //Boundaries
        solidArea.x = 8;
        solidArea.y = 16;
        //boundaries of npc
        solidArea.width = 31;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 44;
        attackArea.height = 44;
        motion1Duration = 15;
        motion2Duration = 20;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        draw(graphics2D, false);
    }

    private String getColorPath(Color color) {
        String basePath = "/mobs/mushroom/";
        switch (color) {
//            case RED -> basePath += "red/crab_red_";
            case BROWN -> basePath += "brown/mushroom_brown_";
//            case BLUE -> basePath += "blue/crab_blue_";
            default -> throw new IllegalArgumentException("Invalid color: " + color);
        }
        return basePath;
    }

    public void getImage(Color color) {
        String basePath = getColorPath(color);
        // Loop from 0 to 15 to set up the images
        for (int i = 0; i < 16; i++) {
            String filename = basePath + String.format("%02d", i);
            switch (i) {
                case 0 -> down = setup(filename);
                case 1 -> down1 = setup(filename);
                case 2 -> down2 = setup(filename);
                case 3 -> down3 = setup(filename);

                case 4 -> left = setup(filename);
                case 5 -> left1 = setup(filename);
                case 6 -> left2 = setup(filename);
                case 7 -> left3 = setup(filename);

                case 8 -> up = setup(filename);
                case 9 -> up1 = setup(filename);
                case 10 -> up2 = setup(filename);
                case 11 -> up3 = setup(filename);

                case 12 -> right = setup(filename);
                case 13 -> right1 = setup(filename);
                case 14 -> right2 = setup(filename);
                case 15 -> right3 = setup(filename);
            }
        }
    }

    public void getAttackImage(Color color) {
        String basePath = getColorPath(color);
        // Loop to set up the images
        for (int i = 16; i < 32; i++) {
            String filename = basePath + String.format("%02d", i);
            switch (i) {
                case 16 -> attackDown = setup(filename);
                case 17 -> attackDown1 = setup(filename);
                case 18 -> attackDown2 = setup(filename);
                case 19 -> attackDown3 = setup(filename);

                case 20 -> attackLeft = setup(filename);
                case 21 -> attackLeft1 = setup(filename);
                case 22 -> attackLeft2 = setup(filename);
                case 23 -> attackLeft3 = setup(filename);

                case 24 -> attackUp = setup(filename);
                case 25 -> attackUp1 = setup(filename);
                case 26 -> attackUp2 = setup(filename);
                case 27 -> attackUp3 = setup(filename);

                case 28 -> attackRight = setup(filename);
                case 29 -> attackRight1 = setup(filename);
                case 30 -> attackRight2 = setup(filename);
                case 31 -> attackRight3 = setup(filename);
            }
        }
    }

    @Override
    public void setAction() {
        if (onPath) {
            //Check if it stops chasing
            checkStopChasing(gameManager.player, 3, 70);
            //Search the direction to go
            searchPath(getGoalCol(gameManager.player), getGoalRow(gameManager.player), false);
        } else {
            //Check if it starts chasing
            checkStartChasing(gameManager.player, 3, 70);
            //Get random direction
            getRandomDirection(120);
        }
        //Check if attacking
        if (!attacking) {
            checkAttacking(40, GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2);
        }
    }

    @Override
    public void damageReaction() {
        lockCounter = 0;
        int chance = random.nextInt(100) + 1;
        if (chance <= 50)
            onPath = true;
        else
            //Monster runs away
            direction = gameManager.player.direction;
    }
}
