package main.java.com.khomsi.game.tiles;

import main.java.com.khomsi.game.main.GamePanel;
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
    //TODO Добавить анимацию воды, как тут в ссылке(https://stackoverflow.com/questions/17387028/java-water-tile-animation)
    //Использовать тот же принцип, как и с игроком, но так же обновлять еще тайлы
    GamePanel gamePanel;
    public Tiles[] tiles;
    public int[][] mapTileNum;
    private final String[] resourcePath = {"/tiles/", "/maps/"};
    List<String> fileNames = new ArrayList<>();
    List<String> collisionStatus = new ArrayList<>();
    Tools tools = new Tools();

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

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

            GamePanel.MAX_WORLD_COL = maxTile.length;
            GamePanel.MAX_WORLD_ROW = maxTile.length;
            mapTileNum = new int[GamePanel.MAX_WORLD_COL][GamePanel.MAX_WORLD_ROW];
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
        Tools tools = new Tools();
        try {
            tiles[index] = new Tiles();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(
                    getClass().getResourceAsStream(resourcePath[0] + imageName)));
            tiles[index].image = tools.scaledImage(tiles[index].image, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
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
            tools.optimizeMapDraw(graphics2D, tiles[tileNum].image, gamePanel, screenX, screenY, worldX, worldY);
            worldCol++;
            if (worldCol == GamePanel.MAX_WORLD_COL) {
                worldCol = 0;

                worldRow++;
            }
        }
    }
}
