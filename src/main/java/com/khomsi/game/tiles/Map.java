package com.khomsi.game.tiles;

import com.khomsi.game.main.GameManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {
    BufferedImage[] worldMap;
    public boolean miniMapOn = false;

    public Map(GameManager gameManager) {
        super(gameManager);
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gameManager.maxMap];
        int worldMapWidth = GameManager.TILE_SIZE * GameManager.maxWorldCol;
        int worldMapHeight = GameManager.TILE_SIZE * GameManager.maxWorldRow;
        for (int i = 0; i < gameManager.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2D = worldMap[i].createGraphics();
            int col = 0;
            int row = 0;
            while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
                //Get tile number and draw 1 by 1
                int tileNum = mapTileNum[i][col][row];
                int x = GameManager.TILE_SIZE * col;
                int y = GameManager.TILE_SIZE * row;
                g2D.drawImage(tiles[tileNum].image, x, y, null);
                col++;
                if (col == GameManager.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            g2D.dispose();
        }
    }

    public void drawFullScreenMap(Graphics2D g2D) {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        //Draw map
        int width = 500;
        int height = 500;
        int x = GameManager.SCREEN_WIDTH / 2 - width / 2;
        int y = GameManager.SCREEN_HEIGHT / 2 - height / 2;
        drawPlayerPosition(g2D, worldMap, x, y, width, height);
        //Hint
        g2D.setFont(gameManager.ui.playMeGames.deriveFont(32F));
        g2D.setColor(Color.WHITE);
        g2D.drawString("Press M to close", 730, 550);
    }

    public void drawMiniMap(Graphics2D g2D) {
        if (miniMapOn) {
            int width = 200;
            int height = 200;
            int x = GameManager.SCREEN_WIDTH - width - 50;
            int y = 50;
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8F));
            drawPlayerPosition(g2D, worldMap, x, y, width, height);
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        }
    }

    private void drawPlayerPosition(Graphics2D g2D, BufferedImage[] worldMap, int x, int y, int width, int height) {
        g2D.drawImage(worldMap[gameManager.currentMap], x, y, width, height, null);

        //Draw player's position
        double scale = (double) (GameManager.TILE_SIZE * GameManager.maxWorldCol) / width;
        int playerX = (int) (x + gameManager.player.worldX / scale);
        int playerY = (int) (y + gameManager.player.worldY / scale);
        int playerSize = (GameManager.TILE_SIZE / 3);
        g2D.drawImage(gameManager.player.setup(gameManager.player.PLAYER_PATH[gameManager.player.playerSkin] + "mini_map"),
                playerX - 6, playerY - 6, playerSize, playerSize, null);
    }
}
