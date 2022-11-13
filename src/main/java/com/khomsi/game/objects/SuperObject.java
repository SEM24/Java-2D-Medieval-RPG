package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        //actual coords to draw the stuff on game screen
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        //FIXME duplicate
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
