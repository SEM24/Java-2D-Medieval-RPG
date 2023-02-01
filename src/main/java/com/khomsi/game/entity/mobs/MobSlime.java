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
        speed = 1;
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
    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gameManager.player.worldX);
        int yDistance = Math.abs(worldY - gameManager.player.worldY);
        int tileDistance = (xDistance + yDistance) / GameManager.TILE_SIZE;
        if (!onPath && tileDistance < 5) {
            //50% chance mob will be agro after you come to 5 tiles close
            int rand = new Random().nextInt(100) + 1;
            if (rand > 50) onPath = true;
        }
        if (onPath && tileDistance > 10) {
            onPath = false;
        }
    }

    @Override
    public void setAction() {
        if (onPath) {
            int goalCol = (gameManager.player.worldX + gameManager.player.solidArea.x) / GameManager.TILE_SIZE;
            int goalRow = (gameManager.player.worldY + gameManager.player.solidArea.y) / GameManager.TILE_SIZE;
            searchPath(goalCol, goalRow, false);

            int rand = new Random().nextInt(200) + 1;
            if (rand > 197 && !projectTile.alive && shootAvailableCounter == 30) {
                projectTile.set(worldX, worldY, direction, true, this);
                gameManager.projectilesList.add(projectTile);
                shootAvailableCounter = 0;
            }
        } else {
            lockCounter++;
            if (lockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i <= 30) {
                    direction = "up";
                }
                if (i <= 30) {
                    direction = "up";
                }
                if (i > 30 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                lockCounter = 0;
            }
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
