package com.khomsi.game.tiles.animated.water.wave;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;

public class WaveTile extends AnimatedTile {
    public WaveTile(GameManager gameManager) {
        super(gameManager);

    }
    @Override
    public void update() {
        int entitySpeed = 8;
        //Changing sprites, depends on nums
        spriteCounter++;
        if (spriteCounter <= entitySpeed) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed && spriteCounter <= entitySpeed + 8) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed + 5 && spriteCounter <= entitySpeed + 15) {
            spriteNum = 3;
        }
        if (spriteCounter > entitySpeed + 13 && spriteCounter <= entitySpeed + 21) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed + 21) {
            spriteCounter = 0;
        }
    }
}
