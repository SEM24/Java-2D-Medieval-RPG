package com.khomsi.game.tilesinteractive.animated.water;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tilesinteractive.animated.AnimatedTile;

public class WaterBubbles extends AnimatedTile {
    public WaterBubbles(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/bubbles/water_0");
        up1 = setup("/tilesinteractive/water/bubbles/water_1");
        up2 = setup("/tilesinteractive/water/bubbles/water_2");
        up3 = setup("/tilesinteractive/water/bubbles/water_3");

        down = setup("/tilesinteractive/water/bubbles/water_0");
        down1 = setup("/tilesinteractive/water/bubbles/water_1");
        down2 = setup("/tilesinteractive/water/bubbles/water_2");
        down3 = setup("/tilesinteractive/water/bubbles/water_3");

        left = setup("/tilesinteractive/water/bubbles/water_0");
        left1 = setup("/tilesinteractive/water/bubbles/water_1");
        left2 = setup("/tilesinteractive/water/bubbles/water_2");
        left3 = setup("/tilesinteractive/water/bubbles/water_3");

        right = setup("/tilesinteractive/water/bubbles/water_0");
        right1 = setup("/tilesinteractive/water/bubbles/water_1");
        right2 = setup("/tilesinteractive/water/bubbles/water_2");
        right3 = setup("/tilesinteractive/water/bubbles/water_3");
    }
}
