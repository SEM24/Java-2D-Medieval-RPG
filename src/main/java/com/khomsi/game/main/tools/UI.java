package com.khomsi.game.main.tools;

import com.khomsi.game.main.GameManager;
import com.khomsi.game.objects.gui.ManaObject;
import com.khomsi.game.objects.interact.CoinBObject;
import com.khomsi.game.entity.Entity;
import com.khomsi.game.enviroment.Lightning;
import com.khomsi.game.objects.gui.HeartObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UI {
    GameManager gameManager;
    Graphics2D graphics2D;
    public Font playMeGames;
    BufferedImage heartFull, heartHalf, heartEmpty, manaFull, manaEmpty, coin;
    List<String> message = new ArrayList<>();
    List<Integer> messageCounter = new ArrayList<>();
    public String currentDialog = "";
    public int commandNum = 0;
    //0: first screen, 1: second screen
    public int titleScreenState = 0;
    //Indicate current cursor position
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    int charIndex = 0;
    public Entity npc;
    String combinedText = "";

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
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
        Entity mana = new ManaObject(gameManager);
        manaFull = mana.image;
        manaEmpty = mana.image2;
        Entity bronzeCoin = new CoinBObject(gameManager);
        coin = bronzeCoin.down;
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
        if (gameManager.gameState == GameManager.TITLE_STATE) {
            drawTitleScreen();
        }
        //Play state
        if (gameManager.gameState == GameManager.PLAY_STATE) {
            drawPlayerHp();
            drawMobHp();
            drawMessage();
        }
        //Pause state
        if (gameManager.gameState == GameManager.PAUSE_STATE) {
            drawPlayerHp();
            drawPauseScreen();
        }
        //Dialog state
        if (gameManager.gameState == GameManager.DIALOGUE_STATE) {
            showDialog();
        }
        //Character state
        if (gameManager.gameState == GameManager.CHARACTER_STATE) {
            drawCharacterScreen();
            drawInventory(gameManager.player, true);
        }
        //Option state
        if (gameManager.gameState == GameManager.OPTION_STATE) {
            drawOptionScreen();
        }
        //Game Over state
        if (gameManager.gameState == GameManager.GAME_OVER_STATE) {
            drawGameOverScreen();
        }
        //Transition Over state
        if (gameManager.gameState == GameManager.TRANSITION_STATE) {
            drawTransitionScreen();
        }
        //Trade state
        if (gameManager.gameState == GameManager.TRADE_STATE) {
            drawTradeScreen();
        }
        //Sleep state
        if (gameManager.gameState == GameManager.SLEEP_STATE) {
            drawSleepScreen();
        }
    }

    private void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gameManager.enManagement.lightning.filterAlfa += 0.01F;
            if (gameManager.enManagement.lightning.filterAlfa > 1F)
                gameManager.enManagement.lightning.filterAlfa = 1F;
        }
        if (counter >= 120) {
            gameManager.enManagement.lightning.filterAlfa -= 0.01F;
            if (gameManager.enManagement.lightning.filterAlfa <= 0F) {
                gameManager.enManagement.lightning.filterAlfa = 0F;
                counter = 0;
                gameManager.enManagement.lightning.dayState = Lightning.DAY;
                gameManager.enManagement.lightning.dayCounter = 0;
                gameManager.gameState = GameManager.PLAY_STATE;
                gameManager.player.getImage();
            }
        }
    }

    private void drawMobHp() {
        for (int i = 0; i < gameManager.mobs[1].length; i++) {
            Entity mob = gameManager.mobs[gameManager.currentMap][i];
            if (mob != null && mob.inCamera()) {
                //Monster Hp bar
                if (mob.hpBarOn && !mob.isBoss) {
                    //Get the length of hp bar, if hp - 4, then the scale is 12 pixels (4 times)
                    double oneScale = (double) GameManager.TILE_SIZE / mob.maxHp;
                    double hpBarValue = oneScale * mob.hp;

                    graphics2D.setColor(new Color(35, 35, 35));
                    graphics2D.fillRect(mob.getScreenX() - 1, mob.getScreenY() - 16,
                            GameManager.TILE_SIZE + 2, 12);

                    graphics2D.setColor(new Color(255, 0, 30));
                    graphics2D.fillRect(mob.getScreenX(), mob.getScreenY() - 15, (int) hpBarValue, 10);
                    mob.hpBarCounter++;
                    //After 10 sec bar hides
                    if (mob.hpBarCounter > 600) {
                        mob.hpBarCounter = 0;
                        mob.hpBarOn = false;
                    }
                } else if (mob.isBoss) {
                    //Get the length of hp bar, if hp - 4, then the scale is 12 pixels (4 times)
                    double oneScale = (double) GameManager.TILE_SIZE * 8 / mob.maxHp;
                    double hpBarValue = oneScale * mob.hp;
                    int x = GameManager.SCREEN_WIDTH / 2 - GameManager.TILE_SIZE * 4;
                    int y = GameManager.TILE_SIZE * 10;

                    graphics2D.setColor(new Color(35, 35, 35));
                    graphics2D.fillRect(x - 1, y - 1,
                            GameManager.TILE_SIZE * 8 + 2, 22);

                    graphics2D.setColor(new Color(255, 0, 30));
                    graphics2D.fillRect(x, y, (int) hpBarValue, 20);
                    graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 24F));
                    graphics2D.setColor(Color.WHITE);
                    graphics2D.drawString(mob.name, x + 4, y - 10);
                }
            }
        }
    }

    private void drawTradeScreen() {
        switch (subState) {
            case 0 -> tradeSelect();
            case 1 -> tradeBuy();
            case 2 -> tradeSell();
        }
        gameManager.keyHandler.enterPressed = false;
    }

    private void tradeSelect() {
        npc.dialogueSet = 0;
        showDialog();

        int x = GameManager.TILE_SIZE * 3;
        int y = GameManager.TILE_SIZE * 7;
        if (npc != null && npc.name != null) {
            drawShadowAndText(npc.name, x + GameManager.TILE_SIZE, y, 3, 3);
        }
        //Draw window
        x = GameManager.TILE_SIZE * 14;
        y = GameManager.TILE_SIZE * 4;
        int width = GameManager.TILE_SIZE * 3;
        int height = (int) (GameManager.TILE_SIZE * 3.5);
        drawSubWindow(x, y, width, height);
        //Draw text
        x += GameManager.TILE_SIZE;
        y += GameManager.TILE_SIZE;
        graphics2D.drawString("Buy", x, y);
        if (commandNum == 0) {
            showChooseCursor(x, y, 25);
            if (gameManager.keyHandler.enterPressed) subState = 1;
        }
        y += GameManager.TILE_SIZE;
        graphics2D.drawString("Sell", x, y);
        if (commandNum == 1) {
            showChooseCursor(x, y, 25);
            if (gameManager.keyHandler.enterPressed) subState = 2;
        }
        y += GameManager.TILE_SIZE;
        graphics2D.drawString("Leave", x, y);
        if (commandNum == 2) {
            showChooseCursor(x, y, 25);
            if (gameManager.keyHandler.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 1);
            }
        }
    }

    private void tradeBuy() {
        //Draw player inventory
        drawInventory(gameManager.player, false);
        //Draw npc inventory
        drawInventory(npc, true);
        //Draw hint window
        drawHintCoin();
        int width;
        int x;
        int height;
        int y;
        //Price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = (int) (GameManager.TILE_SIZE * 5.5);
            y = (int) (GameManager.TILE_SIZE * 5.5);
            width = (int) (GameManager.TILE_SIZE * 2.5);
            height = GameManager.TILE_SIZE;
            drawSubWindow(x, y, width, height);
            graphics2D.drawImage(coin, x + 10, y + 5, 40, 40, null);
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXAlignToRightText(text, GameManager.TILE_SIZE * 8 - 20);
            graphics2D.drawString(text, x, y + 34);
            //Buy item
            if (gameManager.keyHandler.enterPressed) {
                if (npc.inventory.get(itemIndex).price > gameManager.player.coin) {
                    subState = 0;
                    npc.startDialogue(npc, 2);
                } else {
                    if (gameManager.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gameManager.player.coin -= npc.inventory.get(itemIndex).price;
                    } else {
                        subState = 0;
                        npc.startDialogue(npc, 3);
                    }
                }
            }
        }
    }

    private void tradeSell() {
        drawInventory(gameManager.player, true);
        drawHintCoin();
        int width;
        int y;
        int height;
        int x;
        //Price window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gameManager.player.inventory.size()) {
            x = (int) (GameManager.TILE_SIZE * 15.5);
            y = (int) (GameManager.TILE_SIZE * 5.5);
            width = (int) (GameManager.TILE_SIZE * 2.5);
            height = GameManager.TILE_SIZE;
            drawSubWindow(x, y, width, height);
            graphics2D.drawImage(coin, x + 10, y + 5, 40, 40, null);
            int price = gameManager.player.inventory.get(itemIndex).price / 2;
            String text = "" + price;
            x = getXAlignToRightText(text, GameManager.TILE_SIZE * 18 - 20);
            graphics2D.drawString(text, x, y + 34);
            //Sell item
            if (gameManager.keyHandler.enterPressed) {
                if (gameManager.player.inventory.get(itemIndex) == gameManager.player.currentWeapon ||
                        gameManager.player.inventory.get(itemIndex) == gameManager.player.currentShield) {
                    commandNum = 0;
                    subState = 0;
                    npc.startDialogue(npc, 4);
                } else {
                    if (gameManager.player.inventory.get(itemIndex).amount > 1) {
                        gameManager.player.inventory.get(itemIndex).amount--;
                    } else {
                        gameManager.player.inventory.remove(itemIndex);
                    }
                    gameManager.player.coin += price;
                }
            }
        }
    }

    private void drawHintCoin() {
        //Draw hint window
        int x = GameManager.TILE_SIZE * 2;
        int y = (int) (GameManager.TILE_SIZE * 9.1);
        int width = GameManager.TILE_SIZE * 6;
        int height = GameManager.TILE_SIZE * 2;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("Press ESC to return.", x + 24, y + 60);
        //Draw player's coins
        x = GameManager.TILE_SIZE * 12;
        drawSubWindow(x, y, width, height);
        graphics2D.drawString("Your coins: " + gameManager.player.coin, x + 24, y + 60);
    }

    private void drawTransitionScreen() {
        counter++;
        graphics2D.setColor(new Color(0, 0, 0, counter * 5));
        graphics2D.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        if (counter == 50) {
            counter = 0;
            gameManager.gameState = GameManager.PLAY_STATE;
            gameManager.currentMap = gameManager.eventHandler.tempMap;
            gameManager.player.worldX = GameManager.TILE_SIZE * gameManager.eventHandler.tempCol;
            gameManager.player.worldY = GameManager.TILE_SIZE * gameManager.eventHandler.tempRow;
            gameManager.eventHandler.previousEventX = gameManager.player.worldX;
            gameManager.eventHandler.previousEventY = gameManager.player.worldY;
            gameManager.changeArea();
        }
    }

    private void drawGameOverScreen() {
        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        int x;
        int y;
        String text;
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 110F));
        text = "Game Over";
        graphics2D.setColor(Color.BLACK);
        x = getXCenterText(text);
        y = GameManager.TILE_SIZE * 4;
        drawShadowAndText(text, x, y, 4, 4);
        //Retry
        graphics2D.setFont(graphics2D.getFont().deriveFont(50F));
        text = "Retry";
        x = getXCenterText(text);
        y += GameManager.TILE_SIZE * 4;
        drawShadowAndText(text, x, y, 3, 3);
        if (commandNum == 0) {
            showChooseCursor(x, y, 40);
        }
        //Back to title screen
        text = "Quit";
        x = getXCenterText(text);
        y += 55;
        drawShadowAndText(text, x, y, 3, 3);
        if (commandNum == 1) {
            showChooseCursor(x, y, 40);
        }
    }

    private void drawOptionScreen() {
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
        //Sub Window on center of screen
        int frameX = GameManager.TILE_SIZE * 6;
        int frameY = GameManager.TILE_SIZE;
        int frameWidth = GameManager.TILE_SIZE * 8;
        int frameHeight = GameManager.TILE_SIZE * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0 -> optionTop(frameX, frameY);
            case 1 -> fullScreenNotification(frameX, frameY);
            case 2 -> showControl(frameX, frameY);
            case 3 -> endGameConfirm(frameX, frameY);
        }
        gameManager.keyHandler.enterPressed = false;
    }

    public void optionTop(int frameX, int frameY) {
        int textX;
        int textY;
        //Title
        String text = "Settings";
        textX = getXCenterText(text);
        textY = frameY + GameManager.TILE_SIZE;
        graphics2D.drawString(text, textX, textY);

        //Full Screen on/off
        textX = frameX + GameManager.TILE_SIZE;
        textY += GameManager.TILE_SIZE * 2;
        graphics2D.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                gameManager.fullScreenOn = !gameManager.fullScreenOn;
                subState = 1;
            }
        }
        //Music
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Music", textX, textY);
        if (commandNum == 1) showChooseCursor(textX, textY, 25);
        //SE
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("SE", textX, textY);
        if (commandNum == 2) showChooseCursor(textX, textY, 25);
        //Control
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("Control", textX, textY);
        if (commandNum == 3) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }
        //End Game
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        //Back option
        textY += GameManager.TILE_SIZE * 2;
        graphics2D.drawString("Back", textX, textY);
        if (commandNum == 5) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                gameManager.gameState = GameManager.PLAY_STATE;
                commandNum = 0;
            }
        }

        //Full Screen checkBox
        textX = (int) (frameX + GameManager.TILE_SIZE * 4.5);
        textY = frameY + GameManager.TILE_SIZE * 2 + 26;
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.drawOval(textX, textY, 24, 24);
        if (gameManager.fullScreenOn) graphics2D.fillOval(textX, textY, 24, 24);

        //Music volume
        textY += GameManager.TILE_SIZE;
        graphics2D.drawRoundRect(textX, textY, 120, 24, 10, 10);//120/5 = 24
        int volumeWidth = 24 * gameManager.music.volumeScale;
        graphics2D.fillRoundRect(textX, textY, volumeWidth, 24, 10, 10);
        //SE volume
        textY += GameManager.TILE_SIZE;
        graphics2D.drawRoundRect(textX, textY, 120, 24, 10, 10);
        volumeWidth = 24 * gameManager.se.volumeScale;
        graphics2D.fillRoundRect(textX, textY, volumeWidth, 24, 10, 10);

        gameManager.config.saveConfig();
    }

    private void showChooseCursor(int textX, int textY, int cursorX) {
        graphics2D.drawString(">", textX - cursorX, textY);
    }

    private void drawInventory(Entity entity, boolean cursor) {
        //Frame
        int frameX, frameY;
        int frameWidth, frameHeight;
        int slotCol, slotRow;
        if (entity == gameManager.player) {
            frameX = GameManager.TILE_SIZE * 12;
            frameY = GameManager.TILE_SIZE;
            frameWidth = GameManager.TILE_SIZE * 6;
            frameHeight = GameManager.TILE_SIZE * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = GameManager.TILE_SIZE * 2;
            frameY = GameManager.TILE_SIZE;
            frameWidth = GameManager.TILE_SIZE * 6;
            frameHeight = GameManager.TILE_SIZE * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        //Slots
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = GameManager.TILE_SIZE + 3;

        //Draw player's items
        for (int i = 0; i < entity.inventory.size(); i++) {
            //Equip cursor
            if (entity.inventory.get(i).equals(entity.currentWeapon) ||
                    entity.inventory.get(i).equals(entity.currentShield) ||
                    entity.inventory.get(i).equals(entity.currentLight)) {
                int alpha = 200; // % transparent
                graphics2D.setColor(new Color(255, 255, 255, alpha));

                graphics2D.fillRect(slotX, slotY, GameManager.TILE_SIZE, GameManager.TILE_SIZE);
            }
            graphics2D.drawImage(entity.inventory.get(i).down, slotX, slotY, null);
            //Display amount of stackable items
            if (entity.inventory.get(i).amount > 1 && entity == gameManager.player) {
                graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 22F));
                int amountX;
                int amountY;
                String text = "" + entity.inventory.get(i).amount;
                amountX = getXAlignToRightText(text, slotX + 44);
                amountY = slotY + GameManager.TILE_SIZE;
                drawShadowAndText(text, amountX, amountY, 3, 3);
            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
        //Draw cursor
        if (cursor) {
            //Cursor
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);

            int cursorWidth = GameManager.TILE_SIZE;
            int cursorHeight = GameManager.TILE_SIZE;

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
            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);
                splitTextInDialog(entity.inventory.get(itemIndex).itemDescription, textX, textY);
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
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
        final int frameX = GameManager.TILE_SIZE * 2;
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
            graphics2D.drawImage(heartEmpty, x, y, null);
            i++;
            x += GameManager.TILE_SIZE;
        }
        x = GameManager.TILE_SIZE / 2;
        y = GameManager.TILE_SIZE / 2;
        i = 0;
        //Draw current Hp
        while (i < gameManager.player.hp) {
            graphics2D.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gameManager.player.hp) {
                graphics2D.drawImage(heartFull, x, y, null);
            }
            i++;
            x += GameManager.TILE_SIZE;
        }

        //Draw the player's max mana
        x = GameManager.TILE_SIZE / 2;
        y = (int) (GameManager.TILE_SIZE * 1.6);
        i = 0;
        while (i < gameManager.player.maxMana) {
            graphics2D.drawImage(manaEmpty, x, y, null);
            i++;
            x += GameManager.TILE_SIZE;
        }
        //Draw mana
        x = GameManager.TILE_SIZE / 2;
        y = (int) (GameManager.TILE_SIZE * 1.6);
        i = 0;
        while (i < gameManager.player.mana) {
            graphics2D.drawImage(manaFull, x, y, null);
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
        int x = GameManager.TILE_SIZE * 3;
        int y = GameManager.TILE_SIZE * 7;
        int width = GameManager.SCREEN_WIDTH - (GameManager.TILE_SIZE * 6);
        int height = GameManager.TILE_SIZE * 4;
        drawSubWindow(x, y, width, height);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 30F));
        x += GameManager.TILE_SIZE;
        y += GameManager.TILE_SIZE;
        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
            char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            if (charIndex < characters.length) {
                //FIXME check why se crushes the game, while talking with seller
                String s = String.valueOf(characters[charIndex]);
                combinedText = combinedText + s;
                currentDialog = combinedText;
                gameManager.playSE(18);
                charIndex++;
            }
            if (gameManager.keyHandler.enterPressed) {
                charIndex = 0;
                combinedText = "";
                if (gameManager.gameState == GameManager.DIALOGUE_STATE) {
                    npc.dialogueIndex++;
                    gameManager.keyHandler.enterPressed = false;
                }
            }
        }
        //If there's no text in array
        else {
            npc.dialogueIndex = 0;
            if (gameManager.gameState == GameManager.DIALOGUE_STATE) {
                gameManager.gameState = GameManager.PLAY_STATE;
            }
        }
        splitTextInDialog(currentDialog, x, y);
    }

    private void splitTextInDialog(String currentDialog, int x, int y) {
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


    private void fullScreenNotification(int frameX, int frameY) {
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
            if (gameManager.keyHandler.enterPressed) subState = 0;
        }
    }

    private void showControl(int frameX, int frameY) {
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
        graphics2D.drawString("C", textX, textY);
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
            if (gameManager.keyHandler.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    private void endGameConfirm(int frameX, int frameY) {
        int textX = frameX + GameManager.TILE_SIZE;
        int textY = frameY + GameManager.TILE_SIZE * 3;
        currentDialog = "Quit the game and \nreturn to title screen?";
        splitTextInDialog(currentDialog, textX, textY);
        //Yes
        String text = "YES, YES ,YES!";
        textX = getXCenterText(text);
        textY += GameManager.TILE_SIZE * 3;
        graphics2D.drawString(text, textX, textY);
        if (commandNum == 0) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                //Quit the game
                subState = 0;
                gameManager.ui.titleScreenState = 0;
                gameManager.gameState = GameManager.TITLE_STATE;
                gameManager.resetGame(true);
                gameManager.stopMusic();
            }
        }
        //No
        text = "NO, NO ,NO!";
        textX = getXCenterText(text);
        textY += GameManager.TILE_SIZE;
        graphics2D.drawString(text, textX, textY);
        if (commandNum == 1) {
            showChooseCursor(textX, textY, 25);
            if (gameManager.keyHandler.enterPressed) {
                //Quit the game
                subState = 0;
                commandNum = 4;
            }
        }
    }

    private void drawPauseScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 70F));
        String pauseText = "PAUSED";
        int x = getXCenterText(pauseText);
        int y = GameManager.SCREEN_HEIGHT / 2;
        graphics2D.drawString(pauseText, x, y);
    }

    private int getXCenterText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return GameManager.SCREEN_WIDTH / 2 - length / 2;
    }

    private int getXAlignToRightText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        return tailX - length;
    }
}
