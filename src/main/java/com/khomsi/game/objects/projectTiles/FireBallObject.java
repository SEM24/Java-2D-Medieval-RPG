package com.khomsi.game.objects.projectTiles;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.entity.ProjectTile;

import java.awt.*;

public class FireBallObject extends ProjectTile {
    public static final String OBJ_NAME = "FireBall";

    public FireBallObject(GameManager gameManager) {
        super(gameManager);
        name = OBJ_NAME;
        speed = 7;
        maxHp = 80;
        hp = maxHp;
        attack = 2;
        useCost = 1;
        // if the previous tile is still on the screen,
        // you can't shoot next one
        alive = false;
        itemDescription = "[" + name + "]\n" + "Fireball with\n" + attack + " attack.";
        getImage();
    }

    public void getImage() {
        up = setup("/projectiles/fireballs/blue/fireball_blue_up");
        up1 = setup("/projectiles/fireballs/blue/fireball_blue_up_1");
        up2 = setup("/projectiles/fireballs/blue/fireball_blue_up_2");
        up3 = setup("/projectiles/fireballs/blue/fireball_blue_up_3");

        down = setup("/projectiles/fireballs/blue/fireball_blue_down");
        down1 = setup("/projectiles/fireballs/blue/fireball_blue_down_1");
        down2 = setup("/projectiles/fireballs/blue/fireball_blue_down_2");
        down3 = setup("/projectiles/fireballs/blue/fireball_blue_down_3");

        left = setup("/projectiles/fireballs/blue/fireball_blue_left");
        left1 = setup("/projectiles/fireballs/blue/fireball_blue_left_1");
        left2 = setup("/projectiles/fireballs/blue/fireball_blue_left_2");
        left3 = setup("/projectiles/fireballs/blue/fireball_blue_left_3");

        right = setup("/projectiles/fireballs/blue/fireball_blue_right");
        right1 = setup("/projectiles/fireballs/blue/fireball_blue_right_1");
        right2 = setup("/projectiles/fireballs/blue/fireball_blue_right_2");
        right3 = setup("/projectiles/fireballs/blue/fireball_blue_right_3");
    }

    @Override
    public boolean haveResource(Entity entity) {
        return entity.mana >= useCost;
    }

    @Override
    public void subtractResource(Entity entity) {
        entity.mana -= useCost;
    }


    @Override
    public Color getParticleColor() {
        return new Color(76, 104, 133);
    }

    @Override
    public int getParticleSize() {
        int size = 8;
        return size; //8 pixels
    }

    @Override
    public int getParticleSpeed() {
        int speed = 1;
        return speed; //speed of particle
    }

    @Override
    public int getParticleMaxHp() {
        int maxHp = 20;
        return maxHp;
    }
}
