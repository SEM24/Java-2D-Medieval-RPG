package com.khomsi.game.main.tools;

import com.khomsi.game.entity.Entity;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity o1, Entity o2) {
        return Integer.compare(o1.worldY, o2.worldY);
    }
}
