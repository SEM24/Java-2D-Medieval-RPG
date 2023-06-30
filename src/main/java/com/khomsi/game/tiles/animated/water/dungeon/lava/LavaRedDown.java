package com.khomsi.game.tiles.animated.water.dungeon.lava;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.water.dungeon.LavaTile;

public class LavaRedDown extends LavaTile {
    public LavaRedDown(GameManager gameManager) {
        super(gameManager);
        getImage();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void getImage() {
        up = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_14");
        up1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_15");
        up2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_16");
        up3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_17");


        down = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_14");
        down1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_15");
        down2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_16");
        down3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_17");

        left = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_14");
        left1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_15");
        left2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_16");
        left3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_17");

        right = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_14");
        right1 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_15");
        right2 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_16");
        right3 = setup("/tilesinteractive/dungeon/lava_statue/red/lava_statue_17");
    }
}