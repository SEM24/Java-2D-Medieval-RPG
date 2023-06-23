package com.khomsi.game.main.tools.ui;

import com.khomsi.game.main.GameManager;

public class Settings extends UI {
    public Settings(GameManager gameManager) {
        super(gameManager);
    }

    void fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + GameManager.TILE_SIZE;
        int textY = frameY + GameManager.TILE_SIZE * 3;
        currentDialog = "The changes will be \napplied after restarting \nthe game.";
        splitTextInDialog(currentDialog, textX, textY);
        //Back text
        textY = frameY + GameManager.TILE_SIZE * 9;
        graphics2D.drawString("Back", textX, textY);
        //Draw cursor
        if (commandNum == 0) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.isEnterPressed) subState = 0;
        }
    }

    void showControl(int frameX, int frameY) {
        int textX;
        int textY;
        String text = "Control";
        textX = getXCenterText(text);
        textY = frameY + GameManager.TILE_SIZE;
        graphics2D.drawString(text, textX, textY);
        textX = frameX + GameManager.TILE_SIZE;
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Move", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Confirm/Attack", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Shoot/Cast", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Inventory", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Pause", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Settings", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Map/MiniMap", textX, textY);
        textX = frameX + GameManager.TILE_SIZE * 6;
        textY = frameY + GameManager.TILE_SIZE * 2;

        graphics2D.drawString("WASD", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("ENTER", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("CTRL", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("E", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("P", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("ESC", textX, textY);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("M/X", textX, textY);

        //Back button
        textX = frameX + GameManager.TILE_SIZE;
        textY = frameY + GameManager.TILE_SIZE * 9;
        graphics2D.drawString("Back", textX, textY);
        if (commandNum == 0) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.isEnterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }
}
