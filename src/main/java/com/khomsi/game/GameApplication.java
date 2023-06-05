package com.khomsi.game;

import com.khomsi.game.main.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameApplication {
    public static JFrame window;
    private String iconPath = "/player/male/mini_map.png";

    public static void main(String[] args) {
        new GameApplication().startGame();
    }

    private void startGame() {
        window = new JFrame();
        System.setProperty("sun.java2d.opengl", "true");

        //can't resize window
        window.setResizable(false);
        window.setTitle("My 2D Adventure Game");
        //Set icon for window
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        window.setIconImage(logo.getImage());
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
        setupSystemTray();
        gameManager.setupGame();
        gameManager.startGameThread();
    }

    private void setupSystemTray() {
        //let window game hide in tray, when use press close (x) button
        if (SystemTray.isSupported()) {
            window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        }
        SystemTray systemTray = SystemTray.getSystemTray();

        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource(iconPath)));
        PopupMenu popupMenu = new PopupMenu();
        MenuItem show = new MenuItem("Show");

        show.addActionListener(actionEvent -> window.setVisible(true));
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(actionEvent -> System.exit(0));
        popupMenu.add(show);
        popupMenu.add(exit);
        trayIcon.setPopupMenu(popupMenu);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
