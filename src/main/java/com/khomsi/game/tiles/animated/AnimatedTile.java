package com.khomsi.game.tiles.animated;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class AnimatedTile extends Entity {
    GameManager gameManager;

    public AnimatedTile(GameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    @Override
    public void update() {
        int entitySpeed = 5;
        spriteCounter++;
        if (spriteCounter <= entitySpeed) {
            spriteNum = 0;
        }
        if (spriteCounter > entitySpeed && spriteCounter <= entitySpeed + 5) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed + 5 && spriteCounter <= entitySpeed + 13) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 13 && spriteCounter <= entitySpeed + 18) {
            spriteNum = 3;
        }
        if (spriteCounter > entitySpeed + 18) {
            spriteCounter = 0;
        }
    }
}
