package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.entity.Entity;
import main.java.com.khomsi.game.main.GameManager;
import main.java.com.khomsi.game.objects.gui.HeartObject;
import main.java.com.khomsi.game.objects.gui.ManaObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UI {
    GameManager gameManager;
    Graphics2D graphics2D;
    Font playMeGames;
    BufferedImage heart_full, heart_half, heart_empty, mana_full, mana_empty;
    List<String> message = new ArrayList<>();
    List<Integer> messageCounter = new ArrayList<>();
    public String currentDialog = "";
    public int commandNum = 0;
    //0: first screen, 1: second screen
    public int titleScreenState = 0;
    //Indicate current cursor position
    public int slotCol = 0;
    public int slotRow = 0;

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
        Entity mana = new ManaObject(gameManager);
        mana_full = mana.image;
        mana_empty = mana.image2;
    }


    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
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
        //Character state
        if (gameManager.gameState == gameManager.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
    }

    private void drawInventory() {
        //Frame
        int frameX = GameManager.TILE_SIZE * 9;
        int frameY = GameManager.TILE_SIZE;
        int frameWidth = GameManager.TILE_SIZE * 6;
        int frameHeight = GameManager.TILE_SIZE * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Slots
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = GameManager.TILE_SIZE + 3;

        //Draw player's items
        for (int i = 0; i < gameManager.player.inventory.size(); i++) {

            //Equip cursor
            if (gameManager.player.inventory.get(i).equals(gameManager.player.currentWeapon) ||
                    gameManager.player.inventory.get(i).equals(gameManager.player.currentShield)) {
                int alpha = 200; // % transparent
                graphics2D.setColor(new Color(255, 255, 255, alpha));

                graphics2D.fillRect(slotX, slotY, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
            }

            graphics2D.drawImage(gameManager.player.inventory.get(i).down, slotX, slotY, null);
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        //Cursor
        int cursorX = slotXStart + (slotSize * slotCol);
        int cursorY = slotYStart + (slotSize * slotRow);

        int cursorWidth = GameManager.TILE_SIZE;
        int cursorHeight = GameManager.TILE_SIZE;

        //Draw cursor
        Color color = new Color(0, 0, 0);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(9));
        graphics2D.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);
        //stroke
        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(4));
        graphics2D.drawRect(cursorX, cursorY, cursorWidth, cursorHeight);

        //Description frame
        int dFrameY = frameY + frameHeight + 3;
        int dFrameHeight = GameManager.TILE_SIZE * 3;

        //Draw description text
        int textX = frameX + 20;
        int textY = dFrameY + GameManager.TILE_SIZE;
        graphics2D.setFont(graphics2D.getFont().deriveFont(26F));
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gameManager.player.inventory.size()) {
            drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);
            for (String line : gameManager.player.inventory.get(itemIndex).itemDescription.split("\n")) {
                graphics2D.drawString(line, textX, textY);
                textY += 40;
            }
        }
    }

    public int getItemIndexOnSlot() {
        return slotCol + (slotRow * 5);
    }

    private void drawMessage() {
        int messageX = GameManager.TILE_SIZE;
        int messageY = GameManager.TILE_SIZE * 4;
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 32F));
        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                drawShadowAndText(message.get(i), messageX, messageY, 3, 3);
                //msgCounter++
                int counter = messageCounter.get(i) + 1;
                //Set the counter to the array
                messageCounter.set(i, counter);
                messageY += 50;
                //After 3 Sec
                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    private void drawCharacterScreen() {
        //Create a frame
        final int frameX = GameManager.TILE_SIZE;
        final int frameY = GameManager.TILE_SIZE;
        final int frameWidth = GameManager.TILE_SIZE * 5;
        final int frameHeight = GameManager.TILE_SIZE * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(graphics2D.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + GameManager.TILE_SIZE;
        //Same size as font size
        final int lineHeight = 35;

        //Names
        graphics2D.drawString("Level", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Health", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Mana", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Strength", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Agility", textX, textY);
        textY += lineHeight;
//        graphics2D.drawString("Speed", textX, textY);
//        textY += lineHeight;
        graphics2D.drawString("Attack", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Defense", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Xp", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Next lvl", textX, textY);
        textY += lineHeight;
        graphics2D.drawString("Coins", textX, textY);
        textY += lineHeight + 5;
        graphics2D.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        graphics2D.drawString("Shield", textX, textY);

        //Values
        int tailX = (frameX + frameWidth) - 30;
        //Reset Y
        textY = frameY + GameManager.TILE_SIZE;
        String value;

        value = String.valueOf(gameManager.player.level);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = gameManager.player.hp + "/" + gameManager.player.maxHp;
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = gameManager.player.mana + "/" + gameManager.player.maxMana;
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.strength);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.agility);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

//        value = String.valueOf(gameManager.player.speed);
//        textX = getXAlignToRightText(value, tailX);
//        graphics2D.drawString(value, textX, textY);
//        textY += lineHeight;

        value = String.valueOf(gameManager.player.attack);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.defense);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.xp);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.nextLevelXp);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gameManager.player.coin);
        textX = getXAlignToRightText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        graphics2D.drawImage(gameManager.player.currentWeapon.down, tailX - GameManager.TILE_SIZE + 18, textY - 25, null);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawImage(gameManager.player.currentShield.down, tailX - GameManager.TILE_SIZE + 20, textY - 25, null);

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

        //Draw the player's max mana
        x = GameManager.TILE_SIZE / 2;
        y = (int) (GameManager.TILE_SIZE * 1.6);
        i = 0;
        while (i < gameManager.player.maxMana) {
            graphics2D.drawImage(mana_empty, x, y, null);
            i++;
            x += GameManager.TILE_SIZE;
        }
        //Draw mana
        x = GameManager.TILE_SIZE / 2;
        y = (int) (GameManager.TILE_SIZE * 1.6);
        i = 0;
        while (i < gameManager.player.mana) {
            graphics2D.drawImage(mana_full, x, y, null);
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

            //FIXME remove this lines and use the logo of game instead
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
        drawSubWindow(x, y, width, height);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 30F));
        x += GameManager.TILE_SIZE;
        y += GameManager.TILE_SIZE;

        //split the text inside dialog, so it fits the window
        for (String line : currentDialog.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
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

    public int getXAlignToRightText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return tailX - length;
    }
}
