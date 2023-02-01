package main.java.com.khomsi.game.tilesinteractive;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

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
        hp = 3;
    }

    @Override
    public boolean isCorrectWeapon(Entity entity) {
        return entity.currentWeapon.type == TYPE_AXE;
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
}
