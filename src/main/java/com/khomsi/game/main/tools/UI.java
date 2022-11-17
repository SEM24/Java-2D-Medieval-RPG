package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;
import main.java.com.khomsi.game.objects.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Font sans_40, sans_80B;
    BufferedImage keyImage;
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
        KeyObject key = new KeyObject();
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D) {
        if (gameFinished) {
            graphics2D.setFont(sans_40);
            graphics2D.setColor(Color.YELLOW);
            String text;
            int textLength;
            int x;
            int y;
            text = "You found the chest!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = GamePanel.SCREEN_WIDTH / 2 - textLength / 2;
            y = GamePanel.SCREEN_HEIGHT / 2 - (GamePanel.TILE_SIZE * 3);
            graphics2D.drawString(text, x, y);

            text = "Your Time is: " + format.format(gameDuration) + "!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = GamePanel.SCREEN_WIDTH / 2 - textLength / 2;
            y = GamePanel.SCREEN_HEIGHT / 2 + (GamePanel.TILE_SIZE * 5);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(sans_80B);
            graphics2D.setColor(Color.RED);
            text = "Congratulation!";
            textLength = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = GamePanel.SCREEN_WIDTH / 2 - textLength / 2;
            y = GamePanel.SCREEN_HEIGHT / 2 + (GamePanel.TILE_SIZE * 2);
            graphics2D.drawString(text, x, y);
            //stop the thread game
            gamePanel.gameThread = null;

        } else {
            graphics2D.setFont(sans_40);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(keyImage, GamePanel.TILE_SIZE / 2,
                    GamePanel.TILE_SIZE / 2, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            graphics2D.drawString("x " + gamePanel.player.hasKey, 74, 65);
            //time
            gameDuration += (double) 1 / 60;
            graphics2D.setFont(graphics2D.getFont().deriveFont(25F));
            graphics2D.drawString("Time: " + format.format(gameDuration), GamePanel.TILE_SIZE / 3, GamePanel.TILE_SIZE * 3);

            //message
            if (messageOn) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(35F));
                graphics2D.drawString(message, GamePanel.TILE_SIZE * 11, GamePanel.TILE_SIZE + 15);
                messageCounter++;
                //disappear after 2 sec
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
