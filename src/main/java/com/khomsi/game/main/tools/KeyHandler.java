package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GameManager gameManager;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //Debug
    public boolean debugMode = false;

    public KeyHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //none
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //returns int, associated with the key in this event
        //for example, 17-ctrl, 8-backspace, 10-enter
        int code = e.getKeyCode();
        //Title state
        if (gameManager.gameState == gameManager.titleState) {
            if (gameManager.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gameManager.ui.commandNum--;
                    if (gameManager.ui.commandNum < 0) gameManager.ui.commandNum = 2;
                }
                if (code == KeyEvent.VK_S) {
                    gameManager.ui.commandNum++;
                    if (gameManager.ui.commandNum > 2) gameManager.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    switch (gameManager.ui.commandNum) {
                        case 0 -> {
                            gameManager.ui.titleScreenState = 1;
                        }
                        case 1 -> {
                            //TODO
                        }
                        case 2 -> System.exit(0);
                    }
                }
            } else if (gameManager.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    gameManager.ui.commandNum--;
                    if (gameManager.ui.commandNum < 0) gameManager.ui.commandNum = 2;
                }
                if (code == KeyEvent.VK_S) {
                    gameManager.ui.commandNum++;
                    if (gameManager.ui.commandNum > 2) gameManager.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    //Set character's stats and skin, depends on chose
                    switch (gameManager.ui.commandNum) {
                        case 0 -> {
                            //change the skin of character and stats
                            gameManager.player.playerSkin = 0;
//                            gamePanel.player.speed -= 1;
                            gameManager.player.maxHp = 7;
                            gameManager.player.hp = gameManager.player.maxHp;

                            gameManager.player.getPlayerImage();
                            gameManager.gameState = gameManager.playState;
                            gameManager.playMusic(0);
                        }
                        case 1 -> {
                            //change the skin of character and stats
                            gameManager.player.playerSkin = 1;
                            gameManager.player.speed += 1;
//                            gamePanel.player.maxHp = 6;
//                            gamePanel.player.hp = gamePanel.player.maxHp;

                            gameManager.player.getPlayerImage();
                            gameManager.gameState = gameManager.playState;
                            gameManager.playMusic(0);
                        }
                        case 2 -> gameManager.ui.titleScreenState = 0;
                    }
                }
            }
        }
        //play state
        if (gameManager.gameState == gameManager.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            //Pause the game
            if (code == KeyEvent.VK_P) gameManager.gameState = gameManager.pauseState;
            if (code == KeyEvent.VK_ENTER) enterPressed = true;
            //TODO Debug menu
            if (code == KeyEvent.VK_F9) debugMode = !debugMode;
            if (code == KeyEvent.VK_F8) gameManager.tileManager.loadMap("/maps/world01.txt");
        }
        //pause state
        else if (gameManager.gameState == gameManager.pauseState) {
            if (code == KeyEvent.VK_P) gameManager.gameState = gameManager.playState;
        }
        //dialog state
        else if (gameManager.gameState == gameManager.dialogueState) {
            if (code == KeyEvent.VK_ENTER) gameManager.gameState = gameManager.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
        //TODO when player press shift, character runs faster
        if (code == KeyEvent.VK_SHIFT) {
            if (!gameManager.playerRun) {
                gameManager.playerRun = true;
                gameManager.player.speed += 1;
            } else {
                gameManager.playerRun = false;
                gameManager.player.speed -= 1;
            }
        }
    }
}
