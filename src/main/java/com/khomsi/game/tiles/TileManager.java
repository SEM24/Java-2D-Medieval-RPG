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
    Tiles[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        //10 kids of tiles, water grass etc
        tiles = new Tiles[10];
        mapTileNum = new int[GamePanel.MAX_SCREEN_COL][GamePanel.MAX_SCREEN_ROW];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/tiles/wall.png")));

            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream("/tiles/water.png")));
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
            while (col < GamePanel.MAX_SCREEN_COL && row < GamePanel.MAX_SCREEN_ROW) {
                String line = reader.readLine();
                while (col < GamePanel.MAX_SCREEN_COL) {
                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[col]); //use col as index for array
                    //store extracted nums from file to this array
                    mapTileNum[col][row] = number;
                    col++;
                }
                if (col == GamePanel.MAX_SCREEN_COL) {
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < GamePanel.MAX_SCREEN_COL && row < GamePanel.MAX_SCREEN_ROW) {

            int tileNum = mapTileNum[col][row];

            graphics2D.drawImage(tiles[tileNum].image, x, y,
                    GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            col++;
            x += GamePanel.TILE_SIZE;
            if (col == GamePanel.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += GamePanel.TILE_SIZE;
            }
        }
    }
}
