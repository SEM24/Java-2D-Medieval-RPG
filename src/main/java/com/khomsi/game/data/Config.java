package main.java.com.khomsi.game.data;

import main.java.com.khomsi.game.main.GameManager;

import java.io.*;
import java.nio.file.Path;

public class Config {
    GameManager gameManager;
    Path saveData = Path.of("config.ini");

    public Config(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void saveConfig() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveData.toFile()))) {
            // Full Screen
            bw.write("fullscreen=" + (gameManager.fullScreenOn ? "On" : "Off"));
            bw.newLine();
            // Music volume
            bw.write("music_volume=" + gameManager.music.volumeScale);
            bw.newLine();
            // SE volume
            bw.write("se_volume=" + gameManager.se.volumeScale);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error in save method in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try (BufferedReader br = new BufferedReader(new FileReader(saveData.toFile()))) {
            String temp = br.readLine();
            // Full Screen
            if (temp.split("=")[1].equals("On")) {
                gameManager.fullScreenOn = true;
            }
            if (temp.split("=")[1].equals("Off")) {
                gameManager.fullScreenOn = false;
            }
            // Music volume
            temp = br.readLine();
            gameManager.music.volumeScale = Integer.parseInt(temp.split("=")[1]);
            // SE volume
            temp = br.readLine();
            gameManager.se.volumeScale = Integer.parseInt(temp.split("=")[1]);
        } catch (Exception e) {
            System.err.println("Error in loading method in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }
}
