package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //Debug
    public boolean debugMode = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
        if (gamePanel.gameState == gamePanel.titleState) {
            if (code == KeyEvent.VK_W) {
                gamePanel.ui.commandNum--;
                if (gamePanel.ui.commandNum < 0) gamePanel.ui.commandNum = 2;
            }
            if (code == KeyEvent.VK_S) {
                gamePanel.ui.commandNum++;
                if (gamePanel.ui.commandNum > 2) gamePanel.ui.commandNum = 0;
            }
            if (code == KeyEvent.VK_ENTER) {
                switch (gamePanel.ui.commandNum) {
                    case 0 -> {
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.playMusic(0);
                    }
                    case 1 -> {
                        //TODO
                    }
                    case 2 -> System.exit(0);
                }
            }
        }
        //play state
        if (gamePanel.gameState == gamePanel.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;
            if (code == KeyEvent.VK_D) rightPressed = true;
            //Pause the game
            if (code == KeyEvent.VK_P) gamePanel.gameState = gamePanel.pauseState;
            if (code == KeyEvent.VK_ENTER) enterPressed = true;
            //TODO Debug
            if (code == KeyEvent.VK_F9) debugMode = !debugMode;
            if (code == KeyEvent.VK_F8) gamePanel.tileManager.loadMap("/maps/world01.txt");
        }
        //pause state
        else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P) gamePanel.gameState = gamePanel.playState;
        }
        //dialog state
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyEvent.VK_ENTER) gamePanel.gameState = gamePanel.playState;
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
            if (!gamePanel.playerRun) {
                gamePanel.playerRun = true;
                gamePanel.player.speed = 4;
            } else {
                gamePanel.playerRun = false;
                gamePanel.player.speed = 3;
            }
        }
    }
}
