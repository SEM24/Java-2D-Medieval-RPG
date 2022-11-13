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

    private final String playerPath = "/player/";

    public final int screenX, screenY;

    //specific player width due to diff size of sprites
    //TODO maybe it's better to make this type of constant for whole characters
    //because they're also might be different
    private static final int PLAYER_WIDTH = 16;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        //camera position
        screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
        screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of player
        //TODO adjust it if needed
        solidArea.width = 26 - PLAYER_WIDTH;
        solidArea.height = 26;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //player position of player
        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_up.png")));

            up1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_up_1.png")));

            up2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_up_2.png")));

            down = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_down.png")));

            down1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_down_1.png")));

            down2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_down_2.png")));

            left = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_left.png")));

            left1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_left_1.png")));

            left2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_left.png")));

            right = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_right.png")));

            right1 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_right_1.png")));

            right2 = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(playerPath + "boy_right_2.png")));

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
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }
            //Check tile collision
            collisionOn = false;
            gamePanel.checkCollision.checkTile(this);

            //If collision false, play player move
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
            if (spriteCounter > 13 && spriteCounter <= 24) {
                spriteNum = 2;
            }
            if (spriteCounter > 24) {
                spriteCounter = 0;
            }
        } else {
            standCounter++;
            //timer before the idle anim starts
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
        //FIXME player width = hardcode
        graphics2D.drawImage(image, screenX, screenY, GamePanel.TILE_SIZE - PLAYER_WIDTH, GamePanel.TILE_SIZE, null);
    }
}
