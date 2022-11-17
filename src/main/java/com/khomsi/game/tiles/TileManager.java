package main.java.com.khomsi.game.tiles;

import main.java.com.khomsi.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tiles[] tiles;
    public int[][] mapTileNum;
    private final String[] resourcePath = {"/tiles/", "/maps/"};


    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //10 blocks of tiles, water grass etc
        tiles = new Tiles[10];
        mapTileNum = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
        getTileImage();
        loadMap(resourcePath[1] + "world01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "grass.png")));

            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "wall.png")));
            tiles[1].collision = true;

            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "water.png")));
            tiles[2].collision = true;

            tiles[3] = new Tiles();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "earth.png")));

            tiles[4] = new Tiles();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "tree.png")));
            tiles[4].collision = true;

            tiles[5] = new Tiles();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + "sand.png")));
        } catch (IOException e) {
            System.err.println("Error on getting tile images!");
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try (InputStream inputStream = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int col = 0;
            int row = 0;
            while (col < GamePanel.MAX_WORLD_COL && row < GamePanel.MAX_WORLD_ROW) {
                String line = reader.readLine();
                while (col < GamePanel.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[col]); //use col as index for array
                    //store extracted nums from file to this array
                    mapTileNum[col][row] = number;
                    col++;
                }
                if (col == GamePanel.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            System.err.println("Error on loading the map!");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < GamePanel.MAX_WORLD_COL && worldRow < GamePanel.MAX_WORLD_ROW) {

            int tileNum = mapTileNum[worldCol][worldRow];
            //coords for world map
            int worldX = worldCol * GamePanel.TILE_SIZE;
            int worldY = worldRow * GamePanel.TILE_SIZE;
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

                graphics2D.drawImage(tiles[tileNum].image, screenX, screenY,
                        GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
            worldCol++;

            if (worldCol == GamePanel.MAX_WORLD_COL) {
                worldCol = 0;

                worldRow++;
            }
        }
    }
}
