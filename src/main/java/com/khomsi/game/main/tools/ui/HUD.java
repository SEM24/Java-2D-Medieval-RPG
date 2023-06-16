package com.khomsi.game.main.tools.ui;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.hud.ClockHud;
import com.khomsi.game.objects.hud.HeartHud;
import com.khomsi.game.objects.hud.ManaHud;
import com.khomsi.game.objects.hud.WindowHud;
import com.khomsi.game.objects.interact.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.khomsi.game.enviroment.Lightning.*;

public class HUD {
    private final UI ui;
    private final GameManager gameManager;
    private BufferedImage heartHud1, heartHud2, heartHud3, heartHud4, heartHud5, heartHud6;
    private BufferedImage manaHud1, manaHud2, manaHud3, manaHud4, manaHud5, manaHud6;
    private BufferedImage arrow, sun, moon, line, nightFall;
    private BufferedImage coinHud, keyHud;


    public HUD(UI ui, GameManager gameManager) {
        this.ui = ui;
        this.gameManager = gameManager;
    }

    public void initializeHudImages() {
        Entity clock = new ClockHud(gameManager);
        arrow = clock.image2;
        nightFall = clock.down;
        moon = clock.down1;
        sun = clock.image;
        line = clock.image3;

        Entity heartHud = new HeartHud(gameManager);
        heartHud1 = heartHud.down;
        heartHud2 = heartHud.down1;
        heartHud3 = heartHud.down2;

        heartHud4 = heartHud.image;
        heartHud5 = heartHud.image2;
        heartHud6 = heartHud.image3;

        Entity manaHud = new ManaHud(gameManager);
        manaHud1 = manaHud.down;
        manaHud2 = manaHud.down1;
        manaHud3 = manaHud.down2;

        manaHud4 = manaHud.image;
        manaHud5 = manaHud.image2;
        manaHud6 = manaHud.image3;

        Entity windowHud = new WindowHud(gameManager);
        coinHud = windowHud.image;
        keyHud = windowHud.image2;
    }

    void drawHudBars() {
        int clockWidth = (int) (GameManager.TILE_SIZE * 4.4);
        int clockHeight = (int) (GameManager.TILE_SIZE * 1.5);

        int windowX = GameManager.TILE_SIZE / 3;
        int y = 10;
        // Draw the subwindow
        drawRoundUiWindow(windowX - 20, y - 4, clockWidth, clockHeight);

        drawItemsOnHudWindow();
        int y1 = 34;
        int startX = GameManager.TILE_SIZE - 60;

        drawBar(startX, 10, gameManager.player.maxHp, gameManager.player.hp, heartHud1,
                heartHud2, heartHud3, heartHud4, heartHud5, heartHud6);
        drawBar(startX, y1, gameManager.player.maxMana,
                gameManager.player.mana, manaHud1, manaHud2, manaHud3, manaHud4, manaHud5, manaHud6);
    }

    private void drawItemsOnHudWindow() {
        // Draw coin image and number of coins
        int startX = GameManager.TILE_SIZE + 80;
        int startY = 3;
        drawImageWithText(coinHud, String.valueOf(gameManager.player.coin), startX, startY, 36);

        // Draw key image and number of keys
        startY = 25;
        drawImageWithText(keyHud, searchItemForHud(KeyObject.OBJ_NAME), startX, startY, 67);
    }

    private void drawImageWithText(BufferedImage image, String text, int startX, int startY, int textY) {
        ui.graphics2D.drawImage(image, startX, startY, null);
        ui.graphics2D.setFont(ui.graphics2D.getFont().deriveFont(Font.BOLD, 21F));
        ui.graphics2D.setColor(Color.WHITE);
        ui.graphics2D.drawString(text, (int) (GameManager.TILE_SIZE * 3.6), textY);
    }


    public String searchItemForHud(String name) {
        int counter = 0;
        for (int i = 0; i < gameManager.player.inventory.size(); i++) {
            if (gameManager.player.inventory.get(i).name.equals(name)) {
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    private void drawBar(int startX, int startY, int max, int current, BufferedImage beginningImage, BufferedImage middleImage,
                         BufferedImage endImage, BufferedImage emptyStartImage,
                         BufferedImage middleEmptyImage, BufferedImage emptyEndImage) {
        // Calculate the number of beginning, middle, and end sections based on current value
        int beginningCount = (current < 1) ? 0 : 1;
        int endCount = (current == max) ? 1 : 0;
        int i = 0;
        // Calculate the number of middle sections
        int middleCount = Math.max(0, current - beginningCount - endCount);
        int defaultX = startX;
        // Draw empty bar start
        ui.graphics2D.drawImage(emptyStartImage, startX, startY, null);
        startX += 12;

        // Draw middle
        while (i < max - 2) {
            ui.graphics2D.drawImage(middleEmptyImage, startX, startY, null);
            i++;
            startX += 9;
        }

        // Draw empty end section
        ui.graphics2D.drawImage(emptyEndImage, startX + 3, startY, null);

        // Draw current bar
        startX = defaultX;
        i = 0;

        // Draw beginning section (if current value is greater than or equal to 1)
        if (beginningCount > 0) {
            ui.graphics2D.drawImage(beginningImage, startX, startY, null);
            startX += 12;
        }

        // Draw middle sections
        while (i < middleCount) {
            ui.graphics2D.drawImage(middleImage, startX, startY, null);
            i++;
            startX += 9;
        }

        // Draw end section (if current value is equal to max)
        if (endCount > 0) {
            ui.graphics2D.drawImage(endImage, startX + 3, startY, null);
        }
    }

    public void drawClock(int dayState, float filterAlfa) {
        int clockX = 730;
        int clockY = 10;
        int clockGap = 42;
        int clockWidth = GameManager.TILE_SIZE * 6;
        int clockHeight = (int) (GameManager.TILE_SIZE * 1.3);
        final int LINE_COUNT = 5;
        final int WINDOW_OFFSET_X = 20;
        final int WINDOW_OFFSET_Y = 4;
        int startX = clockX - WINDOW_OFFSET_X - 1;
        int endX = clockX + (clockGap * 4 + 1) + WINDOW_OFFSET_X;

        if (gameManager.gameState != GameManager.CHARACTER_STATE) {
            //Draw window for clocks
            drawRoundUiWindow(clockX - WINDOW_OFFSET_X, clockY - WINDOW_OFFSET_Y, clockWidth, clockHeight);
            // Draw lines
            for (int i = 0; i < LINE_COUNT; i++) {
                int xPos = clockX + (clockGap * i);
                ui.graphics2D.drawImage(line, xPos, clockY + WINDOW_OFFSET_Y, null);
            }
            int xPos = clockX + (clockGap * 4 + 1);
            int yPos = (int) (clockY - WINDOW_OFFSET_Y * 3.2);
            ui.graphics2D.drawImage(moon, xPos + WINDOW_OFFSET_X, yPos, null);

            xPos = (xPos - clockGap * 2);
            yPos = yPos - WINDOW_OFFSET_Y;
            ui.graphics2D.drawImage(nightFall, xPos, yPos, null);

            xPos = startX + 1;
            ui.graphics2D.drawImage(sun, xPos, yPos, null);

            // Calculate arrow position based on dayState and filterAlfa
            int arrowX;
            int arrowY = clockY + WINDOW_OFFSET_Y * 6;

            if (dayState == DAY) {
                arrowX = startX;
            } else if (dayState == NIGHTFALL || dayState == NIGHT) {
                arrowX = (int) (startX + (endX - startX) * filterAlfa);
            } else { // DAWN
                arrowX = endX - (int) ((endX - startX) * filterAlfa);

                // Reverse the arrow movement during the dawn phase
                arrowX = endX - (arrowX - startX);

                if (arrowX < startX) {
                    arrowX = startX;
                }
            }
            ui.graphics2D.drawImage(arrow, arrowX, arrowY, null);
        }
    }

    public void drawRoundUiWindow(int x, int y, int width, int height) {
        //Less %, more transparent
        int alpha = 120; // % transparent
        Color color = new Color(36, 36, 36, alpha);
        ui.graphics2D.setColor(color);
        ui.graphics2D.fillRoundRect(x, y, width, height, 8, 8);
    }
}
