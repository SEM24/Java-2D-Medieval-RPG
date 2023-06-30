package com.khomsi.game.tiles.animated.water.wave.side;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveRightSide extends WaveTile {
    public WaveRightSide(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_12");
        up1 = setup("/tilesinteractive/water/waves/water_waves_13");
        up2 = setup("/tilesinteractive/water/waves/water_waves_14");
        up3 = setup("/tilesinteractive/water/waves/water_waves_15");

        down = setup("/tilesinteractive/water/waves/water_waves_12");
        down1 = setup("/tilesinteractive/water/waves/water_waves_13");
        down2 = setup("/tilesinteractive/water/waves/water_waves_14");
        down3 = setup("/tilesinteractive/water/waves/water_waves_15");

        left = setup("/tilesinteractive/water/waves/water_waves_12");
        left1 = setup("/tilesinteractive/water/waves/water_waves_13");
        left2 = setup("/tilesinteractive/water/waves/water_waves_14");
        left3 = setup("/tilesinteractive/water/waves/water_waves_15");

        right = setup("/tilesinteractive/water/waves/water_waves_12");
        right1 = setup("/tilesinteractive/water/waves/water_waves_13");
        right2 = setup("/tilesinteractive/water/waves/water_waves_14");
        right3 = setup("/tilesinteractive/water/waves/water_waves_15");
    }
}