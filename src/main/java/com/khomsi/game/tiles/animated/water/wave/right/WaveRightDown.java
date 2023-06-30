package com.khomsi.game.tiles.animated.water.wave.right;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveRightDown extends WaveTile {
    public WaveRightDown(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_28");
        up1 = setup("/tilesinteractive/water/waves/water_waves_29");
        up2 = setup("/tilesinteractive/water/waves/water_waves_30");
        up3 = setup("/tilesinteractive/water/waves/water_waves_31");

        down = setup("/tilesinteractive/water/waves/water_waves_28");
        down1 = setup("/tilesinteractive/water/waves/water_waves_29");
        down2 = setup("/tilesinteractive/water/waves/water_waves_30");
        down3 = setup("/tilesinteractive/water/waves/water_waves_31");

        left = setup("/tilesinteractive/water/waves/water_waves_28");
        left1 = setup("/tilesinteractive/water/waves/water_waves_29");
        left2 = setup("/tilesinteractive/water/waves/water_waves_30");
        left3 = setup("/tilesinteractive/water/waves/water_waves_31");

        right = setup("/tilesinteractive/water/waves/water_waves_28");
        right1 = setup("/tilesinteractive/water/waves/water_waves_29");
        right2 = setup("/tilesinteractive/water/waves/water_waves_30");
        right3 = setup("/tilesinteractive/water/waves/water_waves_31");
    }
}