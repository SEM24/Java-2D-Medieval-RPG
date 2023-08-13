package com.khomsi.game.entity.npc.dungeon;

import com.khomsi.game.entity.npc.beach.NpcRock;
import com.khomsi.game.main.GameManager;

public class NpcRockMovable extends NpcRock {
    public NpcRockMovable(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void moveObj(String direction) {
        this.direction = direction;
        changeDirection(direction);
    }
}
