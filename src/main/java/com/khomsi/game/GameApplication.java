package com.khomsi.game;

import com.formdev.flatlaf.FlatLightLaf;
import com.khomsi.game.main.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameApplication {
    public static JFrame window;
    private String iconMainPath = "/icons/icon.png";
    private String iconTrayPath = "/icons/trayicon.png";

    public static void main(String[] args) {
        GameApplication application = new GameApplication();
        application.setupFrameColor();
        application.startGame();
    }

    private void startGame() {
        window = new JFrame();
        System.setProperty("sun.java2d.opengl", "true");
        //can't resize window
        window.setResizable(false);
        window.setTitle("Tiny Legend Reborn");
        //Set icon for window
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconMainPath)));
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

    private void setupFrameColor() {
        // Setting that must be called before the whole application code
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        // Setup the Look and Feel before creating the JFrame
        JFrame.setDefaultLookAndFeelDecorated(true);
        UIManager.put("RootPane.background", Color.BLACK); // Set the bar background color
        UIManager.put("TitlePane.background", Color.GRAY); // Set the title bar background color
        UIManager.put("TitlePane.foreground", Color.WHITE); // Set the text bar color
    }

    private void setupSystemTray() {
        //let window game hide in tray, when use press close (x) button
        if (SystemTray.isSupported()) {
            window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        SystemTray systemTray = SystemTray.getSystemTray();

        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource(iconTrayPath)));
        PopupMenu popupMenu = new PopupMenu();
        MenuItem show = new MenuItem("Show");

        show.addActionListener(actionEvent -> window.setVisible(true));
        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(actionEvent -> handleExit(systemTray, trayIcon));
        popupMenu.add(show);
        popupMenu.add(exit);
        trayIcon.setPopupMenu(popupMenu);
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleExit(SystemTray systemTray, TrayIcon trayIcon) {
        systemTray.remove(trayIcon); // Remove the tray icon
        window.dispose(); // Dispose the window
        System.exit(0); // Terminate the application
    }
}
