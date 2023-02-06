//package main.java.com.khomsi.game.entity.mobs;
//
//import main.java.com.khomsi.game.entity.Entity;
//import main.java.com.khomsi.game.main.GameManager;
//import main.java.com.khomsi.game.objects.gui.HeartObject;
//import main.java.com.khomsi.game.objects.gui.ManaObject;
//import main.java.com.khomsi.game.objects.interact.CoinBObject;
//
//import java.util.Random;
//
//public class MobChestMimic extends Entity {
//    public MobChestMimic(GameManager gameManager) {
//        super(gameManager);
//        setDefaultValues();
//        getImage();
//    }
//
//TODO #48, add attack option, another case is movement(but use the same sprite)
//    private void setDefaultValues() {
//        name = "chest";
//        type = TYPE_MOB;
//        defaultSpeed = 0;
//        speed = defaultSpeed;
//        direction = "down";
//        //4 = 2 hearts
//        maxHp = 2;
//        hp = maxHp;
//        attack = 16;
//        defense = 0;
//        xp = 4;
//        //Boundaries
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidArea.width = 42;
//        solidArea.height = 30;
//        solidAreaDefaultX = solidArea.x;
//        solidAreaDefaultY = solidArea.y;
//    }
//
//    private void getImage() {
//        up = setup("/mobs/slimes/blue/slime_blue_up");
//        up1 = setup("/mobs/slimes/blue/slime_blue_up_1");
//        up2 = setup("/mobs/slimes/blue/slime_blue_up_2");
//        up3 = setup("/mobs/slimes/blue/slime_blue_up_3");
//
//        down = setup("/mobs/slimes/blue/slime_blue_down");
//        down1 = setup("/mobs/slimes/blue/slime_blue_down_1");
//        down2 = setup("/mobs/slimes/blue/slime_blue_down_2");
//        down3 = setup("/mobs/slimes/blue/slime_blue_down_3");
//
//        left = setup("/mobs/slimes/blue/slime_blue_down");
//        left1 = setup("/mobs/slimes/blue/slime_blue_down_1");
//        left2 = setup("/mobs/slimes/blue/slime_blue_down_2");
//        left3 = setup("/mobs/slimes/blue/slime_blue_down_3");
//
//        right = setup("/mobs/slimes/blue/slime_blue_down");
//        right1 = setup("/mobs/slimes/blue/slime_blue_down_1");
//        right2 = setup("/mobs/slimes/blue/slime_blue_down_2");
//        right3 = setup("/mobs/slimes/blue/slime_blue_down_3");
//    }
//
//    @Override
//    public void update() {
//        super.update();
//        //Use standing sprites with stand counter
//        standCounter++;
//        //timer before the idle anim starts
//        if (standCounter == 42) {
//            spriteNum = 0;  // Idle sprite
//            standCounter = 0;
//        }
//        int xDistance = Math.abs(worldX - gameManager.player.worldX);
//        int yDistance = Math.abs(worldY - gameManager.player.worldY);
//        int tileDistance = (xDistance + yDistance) / GameManager.TILE_SIZE;
//        if (!onPath && tileDistance < 2) {
//            onPath = true;
//        }
//        if (onPath && tileDistance > 2) {
//            onPath = false;
//        }
//    }
//
//    @Override
//    public void setAction() {
//        if (onPath) {
//            int goalCol = (gameManager.player.worldX + gameManager.player.solidArea.x) / GameManager.TILE_SIZE;
//            int goalRow = (gameManager.player.worldY + gameManager.player.solidArea.y) / GameManager.TILE_SIZE;
//            searchPath(goalCol, goalRow, false);
//        }
//    }
//
//    @Override
//    public void damageReaction() {
//        lockCounter = 0;
//        //Monster moves away
////        direction = gameManager.player.direction;
//        onPath = true;
//    }
//
//    @Override
//    public void checkDrop() {
//        int drop = new Random().nextInt(100) + 1;
//
//        //Set the mob's drop, 50% chance of coin, 30 of heart and mana
//        if (drop < 50) dropItem(new CoinBObject(gameManager));
//        if (drop >= 50 && drop < 80) dropItem(new HeartObject(gameManager));
//        if (drop >= 80 && drop < 100) dropItem(new ManaObject(gameManager));
//    }
//}
