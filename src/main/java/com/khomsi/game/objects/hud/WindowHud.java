package com.khomsi.game.objects.hud;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class WindowHud extends Entity {
    public WindowHud(GameManager gameManager) {
        super(gameManager);

        image = setup("/objects/hud/window/coin");
        image2 = setup("/objects/hud/window/key");
    }
}
