package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

//parent class for player, monster ect
public class Entity {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    //Set default position
    public int worldX, worldY;
    public int speed;
    //we store our images in this variables
    public BufferedImage up, up1, up2, up3, down, down1, down2, down3,
            left, left1, left2, left3, right, right1, right2, right3;
    public String direction = "down";
    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 0;
    //Default values for every entity
    public Rectangle solidArea = new Rectangle(8, 16, 31, 32);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int lockCounter = 0;
    String[] dialogues = new String[20];
    //Char status
    public int maxHp;
    public int hp;
    int dialogIndex = 0;
    GameManager gameManager;
    public Tools tools = new Tools();

    public Entity(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setAction() {

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
        gameManager.checkCollision.checkPlayer(this);

        spriteMovement();
    }

    public void spriteMovement() {
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
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
        //FIXME не будет появляться спрайт, когда стоит,
        // тк там это происходило при условии, что кнопки не нажаты, а тут нпс, как тогда
        // Лучше будет разделить этот счетчик и счетчик нпс, тк могут быть конфликты
//        else {
//            standCounter++;
//            //timer before the idle anim starts
//            if (standCounter == 42) {
//                spriteNum = 0;  // Idle sprite
//                standCounter = 0;
//            }
//        }
    }

    public void draw(Graphics2D graphics2D) {
        //actual coords to draw the stuff on game screen
        int screenX = worldX - gameManager.player.worldX + gameManager.player.screenX;
        int screenY = worldY - gameManager.player.worldY + gameManager.player.screenY;
        BufferedImage image = characterSpriteDirection();
        tools.optimizeMapDraw(graphics2D, image, gameManager, screenX, screenY, worldX, worldY);
    }

    //Use this method to change the sprite direction of character
    public BufferedImage characterSpriteDirection() {
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

    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(imagePath + ".png")));
            image = tools.scaledImage(image, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
        } catch (IOException e) {
            System.err.println("Error in setup in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        return image;
    }

}
