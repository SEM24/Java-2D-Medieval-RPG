package com.khomsi.game.tiles.animated.water.wave.left;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveLeftDown extends WaveTile {
    public WaveLeftDown(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_16");
        up1 = setup("/tilesinteractive/water/waves/water_waves_17");
        up2 = setup("/tilesinteractive/water/waves/water_waves_18");
        up3 = setup("/tilesinteractive/water/waves/water_waves_19");

        down = setup("/tilesinteractive/water/waves/water_waves_16");
        down1 = setup("/tilesinteractive/water/waves/water_waves_17");
        down2 = setup("/tilesinteractive/water/waves/water_waves_18");
        down3 = setup("/tilesinteractive/water/waves/water_waves_19");

        left = setup("/tilesinteractive/water/waves/water_waves_16");
        left1 = setup("/tilesinteractive/water/waves/water_waves_17");
        left2 = setup("/tilesinteractive/water/waves/water_waves_18");
        left3 = setup("/tilesinteractive/water/waves/water_waves_19");

        right = setup("/tilesinteractive/water/waves/water_waves_16");
        right1 = setup("/tilesinteractive/water/waves/water_waves_17");
        right2 = setup("/tilesinteractive/water/waves/water_waves_18");
        right3 = setup("/tilesinteractive/water/waves/water_waves_19");
    }
}