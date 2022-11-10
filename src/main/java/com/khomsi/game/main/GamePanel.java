package main.java.com.khomsi.game.main;

import main.java.com.khomsi.game.entity.Player;
import main.java.com.khomsi.game.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings

    static final int ORIGINAL_TILE_SIZE = 16; //16x16 tiles

    //we need to scale the size of hero, because on big screens it'll be too small
    static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; //48x48 tiles
    public static final int MAX_SCREEN_COL = 16; //16 tiles horizontal
    public static final int MAX_SCREEN_ROW = 12; //12 tiles vertical
    public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; //768 pixels
    public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; //576 pixels

    KeyHandler keyHandler = new KeyHandler();
    //use threads to start, stop,repeat actions.
    Thread gameThread;
    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    int FPS = 60;

    public GamePanel() {
        //set size of this class
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.WHITE);
        //if true, all drawing from this comp will be done in an offscreen painting buffer.
        //if turn on, it can improve the performance of rendering
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        //focus to receive key input
        this.setFocusable(true);
    }

    public void startGameThread() {
        //pass gamePanel to thread
        gameThread = new Thread(this);
        //automatically calls run method
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //update info as character position
                update();
                //draw the screen info with updated info
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1_000_000_000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    //method to draw the components on screen
    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        //extends graphic class and provide more control on geometry, color managment ect.
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.draw(graphics2D);

        player.draw(graphics2D);
        //save some memory
        graphics2D.dispose();
    }
}
