package com.khomsi.game.tiles.interactive;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.objects.spells.HeartObject;

import java.awt.*;
import java.util.Random;

public class Bush extends InteractiveTile {
    public Bush(GameManager gameManager, int col, int row) {
        super(gameManager, col, row);
        this.worldX = GameManager.TILE_SIZE * col;
        this.worldY = GameManager.TILE_SIZE * row;

        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        down = setup("/tilesinteractive/bush");
        destructible = true;
        hp = 1;
    }

    @Override
    public boolean isCorrectWeapon(Entity entity) {
        return entity.currentWeapon.type == TYPE_AXE || entity.currentWeapon.type == TYPE_SWORD;
    }

    @Override
    public InteractiveTile getDestroyedForm() {
//        return new BushIT(gameManager,
//                worldX / GameManager.TILE_SIZE, worldY / GameManager.TILE_SIZE);
        return null;
    }

    @Override
    public void playSE() {
        gameManager.playSE(13);
    }

    @Override
    public Color getParticleColor() {
        return new Color(59, 125, 79);
    }

    @Override
    public int getParticleSize() {
        int size = 6;
        return size; // 6 pixels
    }

    @Override
    public int getParticleSpeed() {
        int speed = 1;
        return speed; //speed of particle
    }

    @Override
    public int getParticleMaxHp() {
        int maxHp = 20;
        return maxHp;
    }

    @Override
    public void checkDrop() {
        int drop = new Random().nextInt(100) + 1;
        if (drop <= 30) {  // 30% chance for coin
            dropItem(new CoinBObject(gameManager));
        } else if (drop > 30 && drop <= 45) {  // 15% chance for heart
            dropItem(new HeartObject(gameManager));
        }
    }
}
