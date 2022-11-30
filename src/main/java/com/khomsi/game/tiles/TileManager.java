package main.java.com.khomsi.game.tiles;

import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.main.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileManager {
    GameManager gameManager;
    public Tiles[] tiles;
    public int[][] mapTileNum;
    private final String[] resourcePath = {"/tiles/", "/maps/"};
    List<String> fileNames = new ArrayList<>();
    List<String> collisionStatus = new ArrayList<>();
    Tools tools = new Tools();

    public TileManager(GameManager gameManager) {
        this.gameManager = gameManager;

        //here change the path to tile data(collision, names)

        InputStream input = getClass().getResourceAsStream(resourcePath[1] + "tiledata.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(input)));
        String line;

        try {
            //read line and put text inside line, if it's null(file ends), stop it
            while ((line = reader.readLine()) != null) {
                fileNames.add(line);
                collisionStatus.add(reader.readLine());
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error in getting files in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        //for example 10 blocks of water grass etc, based on filename size
        tiles = new Tiles[fileNames.size()];
        getTileImage();

        //here change the path to map
        input = getClass().getResourceAsStream(resourcePath[1] + "world01.txt");
        reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(input)));
        try {
            String line1 = reader.readLine();
            String[] maxTile = line1.split(" ");

            GameManager.maxWorldCol = maxTile.length;
            GameManager.maxWorldRow = maxTile.length;
            mapTileNum = new int[GameManager.maxWorldCol][GameManager.maxWorldRow];
            reader.close();
        } catch (IOException e) {
            System.err.println("Exception in TileManage in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        //load the map
        loadMap(resourcePath[1] + "world01.txt");
    }

    public void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {
            //Get filename, collision type and pass whole info here
            setup(i, fileNames.get(i), Boolean.parseBoolean(collisionStatus.get(i)));
        }
    }

    /*
     We handle all duplicated lines, like initialization import img, scale, set collision in this method
     */
    public void setup(int index, String imageName, boolean collision) {
        try {
            tiles[index] = new Tiles();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + imageName)));
            tiles[index].image = tools.scaledImage(tiles[index].image, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
            tiles[index].collision = collision;

        } catch (IOException e) {
            System.err.println("Error in setup method in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try (InputStream inputStream = getClass().getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(
                     Objects.requireNonNull(inputStream)))) {
            int col = 0;
            int row = 0;
            while (col < GameManager.maxWorldCol && row < GameManager.maxWorldRow) {
                String line = reader.readLine();
                while (col < GameManager.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[col]); //use col as index for array
                    //store extracted nums from file to this array
                    mapTileNum[col][row] = number;
                    col++;
                }
                if (col == GameManager.maxWorldCol) {
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

        while (worldCol < GameManager.maxWorldCol && worldRow < GameManager.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            //coords for world map
            int worldX = worldCol * GameManager.TILE_SIZE;
            int worldY = worldRow * GameManager.TILE_SIZE;
            //actual coords to draw the stuff on game screen
            int screenX = worldX - gameManager.player.worldX + gameManager.player.screenX;
            int screenY = worldY - gameManager.player.worldY + gameManager.player.screenY;
            tools.optimizeMapDraw(graphics2D, tiles[tileNum].image, gameManager, screenX, screenY, worldX, worldY);
            worldCol++;
            if (worldCol == GameManager.maxWorldCol) {
                worldCol = 0;

                worldRow++;
            }
        }
    }
}
