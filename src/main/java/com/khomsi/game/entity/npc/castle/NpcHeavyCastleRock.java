package com.khomsi.game.entity.npc.castle;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.entity.npc.dungeon.NpcHeavyDungeonRock;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.inside.castle.BigDoorClosedObject;
import com.khomsi.game.objects.inside.castle.BigDoorOpenedObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.khomsi.game.objects.inside.castle.DoorType.OPENED;

public class NpcHeavyCastleRock extends NpcHeavyDungeonRock {
    public static final String NPC_NAME = "Rock Castle";

    public NpcHeavyCastleRock(GameManager gameManager) {
        super(gameManager);
        name = NPC_NAME;
    }

    @Override
    public void moveObj(String direction) {
        this.direction = direction;
        changeDirection(direction);
        detectPlate();
    }

    public void detectPlate() {
        List<Entity> rocks = getEntitiesForPlates();
        // Count the rocks on plates
        long count = rocks.stream()
                .filter(rock -> rock.linkedEntity != null)
                .count();

        //If all the rocks are on the plates, the door opens
        if (count == rocks.size()) {
            Arrays.stream(gameManager.object[gameManager.currentMap])
                    .filter(BigDoorClosedObject.class::isInstance)
                    .findFirst()
                    .ifPresent(obj -> {
                        int index = IntStream.range(0, gameManager.object[gameManager.currentMap].length)
                                .filter(i -> gameManager.object[gameManager.currentMap][i] == obj)
                                .findFirst()
                                .orElse(-1);

                        if (index != -1) {
                            gameManager.object[gameManager.currentMap][index]
                                    = new BigDoorOpenedObject(gameManager, OPENED);
                            gameManager.object[gameManager.currentMap][index].worldX = GameManager.TILE_SIZE * 74;
                            gameManager.object[gameManager.currentMap][index].worldY = GameManager.TILE_SIZE * 52;
                            gameManager.playSE(4);
                        }
                    });
        }
    }
}
