package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Graphics2D graphics2D;
    Font sans_40, sans_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double gameDuration;
    DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        sans_40 = new Font("Comic Sans MS", Font.PLAIN, 40);
        sans_80B = new Font("Comic Sans MS", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        graphics2D.setFont(sans_40);
        graphics2D.setColor(Color.WHITE);
        if (gamePanel.gameState == gamePanel.playState) {
            //TODO player stuff
        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }
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
