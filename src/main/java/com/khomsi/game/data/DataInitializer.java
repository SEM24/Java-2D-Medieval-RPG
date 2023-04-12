package main.java.com.khomsi.game.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataInitializer implements Serializable {
    //Player stats
    int level;
    int maxHp;
    int hp;
    int speed;
    int maxMana;
    int mana;
    int strength;
    int agility;
    int xp;
    int nextLevelXp;
    int coin;
    int playerSkin;

    //Player inventory
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    //Objects on map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
}