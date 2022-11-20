package main.java.com.khomsi.game.objects;

import main.java.com.khomsi.game.main.GamePanel;
import main.java.com.khomsi.game.main.tools.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    Tools tools = new Tools();


    public void draw(Graphics2D graphics2D, GamePanel gamePanel) {
        //actual coords to draw the stuff on game screen
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        tools.optimizeMapDraw(graphics2D, image, gamePanel, screenX, screenY, worldX, worldY);
    }
}
