package com.khomsi.game.objects.hud;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class HeartHud extends Entity {

    public HeartHud(GameManager gameManager) {
        super(gameManager);
        down = setup("/objects/hud/heart/full/hud_00");
        down1 = setup("/objects/hud/heart/full/hud_01");
        down2 = setup("/objects/hud/heart/full/hud_02");

        image = setup("/objects/hud/heart/empty/hud_03");
        image2 = setup("/objects/hud/heart/empty/hud_04");
        image3 = setup("/objects/hud/heart/empty/hud_05");
    }
}

