package com.khomsi.game.tiles.animated.water.wave;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;

import java.awt.*;

public class WaveTile extends AnimatedTile {
    public WaveTile(GameManager gameManager) {
        super(gameManager);
        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
    }
    @Override
    public void update() {
        int entitySpeed = 5;
        //Changing sprites, depends on nums
        spriteCounter++;
        if (spriteCounter <= entitySpeed) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed && spriteCounter <= entitySpeed + 5) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 5 && spriteCounter <= entitySpeed + 13) {
            spriteNum = 3;
        }
        if (spriteCounter > entitySpeed + 13 && spriteCounter <= entitySpeed + 18) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 18) {
            spriteCounter = 0;
        }
    }
}
