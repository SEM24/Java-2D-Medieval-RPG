package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GamePanel;
import main.java.com.khomsi.game.main.tools.KeyHandler;
import main.java.com.khomsi.game.main.tools.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    Tools tools = new Tools();
    private final String playerPath = "/player/";
    public final int screenX, screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;
        //camera position
        screenX = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE / 2);
        screenY = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE / 2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of player
        //TODO adjust it if needed
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //player position of player
        worldX = GamePanel.TILE_SIZE * 23;
        worldY = GamePanel.TILE_SIZE * 21;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        up = setup(playerPath + "player_up");
        up1 = setup(playerPath + "player_up_1");
        up2 = setup(playerPath + "player_up_2");
        up3 = setup(playerPath + "player_up_3");
        down = setup(playerPath + "player_down");
        down1 = setup(playerPath + "player_down_1");
        down2 = setup(playerPath + "player_down_2");
        down3 = setup(playerPath + "player_down_3");
        left = setup(playerPath + "player_left");
        left1 = setup(playerPath + "player_left_1");
        left2 = setup(playerPath + "player_left_2");
        left3 = setup(playerPath + "player_left_3");
        right = setup(playerPath + "player_right");
        right1 = setup(playerPath + "player_right_1");
        right2 = setup(playerPath + "player_right_2");
        right3 = setup(playerPath + "player_right_3");
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

            //Check obj collision
            int objIndex = gamePanel.checkCollision.checkObject(this, true);
            takeObject(objIndex);
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
        } else {
            standCounter++;
            //timer before the idle anim starts
            if (standCounter == 42) {
                spriteNum = 0;  // Idle sprite
                standCounter = 0;
            }
        }
    }

    public void takeObject(int index) {
        //if index is not player, make a reaction on obj
        if (index != 999) {
            //TODO
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = characterSpriteDirection();
        graphics2D.drawImage(image, screenX, screenY, null);
    }
}
