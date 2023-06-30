package com.khomsi.game.tiles.animated.water.wave.center;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveCenterUp extends WaveTile {
    public WaveCenterUp(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_04");
        up1 = setup("/tilesinteractive/water/waves/water_waves_05");
        up2 = setup("/tilesinteractive/water/waves/water_waves_06");
        up3 = setup("/tilesinteractive/water/waves/water_waves_07");

        down = setup("/tilesinteractive/water/waves/water_waves_04");
        down1 = setup("/tilesinteractive/water/waves/water_waves_05");
        down2 = setup("/tilesinteractive/water/waves/water_waves_06");
        down3 = setup("/tilesinteractive/water/waves/water_waves_07");

        left = setup("/tilesinteractive/water/waves/water_waves_04");
        left1 = setup("/tilesinteractive/water/waves/water_waves_05");
        left2 = setup("/tilesinteractive/water/waves/water_waves_06");
        left3 = setup("/tilesinteractive/water/waves/water_waves_07");

        right = setup("/tilesinteractive/water/waves/water_waves_04");
        right1 = setup("/tilesinteractive/water/waves/water_waves_05");
        right2 = setup("/tilesinteractive/water/waves/water_waves_06");
        right3 = setup("/tilesinteractive/water/waves/water_waves_07");
    }
}