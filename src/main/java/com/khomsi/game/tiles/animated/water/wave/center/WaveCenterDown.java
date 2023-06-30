package com.khomsi.game.tiles.animated.water.wave.center;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveCenterDown extends WaveTile {
    public WaveCenterDown(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_24");
        up1 = setup("/tilesinteractive/water/waves/water_waves_25");
        up2 = setup("/tilesinteractive/water/waves/water_waves_26");
        up3 = setup("/tilesinteractive/water/waves/water_waves_27");

        down = setup("/tilesinteractive/water/waves/water_waves_24");
        down1 = setup("/tilesinteractive/water/waves/water_waves_25");
        down2 = setup("/tilesinteractive/water/waves/water_waves_26");
        down3 = setup("/tilesinteractive/water/waves/water_waves_27");

        left = setup("/tilesinteractive/water/waves/water_waves_24");
        left1 = setup("/tilesinteractive/water/waves/water_waves_25");
        left2 = setup("/tilesinteractive/water/waves/water_waves_26");
        left3 = setup("/tilesinteractive/water/waves/water_waves_27");

        right = setup("/tilesinteractive/water/waves/water_waves_24");
        right1 = setup("/tilesinteractive/water/waves/water_waves_25");
        right2 = setup("/tilesinteractive/water/waves/water_waves_26");
        right3 = setup("/tilesinteractive/water/waves/water_waves_27");
    }
}