package com.khomsi.game.objects.editor;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.editor.placement.*;

public class PlaceObjects {
    private final GameManager gameManager;
    public static final int MAP_MAIN = 0;
    public static final int MAP_DUNGEON = 1;
    public static final int MAP_HOUSE_BEACH = 2;

    public PlaceObjects(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setObjects() {
        ObjectPlacement objectPlacement = new ObjectPlacement(gameManager);
        objectPlacement.placeObjectsOnMap0();
        objectPlacement.placeObjectsOnMapHouseBeach1();
        objectPlacement.objectsOnDungeonMap1();
    }

    public void setInteractiveTiles() {
        InteractiveTilePlacement interactiveTilePlacement = new InteractiveTilePlacement(gameManager);
        interactiveTilePlacement.placeInteractiveTilesOnMap0();
    }

    public void setAnimatedTiles() {
        AnimatedTilePlacement animatedTilePlacement = new AnimatedTilePlacement(gameManager);
        animatedTilePlacement.placeWaterAnimationOnMap0();
    }

    public void setNpc() {
        NpcPlacement npcPlacement = new NpcPlacement(gameManager);
        npcPlacement.placeNpcOnMap0();
        npcPlacement.placeNpcOnDungeonMap1();
    }

    public void setMobs() {
        MobPlacement mobPlacement = new MobPlacement(gameManager);
        mobPlacement.setMobsOnMap0();
        mobPlacement.setMobsOnDungeonMap1();
    }

    public void setTrapsOnDungeonMap1() {
        DungeonTrapsPlacement trapsPlacement = new DungeonTrapsPlacement(gameManager);
        trapsPlacement.trapsOnDungeonMap1();
    }
}