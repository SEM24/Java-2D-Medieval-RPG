package com.khomsi.game.entity.player;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.main.tools.KeyHandler;
import com.khomsi.game.objects.equipment.MetalShieldObject;
import com.khomsi.game.objects.equipment.MetalSwordObject;
import com.khomsi.game.objects.interact.KeyObject;
import com.khomsi.game.objects.interact.MagicNecklaceObject;
import com.khomsi.game.objects.light.LanternObject;
import com.khomsi.game.objects.projectTiles.FireBallObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    public static final String[] PLAYER_PATH = {"/player/male/", "/player/female/"};
    //Male/Female type of skin
    public int playerSkin = 1;
    public final int screenX, screenY;
    public int playerIndex = 999;
    public boolean attackCanceled = false;
    //call this only when the light condition is updated
    public boolean lightUpdated = false;

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
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
    }

    public void setDefaultValues() {
        //player position of player
        setDefaultPosition();
        defaultSpeed = 2;
        speed = defaultSpeed;
        invincible = false;

        //player hp, 6 = 3 hearts, 5 = 2.5 hearts
        level = 1;
        maxHp = 6;
        hp = maxHp;
        maxMana = 6;
        mana = maxMana;
        //TODO basic var for arrows, future realisation
//        ammo = 10;
        //more strength = more given damage
        strength = 3;
        //more agility = less received damage
        agility = 1;
        xp = 0;
        nextLevelXp = 20;
        coin = 10;
        currentWeapon = new MetalSwordObject(gameManager);
        currentShield = new MetalShieldObject(gameManager);
        currentLight = null;
        //In the beginning of the game, player has this skill.
        //TODO make it optional when you choose the screen sections
        //Think how to make an magic animation
        projectTile = new FireBallObject(gameManager);
        //TODO for arrows
//        projectTile = new MagicArrowObject(gameManager);
        //The total attack is decided by strength and weapon
        attack = getAttack();
        //The total defence value is decided by agility and shield
        defense = getDefense();
        setItems();
        setDialogue();
    }

    public void setDefaultPosition() {
        //player position of player
        gameManager.currentMap = 0;
        worldX = GameManager.TILE_SIZE * 29;
        worldY = GameManager.TILE_SIZE * 92;
        direction = "down";
   /*     gameManager.currentMap = 0;
        worldX = GameManager.TILE_SIZE * 15;
        worldY = GameManager.TILE_SIZE * 27;
        direction = "down";*/

/*        gameManager.currentMap = 0;
        worldX = GameManager.TILE_SIZE * 75;
        worldY = GameManager.TILE_SIZE * 56;
        direction = "down";*/
    }

    public void restoreStatus() {
        hp = maxHp;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }

    private void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new KeyObject(gameManager));
        inventory.add(new MagicNecklaceObject(gameManager));
        inventory.add(new LanternObject(gameManager));
    }

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1Duration = currentWeapon.motion1Duration;
        motion2Duration = currentWeapon.motion2Duration;
        return strength * currentWeapon.attackValue;
    }

    //Change the skin of character and stats
    public void createNewPlayer(int skinType, int maxPlayerHp, int buffSpeed) {
        playerSkin = skinType;
        defaultSpeed = buffSpeed;
        speed = defaultSpeed;
        maxHp = maxPlayerHp;
        loadImages();
        gameManager.gameState = GameManager.PLAY_STATE;
        gameManager.playMusic(0);
    }

    public void loadImages() {
        getImage();
        getAttackImage();
        getGuardImages();
    }

    public int getDefense() {
        return agility * currentShield.defenseValue;
    }

    public void getImage() {
        up = setup(PLAYER_PATH[playerSkin] + "player_up");
        up1 = setup(PLAYER_PATH[playerSkin] + "player_up_1");
        up2 = setup(PLAYER_PATH[playerSkin] + "player_up_2");
        up3 = setup(PLAYER_PATH[playerSkin] + "player_up_3");
        down = setup(PLAYER_PATH[playerSkin] + "player_down");
        down1 = setup(PLAYER_PATH[playerSkin] + "player_down_1");
        down2 = setup(PLAYER_PATH[playerSkin] + "player_down_2");
        down3 = setup(PLAYER_PATH[playerSkin] + "player_down_3");
        left = setup(PLAYER_PATH[playerSkin] + "player_left");
        left1 = setup(PLAYER_PATH[playerSkin] + "player_left_1");
        left2 = setup(PLAYER_PATH[playerSkin] + "player_left_2");
        left3 = setup(PLAYER_PATH[playerSkin] + "player_left_3");
        right = setup(PLAYER_PATH[playerSkin] + "player_right");
        right1 = setup(PLAYER_PATH[playerSkin] + "player_right_1");
        right2 = setup(PLAYER_PATH[playerSkin] + "player_right_2");
        right3 = setup(PLAYER_PATH[playerSkin] + "player_right_3");
    }

    public void getDyingImages() {
        up = setup(PLAYER_PATH[playerSkin] + "gameover/player_up");
        up1 = setup(PLAYER_PATH[playerSkin] + "gameover/player_up");
        up2 = setup(PLAYER_PATH[playerSkin] + "gameover/player_up");
        up3 = setup(PLAYER_PATH[playerSkin] + "gameover/player_up");

        left = setup(PLAYER_PATH[playerSkin] + "gameover/player_left");
        left1 = setup(PLAYER_PATH[playerSkin] + "gameover/player_left");
        left2 = setup(PLAYER_PATH[playerSkin] + "gameover/player_left");
        left3 = setup(PLAYER_PATH[playerSkin] + "gameover/player_left");


        right = setup(PLAYER_PATH[playerSkin] + "gameover/player_right");
        right1 = setup(PLAYER_PATH[playerSkin] + "gameover/player_right");
        right2 = setup(PLAYER_PATH[playerSkin] + "gameover/player_right");
        right3 = setup(PLAYER_PATH[playerSkin] + "gameover/player_right");

        down = setup(PLAYER_PATH[playerSkin] + "gameover/player_down");
        down1 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down");
        down2 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down");
        down3 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down");
    }

    public void replaceDyingPlayerDownImage() {
        down = setup(PLAYER_PATH[playerSkin] + "gameover/player_down_1");
        down1 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down_1");
        down2 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down_1");
        down3 = setup(PLAYER_PATH[playerSkin] + "gameover/player_down_1");
    }

    //TODO implement the logic
    public void getFallIntoPitImage() {
        up = setup(PLAYER_PATH[playerSkin] + "pitfall/player_0");
        up1 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_0");
        up2 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_0");
        up3 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_0");

        left = setup(PLAYER_PATH[playerSkin] + "pitfall/player_1");
        left1 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_1");
        left2 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_1");
        left3 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_1");


        right = setup(PLAYER_PATH[playerSkin] + "pitfall/player_2");
        right1 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_2");
        right2 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_2");
        right3 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_2");

        down = setup(PLAYER_PATH[playerSkin] + "pitfall/player_3");
        down1 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_3");
        down2 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_3");
        down3 = setup(PLAYER_PATH[playerSkin] + "pitfall/player_3");
    }

    public void getAttackImage() {
        //Adjust image size due to not standard size
        int attackUpDownW = GameManager.TILE_SIZE;
        int attackUpDownH = GameManager.TILE_SIZE * 2;
        int attackLRW = GameManager.TILE_SIZE * 2;
        int attackLRH = GameManager.TILE_SIZE;
        //TODO Each tool can be having diff colors,add index to the class and write the num here.
        //If the sword is chosen
        if (currentWeapon.type == TYPE_SWORD) {
            attackUp = setup(PLAYER_PATH[playerSkin] + "attacksword/player_up_attack", attackUpDownW, attackUpDownH);
            attackUp1 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_up_attack_1", attackUpDownW, attackUpDownH);
            attackUp2 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_up_attack_2", attackUpDownW, attackUpDownH);
            attackUp3 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_up_attack_3", attackUpDownW, attackUpDownH);
            attackDown = setup(PLAYER_PATH[playerSkin] + "attacksword/player_down_attack", attackUpDownW, attackUpDownH);
            attackDown1 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_down_attack_1", attackUpDownW, attackUpDownH);
            attackDown2 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_down_attack_2", attackUpDownW, attackUpDownH);
            attackDown3 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_down_attack_3", attackUpDownW, attackUpDownH);
            attackLeft = setup(PLAYER_PATH[playerSkin] + "attacksword/player_left_attack", attackLRW, attackLRH);
            attackLeft1 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_left_attack_1", attackLRW, attackLRH);
            attackLeft2 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_left_attack_2", attackLRW, attackLRH);
            attackLeft3 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_left_attack_3", attackLRW, attackLRH);
            attackRight = setup(PLAYER_PATH[playerSkin] + "attacksword/player_right_attack", attackLRW, attackLRH);
            attackRight1 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_right_attack_1", attackLRW, attackLRH);
            attackRight2 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_right_attack_2", attackLRW, attackLRH);
            attackRight3 = setup(PLAYER_PATH[playerSkin] + "attacksword/player_right_attack_3", attackLRW, attackLRH);
        }
        if (currentWeapon.type == TYPE_AXE) {
            attackUp = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_up_axe", attackUpDownW, attackUpDownH);
            attackUp1 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_up_axe_1", attackUpDownW, attackUpDownH);
            attackUp2 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_up_axe_2", attackUpDownW, attackUpDownH);
            attackUp3 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_up_axe_3", attackUpDownW, attackUpDownH);
            attackDown = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_down_axe", attackUpDownW, attackUpDownH);
            attackDown1 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_down_axe_1", attackUpDownW, attackUpDownH);
            attackDown2 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_down_axe_2", attackUpDownW, attackUpDownH);
            attackDown3 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_down_axe_3", attackUpDownW, attackUpDownH);
            attackLeft = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_left_axe", attackLRW, attackLRH);
            attackLeft1 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_left_axe_1", attackLRW, attackLRH);
            attackLeft2 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_left_axe_2", attackLRW, attackLRH);
            attackLeft3 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_left_axe_3", attackLRW, attackLRH);
            attackRight = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_right_axe", attackLRW, attackLRH);
            attackRight1 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_right_axe_1", attackLRW, attackLRH);
            attackRight2 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_right_axe_2", attackLRW, attackLRH);
            attackRight3 = setup(PLAYER_PATH[playerSkin] + "attackaxe/player_right_axe_3", attackLRW, attackLRH);
        }
        if (currentWeapon.type == TYPE_HOOK) {
            attackUp = setup(PLAYER_PATH[playerSkin] + "hook/player_up_attack", attackUpDownW, attackUpDownH);
            attackUp1 = setup(PLAYER_PATH[playerSkin] + "hook/player_up_attack_1", attackUpDownW, attackUpDownH);
            attackUp2 = setup(PLAYER_PATH[playerSkin] + "hook/player_up_attack_2", attackUpDownW, attackUpDownH);
            attackUp3 = setup(PLAYER_PATH[playerSkin] + "hook/player_up_attack_3", attackUpDownW, attackUpDownH);

            attackDown = setup(PLAYER_PATH[playerSkin] + "hook/player_down_attack", attackUpDownW, attackUpDownH);
            attackDown1 = setup(PLAYER_PATH[playerSkin] + "hook/player_down_attack_1", attackUpDownW, attackUpDownH);
            attackDown2 = setup(PLAYER_PATH[playerSkin] + "hook/player_down_attack_2", attackUpDownW, attackUpDownH);
            attackDown3 = setup(PLAYER_PATH[playerSkin] + "hook/player_down_attack_3", attackUpDownW, attackUpDownH);

            attackLeft = setup(PLAYER_PATH[playerSkin] + "hook/player_left_attack", attackLRW, attackLRH);
            attackLeft1 = setup(PLAYER_PATH[playerSkin] + "hook/player_left_attack_1", attackLRW, attackLRH);
            attackLeft2 = setup(PLAYER_PATH[playerSkin] + "hook/player_left_attack_2", attackLRW, attackLRH);
            attackLeft3 = setup(PLAYER_PATH[playerSkin] + "hook/player_left_attack_3", attackLRW, attackLRH);
            attackRight = setup(PLAYER_PATH[playerSkin] + "hook/player_right_attack", attackLRW, attackLRH);
            attackRight1 = setup(PLAYER_PATH[playerSkin] + "hook/player_right_attack_1", attackLRW, attackLRH);
            attackRight2 = setup(PLAYER_PATH[playerSkin] + "hook/player_right_attack_2", attackLRW, attackLRH);
            attackRight3 = setup(PLAYER_PATH[playerSkin] + "hook/player_right_attack_3", attackLRW, attackLRH);
        }
    }

    public void getGuardImages() {
        guardUp = setup(PLAYER_PATH[playerSkin] + "shield/player_up_shield");
        guardDown = setup(PLAYER_PATH[playerSkin] + "shield/player_down_shield");
        guardLeft = setup(PLAYER_PATH[playerSkin] + "shield/player_left_shield");
        guardRight = setup(PLAYER_PATH[playerSkin] + "shield/player_right_shield");
    }

    public void getSleepingImage(BufferedImage image) {
        up = image;
        up1 = image;
        up2 = image;
        up3 = image;
        down = image;
        down1 = image;
        down2 = image;
        down3 = image;
        left = image;
        left1 = image;
        left2 = image;
        left3 = image;
        right = image;
        right1 = image;
        right2 = image;
        right3 = image;
    }

    //This method updates player's coordinates
    @Override
    public void update() {
        if (knockBack) {
            collisionOn = false;
            gameManager.checkCollision.checkTile(this);
            gameManager.checkCollision.checkObject(this, true);
            gameManager.checkCollision.checkEntity(this, gameManager.npcList);
            gameManager.checkCollision.checkEntity(this, gameManager.mobs);
            gameManager.checkCollision.checkEntity(this, gameManager.interactTile);
            collisionAndKnockBack();
        } else if (attacking) {
            entityAttack();
        }
        //You can't guard while attacking
        else if (keyHandler.isSpacePressed) {
            guarding = true;
            guardCounter++;
        }
        //to avoid moving the character without pressing buttons
        else if (keyHandler.isUpPressed || keyHandler.isDownPressed ||
                keyHandler.isLeftPressed || keyHandler.isRightPressed || keyHandler.isEnterPressed) {

            //use else if to avoid diagonal movement, if it's not needed, use just if
            if (keyHandler.isUpPressed) {
                direction = "up";
            } else if (keyHandler.isDownPressed) {
                direction = "down";
            } else if (keyHandler.isLeftPressed) {
                direction = "left";
            } else if (keyHandler.isRightPressed) {
                direction = "right";
            }

            turnOnCollision();
            //Check Event
            gameManager.eventHandler.checkEvent();
            //Change the direction of player
            if (!collisionOn && !keyHandler.isEnterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
                spriteMovement();
            }
            //To prevent sword swing when interact with events by enter press
            if (keyHandler.isEnterPressed && !attackCanceled) {
                gameManager.playSE(10);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gameManager.keyHandler.isEnterPressed = false;
            guarding = false;
            guardCounter = 0;
            //Change the player's walk/attack sprites
        }
        //Use standing sprites with stand counter
        else {
            standCounter++;
            //timer before the idle anim starts
            if (standCounter == 42) {
                spriteNum = 0;  // Idle sprite
                standCounter = 0;
            }
            //If you aren't pressing any key, disable guard state
            guarding = false;
            guardCounter = 0;
        }

        //Shoot projectTiles, if the previous tile is still on the screen,
        //you can't shoot next one
        if (keyHandler.isShootKeyPressed && !projectTile.alive
                && shootAvailableCounter == 30 && projectTile.haveResource(this)) {
            //Set default coord, direction, alive state and entity
            projectTile.set(worldX, worldY, direction, true, this);
            //Subtract the cost
            projectTile.subtractResource(this);

            //Add this projTile to list
            checkVacancy();
            shootAvailableCounter = 0;
            //TODO change soundEffect
            gameManager.playSE(1);
        }
        //Make player invisible for 1 sec after receiving the damage
        if (invincible) {
            invincibleCounter++;
            //1 sec
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }
        }
        //Timer for shooting the tiles, you wait 30 frames
        if (shootAvailableCounter < 30) {
            shootAvailableCounter++;
        }
        lifeManaConditions();
    }

    private void turnOnCollision() {
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

        //Check interactive tiles collision
        gameManager.checkCollision.checkEntity(this, gameManager.interactTile);
    }

    private void lifeManaConditions() {
        //If player's life > than max life, set the maxLife
        if (hp > maxHp) {
            hp = maxHp;
        }
        //If player's mana > than max mana, set the maxMana
        if (mana > maxMana) {
            mana = maxMana;
        }
        if (!keyHandler.godMode) {
            if (hp <= 0) {
                gameManager.gameState = GameManager.DYING_STATE;
                //To prevent pressing immediate retry, while you was pressing enter(attack)
                gameManager.ui.commandNum = -1;
                gameManager.stopMusic();
                //TODO add another music while the player lost the game
                //gameManager.playMusic(index);
                gameManager.playSE(14);
            }
        }
    }

    public void damageProjectTile(int index) {
        if (index != playerIndex) {
            //player's weapon hits the weapon = project tile disappears
            Entity projectTile = gameManager.projectile[gameManager.currentMap][index];
            projectTile.alive = false;
            generateParticle(projectTile, projectTile);
        }
    }

    public void damageInteractiveTiles(int index) {
        if (index != playerIndex
                && gameManager.interactTile[gameManager.currentMap][index].destructible
                && gameManager.interactTile[gameManager.currentMap][index].isCorrectWeapon(this)
                && !gameManager.interactTile[gameManager.currentMap][index].invincible) {
            gameManager.interactTile[gameManager.currentMap][index].playSE();
            gameManager.interactTile[gameManager.currentMap][index].hp--;
            gameManager.interactTile[gameManager.currentMap][index].invincible = true;
            //Generate particles
            generateParticle(gameManager.interactTile[gameManager.currentMap][index],
                    gameManager.interactTile[gameManager.currentMap][index]);

            if (gameManager.interactTile[gameManager.currentMap][index].hp == 0) {
                gameManager.interactTile[gameManager.currentMap][index].checkDrop();
                gameManager.interactTile[gameManager.currentMap][index] =
                        gameManager.interactTile[gameManager.currentMap][index].getDestroyedForm();
            }
        }
    }

    public void damageMob(int monsterIndex, Entity attacker, int attack, int knockBackPower) {
        if (monsterIndex != playerIndex) {
            if (!gameManager.mobs[gameManager.currentMap][monsterIndex].invincible) {
                gameManager.playSE(8);
                if (knockBackPower > 0) {
                    setKnockBack(gameManager.mobs[gameManager.currentMap][monsterIndex], attacker, knockBackPower);
                }
                if (gameManager.mobs[gameManager.currentMap][monsterIndex].offBalance) {
                    attack *= 3;
                }
                int damage = attack - gameManager.mobs[gameManager.currentMap][monsterIndex].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gameManager.mobs[gameManager.currentMap][monsterIndex].hp -= damage;
                gameManager.ui.addMessage(damage + " damage!");
                gameManager.mobs[gameManager.currentMap][monsterIndex].invincible = true;
                gameManager.mobs[gameManager.currentMap][monsterIndex].damageReaction();
                if (gameManager.mobs[gameManager.currentMap][monsterIndex].hp <= 0) {
                    gameManager.mobs[gameManager.currentMap][monsterIndex].die = true;
                    gameManager.ui.addMessage("Killed the " + gameManager.mobs[gameManager.currentMap][monsterIndex].name + "!");
                    gameManager.ui.addMessage("Xp + " + gameManager.mobs[gameManager.currentMap][monsterIndex].xp);
                    xp += gameManager.mobs[gameManager.currentMap][monsterIndex].xp;
                    checkLevelUp();
                }
            }
        }
    }

    private void interactMob(int mobIndex) {
        if (mobIndex != playerIndex && !invincible && !gameManager.mobs[gameManager.currentMap][mobIndex].die) {
            gameManager.playSE(8);
            int damage = gameManager.mobs[gameManager.currentMap][mobIndex].attack - defense;
            if (damage < 1) {
                damage = 1;
            }
            hp -= damage;
            invincible = true;
            transparent = true;
        }
    }


    private void interactNpc(int npcIndex) {
        //if index not 999 - player touches the npc
        if (npcIndex != playerIndex) {
            if (gameManager.keyHandler.isEnterPressed) {
                attackCanceled = true;
                gameManager.npcList[gameManager.currentMap][npcIndex].speak();
            }
            gameManager.npcList[gameManager.currentMap][npcIndex].moveObj(direction);
        }
    }

    private void checkLevelUp() {
        int maxValue = 12;
        if (xp >= nextLevelXp) {
            level++;
            //This will make it so the exp resets but also takes into
            // account any exp collected that is over the nextLevelExp
            xp = xp - nextLevelXp;
            nextLevelXp = nextLevelXp * 2;
            // Increase maxHp by 2 if it is less than maxValue
            if (maxHp < maxValue) {
                maxHp += 2;
            }
            // Increase maxMana by 1 if it is less than maxValue
            if (maxMana < maxValue) {
                maxMana += 1;
            }
            strength++;
            agility++;
            attack = getAttack();
            defense = getDefense();
            gameManager.playSE(11);

            setDialogue();
            startDialogue(this, 0);
            //FIXME fix the pause method
//            gameManager.pauseMusic(5);
        }
    }

    public void setDialogue() {
        dialogues[0][0] = "Your new level is " + level + " !\n" +
                "You became stronger!";
    }

    public void selectItem() {
        int itemIndex = gameManager.ui.getItemIndexOnSlot(gameManager.ui.playerSlotCol, gameManager.ui.playerSlotRow);
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == TYPE_SWORD || selectedItem.type == TYPE_AXE || selectedItem.type == TYPE_HOOK) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if (selectedItem.type == TYPE_SHIELD) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == TYPE_LIGHT) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if (selectedItem.type == TYPE_CONSUMABLE) {
                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else
                        inventory.remove(itemIndex);
                }
            }
        }
    }

    private void takeObject(int index) {
        //if index is not player, make a reaction on obj
        if (index != playerIndex) {
            //Take only items
            if (gameManager.object[gameManager.currentMap][index].type == TYPE_PICK_UP_ONLY) {
                gameManager.object[gameManager.currentMap][index].use(this);
                gameManager.object[gameManager.currentMap][index] = null;
            }
            //Obstacle
            else if (gameManager.object[gameManager.currentMap][index].type == TYPE_OBSTACLE) {
                if (keyHandler.isEnterPressed) {
                    attackCanceled = true;
                    gameManager.object[gameManager.currentMap][index].interact();
                }
            } else {
                //Inventory items
                String text;
                if (canObtainItem(gameManager.object[gameManager.currentMap][index])) {
                    gameManager.playSE(2);
                    text = "Got a " + gameManager.object[gameManager.currentMap][index].name + "!";
                } else {
                    text = "You can't pick up the item anymore!";
                }
                gameManager.ui.addMessage(text);
                gameManager.object[gameManager.currentMap][index] = null;
            }
        }
    }

    public int searchItemInventory(String itemName) {
        int itemIndex = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;
        //Get item in seller's inventory
        Entity newItem = gameManager.entityGenerator.getObject(item.name);
        //Check if item is stackable
        if (newItem.stackable) {
            int index = searchItemInventory(newItem.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                //New item, so need to check vacancy
                if (inventory.size() != maxInventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        } else {
            //Not stackable, so check vacancy
            if (inventory.size() != maxInventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
    }

    @Override
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
                if (guarding) {
                    image = guardUp;
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
                if (guarding) {
                    image = guardDown;
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
                if (guarding) {
                    image = guardLeft;
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
                if (guarding) {
                    image = guardRight;
                }
            }
        }

        //Make the player partly transparent
        if (transparent)
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
        if (drawing)
            graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        //Reset alfa
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
    }
}
