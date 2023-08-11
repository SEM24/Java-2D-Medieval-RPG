package com.khomsi.game.entity.npc.beach.crab;

import com.khomsi.game.entity.npc.beach.CrabEntity;
import com.khomsi.game.main.GameManager;

public class NpcCrabRed extends CrabEntity {

    public NpcCrabRed(GameManager gameManager) {
        super(gameManager);
        getImage("/npc/beach/crab/red/crab_red_");
    }
}