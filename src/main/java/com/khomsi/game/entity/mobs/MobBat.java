package com.khomsi.game.entity.mobs;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.gui.HeartObject;
import com.khomsi.game.objects.gui.ManaObject;
import com.khomsi.game.objects.interact.CoinBObject;

import java.util.Random;

public class MobBat extends Entity {
    public MobBat(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
    }

    private void setDefaultValues() {
        name = "Bat";
        type = TYPE_MOB;
        defaultSpeed = 4;
        speed = defaultSpeed;
        //4 = 2 hearts
        maxHp = 4;
        hp = maxHp;
        attack = 2;
        defense = 0;
        xp = 5;
        //Boundaries
        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    private void getImage() {
        up = setup("/mobs/bat/bat_left_1");
        up1 = setup("/mobs/bat/bat_left_2");
        up2 = setup("/mobs/bat/bat_left_1");
        up3 = setup("/mobs/bat/bat_left_2");

        down = setup("/mobs/bat/bat_left_1");
        down1 = setup("/mobs/bat/bat_left_2");
        down2 = setup("/mobs/bat/bat_left_1");
        down3 = setup("/mobs/bat/bat_left_2");

        left = setup("/mobs/bat/bat_left_1");
        left1 = setup("/mobs/bat/bat_left_2");
        left2 = setup("/mobs/bat/bat_left_1");
        left3 = setup("/mobs/bat/bat_left_2");

        right = setup("/mobs/bat/bat_left_1");
        right1 = setup("/mobs/bat/bat_left_2");
        right2 = setup("/mobs/bat/bat_left_1");
        right3 = setup("/mobs/bat/bat_left_2");
    }

    @Override
    public void setAction() {
        if (onPath) {

        } else {
            //Get random direction
            getRandomDirection(20);
        }
    }

    @Override
    public void damageReaction() {
        lockCounter = 0;
        //Monster moves away
        direction = gameManager.player.direction;
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
