package com.khomsi.game.tiles.animated.water.dungeon;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;

import java.awt.*;

public class LavaTile extends AnimatedTile {
    public LavaTile(GameManager gameManager) {
        super(gameManager);
        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 16;
        //TODO
//        type = TYPE_LIGHT;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
//        lightRadius = 150;
    }
    @Override
    public void update() {
        int entitySpeed = 18;
        spriteCounter++;
        if (spriteCounter <= entitySpeed) {
            spriteNum = 1;
        }
        if (spriteCounter > entitySpeed && spriteCounter <= entitySpeed + 14) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 14 && spriteCounter <= entitySpeed + 24) {
            spriteNum = 3;
        }
        if (spriteCounter > entitySpeed + 24 && spriteCounter <= entitySpeed + 33) {
            spriteNum = 2;
        }
        if (spriteCounter > entitySpeed + 33) {
            spriteCounter = 0;
        }
    }
}
