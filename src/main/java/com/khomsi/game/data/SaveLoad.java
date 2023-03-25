package main.java.com.khomsi.game.data;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.equipment.AxeObject;
import main.java.com.khomsi.game.objects.equipment.GoldShieldObject;
import main.java.com.khomsi.game.objects.equipment.MetalShieldObject;
import main.java.com.khomsi.game.objects.equipment.MetalSwordObject;
import main.java.com.khomsi.game.objects.gui.HeartObject;
import main.java.com.khomsi.game.objects.gui.ManaObject;
import main.java.com.khomsi.game.objects.interact.ChestObject;
import main.java.com.khomsi.game.objects.interact.CoinBObject;
import main.java.com.khomsi.game.objects.interact.KeyObject;
import main.java.com.khomsi.game.objects.light.LanternObject;
import main.java.com.khomsi.game.objects.outside.BedObject;
import main.java.com.khomsi.game.objects.outside.DoorObject;
import main.java.com.khomsi.game.objects.outside.TentObject;
import main.java.com.khomsi.game.objects.spells.PotionObject;

import java.io.*;

public class SaveLoad {
    GameManager gameManager;

    public SaveLoad(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    //    public Entity getObject(String itemNames) {
//        Entity object = null;
//        switch (itemNames) {
//            case AxeObject.OBJ_NAME -> object = new AxeObject(gameManager);
//            case GoldShieldObject.OBJ_NAME -> object = new GoldShieldObject(gameManager);
//            case MetalShieldObject.OBJ_NAME -> object = new MetalShieldObject(gameManager);
//            case MetalSwordObject.OBJ_NAME -> object = new MetalSwordObject(gameManager);
//            case KeyObject.OBJ_NAME -> object = new KeyObject(gameManager);
//            case LanternObject.OBJ_NAME -> object = new LanternObject(gameManager);
//            case TentObject.OBJ_NAME -> object = new TentObject(gameManager);
//            case PotionObject.OBJ_NAME -> object = new PotionObject(gameManager);
//            case ChestObject.OBJ_NAME -> object = new ChestObject(gameManager);
//        }
//        return object;
//    }

    //FIXME re-write the code
    public Entity getObject(String itemNames) {
        Entity object = null;
        switch (itemNames) {
            case "Axe" -> object = new AxeObject(gameManager);
            case "Gold Shield" -> object = new GoldShieldObject(gameManager);
            case "Metal Shield" -> object = new MetalShieldObject(gameManager);
            case "Metal Sword" -> object = new MetalSwordObject(gameManager);
            case "Key" -> object = new KeyObject(gameManager);
            case "Lantern" -> object = new LanternObject(gameManager);
            case "Tent" -> object = new TentObject(gameManager);
            case "Potion" -> object = new PotionObject(gameManager);
            case "Chest" -> object = new ChestObject(gameManager);
            case "Door" -> object = new DoorObject(gameManager);
            case "Coin Bronze" -> object = new CoinBObject(gameManager);
            case "Mana" -> object = new ManaObject(gameManager);
            case "Heart" -> object = new HeartObject(gameManager);
            case "Bed" -> object = new BedObject(gameManager);
        }
        return object;
    }

    /*
     * Save method to save data info about player
     */
    public void save() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("save.dat"))) {
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

            //Player's inventory
            for (int i = 0; i < gameManager.player.inventory.size(); i++) {
                initializer.itemNames.add(gameManager.player.inventory.get(i).name);
                initializer.itemAmounts.add(gameManager.player.inventory.get(i).amount);
            }
            //Player equipment
            initializer.currentWeaponSlot = gameManager.player.getCurrentWeaponSlot();
            initializer.currentShieldSlot = gameManager.player.getCurrentShieldSlot();

            //FIXME re-write the code
//            //Objects on map
//            int maxMap = gameManager.maxMap;
//            initializer.mapObjectNames = new String[maxMap][gameManager.object[1].length];
//            initializer.mapObjectWorldX = new int[maxMap][gameManager.object[1].length];
//            initializer.mapObjectWorldY = new int[maxMap][gameManager.object[1].length];
//            initializer.mapObjectLootNames = new String[maxMap][gameManager.object[1].length];
//            initializer.mapObjectOpened = new boolean[maxMap][gameManager.object[1].length];
//
//            for (int mapNum = 0; mapNum < maxMap; mapNum++) {
//                for (int i = 0; i < gameManager.object[1].length; i++) {
//                    if (gameManager.object[mapNum][i] == null) {
//                        initializer.mapObjectNames[mapNum][i] = "NA";
//                    } else {
//                        initializer.mapObjectNames[mapNum][i] = gameManager.object[mapNum][i].name;
//                        initializer.mapObjectWorldX[mapNum][i] = gameManager.object[mapNum][i].worldX;
//                        initializer.mapObjectWorldY[mapNum][i] = gameManager.object[mapNum][i].worldY;
//                        if (gameManager.object[mapNum][i].loot != null) {
//                            initializer.mapObjectLootNames[mapNum][i] = gameManager.object[mapNum][i].loot.name;
//                        }
//                        initializer.mapObjectOpened[mapNum][i] = gameManager.object[mapNum][i].opened;
//                    }
//                }
//            }
            //Write object in file
            os.writeObject(initializer);
        } catch (IOException e) {
            System.err.println("Exception " + e.getMessage() + " in " + getClass().getSimpleName());
        }
    }

    /*
     * Load method to get data info about player
     */
    public void load() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("save.dat"))) {
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
            //Load skin of player
            gameManager.player.loadImages();

            //Player inventory
            gameManager.player.inventory.clear();
            for (int i = 0; i < initializer.itemNames.size(); i++) {
                gameManager.player.inventory.add(getObject(initializer.itemNames.get(i)));
                gameManager.player.inventory.get(i).amount = initializer.itemAmounts.get(i);
            }
            //Player equipment
            gameManager.player.currentWeapon = gameManager.player.inventory.get(initializer.currentWeaponSlot);
            gameManager.player.currentShield = gameManager.player.inventory.get(initializer.currentShieldSlot);
            gameManager.player.getAttack();
            gameManager.player.getDefense();
            gameManager.player.getAttackImage();


            //FIXME re-write the code
            //Objects on map
//            for (int mapnum = 0; mapnum < gameManager.maxMap; mapnum++) {
//                for (int i = 0; i < gameManager.object[1].length; i++) {
//                    if (initializer.mapObjectNames[mapnum][i].equals("NA")) {
//                        gameManager.object[mapnum][i] = null;
//                    } else {
//                        gameManager.object[mapnum][i] = getObject(initializer.mapObjectNames[mapnum][i]);
//                        gameManager.object[mapnum][i].worldX = initializer.mapObjectWorldX[mapnum][i];
//                        gameManager.object[mapnum][i].worldY = initializer.mapObjectWorldY[mapnum][i];
//                        if (initializer.mapObjectLootNames != null) {
//                            gameManager.object[mapnum][i].loot = getObject(initializer.mapObjectLootNames[mapnum][i]);
//                        }
//                        gameManager.object[mapnum][i].opened = initializer.mapObjectOpened[mapnum][i];
//                        if (gameManager.object[mapnum][i].opened) {
//                            gameManager.object[mapnum][i].down = gameManager.object[mapnum][i].image2;
//                        }
//                    }
//                }
//            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Exception " + e.getMessage() + " in " + getClass().getSimpleName());
        }
    }
}
