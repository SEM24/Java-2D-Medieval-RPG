package main.java.com.khomsi.game.main;

import main.java.com.khomsi.game.entity.Entity;

public class CheckCollision {
    //TODO clean up the code
    GameManager panel;

    public CheckCollision(GameManager panel) {
        this.panel = panel;
    }

    public void checkTile(Entity entity) {
        //solid.x = 8, y = 16, width, height = 32
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / GameManager.TILE_SIZE;
        int entityRightCol = entityRightWorldX / GameManager.TILE_SIZE;
        int entityTopRow = entityTopWorldY / GameManager.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / GameManager.TILE_SIZE;


//        int tileNum1, tileNum2, tileNum3, tileNum4;
        int tileNum1, tileNum2;

        //Then based on the current worldX and worldY, check the tile number of these 4 tiles. Basically we find out what the solidArea's 4 corners are hitting.

//        tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
//        tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
//        tileNum3 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
//        tileNum4 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
//
//        activateCollision(entity, tileNum1, tileNum2, tileNum3, tileNum4);

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
        }
    }

    //Check if any side of tile has collision
    private void activateCollision(Entity entity, int tileNum1, int tileNum2) {
        if (panel.tileManager.tiles[tileNum1].collision ||
                panel.tileManager.tiles[tileNum2].collision) {
            entity.collisionOn = true;
        }
    }

    //Check if any side of tile has collision
//    private void activateCollision(Entity entity, int tileNum1, int tileNum2, int tileNum3, int tileNum4) {
//        //Then check if any of them are solid tile.
//        if (panel.tileManager.tiles[tileNum1].collision || panel.tileManager.tiles[tileNum2].collision ||
//                panel.tileManager.tiles[tileNum3].collision || panel.tileManager.tiles[tileNum4].collision) {
//            entity.collisionOn = true;
//        }
//    }


    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < panel.object.length; i++) {

            if (panel.object[i] != null) {
                //get entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the obj's solid pos
                panel.object[i].solidArea.x = panel.object[i].worldX
                        + panel.object[i].solidArea.x;
                panel.object[i].solidArea.y = panel.object[i].worldY
                        + panel.object[i].solidArea.y;

                //Change the movement collision and detect it
                entityDirectionSpeed(entity);
                index = checkCollisionForPlayer(entity, player, index, i, panel.object);

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.object[i].solidArea.x = panel.object[i].solidAreaDefaultX;
                panel.object[i].solidArea.y = panel.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private void entityDirectionSpeed(Entity entity) {
        switch (entity.direction) {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
    }

    private int checkCollisionForPlayer(Entity entity, boolean player, int index, int i, Entity[] object) {
        if (entity.solidArea.intersects(object[i].solidArea)) {
            if (object[i].collision) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
        return index;
    }

    //NPC/Enemies collision
    public int checkEntity(Entity entity, Entity[] entities) {
        int index = 999;

        for (int i = 0; i < entities.length; i++) {

            if (entities[i] != null) {
                //get entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the  entity's solid pos
                entities[i].solidArea.x = entities[i].worldX
                        + entities[i].solidArea.x;
                entities[i].solidArea.y = entities[i].worldY
                        + entities[i].solidArea.y;

                //Change the movement collision and detect it
                entityDirectionSpeed(entity);
                index = checkCollisionForEntity(entity, entities, index, i);

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                entities[i].solidArea.x = entities[i].solidAreaDefaultX;
                entities[i].solidArea.y = entities[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private int checkCollisionForEntity(Entity entity, Entity[] entities, int index, int i) {

        if (entity.solidArea.intersects(entities[i].solidArea)) {
            if (entities[i] != entity) {
                entity.collisionOn = true;
                index = i;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {
        boolean interactPlayer = false;
        //get entity's solid area pos
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get the obj's solid pos
        panel.player.solidArea.x = panel.player.worldX
                + panel.player.solidArea.x;
        panel.player.solidArea.y = panel.player.worldY
                + panel.player.solidArea.y;

        //Change the movement collision and detect it
        entityDirectionSpeed(entity);

        if (entity.solidArea.intersects(panel.player.solidArea)) {
            entity.collisionOn = true;
            interactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        panel.player.solidArea.x = panel.player.solidAreaDefaultX;
        panel.player.solidArea.y = panel.player.solidAreaDefaultY;
        return interactPlayer;
    }
}
