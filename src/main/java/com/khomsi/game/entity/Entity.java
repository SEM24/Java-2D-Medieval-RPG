package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

//parent class for player, monster ect
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

    //State
    public String direction = "down";
    public boolean collisionOn = false;
    public boolean collision = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean die = false;
    boolean hpBarOn = false;

    //Entity stats
    //Set default position
    public int worldX, worldY;
    public int speed;
    public int maxHp;
    public int hp;
    public int level;
    public int strength;
    public int agility;
    public int attack;
    public int defense;
    public int xp;
    public int nextLevelXp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;

    //Item attributes
    public int attackValue;
    public int defenseValue;
    public String itemDescription = "";
    //0 = player, 1 = npc, 2 = mob
    public int type;
    //TOOLS
    int dialogIndex = 0;
    public String[] dialogues = new String[20];
    public String name;
    //Default values for every entity
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public GameManager gameManager;
    public Tools tools = new Tools();

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setAction() {
    }

    public void damageReaction() {
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

    public void update() {
        setAction();
        collisionOn = false;
        gameManager.checkCollision.checkTile(this);
        gameManager.checkCollision.checkObject(this, false);
        gameManager.checkCollision.checkEntity(this, gameManager.npcList);
        gameManager.checkCollision.checkEntity(this, gameManager.mobs);
        boolean interactPlayer = gameManager.checkCollision.checkPlayer(this);
        //If it's monster, and it touches the player, receive the dmg
        if (this.type == 2 && interactPlayer) {
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
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
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
    }

    public void spriteMovement() {
        //Changing sprites, depends on nums
        spriteCounter++;
        if (spriteCounter <= 13) {
            spriteNum = 1;
        }
        if (spriteCounter > 13 && spriteCounter <= 23) {
            spriteNum = 2;
        }
        if (spriteCounter > 23 && spriteCounter <= 33) {
            spriteNum = 3;
        }
        if (spriteCounter > 33 && spriteCounter <= 42) {
            spriteNum = 2;
        }
        if (spriteCounter > 42) {
            spriteCounter = 0;
        }
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
            if (type == 2 && hpBarOn) {
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
            graphics2D.drawImage(characterSpriteDirectionImage(), screenX, screenY,
                    GameManager.TILE_SIZE, GameManager.TILE_SIZE, null);
            changeAlfa(graphics2D, 1F);
        }
    }

    private void dieAnimation(Graphics2D graphics2D) {
        dieCounter++;

        int sec = 5;
        //todo to use death animation of mob - create extra sprites and add them
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
            die = false;
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
}
