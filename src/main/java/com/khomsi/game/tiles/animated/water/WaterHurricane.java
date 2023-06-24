package com.khomsi.game.tiles.animated.water;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;

public class WaterHurricane extends AnimatedTile {
    public WaterHurricane(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/hurricane/water_4");
        up1 = setup("/tilesinteractive/water/hurricane/water_5");
        up2 = setup("/tilesinteractive/water/hurricane/water_6");
        up3 = setup("/tilesinteractive/water/hurricane/water_7");

        down = setup("/tilesinteractive/water/hurricane/water_4");
        down1 = setup("/tilesinteractive/water/hurricane/water_5");
        down2 = setup("/tilesinteractive/water/hurricane/water_6");
        down3 = setup("/tilesinteractive/water/hurricane/water_7");

        left = setup("/tilesinteractive/water/hurricane/water_4");
        left1 = setup("/tilesinteractive/water/hurricane/water_5");
        left2 = setup("/tilesinteractive/water/hurricane/water_6");
        left3 = setup("/tilesinteractive/water/hurricane/water_7");

        right = setup("/tilesinteractive/water/hurricane/water_4");
        right1 = setup("/tilesinteractive/water/hurricane/water_5");
        right2 = setup("/tilesinteractive/water/hurricane/water_6");
        right3 = setup("/tilesinteractive/water/hurricane/water_7");
    }
}
