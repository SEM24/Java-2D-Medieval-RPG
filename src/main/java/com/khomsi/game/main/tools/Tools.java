package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;

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
    public void optimizeMapDraw(Graphics2D graphics2D, BufferedImage image, GamePanel gamePanel,
                                int screenX, int screenY, int worldX, int worldY) {
        //use if case to optimize the game and not to draw whole map, that player even doesn't see
        //up, down, left, right checking (boundaries)
        if (worldX + GamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - GamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + GamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - GamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.screenY) {

            graphics2D.drawImage(image, screenX, screenY,
                    GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
        }
    }
}
