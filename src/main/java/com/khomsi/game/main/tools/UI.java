package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.HeartObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GameManager gameManager;
    Graphics2D graphics2D;
    Font playMeGames;
    BufferedImage heart_full, heart_half, heart_empty;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialog = "";
    public int commandNum = 0;
    //0 : first screen, 1: second screen
    public int titleScreenState = 0;

    public UI(GameManager gameManager) {
        this.gameManager = gameManager;
        try (InputStream input = getClass().getResourceAsStream("/font/PlaymegamesReguler-2OOee.ttf")) {
            playMeGames = Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (FontFormatException | IOException e) {
            System.err.println("Error in importing fonts in " + getClass().getSimpleName());
            e.printStackTrace();
        }
        //HUD object
        Entity heart = new HeartObject(gameManager);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
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
        if (gameManager.gameState == gameManager.titleState) {
            drawTitleScreen();
        }
        //Play state
        if (gameManager.gameState == gameManager.playState) {
            drawPlayerHp();
        }
        //Pause state
        if (gameManager.gameState == gameManager.pauseState) {
            drawPlayerHp();
            drawPauseScreen();
        }
        //Dialog state
        if (gameManager.gameState == gameManager.dialogueState) {
            drawPlayerHp();
            showDialog();
        }
    }

    private void drawPlayerHp() {
        int x = GameManager.TILE_SIZE / 2;
        int y = GameManager.TILE_SIZE / 2;
        int i = 0;
        //Draw max Hp
        while (i < gameManager.player.maxHp / 2) {
            graphics2D.drawImage(heart_empty, x, y, null);
            i++;
            x += GameManager.TILE_SIZE;
        }
        x = GameManager.TILE_SIZE / 2;
        y = GameManager.TILE_SIZE / 2;
        i = 0;
        //Draw current Hp
        while (i < gameManager.player.hp) {
            graphics2D.drawImage(heart_half, x, y, null);
            i++;
            if (i < gameManager.player.hp) {
                graphics2D.drawImage(heart_full, x, y, null);
            }
            i++;
            x += GameManager.TILE_SIZE;
        }
    }

    public void drawTitleScreen() {
        Color bgColor = new Color(31, 57, 75);
        if (titleScreenState == 0) {
            graphics2D.setColor(bgColor);
            graphics2D.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
            //Title name
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 80F));
            String text = "2D Game Adventure";

            int x = getXCenterText(text);
            int y = GameManager.TILE_SIZE * 3;
            drawShadowAndText(text, x, y, 5, 5);

            //FIXME remove this line and use the logo of game instead
            //Image of player, center it
            x = GameManager.SCREEN_WIDTH / 2 - (GameManager.TILE_SIZE * 2) / 2;
            y += GameManager.TILE_SIZE * 2;
            graphics2D.drawImage(gameManager.player.down, x, y,
                    GameManager.TILE_SIZE * 2, GameManager.TILE_SIZE * 2, null);

            //Menu
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 40F));
            text = "NEW GAME";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE * 3.5;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 0) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }
            text = "LOAD GAME";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 1) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }
            text = "EXIT";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 2) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }
        } else if (titleScreenState == 1) {
            //Class selection screen
            graphics2D.setColor(bgColor);
            graphics2D.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
            graphics2D.setFont(graphics2D.getFont().deriveFont(42F));
            String text = "Select your character!";
            int x = getXCenterText(text);
            int y = GameManager.TILE_SIZE * 3;
            drawShadowAndText(text, x, y, 3, 3);

            text = "Male";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE * 3;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 0) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }

            text = "Female";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 1) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }

            text = "Back";
            x = getXCenterText(text);
            y += GameManager.TILE_SIZE * 3;
            drawShadowAndText(text, x, y, 3, 3);
            if (commandNum == 2) {
                drawShadowAndText(">", x - GameManager.TILE_SIZE, y, 3, 3);
            }
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
        int x = GameManager.TILE_SIZE * 2;
        int y = GameManager.TILE_SIZE / 2;
        int width = GameManager.SCREEN_WIDTH - (GameManager.TILE_SIZE * 4);
        int height = GameManager.TILE_SIZE * 4;
        showSubWindow(x, y, width, height);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 30F));
        x += GameManager.TILE_SIZE;
        y += GameManager.TILE_SIZE;

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
        int y = GameManager.SCREEN_HEIGHT / 2;
        graphics2D.drawString(pauseText, x, y);
    }

    public int getXCenterText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return GameManager.SCREEN_WIDTH / 2 - length / 2;
    }
}
