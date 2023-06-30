package com.khomsi.game.tiles.animated.water.dungeon.lava;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.tiles.animated.water.dungeon.LavaTile;

public class LavaGreenDown extends LavaTile {
    public LavaGreenDown(GameManager gameManager) {
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
        up = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_05");
        up1 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_06");
        up2 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_07");
        up3 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_08");


        down = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_05");
        down1 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_06");
        down2 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_07");
        down3 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_08");

        left = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_05");
        left1 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_06");
        left2 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_07");
        left3 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_08");

        right = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_05");
        right1 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_06");
        right2 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_07");
        right3 = setup("/tilesinteractive/dungeon/lava_statue/green/lava_statue_08");
    }
}