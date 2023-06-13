package com.khomsi.game.main.tools;

import com.khomsi.game.main.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

    //Method with default values for images
    public BufferedImage setup(String imageName) {
        return setup(imageName, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(imagePath + ".png")));
            image = scaledImage(image, width, height);
        } catch (IOException e) {
            System.err.println("Error in setup in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        return image;
    }
}
