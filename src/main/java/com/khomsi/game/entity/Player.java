package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GamePanel;
import main.java.com.khomsi.game.main.tools.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    private final String playerPath = "/player/";
    public final int screenX, screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
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
        solidArea.width = 31;
        solidArea.height = 32;

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
            int npcIndex = gamePanel.checkCollision.checkEntity(this, gamePanel.npc);
            interactNpc(npcIndex);
            //If collision false, play player move
            spriteMovement();
        }
    }

    private void interactNpc(int npcIndex) {
        //if index not 999 - player touches the npc
        if (npcIndex != 999 && gamePanel.keyHandler.enterPressed) {
            gamePanel.gameState = gamePanel.dialogueState;
            gamePanel.npc[npcIndex].speak();
        }
        gamePanel.keyHandler.enterPressed = false;
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
