package main.java.com.khomsi.game.entity.npc.object;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.outside.DungeonDoorClosedObject;
import main.java.com.khomsi.game.objects.outside.DungeonDoorOpenedObject;
import main.java.com.khomsi.game.tilesinteractive.InteractiveTile;
import main.java.com.khomsi.game.tilesinteractive.SwitchPress;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        List<InteractiveTile> plates = new ArrayList<>();
        List<Entity> rocks = new ArrayList<>();

        //Create plates
        for (int i = 0; i < gameManager.interactTile[1].length; i++) {
            if (gameManager.interactTile[gameManager.currentMap][i] != null &&
                    gameManager.interactTile[gameManager.currentMap][i] instanceof SwitchPress) {
                plates.add(gameManager.interactTile[gameManager.currentMap][i]);
            }
        }
        //Create rocks
        for (int i = 0; i < gameManager.npcList[1].length; i++) {
            if (gameManager.npcList[gameManager.currentMap][i] != null &&
                    gameManager.npcList[gameManager.currentMap][i] instanceof NpcRock) {
                rocks.add(gameManager.npcList[gameManager.currentMap][i]);
            }
        }
        int count = 0;
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
        //Scan the rocks
        for (Entity rock : rocks) {
            //Count the rocks on plates
            if (rock.linkedEntity != null) count++;
        }
        //If all the rocks are on the plates, the door opens
        if (count == rocks.size()) {
            for (int i = 0; i < gameManager.object[1].length; i++) {
                if (gameManager.object[gameManager.currentMap][i] != null &&
                        gameManager.object[gameManager.currentMap][i] instanceof DungeonDoorClosedObject) {
                    gameManager.object[gameManager.currentMap][i] = null;
                    gameManager.object[gameManager.currentMap][i] = new DungeonDoorOpenedObject(gameManager);
                    gameManager.object[gameManager.currentMap][i].worldX = GameManager.TILE_SIZE * 29;
                    gameManager.object[gameManager.currentMap][i].worldY = GameManager.TILE_SIZE * 19;
                    i++;
                    //TODO change SE to louder one
                    gameManager.playSE(4);
                }
            }
        }
    }
}
