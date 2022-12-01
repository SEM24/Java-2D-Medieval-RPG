package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    private final String[] playerPath = {"/player/male/", "/player/female/"};
    //Standard playerType
    public int playerSkin = 0;
    public final int screenX, screenY;
    int playerIndex = 999;

    public Player(GameManager gameManager, KeyHandler keyHandler) {
        super(gameManager);
        this.keyHandler = keyHandler;
        //camera position
        screenX = GameManager.SCREEN_WIDTH / 2 - (GameManager.TILE_SIZE / 2);
        screenY = GameManager.SCREEN_HEIGHT / 2 - (GameManager.TILE_SIZE / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of player
        //adjust it if needed
        solidArea.width = 31;
        solidArea.height = 32;

        setDefaultValues();
        //FIXME remove this line and use the logo of game instead in ui method
        //To draw the preview image on the screen, can be removed
        getPlayerImage();
    }

    private void setDefaultValues() {
        //player position of player
        worldX = GameManager.TILE_SIZE * 23;
        worldY = GameManager.TILE_SIZE * 21;
        speed = 2;
        direction = "down";
        //player hp, 6 = 3 hearts, 6 = 2.5 hearts
        maxHp = 6;
        hp = maxHp;
    }

    public void getPlayerImage() {
        up = setup(playerPath[playerSkin] + "player_up");
        up1 = setup(playerPath[playerSkin] + "player_up_1");
        up2 = setup(playerPath[playerSkin] + "player_up_2");
        up3 = setup(playerPath[playerSkin] + "player_up_3");
        down = setup(playerPath[playerSkin] + "player_down");
        down1 = setup(playerPath[playerSkin] + "player_down_1");
        down2 = setup(playerPath[playerSkin] + "player_down_2");
        down3 = setup(playerPath[playerSkin] + "player_down_3");
        left = setup(playerPath[playerSkin] + "player_left");
        left1 = setup(playerPath[playerSkin] + "player_left_1");
        left2 = setup(playerPath[playerSkin] + "player_left_2");
        left3 = setup(playerPath[playerSkin] + "player_left_3");
        right = setup(playerPath[playerSkin] + "player_right");
        right1 = setup(playerPath[playerSkin] + "player_right_1");
        right2 = setup(playerPath[playerSkin] + "player_right_2");
        right3 = setup(playerPath[playerSkin] + "player_right_3");
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
            gameManager.checkCollision.checkTile(this);

            //Check obj collision
            int objIndex = gameManager.checkCollision.checkObject(this, true);
            takeObject(objIndex);
            //Check npc collision
            int npcIndex = gameManager.checkCollision.checkEntity(this, gameManager.npcList);
            interactNpc(npcIndex);

            //Check mob's collision
            int mobIndex = gameManager.checkCollision.checkEntity(this, gameManager.mobs);
            interactMob(mobIndex);
            //Check Event
            gameManager.eventHandler.checkEvent();
            gameManager.keyHandler.enterPressed = false;
            //If collision false, play player move
            spriteMovement();
        } else {
            standCounter++;
            //timer before the idle anim starts
            if (standCounter == 42) {
                spriteNum = 0;  // Idle sprite
                standCounter = 0;
            }
        }
        //Make player invisible for 1 sec after receiving the damage
        if (invincible) {
            invincibleCounter++;
            //1 sec
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    private void interactMob(int mobIndex) {
        //TODO
        if (mobIndex != playerIndex && !invincible) {
            hp -= 1;
            invincible = true;
        }
    }


    private void interactNpc(int npcIndex) {
        //if index not 999 - player touches the npc
        if (npcIndex != playerIndex && gameManager.keyHandler.enterPressed) {
            gameManager.gameState = gameManager.dialogueState;
            gameManager.npcList[npcIndex].speak();
        }
    }

    private void takeObject(int index) {
        //if index is not player, make a reaction on obj
        if (index != playerIndex) {
            //TODO interact any object with player
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = characterSpriteDirection();
        //Make the player partly transparent
        if (invincible)
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));

        graphics2D.drawImage(image, screenX, screenY, null);
        //Reset alfa
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }
}
