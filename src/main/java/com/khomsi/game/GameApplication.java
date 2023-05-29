package com.khomsi.game;

import com.khomsi.game.main.GameManager;

import javax.swing.*;

public class GameApplication {
    public static JFrame window;

    public static void main(String[] args) {
        new GameApplication().startGame();
    }

    private void startGame() {
        window = new JFrame();
        System.setProperty("sun.java2d.d3d", "false");
        //let window close properly, when use press close (x) button
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //can't resize window
        window.setResizable(false);
        window.setTitle("My 2D Adventure Game");
        GameManager gameManager = new GameManager();
        window.add(gameManager);
        gameManager.config.loadConfig();
        if (gameManager.fullScreenOn) {
            window.setUndecorated(true);
        }
        window.pack();

        //the window will be displayed in the center of the screen
        window.setLocationRelativeTo(null);
        //we can see the window
        window.setVisible(true);
        gameManager.setupGame();
        gameManager.startGameThread();
    }
}
