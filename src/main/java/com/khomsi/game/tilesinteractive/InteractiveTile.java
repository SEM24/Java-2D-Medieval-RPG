package main.java.com.khomsi.game.tilesinteractive;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

public class InteractiveTile extends Entity {
    GameManager gameManager;

    public boolean destructible = false;

    public InteractiveTile(GameManager gameManager, int col, int row) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    public boolean isCorrectWeapon(Entity entity) {
        return false;
    }

    @Override
    public void update() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public InteractiveTile getDestroyedForm() {
        return null;
    }

    public void playSE() {

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        //actual coords to draw the stuff on game screen
        int screenX = worldX - gameManager.player.worldX + gameManager.player.screenX;
        int screenY = worldY - gameManager.player.worldY + gameManager.player.screenY;

        if (worldX + GameManager.TILE_SIZE > gameManager.player.worldX - gameManager.player.screenX &&
                worldX - GameManager.TILE_SIZE < gameManager.player.worldX + gameManager.player.screenX &&
                worldY + GameManager.TILE_SIZE > gameManager.player.worldY - gameManager.player.screenY &&
                worldY - GameManager.TILE_SIZE < gameManager.player.worldY + gameManager.player.screenY) {

            graphics2D.drawImage(down, screenX, screenY, null);
        }
    }
}
