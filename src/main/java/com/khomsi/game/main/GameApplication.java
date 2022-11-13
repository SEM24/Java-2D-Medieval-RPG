package main.java.com.khomsi.game.main;

import javax.swing.*;

public class GameApplication {
    public static void main(String[] args) {
        new GameApplication().startGame();
    }

    private void startGame() {
        JFrame window = new JFrame();

        //let window close properly, when use press close (x) button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //can't resize window
        window.setResizable(false);
        window.setTitle("My 2D Adventure Game");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        //the window will be displayed in the center of the screen
        window.setLocationRelativeTo(null);
        //we can see the window
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
