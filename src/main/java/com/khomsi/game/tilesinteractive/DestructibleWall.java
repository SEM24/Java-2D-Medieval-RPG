package main.java.com.khomsi.game.tilesinteractive;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

public class DestructibleWall extends InteractiveTile {
    public DestructibleWall(GameManager gameManager, int col, int row) {
        super(gameManager, col, row);
        this.worldX = GameManager.TILE_SIZE * col;
        this.worldY = GameManager.TILE_SIZE * row;
        down = setup("/tilesinteractive/dungeon/wall_missing_brick");
        destructible = true;
        hp = 3;
    }

    @Override
    public boolean isCorrectWeapon(Entity entity) {
        return entity.currentWeapon.type == TYPE_HOOK;
    }

    @Override
    public Color getParticleColor() {
        return new Color(66, 40, 53);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        return null;
    }

    @Override
    public void playSE() {
        gameManager.playSE(13);
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
}
