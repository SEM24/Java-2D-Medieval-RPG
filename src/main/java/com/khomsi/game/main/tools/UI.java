package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D graphics2D;
    Font playMeGames;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialog = "";
    public int commandNum = 0;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try (InputStream input = getClass().getResourceAsStream("/font/PlaymegamesReguler-2OOee.ttf")) {
            playMeGames = Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error in importing fonts in " + getClass().getSimpleName());
            e.printStackTrace();
        }
    }

    //TODO remove if it's not usable
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.setFont(playMeGames);
        graphics2D.setColor(Color.WHITE);
        //Title state
        if (gamePanel.gameState == gamePanel.titleState) {
            drawTitleScreen();
        }
        //Play state
        if (gamePanel.gameState == gamePanel.playState) {
            //TODO play stuff
        }
        //Pause state
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
        //Diaglog state
        if (gamePanel.gameState == gamePanel.dialogueState) {
            showDialog();
        }
    }

    public void drawTitleScreen() {
        graphics2D.setColor(new Color(10, 77, 138));
        graphics2D.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
        //Title name
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 80F));
        String text = "2D Game Adventure";

        int x = getXCenterText(text);
        int y = GamePanel.TILE_SIZE * 3;
        drawShadowAndText(text, x, y, 5, 5);

        //Image of player, center it
        x = GamePanel.SCREEN_WIDTH / 2 - (GamePanel.TILE_SIZE * 2) / 2;
        y += GamePanel.TILE_SIZE * 2;
        graphics2D.drawImage(gamePanel.player.down, x, y,
                GamePanel.TILE_SIZE * 2, GamePanel.TILE_SIZE * 2, null);

        //Menu
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 40F));
        text = "NEW GAME";
        x = getXCenterText(text);
        y += GamePanel.TILE_SIZE * 3.5;
        drawShadowAndText(text, x, y, 3, 3);
        if (commandNum == 0) {
            drawShadowAndText(">", x - GamePanel.TILE_SIZE, y, 3, 3);
        }
        text = "LOAD GAME";
        x = getXCenterText(text);
        y += GamePanel.TILE_SIZE;
        drawShadowAndText(text, x, y, 3, 3);
        if (commandNum == 1) {
            drawShadowAndText(">", x - GamePanel.TILE_SIZE, y, 3, 3);
        }
        text = "EXIT";
        x = getXCenterText(text);
        y += GamePanel.TILE_SIZE;
        drawShadowAndText(text, x, y, 3, 3);
        if (commandNum == 2) {
            drawShadowAndText(">", x - GamePanel.TILE_SIZE, y, 3, 3);
        }
    }

    private void drawShadowAndText(String text, int x, int y, int sx, int sy) {
        //Shadow
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(text, x + sx, y + sy);
        //Main text
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(text, x, y);
    }

    public void showDialog() {
        int x = GamePanel.TILE_SIZE * 2;
        int y = GamePanel.TILE_SIZE / 2;
        int width = GamePanel.SCREEN_WIDTH - (GamePanel.TILE_SIZE * 4);
        int height = GamePanel.TILE_SIZE * 4;
        showSubWindow(x, y, width, height);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 30F));
        x += GamePanel.TILE_SIZE;
        y += GamePanel.TILE_SIZE;

        //split the text inside dialog, so it fits the window
        for (String line : currentDialog.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }

    public void showSubWindow(int x, int y, int width, int height) {
        int alpha = 200; // % transparent
        Color color = new Color(108, 108, 156, alpha);
        graphics2D.setColor(color);
        graphics2D.fillRect(x, y, width, height);

        //shadow
        color = new Color(0, 0, 0);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(11));
        graphics2D.drawRect(x + 5, y + 5, width - 10, height - 10);
        //stroke
        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRect(x + 5, y + 5, width - 10, height - 10);
    }

    public void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 70F));
        String pauseText = "PAUSED";
        int x = getXCenterText(pauseText);
        int y = GamePanel.SCREEN_HEIGHT / 2;
        graphics2D.drawString(pauseText, x, y);
    }

    public int getXCenterText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return GamePanel.SCREEN_WIDTH / 2 - length / 2;
    }
}
