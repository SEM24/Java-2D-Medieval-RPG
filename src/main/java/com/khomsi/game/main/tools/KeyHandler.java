package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
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

        if (code == KeyEvent.VK_W) upPressed = true;
        if (code == KeyEvent.VK_S) downPressed = true;
        if (code == KeyEvent.VK_A) leftPressed = true;
        if (code == KeyEvent.VK_D) rightPressed = true;
        //Pause the game
        if (code == KeyEvent.VK_P) {
            if (gamePanel.gameState == gamePanel.playState)
                gamePanel.gameState = gamePanel.pauseState;
            else if (gamePanel.gameState == gamePanel.pauseState)
                gamePanel.gameState = gamePanel.playState;
        }

        //TODO Debug
        if (code == KeyEvent.VK_F9) debugMode = !debugMode;
        if (code == KeyEvent.VK_F8) gamePanel.tileManager.loadMap("/maps/world01.txt");
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
