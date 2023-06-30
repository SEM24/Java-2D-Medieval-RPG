package com.khomsi.game.tiles.animated.water.dungeon.lava;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.water.dungeon.LavaTile;

public class LavaRedUp extends LavaTile {
    public LavaRedUp(GameManager gameManager) {
        super(gameManager);
        getImage();
    }

    public void getImage() {
        up = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_10");
        up1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_11");
        up2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_12");
        up3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_13");

        down = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_10");
        down1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_11");
        down2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_12");
        down3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_13");

        left = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_10");
        left1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_11");
        left2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_12");
        left3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_13");

        right = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_10");
        right1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_11");
        right2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_12");
        right3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_13");
    }
}