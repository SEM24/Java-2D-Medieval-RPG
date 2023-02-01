package main.java.com.khomsi.game.tilesinteractive;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

public class DryTree extends InteractiveTile {
    public DryTree(GameManager gameManager, int col, int row) {
        super(gameManager, col, row);
        this.worldX = GameManager.TILE_SIZE * col;
        this.worldY = GameManager.TILE_SIZE * row;

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 10;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //Boundaries of tree
        solidArea.width = 70;
        solidArea.height = 100;

        down = setup("/tilesinteractive/pine_tree_dry",
                (int) (GameManager.TILE_SIZE * 1.95), GameManager.TILE_SIZE * 2 + 30);
        destructible = true;
        hp = 3;
    }

    @Override
    public boolean isCorrectWeapon(Entity entity) {
        return entity.currentWeapon.type == TYPE_AXE;
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        return new TrunkIT(gameManager,
                worldX / GameManager.TILE_SIZE, worldY / GameManager.TILE_SIZE);
    }

    @Override
    public void playSE() {
        gameManager.playSE(13);
    }

    @Override
    public Color getParticleColor() {
        return new Color(124, 38, 53);
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
