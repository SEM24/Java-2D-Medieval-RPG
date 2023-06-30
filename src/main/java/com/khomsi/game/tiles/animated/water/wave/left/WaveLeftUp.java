package com.khomsi.game.tiles.animated.water.wave.left;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.AnimatedTile;
import com.khomsi.game.tiles.animated.water.wave.WaveTile;

public class WaveLeftUp extends WaveTile {
    public WaveLeftUp(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/water/waves/water_waves_00");
        up1 = setup("/tilesinteractive/water/waves/water_waves_01");
        up2 = setup("/tilesinteractive/water/waves/water_waves_02");
        up3 = setup("/tilesinteractive/water/waves/water_waves_03");

        down = setup("/tilesinteractive/water/waves/water_waves_00");
        down1 = setup("/tilesinteractive/water/waves/water_waves_01");
        down2 = setup("/tilesinteractive/water/waves/water_waves_02");
        down3 = setup("/tilesinteractive/water/waves/water_waves_03");

        left = setup("/tilesinteractive/water/waves/water_waves_00");
        left1 = setup("/tilesinteractive/water/waves/water_waves_01");
        left2 = setup("/tilesinteractive/water/waves/water_waves_02");
        left3 = setup("/tilesinteractive/water/waves/water_waves_03");

        right = setup("/tilesinteractive/water/waves/water_waves_00");
        right1 = setup("/tilesinteractive/water/waves/water_waves_01");
        right2 = setup("/tilesinteractive/water/waves/water_waves_02");
        right3 = setup("/tilesinteractive/water/waves/water_waves_03");
    }
}