package main.java.com.khomsi.game.main;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.objects.SuperObject;

public class CheckCollision {
    GamePanel panel;

    public CheckCollision(GamePanel panel) {
        this.panel = panel;
    }

    public void checkTile(Entity entity) {
        //solid.x = 8, y = 16, width, height = 32
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / GamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / GamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / GamePanel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / GamePanel.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / GamePanel.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / GamePanel.TILE_SIZE;
                tileNum1 = panel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                activateCollision(entity, tileNum1, tileNum2);
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / GamePanel.TILE_SIZE;
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

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        index = checkCollisionForPlayer(entity, player, index, i, panel.object);
                        //if it's npc or someone else, they can't pick up tools
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        index = checkCollisionForPlayer(entity, player, index, i, panel.object);
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        index = checkCollisionForPlayer(entity, player, index, i, panel.object);
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        index = checkCollisionForPlayer(entity, player, index, i, panel.object);
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                panel.object[i].solidArea.x = panel.object[i].solidAreaDefaultX;
                panel.object[i].solidArea.y = panel.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private int checkCollisionForPlayer(Entity entity, boolean player, int index, int i, SuperObject[] object) {
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
                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        index = checkCollisionForNPC(entity, entities, index, i);
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        index = checkCollisionForNPC(entity, entities, index, i);
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        index = checkCollisionForNPC(entity, entities, index, i);
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        index = checkCollisionForNPC(entity, entities, index, i);
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                entities[i].solidArea.x = entities[i].solidAreaDefaultX;
                entities[i].solidArea.y = entities[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    private int checkCollisionForNPC(Entity entity, Entity[] entities, int index, int i) {
        if (entity.solidArea.intersects(entities[i].solidArea)) {
            entity.collisionOn = true;
            index = i;
        }
        return index;
    }

    public void checkPlayer(Entity entity) {
        //get entity's solid area pos
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        //get the obj's solid pos
        panel.player.solidArea.x = panel.player.worldX
                + panel.player.solidArea.x;
        panel.player.solidArea.y = panel.player.worldY
                + panel.player.solidArea.y;

        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(panel.player.solidArea)) {
                    entity.collisionOn = true;
                }
            }
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        panel.player.solidArea.x = panel.player.solidAreaDefaultX;
        panel.player.solidArea.y = panel.player.solidAreaDefaultY;
    }
}
