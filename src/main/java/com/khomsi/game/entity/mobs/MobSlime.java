package main.java.com.khomsi.game.entity.mobs;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.gui.HeartObject;
import main.java.com.khomsi.game.objects.gui.ManaObject;
import main.java.com.khomsi.game.objects.interact.CoinBObject;
import main.java.com.khomsi.game.objects.projectTiles.RockObject;

import java.util.Random;

public class MobSlime extends Entity {
    public MobSlime(GameManager gameManager) {
        super(gameManager);
        setDefaultValues();
        getImage();
    }

    private void setDefaultValues() {
        name = "Slime";
        type = TYPE_MOB;
        defaultSpeed = 1;
        speed = defaultSpeed;
        direction = "down";
        //4 = 2 hearts
        maxHp = 4;
        hp = maxHp;
        attack = 5;
        defense = 0;
        xp = 2;
        projectTile = new RockObject(gameManager);
        //Boundaries
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    private void getImage() {
        up = setup("/mobs/slime_down");
        up1 = setup("/mobs/slime_down_1");
        up2 = setup("/mobs/slime_down");
        up3 = setup("/mobs/slime_down_1");

        down = setup("/mobs/slime_down");
        down1 = setup("/mobs/slime_down_1");
        down2 = setup("/mobs/slime_down");
        down3 = setup("/mobs/slime_down_1");

        left = setup("/mobs/slime_down");
        left1 = setup("/mobs/slime_down_1");
        left2 = setup("/mobs/slime_down");
        left3 = setup("/mobs/slime_down_1");

        right = setup("/mobs/slime_down");
        right1 = setup("/mobs/slime_down_1");
        right2 = setup("/mobs/slime_down");
        right3 = setup("/mobs/slime_down_1");
    }

    @Override
    public void setAction() {
        if (onPath) {
            //Check if it stops chasing
            checkStopChasing(gameManager.player, 10, 100);
            //Search the direction to go
            searchPath(getGoalCol(gameManager.player), getGoalRow(gameManager.player), false);
            //Check if it shoots the projTiles
            checkShoot(200, 30);
        } else {
            //Check if it starts chasing
            checkStartChasing(gameManager.player, 5, 100);
            //Get random direction
            getRandomDirection(120);
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
