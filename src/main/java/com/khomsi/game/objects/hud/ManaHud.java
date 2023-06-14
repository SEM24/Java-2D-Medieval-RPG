package com.khomsi.game.objects.hud;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class ManaHud extends Entity {

    public ManaHud(GameManager gameManager) {
        super(gameManager);

        down = setup("/objects/hud/mana/full/hud_06");
        down1 = setup("/objects/hud/mana/full/hud_07");
        down2 = setup("/objects/hud/mana/full/hud_08");

        image = setup("/objects/hud/mana/empty/hud_09");
        image2 = setup("/objects/hud/mana/empty/hud_10");
        image3 = setup("/objects/hud/mana/empty/hud_11");
    }
}
