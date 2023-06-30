package com.khomsi.game.tiles.animated.water.wave.right;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveRightUp extends WaveTile {
    public WaveRightUp(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_08");
        up1 = setup("/tilesinteractive/water/waves/water_waves_09");
        up2 = setup("/tilesinteractive/water/waves/water_waves_10");
        up3 = setup("/tilesinteractive/water/waves/water_waves_11");

        down = setup("/tilesinteractive/water/waves/water_waves_08");
        down1 = setup("/tilesinteractive/water/waves/water_waves_09");
        down2 = setup("/tilesinteractive/water/waves/water_waves_10");
        down3 = setup("/tilesinteractive/water/waves/water_waves_11");

        left = setup("/tilesinteractive/water/waves/water_waves_08");
        left1 = setup("/tilesinteractive/water/waves/water_waves_09");
        left2 = setup("/tilesinteractive/water/waves/water_waves_10");
        left3 = setup("/tilesinteractive/water/waves/water_waves_11");

        right = setup("/tilesinteractive/water/waves/water_waves_08");
        right1 = setup("/tilesinteractive/water/waves/water_waves_09");
        right2 = setup("/tilesinteractive/water/waves/water_waves_10");
        right3 = setup("/tilesinteractive/water/waves/water_waves_11");
    }
}