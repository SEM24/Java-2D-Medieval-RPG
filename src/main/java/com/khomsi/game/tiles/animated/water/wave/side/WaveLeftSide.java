package com.khomsi.game.tiles.animated.water.wave.side;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveLeftSide extends WaveTile {
    public WaveLeftSide(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_20");
        up1 = setup("/tilesinteractive/water/waves/water_waves_21");
        up2 = setup("/tilesinteractive/water/waves/water_waves_22");
        up3 = setup("/tilesinteractive/water/waves/water_waves_23");

        down = setup("/tilesinteractive/water/waves/water_waves_20");
        down1 = setup("/tilesinteractive/water/waves/water_waves_21");
        down2 = setup("/tilesinteractive/water/waves/water_waves_22");
        down3 = setup("/tilesinteractive/water/waves/water_waves_23");

        left = setup("/tilesinteractive/water/waves/water_waves_20");
        left1 = setup("/tilesinteractive/water/waves/water_waves_21");
        left2 = setup("/tilesinteractive/water/waves/water_waves_22");
        left3 = setup("/tilesinteractive/water/waves/water_waves_23");

        right = setup("/tilesinteractive/water/waves/water_waves_20");
        right1 = setup("/tilesinteractive/water/waves/water_waves_21");
        right2 = setup("/tilesinteractive/water/waves/water_waves_22");
        right3 = setup("/tilesinteractive/water/waves/water_waves_23");
    }
}