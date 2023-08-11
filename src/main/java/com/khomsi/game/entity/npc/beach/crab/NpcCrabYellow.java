package com.khomsi.game.entity.npc.beach.crab;

import com.khomsi.game.entity.npc.beach.CrabEntity;
import com.khomsi.game.main.GameManager;

public class NpcCrabYellow extends CrabEntity {

    public NpcCrabYellow(GameManager gameManager) {
        super(gameManager);
        getImage("/npc/beach/crab/yellow/crab_yellow_");
    }
}