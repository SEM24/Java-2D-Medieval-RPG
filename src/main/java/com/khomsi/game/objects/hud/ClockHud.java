package com.khomsi.game.objects.hud;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

public class ClockHud extends Entity {

    public ClockHud(GameManager gameManager) {
        super(gameManager);
        down = setup("/clock/clock_nightfall");
        down1 = setup("/clock/clock_moon");
        image = setup("/clock/clock_sun");
        image2 = setup("/clock/clock_arrow");
        image3 = setup("/clock/clock_line");
    }
}
