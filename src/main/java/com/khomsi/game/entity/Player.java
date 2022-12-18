package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.KeyHandler;
import main.java.com.khomsi.game.objects.KeyObject;
import main.java.com.khomsi.game.objects.MetalShieldObject;
import main.java.com.khomsi.game.objects.MetalSwordObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    KeyHandler keyHandler;
    private final String[] playerPath = {"/player/male/", "/player/female/"};
    //Male/Female type of skin
    public int playerSkin;
    public final int screenX, screenY;
    int playerIndex = 999;
    public boolean attackCanceled = false;
    public List<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

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
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        setItems();
        //FIXME remove this line and use the logo of game instead in ui method!!!
        //To draw the preview image on the screen, can be removed
//        getPlayerImage();
//        getPlayerAttackImage();
    }

    private void setDefaultValues() {
        //player position of player
        worldX = GameManager.TILE_SIZE * 23;
        worldY = GameManager.TILE_SIZE * 21;
        speed = 2;
        direction = "down";

        //player hp, 6 = 3 hearts, 6 = 2.5 hearts
        level = 1;
        maxHp = 6;
        hp = maxHp;
        //more strength = more given damage
        strength = 1;
        //more agility = less received damage
        agility = 1;
        xp = 0;
        nextLevelXp = 5;
        coin = 0;
        currentWeapon = new MetalSwordObject(gameManager);
        currentShield = new MetalShieldObject(gameManager);
        attack = getAttack();
        defense = getDefense();
    }

    private void setItems() {
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new KeyObject(gameManager));
        inventory.add(new KeyObject(gameManager));
    }

    private int getAttack() {
        return strength * currentWeapon.attackValue;
    }

    private int getDefense() {
        return agility * currentShield.defenseValue;
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

    public void getPlayerAttackImage() {
        //Adjust image size due to not standard size
        int attackUpDownW = GameManager.TILE_SIZE;
        int attackUpDownH = GameManager.TILE_SIZE * 2;
        int attackLRW = GameManager.TILE_SIZE * 2;
        int attackLRH = GameManager.TILE_SIZE;
        attackUp = setup(playerPath[playerSkin] + "/attackSword/player_up_attack", attackUpDownW, attackUpDownH);
        attackUp1 = setup(playerPath[playerSkin] + "/attackSword/player_up_attack_1", attackUpDownW, attackUpDownH);
        attackUp2 = setup(playerPath[playerSkin] + "/attackSword/player_up_attack_2", attackUpDownW, attackUpDownH);
        attackUp3 = setup(playerPath[playerSkin] + "/attackSword/player_up_attack_3", attackUpDownW, attackUpDownH);
        attackDown = setup(playerPath[playerSkin] + "/attackSword/player_down_attack", attackUpDownW, attackUpDownH);
        attackDown1 = setup(playerPath[playerSkin] + "/attackSword/player_down_attack_1", attackUpDownW, attackUpDownH);
        attackDown2 = setup(playerPath[playerSkin] + "/attackSword/player_down_attack_2", attackUpDownW, attackUpDownH);
        attackDown3 = setup(playerPath[playerSkin] + "/attackSword/player_down_attack_3", attackUpDownW, attackUpDownH);
        attackLeft = setup(playerPath[playerSkin] + "/attackSword/player_left_attack", attackLRW, attackLRH);
        attackLeft1 = setup(playerPath[playerSkin] + "/attackSword/player_left_attack_1", attackLRW, attackLRH);
        attackLeft2 = setup(playerPath[playerSkin] + "/attackSword/player_left_attack_2", attackLRW, attackLRH);
        attackLeft3 = setup(playerPath[playerSkin] + "/attackSword/player_left_attack_3", attackLRW, attackLRH);
        attackRight = setup(playerPath[playerSkin] + "/attackSword/player_right_attack", attackLRW, attackLRH);
        attackRight1 = setup(playerPath[playerSkin] + "/attackSword/player_right_attack_1", attackLRW, attackLRH);
        attackRight2 = setup(playerPath[playerSkin] + "/attackSword/player_right_attack_2", attackLRW, attackLRH);
        attackRight3 = setup(playerPath[playerSkin] + "/attackSword/player_right_attack_3", attackLRW, attackLRH);
    }

    //This method updates player's coordinates
    public void update() {
        if (attacking) {
            playerAttack();
        }
        //to avoid moving the character without pressing buttons
        else if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {

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

            //If collision false, play player move
            if (!collisionOn && !keyHandler.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
                spriteMovement();
            }
        } else {
            standCounter++;
            //timer before the idle anim starts
            if (standCounter == 42) {
                spriteNum = 0;  // Idle sprite
                standCounter = 0;
            }
        }
        //To prevent sword swing when interact with events by enter press
        if (keyHandler.enterPressed && !attackCanceled) {
            gameManager.playSE(10);
            attacking = true;
            spriteCounter = 0;
        }
        attackCanceled = false;

        gameManager.keyHandler.enterPressed = false;

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

    private void playerAttack() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 0;
        }
        if (spriteCounter > 5 && spriteCounter <= 11) {
            spriteNum = 1;
        }
        if (spriteCounter > 11 && spriteCounter <= 18) {
            spriteNum = 2;
        }
        if (spriteCounter > 18 && spriteCounter <= 24) {
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
            //Check monster collision
            int monsterIndex = gameManager.checkCollision.checkEntity(this, gameManager.mobs);
            damageMob(monsterIndex);
            //After checking collision, restore org data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 24) {
            spriteCounter = 0;
            spriteNum = 0;
            attacking = false;
        }
    }

    private void damageMob(int monsterIndex) {
        if (monsterIndex != playerIndex) {
            if (!gameManager.mobs[monsterIndex].invincible) {
                gameManager.playSE(8);

                int damage = attack - gameManager.mobs[monsterIndex].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gameManager.mobs[monsterIndex].hp -= damage;
                gameManager.ui.addMessage(damage + " damage!");
                gameManager.mobs[monsterIndex].invincible = true;
                gameManager.mobs[monsterIndex].damageReaction();
                if (gameManager.mobs[monsterIndex].hp <= 0) {
                    gameManager.mobs[monsterIndex].die = true;
                    gameManager.ui.addMessage("Killed the " + gameManager.mobs[monsterIndex].name + "!");
                    gameManager.ui.addMessage("Xp + " + gameManager.mobs[monsterIndex].xp);
                    xp += gameManager.mobs[monsterIndex].xp;
                    checkLevelUp();
                }
            }
        }
    }

    private void interactMob(int mobIndex) {
        //TODO when the mob dies and player touches the mob - player receives the damage(not logical)
        if (mobIndex != playerIndex && !invincible && !gameManager.mobs[mobIndex].die) {
            gameManager.playSE(8);
            int damage = gameManager.mobs[mobIndex].attack - defense;
            if (damage < 0) {
                damage = 0;
            }
            hp -= damage;
            invincible = true;
        }
    }


    private void interactNpc(int npcIndex) {
        if (gameManager.keyHandler.enterPressed) {
            //if index not 999 - player touches the npc
            if (npcIndex != playerIndex) {
                attackCanceled = true;
                gameManager.gameState = gameManager.dialogueState;
                gameManager.npcList[npcIndex].speak();
            }
        }
    }

    private void checkLevelUp() {
        if (xp >= nextLevelXp) {
            level++;
            //TODO test it
            //This will make it so the exp resets but also takes into
            // account any exp collected that is over the nextLevelExp
            xp = xp - nextLevelXp;
            nextLevelXp = nextLevelXp * 2;
            maxHp += 2;
            strength++;
            agility++;
            attack = getAttack();
            defense = getDefense();
            gameManager.playSE(11);
            gameManager.gameState = gameManager.dialogueState;
            gameManager.ui.currentDialog = "Your new level is " + level + " !\n" +
                    "You became stronger!";
            gameManager.pauseMusic(5);
        }
    }

    private void takeObject(int index) {
        //if index is not player, make a reaction on obj
        if (index != playerIndex) {
            //TODO interact any object with player
        }
    }

    public void draw(Graphics2D graphics2D) {
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        BufferedImage image = null;
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
                    tempScreenY = screenY - GameManager.TILE_SIZE;
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
                    tempScreenX = screenX - GameManager.TILE_SIZE;
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

        //Make the player partly transparent
        if (invincible)
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));

        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        //Reset alfa
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }
}
