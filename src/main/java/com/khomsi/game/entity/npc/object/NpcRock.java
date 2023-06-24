package com.khomsi.game.entity.npc.object;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.objects.outside.DungeonDoorClosedObject;
import com.khomsi.game.objects.outside.DungeonDoorOpenedObject;
import com.khomsi.game.tiles.interactive.InteractiveTile;
import com.khomsi.game.tiles.interactive.SwitchPress;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NpcRock extends Entity {
    public static final String NPC_NAME = "Rock";

    public NpcRock(GameManager gameManager) {
        super(gameManager);
        direction = "down";
        speed = 4;
        name = NPC_NAME;

        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //boundaries of npc
        solidArea.width = 44;
        solidArea.height = 40;
        dialogueSet = -1;

        getImage();
        setDialog();
    }

    @Override
    public void update() {
    }

    public void getImage() {
        up = setup("/objects/dungeon/rock_heavy");
        up1 = setup("/objects/dungeon/rock_heavy");
        up2 = setup("/objects/dungeon/rock_heavy");
        up3 = setup("/objects/dungeon/rock_heavy");
        down = setup("/objects/dungeon/rock_heavy");
        down1 = setup("/objects/dungeon/rock_heavy");
        down2 = setup("/objects/dungeon/rock_heavy");
        down3 = setup("/objects/dungeon/rock_heavy");
        left = setup("/objects/dungeon/rock_heavy");
        left1 = setup("/objects/dungeon/rock_heavy");
        left2 = setup("/objects/dungeon/rock_heavy");
        left3 = setup("/objects/dungeon/rock_heavy");
        right = setup("/objects/dungeon/rock_heavy");
        right1 = setup("/objects/dungeon/rock_heavy");
        right2 = setup("/objects/dungeon/rock_heavy");
        right3 = setup("/objects/dungeon/rock_heavy");
    }


    private void setDialog() {
        dialogues[0][0] = "I'm just a rock.\nWhat do you want from me?";
    }

    @Override
    public void setAction() {

    }

    //Maybe add special stuff, different custom text for this character

    @Override
    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);
        dialogueSet++;
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
        }
    }

    @Override
    public void moveObj(String direction) {
        this.direction = direction;
        changeDirection(direction);
        detectPlate();
    }

    public void detectPlate() {
        int currentMap = gameManager.currentMap;

        List<InteractiveTile> plates = Arrays.stream(gameManager.interactTile[currentMap])
                .filter(SwitchPress.class::isInstance)
                .toList();

        List<Entity> rocks = Arrays.stream(gameManager.npcList[currentMap])
                .filter(NpcRock.class::isInstance)
                .toList();

        //Scan the plates
        for (InteractiveTile plate : plates) {
            int xDistance = Math.abs(worldX - plate.worldX);
            int yDistance = Math.abs(worldY - plate.worldY);
            int distance = Math.max(xDistance, yDistance);
            if (distance < 8) {
                if (linkedEntity == null) {
                    linkedEntity = plate;
                    gameManager.playSE(21);
                }
            } else {
                if (linkedEntity == plate)
                    linkedEntity = null;
            }
        }
        // Count the rocks on plates
        long count = rocks.stream()
                .filter(rock -> rock.linkedEntity != null)
                .count();

        //If all the rocks are on the plates, the door opens
        if (count == rocks.size()) {
            Arrays.stream(gameManager.object[gameManager.currentMap])
                    .filter(DungeonDoorClosedObject.class::isInstance)
                    .findFirst()
                    .ifPresent(obj -> {
                        int index = IntStream.range(0, gameManager.object[gameManager.currentMap].length)
                                .filter(i -> gameManager.object[gameManager.currentMap][i] == obj)
                                .findFirst()
                                .orElse(-1);

                        if (index != -1) {
                            gameManager.object[gameManager.currentMap][index] = new DungeonDoorOpenedObject(gameManager);
                            gameManager.object[gameManager.currentMap][index].worldX = GameManager.TILE_SIZE * 29;
                            gameManager.object[gameManager.currentMap][index].worldY = GameManager.TILE_SIZE * 19;
                            // TODO: Change SE to louder one
                            gameManager.playSE(4);
                        }
                    });
        }
    }
}
