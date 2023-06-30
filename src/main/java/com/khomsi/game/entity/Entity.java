package com.khomsi.game.entity;

import com.khomsi.game.entity.player.Player;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.main.tools.Tools;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.objects.spells.HeartObject;
import com.khomsi.game.objects.spells.ManaObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//parent class for player, monster etc
public class Entity extends Tools {
    //IMAGES
    public BufferedImage image, image2, image3;
    //Store our walking images in this variables
    public BufferedImage up, up1, up2, up3, down, down1, down2, down3,
            left, left1, left2, left3, right, right1, right2, right3;
    //Store attack images
    public BufferedImage attackUp, attackUp1, attackUp2, attackUp3, attackDown, attackDown1,
            attackDown2, attackDown3, attackLeft, attackLeft1, attackLeft2, attackLeft3,
            attackRight, attackRight1, attackRight2, attackRight3;
    public BufferedImage guardUp, guardDown, guardLeft, guardRight;

    //COUNTERS
    public int lockCounter = 0;
    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 0;
    public int invincibleCounter = 0;
    int dieCounter = 0;
    public int hpBarCounter = 0;
    public int shootAvailableCounter = 0;

    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    //State
    public String direction = "down";
    public boolean collisionOn = false;
    public boolean collision = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean die = false;
    public boolean fallIntoPit = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;

    public boolean knockBack = false;
    public boolean guarding = false;
    public boolean transparent = false;
    public boolean offBalance = false;
    public boolean markered = false;
    public boolean opened = false;
    public boolean bossSleep = false;
    public boolean drawing = true;
    public Entity loot;


    //Entity stats
    //Set default position
    public String name;
    public int worldX, worldY;
    public int speed;
    public int maxHp;
    public int hp;
    public int level;
    public int maxMana;
    public int defaultSpeed;
    public int mana;
    public int strength;
    public int ammo;
    public int agility;
    public int attack;
    public int defense;
    public int xp;
    public int nextLevelXp;
    public int coin;
    public int motion1Duration;
    public int motion2Duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;

    //Item attributes
    public int value;
    public int attackValue;
    public int defenseValue;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public boolean inRage = false;
    public boolean isBoss;
    public int amount = 1;
    public int lightRadius;
    public String itemDescription = "";
    //Cost of shooting the tile
    public int useCost;
    //0 = player, 1 = npc, 2 = mob
    public int type;
    public static final int TYPE_PLAYER = 0;
    public static final int TYPE_NPC = 1;
    public static final int TYPE_MOB = 2;
    public static final int TYPE_SWORD = 3;
    public static final int TYPE_AXE = 4;
    public static final int TYPE_SHIELD = 5;
    public static final int TYPE_CONSUMABLE = 6;
    public static final int TYPE_PICK_UP_ONLY = 7;
    public static final int TYPE_OBSTACLE = 8;
    public static final int TYPE_LIGHT = 9;
    public static final int TYPE_HOOK = 10;

    //TOOLS
    public int dialogueIndex = 0;
    public int dialogueSet = 0;
    public String[][] dialogues = new String[20][20];

    //Default values for every entity
    public List<Entity> inventory = new ArrayList<>();
    public int maxInventorySize = 24;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public GameManager gameManager;
    public ProjectTile projectTile;
    public Entity attacker;
    public String knockBackDirection;
    public Entity linkedEntity;

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public void dropItem(Entity droppedItem) {
        //Scan array and set the item
        for (int i = 0; i < gameManager.object[1].length; i++) {
            if (gameManager.object[gameManager.currentMap][i] == null) {
                gameManager.object[gameManager.currentMap][i] = droppedItem;
                //Coord of died mob
                gameManager.object[gameManager.currentMap][i].worldX = worldX;
                gameManager.object[gameManager.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void facePlayer() {
        switch (gameManager.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gameManager.gameState = GameManager.DIALOGUE_STATE;
        gameManager.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void checkCollision() {
        collisionOn = false;
        gameManager.checkCollision.checkTile(this);
        gameManager.checkCollision.checkObject(this, false);
        gameManager.checkCollision.checkEntity(this, gameManager.npcList);
        gameManager.checkCollision.checkEntity(this, gameManager.mobs);
        gameManager.checkCollision.checkEntity(this, gameManager.interactTile);
        boolean interactPlayer = gameManager.checkCollision.checkPlayer(this);
        //If it's monster, and it touches the player, receive the dmg
        if (this.type == TYPE_MOB && interactPlayer) {
            damagePlayer(attack);
        }
    }

    public void update() {
        if (!bossSleep) {
            if (knockBack) {
                checkCollision();
                collisionAndKnockBack();
            } else if (attacking) {
                entityAttack();
            } else {
                setAction();
                changeDirection(direction);
                spriteMovement();
            }
            if (invincible) {
                invincibleCounter++;
                //1 sec
                if (invincibleCounter > 40) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if (shootAvailableCounter < 30) {
                shootAvailableCounter++;
            }
            if (offBalance) {
                offBalanceCounter++;
                if (offBalanceCounter > 60) {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
    }

    protected void collisionAndKnockBack() {
        if (collisionOn) {
            knockBackCounter = 0;
            knockBack = false;
            speed = defaultSpeed;
        } else {
            switch (knockBackDirection) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        knockBackCounter++;
        if (knockBackCounter == 10) {
            knockBackCounter = 0;
            knockBack = false;
            speed = defaultSpeed;
        }
    }

    public void moveTowardPlayer(int interval) {
        lockCounter++;
        Player player = gameManager.player;
        if (lockCounter > interval) {
            if (getXDistance(player) > getYDistance(player)) {
                //Player is on the left side
                if (player.getCenterX() < getCenterX()) {
                    direction = "left";
                } else direction = "right";
            } else if (getXDistance(player) < getYDistance(player)) {
                if (player.getCenterY() < getCenterY()) {
                    direction = "up";
                } else direction = "down";
            }
            lockCounter = 0;
        }
    }

    public void damagePlayer(int attack) {
        //Check if player can receive the dmg
        if (!gameManager.player.invincible) {
            int damage = attack - gameManager.player.defense;
            //Get opposite direction of this entity
            String canGuardDirection = getOppositeDirection(direction);
            if (gameManager.player.guarding && gameManager.player.direction.equals(canGuardDirection)) {
                //Parry (11 frames window)
                if (gameManager.player.guardCounter < 11) {
                    damage = 0;
                    gameManager.playSE(16);
                    setKnockBack(this, gameManager.player, knockBackPower);
                    offBalance = true;
                    //Return sprite to previous one, stan effect
                    spriteCounter -= 60;
                }
                //Normal guard
                else {
                    // Normal guard
                    if (damage < 1) {
                        damage = 1;  // Make sure damage is non-negative
                    } else {
                        damage /= 3;  // Divide the damage by 3
                    }
                    gameManager.playSE(17);
                }
            }
            //Not guarding
            else {
                gameManager.playSE(9);
                if (damage < 1) {
                    damage = 1;
                }
            }
            if (damage != 0) {
                gameManager.player.transparent = true;
                setKnockBack(gameManager.player, this, knockBackPower);
            }
            gameManager.player.hp -= damage;
            gameManager.player.invincible = true;
        }
    }

    public String getOppositeDirection(String direction) {
        String oppositeDirection = "";
        switch (direction) {
            case "up" -> oppositeDirection = "down";
            case "down" -> oppositeDirection = "up";
            case "left" -> oppositeDirection = "right";
            case "right" -> oppositeDirection = "left";
        }
        return oppositeDirection;
    }

    public void spriteMovement(int entitySpeed) {
        //Changing sprites, depends on nums
        spriteCounter++;
        if (spriteCounter <= entitySpeed) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed && spriteCounter <= entitySpeed + 10) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 10 && spriteCounter <= entitySpeed + 20) {
            spriteNum = 3;
        }
        if (spriteCounter > entitySpeed + 20 && spriteCounter <= entitySpeed + 29) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 29) {
            spriteCounter = 0;
        }
    }

    //Method with default values for changing sprites
    public void spriteMovement() {
        spriteMovement(13);
    }

    public boolean inCamera() {
        int multiplier = 5;
        return worldX + GameManager.TILE_SIZE * multiplier > gameManager.player.worldX - gameManager.player.screenX &&
                worldX - GameManager.TILE_SIZE < gameManager.player.worldX + gameManager.player.screenX &&
                worldY + GameManager.TILE_SIZE * multiplier > gameManager.player.worldY - gameManager.player.screenY &&
                worldY - GameManager.TILE_SIZE < gameManager.player.worldY + gameManager.player.screenY;
    }

    public void draw(Graphics2D graphics2D) {
        //actual coords to draw the stuff on game screen
        int screenX = getScreenX();
        int screenY = getScreenY();
        BufferedImage image = null;

        if (inCamera()) {
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            //Use this method to change the sprite direction of character
            switch (direction) {
                case "up" -> {
                    if (!attacking) {
                        if (spriteNum == 0) image = up;
                        if (spriteNum == 1) image = up1;
                        if (spriteNum == 2) image = up2;
                        if (spriteNum == 3) image = up3;
                    }
                    if (attacking) {
                        tempScreenY = screenY - up.getHeight();
                        if (spriteNum == 0) image = attackUp;
                        if (spriteNum == 1) image = attackUp1;
                        if (spriteNum == 2) image = attackUp2;
                        if (spriteNum == 3) image = attackUp3;
                    }
                }
                case "down" -> {
                    if (!attacking) {
                        if (spriteNum == 0) image = down;
                        if (spriteNum == 1) image = down1;
                        if (spriteNum == 2) image = down2;
                        if (spriteNum == 3) image = down3;
                    }
                    if (attacking) {
                        if (spriteNum == 0) image = attackDown;
                        if (spriteNum == 1) image = attackDown1;
                        if (spriteNum == 2) image = attackDown2;
                        if (spriteNum == 3) image = attackDown3;
                    }
                }
                case "left" -> {
                    if (!attacking) {
                        if (spriteNum == 0) image = left;
                        if (spriteNum == 1) image = left1;
                        if (spriteNum == 2) image = left2;
                        if (spriteNum == 3) image = left3;
                    }
                    if (attacking) {
                        tempScreenX = screenX - left.getWidth();
                        if (spriteNum == 0) image = attackLeft;
                        if (spriteNum == 1) image = attackLeft1;
                        if (spriteNum == 2) image = attackLeft2;
                        if (spriteNum == 3) image = attackLeft3;
                    }
                }
                case "right" -> {
                    if (!attacking) {
                        if (spriteNum == 0) image = right;
                        if (spriteNum == 1) image = right1;
                        if (spriteNum == 2) image = right2;
                        if (spriteNum == 3) image = right3;
                    }
                    if (attacking) {
                        if (spriteNum == 0) image = attackRight;
                        if (spriteNum == 1) image = attackRight1;
                        if (spriteNum == 2) image = attackRight2;
                        if (spriteNum == 3) image = attackRight3;
                    }
                }
            }


            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlfa(graphics2D, 0.8F);
            }

            if (die) {
                dieAnimation(graphics2D);
            }
            graphics2D.drawImage(image,
                    tempScreenX, tempScreenY, null);
            changeAlfa(graphics2D, 1F);
        }
    }

    private void dieAnimation(Graphics2D graphics2D) {
        dieCounter++;

        int sec = 5;
        //TODO to use death animation of mob rather than the color
        // changing - create extra sprites and add them
        //switch them instead of change alfa
        //Every 5 frame switch monster alfa
        if (dieCounter <= sec) changeAlfa(graphics2D, 0F);
        if (dieCounter > sec && dieCounter <= sec * 2) changeAlfa(graphics2D, 1F);
        if (dieCounter > sec * 2 && dieCounter <= sec * 3) changeAlfa(graphics2D, 0F);
        if (dieCounter > sec * 3 && dieCounter <= sec * 4) changeAlfa(graphics2D, 1F);
        if (dieCounter > sec * 4 && dieCounter <= sec * 5) changeAlfa(graphics2D, 0F);
        if (dieCounter > sec * 5 && dieCounter <= sec * 6) changeAlfa(graphics2D, 1F);
        if (dieCounter > sec * 6 && dieCounter <= sec * 7) changeAlfa(graphics2D, 0F);
        if (dieCounter > sec * 7 && dieCounter <= sec * 8) changeAlfa(graphics2D, 1F);
        if (dieCounter > sec * 8) {
            alive = false;
        }
    }


    private void changeAlfa(Graphics2D graphics2D, float alfaValue) {
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alfaValue));
    }

    public void generateParticle(Entity generator, Entity target) {
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speedNew = generator.getParticleSpeed();
        int maxHpNew = generator.getParticleMaxHp();

        Particle particleTL = new Particle(gameManager, target, color, size,
                speedNew, maxHpNew, -2, -1);
        Particle particleTR = new Particle(gameManager, target, color, size,
                speedNew, maxHpNew, 2, -1);
        Particle particleDL = new Particle(gameManager, target, color, size,
                speedNew, maxHpNew, -2, 1);
        Particle particleDR = new Particle(gameManager, target, color, size,
                speedNew, maxHpNew, 2, 1);
        gameManager.particleList.add(particleTL);
        gameManager.particleList.add(particleTR);
        gameManager.particleList.add(particleDL);
        gameManager.particleList.add(particleDR);
    }


    public void searchPath(int goalCol, int goalRow, boolean reachDestination) {
        int startCol = (worldX + solidArea.x) / GameManager.TILE_SIZE;
        int startRow = (worldY + solidArea.y) / GameManager.TILE_SIZE;
        gameManager.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);
        if (gameManager.pathFinder.search()) {
            //Next worldX, worldY
            int nextX = gameManager.pathFinder.pathList.get(0).col * GameManager.TILE_SIZE;
            int nextY = gameManager.pathFinder.pathList.get(0).row * GameManager.TILE_SIZE;
            //Get entity's solidArea pos
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;
            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + GameManager.TILE_SIZE) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + GameManager.TILE_SIZE) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + GameManager.TILE_SIZE) {
                //left or right
                if (enLeftX > nextX) direction = "left";
                if (enLeftX < nextX) direction = "right";
            } else if (enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if (collisionOn) direction = "left";
            } else if (enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if (collisionOn) direction = "right";
            } else if (enTopY < nextY && enLeftX > nextX) {
                //down or left
                direction = "down";
                checkCollision();
                if (collisionOn) direction = "left";
            } else if (enTopY < nextY && enLeftX < nextX) {
                //down or right
                direction = "down";
                checkCollision();
                if (collisionOn) direction = "right";
            }
        }
        if (reachDestination) {
            //if reaches the goal, stop searching
            int nextCol = gameManager.pathFinder.pathList.get(0).col;
            int nextRow = gameManager.pathFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow) {
                onPath = false;
            }
        }
    }

    public void entityAttack() {
        spriteCounter++;

        if (spriteCounter <= motion1Duration) {
            spriteNum = 0;
        }
        if (spriteCounter > motion1Duration && spriteCounter <= motion2Duration + 6) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1Duration + 6 && spriteCounter <= motion2Duration + 13) {
            spriteNum = 2;
        }
        if (spriteCounter > motion1Duration + 13 && spriteCounter <= motion2Duration + 19) {
            spriteNum = 3;
            //Save current x,y, solid area
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            //Adjust player's worldX/Y for attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }
            //Change size of solid area to attack area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            if (type == TYPE_MOB) {
                if (gameManager.checkCollision.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else { //Player
                //Check monster collision
                int monsterIndex = gameManager.checkCollision.checkEntity(this, gameManager.mobs);
                gameManager.player.damageMob(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int interTileIndex = gameManager.checkCollision.checkEntity(this, gameManager.interactTile);
                gameManager.player.damageInteractiveTiles(interTileIndex);
                int projectTileIndex = gameManager.checkCollision.checkEntity(this, gameManager.projectile);
                gameManager.player.damageProjectTile(projectTileIndex);
            }

            //After checking collision, restore original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2Duration + 19) {
            spriteCounter = 0;
            spriteNum = 0;
            attacking = false;
        }
    }

    //If you need very aggressive monster, make rate lower
    public void checkAttacking(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDis = getXDistance(gameManager.player);
        int yDis = getYDistance(gameManager.player);
        switch (direction) {
            case "up" -> {
                if (gameManager.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal)
                    targetInRange = true;
            }
            case "down" -> {
                if (gameManager.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal)
                    targetInRange = true;
            }
            case "left" -> {
                if (gameManager.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal)
                    targetInRange = true;
            }
            case "right" -> {
                if (gameManager.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal)
                    targetInRange = true;
            }
        }
        if (targetInRange) {
            int rand = new Random().nextInt(rate);
            if (rand == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shootAvailableCounter = 0;
            }
        }
    }

    public void changeDirection(String direction) {
        checkCollision();
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
        gameManager.playerRun = false;
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public void checkVacancy() {
        for (int i = 0; i < gameManager.projectile[1].length; i++) {
            if (gameManager.projectile[gameManager.currentMap][i] == null) {
                gameManager.projectile[gameManager.currentMap][i] = projectTile;
                break;
            }
        }
    }

    public void checkShoot(int rate, int shootInterval) {
        int rand = new Random().nextInt(rate);
        if (rand == 0 && !projectTile.alive && shootAvailableCounter == shootInterval) {
            projectTile.set(worldX, worldY, direction, true, this);
            checkVacancy();
            shootAvailableCounter = 0;
        }
    }

    /**
     * @param distance if entity is n-tile distance, it will have a chance to chase
     * @param rate     number of chance when the entity can chase
     */
    public void checkStartChasing(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int rand = new Random().nextInt(rate);
            if (rand == 0) onPath = true;
        }
    }

    public void checkStopChasing(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int rand = new Random().nextInt(rate);
            if (rand == 0) onPath = false;
        }
    }

    public void getRandomDirection(int interval) {
        lockCounter++;
        if (lockCounter > interval) {
            Random random = new Random();
            int rand = random.nextInt(100) + 1;
            if (rand <= 30) direction = "up";
            if (rand <= 30) direction = "up";
            if (rand > 30 && rand <= 50) direction = "down";
            if (rand > 50 && rand <= 75) direction = "left";
            if (rand > 75) direction = "right";

            lockCounter = 0;
        }
    }

    public int getDetected(Entity entity, Entity[][] target, String targetName) {
        int index = 999;
        int nextWorldX = entity.getLeftX();
        int nextWorldY = entity.getTopY();

        switch (entity.direction) {
            case "up" -> nextWorldY = entity.getTopY() - gameManager.player.speed;
            case "down" -> nextWorldY = entity.getBottomY() + gameManager.player.speed;
            case "left" -> nextWorldX = entity.getLeftX() - gameManager.player.speed;
            case "right" -> nextWorldX = entity.getRightX() + gameManager.player.speed;
        }
        int col = nextWorldX / GameManager.TILE_SIZE;
        int row = nextWorldY / GameManager.TILE_SIZE;
        for (int i = 0; i < target[1].length; i++) {
            if (target[gameManager.currentMap][i] != null) {
                if (target[gameManager.currentMap][i].getCol() == col &&
                        target[gameManager.currentMap][i].getRow() == row &&
                        target[gameManager.currentMap][i].name.equals(targetName)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public void resetCounters() {
        lockCounter = 0;
        spriteCounter = 0;
        standCounter = 0;
        spriteNum = 0;
        invincibleCounter = 0;
        dieCounter = 0;
        hpBarCounter = 0;
        shootAvailableCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public int getScreenX() {
        return worldX - gameManager.player.worldX + gameManager.player.screenX;
    }

    public int getScreenY() {
        return worldY - gameManager.player.worldY + gameManager.player.screenY;
    }

    public int getCenterX() {
        return worldX + left.getWidth() / 2;
    }

    public int getCenterY() {
        return worldY + up.getHeight() / 2;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / GameManager.TILE_SIZE;
    }

    public int getRow() {
        return (worldY + solidArea.y) / GameManager.TILE_SIZE;
    }

    public int getXDistance(Entity target) {
        return Math.abs(getCenterX() - target.getCenterX());
    }

    public int getYDistance(Entity target) {
        return Math.abs(getCenterY() - target.getCenterY());
    }

    public int getTileDistance(Entity target) {
        return (getXDistance(target) + getYDistance(target)) / GameManager.TILE_SIZE;
    }

    public int getGoalRow(Entity target) {
        return (target.worldY + target.solidArea.y) / GameManager.TILE_SIZE;
    }

    public int getGoalCol(Entity target) {
        return (target.worldX + target.solidArea.x) / GameManager.TILE_SIZE;
    }

    //WARNING! We always override these methods by subclasses
    //so there's no need to have logic inside them
    public void speak() {
    }

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed; //speed of particle
    }

    public int getParticleMaxHp() {
        int maxHp = 0;
        return maxHp;
    }

    public void moveObj(String direction) {

    }

    public void setAction() {

    }

    public void setLoot(Entity loot) {

    }

    public void damageReaction() {
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {
        int drop = new Random().nextInt(100) + 1;

        //Set the mob's drop, 50% chance of coin, 30 of heart and mana
        if (drop < 50) dropItem(new CoinBObject(gameManager));
        if (drop >= 50 && drop < 80) dropItem(new HeartObject(gameManager));
        if (drop >= 80 && drop < 100) dropItem(new ManaObject(gameManager));
    }

    public void interact() {

    }

}
