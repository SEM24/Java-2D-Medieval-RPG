package com.khomsi.game.main.tools;

import com.khomsi.game.main.GameManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tools {
    public BufferedImage scaledImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();

        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }

    //Optimizer the drawing of map
    public void optimizeMapDraw(Graphics2D graphics2D, BufferedImage image, GameManager gameManager,
                                int screenX, int screenY, int worldX, int worldY) {
        //use if case to optimize the game and not to draw whole map, that player even doesn't see
        //up, down, left, right checking (boundaries)
        if (worldX + GameManager.TILE_SIZE > gameManager.player.worldX - gameManager.player.screenX &&
                worldX - GameManager.TILE_SIZE < gameManager.player.worldX + gameManager.player.screenX &&
                worldY + GameManager.TILE_SIZE > gameManager.player.worldY - gameManager.player.screenY &&
                worldY - GameManager.TILE_SIZE < gameManager.player.worldY + gameManager.player.screenY) {

            graphics2D.drawImage(image, screenX, screenY,
                    GameManager.TILE_SIZE, GameManager.TILE_SIZE, null);
        }
    }
}
