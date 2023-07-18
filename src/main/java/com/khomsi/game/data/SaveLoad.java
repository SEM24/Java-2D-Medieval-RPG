package com.khomsi.game.data;

import com.khomsi.game.main.GameManager;

import java.io.*;

public class SaveLoad {
    GameManager gameManager;
    // Path to save.dat file to check the statement
    private static final String FILE_PATH = "save.dat";
    public final File file = new File(FILE_PATH);
    public final boolean hasFile = file.exists();

    public SaveLoad(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /*
     * Save method to save data info about player
     */
    public void save() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            DataInitializer initializer = new DataInitializer();
            initializer.level = gameManager.player.level;
            initializer.maxHp = gameManager.player.maxHp;
            initializer.hp = gameManager.player.hp;
            initializer.maxMana = gameManager.player.maxMana;
            initializer.speed = gameManager.player.speed;
            initializer.mana = gameManager.player.mana;
            initializer.strength = gameManager.player.strength;
            initializer.agility = gameManager.player.agility;
            initializer.xp = gameManager.player.xp;
            initializer.nextLevelXp = gameManager.player.nextLevelXp;
            initializer.coin = gameManager.player.coin;
            initializer.playerSkin = gameManager.player.playerSkin;
            //TODO currently doesn't work
            // Save play time and game timer
            initializer.savedPlayTime = gameManager.playTime;
            initializer.savedGameTimer = gameManager.getGameTimer();

            //Player's inventory
            for (int i = 0; i < gameManager.player.inventory.size(); i++) {
                initializer.itemNames.add(gameManager.player.inventory.get(i).name);
                initializer.itemAmounts.add(gameManager.player.inventory.get(i).amount);
            }
            //Player equipment
            initializer.currentWeaponSlot = gameManager.player.getCurrentWeaponSlot();
            initializer.currentShieldSlot = gameManager.player.getCurrentShieldSlot();

            //Objects on map
            int maxMap = gameManager.maxMap;
            initializer.mapObjectNames = new String[maxMap][gameManager.object[1].length];
            initializer.mapObjectWorldX = new int[maxMap][gameManager.object[1].length];
            initializer.mapObjectWorldY = new int[maxMap][gameManager.object[1].length];
            initializer.mapObjectLootNames = new String[maxMap][gameManager.object[1].length];
            initializer.mapObjectOpened = new boolean[maxMap][gameManager.object[1].length];

            for (int mapNum = 0; mapNum < maxMap; mapNum++) {
                for (int i = 0; i < gameManager.object[1].length; i++) {
                    if (gameManager.object[mapNum][i] == null) {
                        initializer.mapObjectNames[mapNum][i] = "NA";
                    } else {
                        initializer.mapObjectNames[mapNum][i] = gameManager.object[mapNum][i].name;
                        initializer.mapObjectWorldX[mapNum][i] = gameManager.object[mapNum][i].worldX;
                        initializer.mapObjectWorldY[mapNum][i] = gameManager.object[mapNum][i].worldY;
                        if (gameManager.object[mapNum][i].loot != null) {
                            initializer.mapObjectLootNames[mapNum][i] = gameManager.object[mapNum][i].loot.name;
                        }
                        initializer.mapObjectOpened[mapNum][i] = gameManager.object[mapNum][i].opened;
                    }
                }
            }
            //Write object in file
            os.writeObject(initializer);
        } catch (IOException e) {
            System.err.println("Exception " + e.getMessage() + " in " + getClass().getSimpleName());
        }
    }

    /*
     * Load method to get data info about player
     */
    public boolean load() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            //Read object from file
            DataInitializer initializer = (DataInitializer) is.readObject();
            gameManager.player.level = initializer.level;
            gameManager.player.maxHp = initializer.maxHp;
            gameManager.player.hp = initializer.hp;
            gameManager.player.speed = initializer.speed;
            gameManager.player.maxMana = initializer.maxMana;
            gameManager.player.mana = initializer.mana;
            gameManager.player.strength = initializer.strength;
            gameManager.player.agility = initializer.agility;
            gameManager.player.xp = initializer.xp;
            gameManager.player.nextLevelXp = initializer.nextLevelXp;
            gameManager.player.coin = initializer.coin;
            gameManager.player.playerSkin = initializer.playerSkin;

            // Load play time and game timer
            gameManager.playTime = initializer.savedPlayTime;
            gameManager.gameTimer = initializer.savedGameTimer;
            //Load skin of player
            gameManager.player.loadImages();

            //Player inventory
            gameManager.player.inventory.clear();
            for (int i = 0; i < initializer.itemNames.size(); i++) {
                gameManager.player.inventory.add(gameManager.entityGenerator.getObject(initializer.itemNames.get(i)));
                gameManager.player.inventory.get(i).amount = initializer.itemAmounts.get(i);
            }
            //Player equipment
            gameManager.player.currentWeapon = gameManager.player.inventory.get(initializer.currentWeaponSlot);
            gameManager.player.currentShield = gameManager.player.inventory.get(initializer.currentShieldSlot);
            gameManager.player.getAttack();
            gameManager.player.getDefense();
            gameManager.player.getAttackImage();

            //Objects on map
            for (int mapNum = 0; mapNum < gameManager.maxMap; mapNum++) {
                for (int i = 0; i < gameManager.object[1].length; i++) {
                    if (initializer.mapObjectNames[mapNum][i].equals("NA")) {
                        gameManager.object[mapNum][i] = null;
                    } else {
                        gameManager.object[mapNum][i]
                                = gameManager.entityGenerator.getObject(initializer.mapObjectNames[mapNum][i]);
                        gameManager.object[mapNum][i].worldX = initializer.mapObjectWorldX[mapNum][i];
                        gameManager.object[mapNum][i].worldY = initializer.mapObjectWorldY[mapNum][i];
                        if (initializer.mapObjectLootNames[mapNum][i] != null) {
                            gameManager.object[mapNum][i].setLoot(
                                    gameManager.entityGenerator.getObject(initializer.mapObjectLootNames[mapNum][i]));
                        }
                        gameManager.object[mapNum][i].opened = initializer.mapObjectOpened[mapNum][i];
                        if (gameManager.object[mapNum][i].opened) {
                            gameManager.object[mapNum][i].down = gameManager.object[mapNum][i].image2;
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Exception " + e.getMessage() + " in " + getClass().getSimpleName());
            return false;
        }
        return true;
    }

    public void loadTitleData() {
        if (!hasFile) {
            // Set to default values or fallback values
            gameManager.levelForTitle = gameManager.player.level;
            gameManager.coinAmount = gameManager.player.coin;
            gameManager.playTime = gameManager.getGameTimer();
        } else {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                // Read object from file
                DataInitializer initializer = (DataInitializer) is.readObject();
                gameManager.levelForTitle = initializer.level;
                gameManager.coinAmount = initializer.coin;
                gameManager.playTime = initializer.savedPlayTime;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Exception " + e.getMessage() + " in " + getClass().getSimpleName());
            }
        }
    }
}
