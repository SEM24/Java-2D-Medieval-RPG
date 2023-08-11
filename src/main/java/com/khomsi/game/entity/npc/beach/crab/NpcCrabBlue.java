package com.khomsi.game.entity.npc.beach.crab;

import com.khomsi.game.entity.npc.beach.CrabEntity;
import com.khomsi.game.main.GameManager;

public class NpcCrabBlue extends CrabEntity {

    public NpcCrabBlue(GameManager gameManager) {
        super(gameManager);
        getImage("/npc/beach/crab/blue/crab_blue_");
    }
}
