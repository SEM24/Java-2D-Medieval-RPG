package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//parent class for player, monster etc
public class Entity {
    //IMAGES
    public BufferedImage image, image2, image3;
    //Store our walking images in this variables
    public BufferedImage up, up1, up2, up3, down, down1, down2, down3,
            left, left1, left2, left3, right, right1, right2, right3;
    //Store attack images
    public BufferedImage attackUp, attackUp1, attackUp2, attackUp3, attackDown, attackDown1,
            attackDown2, attackDown3, attackLeft, attackLeft1, attackLeft2, attackLeft3,
            attackRight, attackRight1, attackRight2, attackRight3;

    //COUNTERS
    public int lockCounter = 0;
    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 0;
    public int invincibleCounter;
    int dieCounter = 0;
    int hpBarCounter = 0;
    public int shootAvailableCounter = 0;

    int knockBackCounter = 0;
    //State
    public String direction = "down";
    public boolean collisionOn = false;
    public boolean collision = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean die = false;
    public boolean fallIntoPit = false;
    boolean hpBarOn = false;
    public boolean onPath = false;

    public boolean knockBack = false;

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
    public Entity currentWeapon;
    public Entity currentShield;

    //Item attributes
    public int value;
    public int attackValue;
    public int defenseValue;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
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

    //TOOLS
    int dialogIndex = 0;
    public String[] dialogues = new String[20];

    //Default values for every entity
    public List<Entity> inventory = new ArrayList<>();
    public int maxInventorySize = 24;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public GameManager gameManager;
    public Tools tools = new Tools();
    public ProjectTile projectTile;

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

    public void speak() {
        if (dialogues[dialogIndex] == null) {
            dialogIndex = 0;
        }
        gameManager.ui.currentDialog = dialogues[dialogIndex];
        dialogIndex++;
        switch (gameManager.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
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
        if (knockBack) {
            checkCollision();
            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (direction) {
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
        } else {
            setAction();
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
        spriteMovement();
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
    }

    public void damagePlayer(int attack) {
        //Check if player can receive the dmg
        if (!gameManager.player.invincible) {
            gameManager.playSE(9);
            int damage = attack - gameManager.player.defense;
            if (damage < 0) {
                damage = 0;
            }
            gameManager.player.hp -= damage;
            gameManager.player.invincible = true;
        }
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

    public void draw(Graphics2D graphics2D) {
        //actual coords to draw the stuff on game screen
        int screenX = worldX - gameManager.player.worldX + gameManager.player.screenX;
        int screenY = worldY - gameManager.player.worldY + gameManager.player.screenY;

        if (worldX + GameManager.TILE_SIZE > gameManager.player.worldX - gameManager.player.screenX &&
                worldX - GameManager.TILE_SIZE < gameManager.player.worldX + gameManager.player.screenX &&
                worldY + GameManager.TILE_SIZE > gameManager.player.worldY - gameManager.player.screenY &&
                worldY - GameManager.TILE_SIZE < gameManager.player.worldY + gameManager.player.screenY) {

            //Monster Hp bar
            if (type == TYPE_MOB && hpBarOn) {
                //Get the length of hp bar, if hp - 4, then the scale is 12 pixels (4 times)
                double oneScale = (double) GameManager.TILE_SIZE / maxHp;
                double hpBarValue = oneScale * hp;

                graphics2D.setColor(new Color(35, 35, 35));
                graphics2D.fillRect(screenX - 1, screenY - 16, GameManager.TILE_SIZE + 2, 12);

                graphics2D.setColor(new Color(255, 0, 30));
                graphics2D.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                hpBarCounter++;
                //After 10 sec bar hides
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
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
            graphics2D.drawImage(characterSpriteDirectionImage(),
                    screenX, screenY, null);
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


    //Use this method to change the sprite direction of character
    public BufferedImage characterSpriteDirectionImage() {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = up3;
                if (spriteNum == 0) image = up;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = down3;
                if (spriteNum == 0) image = down;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = left3;
                if (spriteNum == 0) image = left;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right3;
                if (spriteNum == 0) image = right;
            }
        }
        return image;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(imagePath + ".png")));
            image = tools.scaledImage(image, width, height);
        } catch (IOException e) {
            System.err.println("Error in setup in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        return image;
    }

    //Method with default values for images
    public BufferedImage setup(String imageName) {
        return setup(imageName, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
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

    public void checkVacancy() {
        for (int i = 0; i < gameManager.projectile[1].length; i++) {
            if (gameManager.projectile[gameManager.currentMap][i] == null) {
                gameManager.projectile[gameManager.currentMap][i] = projectTile;
                break;
            }
        }
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

    //WARNING! We always override these methods by subclasses
    //so there's no need to have logic inside them
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

    public void setAction() {

    }

    public void damageReaction() {
    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {

    }

    public void interact() {

    }

}
