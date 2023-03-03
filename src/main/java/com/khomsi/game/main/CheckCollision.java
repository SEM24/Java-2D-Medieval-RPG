package main.java.com.khomsi.game.main;

import main.java.com.khomsi.game.entity.Entity;

public class CheckCollision {
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

        int tileNum1, tileNum2;
        //Use a temporal direction when it's being knockBacked
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        switch (direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityLeftCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / GameManager.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[panel.currentMap][entityRightCol][entityBottomRow];
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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        for (int i = 0; i < panel.object[1].length; i++) {

            if (panel.object[panel.currentMap][i] != null) {
                //get entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the obj's solid pos
                panel.object[panel.currentMap][i].solidArea.x = panel.object[panel.currentMap][i].worldX
                        + panel.object[panel.currentMap][i].solidArea.x;
                panel.object[panel.currentMap][i].solidArea.y = panel.object[panel.currentMap][i].worldY
                        + panel.object[panel.currentMap][i].solidArea.y;

                //Change the movement collision and detect it
                switch (direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                index = checkCollisionForPlayer(entity, player, index, i, panel.object);

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.object[panel.currentMap][i].solidArea.x =
                        panel.object[panel.currentMap][i].solidAreaDefaultX;
                panel.object[panel.currentMap][i].solidArea.y =
                        panel.object[panel.currentMap][i].solidAreaDefaultY;
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

    private int checkCollisionForPlayer(Entity entity, boolean player, int index, int i, Entity[][] object) {
        if (entity.solidArea.intersects(object[panel.currentMap][i].solidArea)) {
            if (object[panel.currentMap][i].collision) {
                entity.collisionOn = true;
            }
            if (player) {
                index = i;
            }
        }
        return index;
    }

    //NPC/Enemies collision
    public int checkEntity(Entity entity, Entity[][] entities) {
        int index = 999;
        String direction = entity.direction;
        if (entity.knockBack) {
            direction = entity.knockBackDirection;
        }
        for (int i = 0; i < entities[1].length; i++) {

            if (entities[panel.currentMap][i] != null) {
                //get entity's solid area pos
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the  entity's solid pos
                entities[panel.currentMap][i].solidArea.x =
                        entities[panel.currentMap][i].worldX
                                + entities[panel.currentMap][i].solidArea.x;
                entities[panel.currentMap][i].solidArea.y =
                        entities[panel.currentMap][i].worldY + entities[panel.currentMap][i].solidArea.y;

                //Change the movement collision and detect it
                switch (direction) {
                    case "up" -> entity.solidArea.y -= entity.speed;
                    case "down" -> entity.solidArea.y += entity.speed;
                    case "left" -> entity.solidArea.x -= entity.speed;
                    case "right" -> entity.solidArea.x += entity.speed;
                }
                index = checkCollisionForEntity(entity, entities, index, i);

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                entities[panel.currentMap][i].solidArea.x = entities[panel.currentMap][i].solidAreaDefaultX;
                entities[panel.currentMap][i].solidArea.y = entities[panel.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private int checkCollisionForEntity(Entity entity, Entity[][] entities, int index, int i) {
        if (entity.solidArea.intersects(entities[panel.currentMap][i].solidArea)) {
            if (entities[panel.currentMap][i] != entity) {
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
