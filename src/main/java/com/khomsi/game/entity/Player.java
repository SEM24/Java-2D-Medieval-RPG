package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GamePanel;
import main.java.com.khomsi.game.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_up.png")));

            up1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_up_1.png")));

            up2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_up_2.png")));

            down = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_down.png")));

            down1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_down_1.png")));

            down2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_down_2.png")));

            left = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_left.png")));

            left1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_left_1.png")));

            left2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_left_2.png")));

            right = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_right.png")));

            right1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_right_1.png")));

            right2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch (IOException e) {
            System.err.println("Error on getting player images!");
            e.printStackTrace();
        }
    }

    //This method updates player's coordinates
    public void update() {
        //to avoid moving the character without pressing buttons
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed) {

            //use else if to avoid diagonal movement, if it's not needed, use just if
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }

            //changing sprites, depends on nums
            spriteCounter++;
            if (spriteCounter <= 13 - speed) {
                spriteNum = 1;
            }
            if (spriteCounter > 13 && spriteCounter <= 24) {
                spriteNum = 2;
            }
            if (spriteCounter > 24) {
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            if (standCounter == 24) {
                spriteNum = 3;  // Idle sprite
                standCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = up;
            }
            case "down" -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = down;
            }
            case "left" -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = left;
            }
            case "right" -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right;
            }
        }
        graphics2D.drawImage(image, x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
    }
}
