package com.khomsi.game.tiles.interactive;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;

import java.awt.*;

public class InteractiveTile extends Entity {
    protected GameManager gameManager;

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
            if (invincibleCounter > 20) {
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
