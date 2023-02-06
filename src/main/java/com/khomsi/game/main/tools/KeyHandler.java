package main.java.com.khomsi.game.main.tools;

import main.java.com.khomsi.game.main.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GameManager gameManager;
    public boolean upPressed, downPressed, leftPressed,
            rightPressed, enterPressed, shootKeyPressed;
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
        if (gameManager.gameState == GameManager.TITLE_STATE) {
            titleState(code);
        }
        //play state
        else if (gameManager.gameState == GameManager.PLAY_STATE) {
            playerState(code);
        }
        //pause state
        else if (gameManager.gameState == GameManager.PAUSE_STATE) {
            pauseState(code);
        }
        //Dialog state
        else if (gameManager.gameState == GameManager.DIALOGUE_STATE) {
            dialogState(code);
        }
        //Character state
        else if (gameManager.gameState == GameManager.CHARACTER_STATE) {
            characterState(code);
        }
        //Option state
        else if (gameManager.gameState == GameManager.OPTION_STATE) {
            optionState(code);
        }
        //Game Over state
        else if (gameManager.gameState == GameManager.GAME_OVER_STATE) {
            gameOverState(code);
        }
        //Trade state
        else if (gameManager.gameState == GameManager.TRADE_STATE) {
            tradeState(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
        //When player unpressed shift, he walks
        if (code == KeyEvent.VK_SHIFT && gameManager.playerRun) {
            gameManager.player.speed -= 1;
            gameManager.playerRun = false;
        }
        if (code == KeyEvent.VK_CONTROL) shootKeyPressed = false;
    }

    private void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        //First selection state
        if (gameManager.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gameManager.ui.commandNum--;
                if (gameManager.ui.commandNum < 0) {
                    gameManager.ui.commandNum = 2;
                }
                gameManager.playSE(12);
            }
            if (code == KeyEvent.VK_S) {
                gameManager.ui.commandNum++;
                if (gameManager.ui.commandNum > 2) {
                    gameManager.ui.commandNum = 0;
                }
                gameManager.playSE(12);
            }
        }
        if (gameManager.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gameManager.ui.subState = 0;
            }
        }
        if (gameManager.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gameManager.ui.subState = 0;
            }
        }
    }

    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gameManager.ui.commandNum--;
            if (gameManager.ui.commandNum < 0) {
                gameManager.ui.commandNum = 1;
            }
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S) {
            gameManager.ui.commandNum++;
            if (gameManager.ui.commandNum > 1) {
                gameManager.ui.commandNum = 0;
            }
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gameManager.ui.commandNum == 0) {
                gameManager.gameState = GameManager.PLAY_STATE;
                gameManager.retry();
                gameManager.playMusic(0);
            } else if (gameManager.ui.commandNum == 1) {
                gameManager.ui.titleScreenState = 0;
                gameManager.gameState = GameManager.TITLE_STATE;
                gameManager.restart();
            }
        }
    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) gameManager.gameState = GameManager.PLAY_STATE;
        if (code == KeyEvent.VK_ENTER) enterPressed = true;

        int maxCommandNum = 0;
        switch (gameManager.ui.subState) {
            case 0 -> maxCommandNum = 5;
            case 3 -> maxCommandNum = 1;
        }
        if (code == KeyEvent.VK_W) {
            gameManager.ui.commandNum--;
            gameManager.playSE(12);
            if (gameManager.ui.commandNum < 0) gameManager.ui.commandNum = maxCommandNum;
        }
        if (code == KeyEvent.VK_S) {
            gameManager.ui.commandNum++;
            gameManager.playSE(12);
            if (gameManager.ui.commandNum > maxCommandNum) gameManager.ui.commandNum = 0;
        }
        //Change volume
        if (code == KeyEvent.VK_A) {
            if (gameManager.ui.subState == 0) {
                if (gameManager.ui.commandNum == 1 && gameManager.music.volumeScale > 0) {
                    gameManager.music.volumeScale--;
                    gameManager.music.checkVolume();
                    gameManager.playSE(12);
                }
                if (gameManager.ui.commandNum == 2 && gameManager.se.volumeScale > 0) {
                    gameManager.se.volumeScale--;
                    gameManager.playSE(12);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gameManager.ui.subState == 0) {
                if (gameManager.ui.commandNum == 1 && gameManager.music.volumeScale < 5) {
                    gameManager.music.volumeScale++;
                    gameManager.music.checkVolume();
                    gameManager.playSE(12);
                }
                if (gameManager.ui.commandNum == 2 && gameManager.se.volumeScale < 5) {
                    gameManager.se.volumeScale++;
                    gameManager.playSE(12);
                }
            }
        }
    }

    private void playerState(int code) {
        if (code == KeyEvent.VK_W) upPressed = true;
        if (code == KeyEvent.VK_S) downPressed = true;
        if (code == KeyEvent.VK_A) leftPressed = true;
        if (code == KeyEvent.VK_D) rightPressed = true;
        //Show character stats
        if (code == KeyEvent.VK_C) gameManager.gameState = GameManager.CHARACTER_STATE;
        //Pause the game
        if (code == KeyEvent.VK_P) gameManager.gameState = GameManager.PAUSE_STATE;
        if (code == KeyEvent.VK_ENTER) enterPressed = true;

        if (code == KeyEvent.VK_CONTROL) shootKeyPressed = true;
        if (code == KeyEvent.VK_ESCAPE) gameManager.gameState = GameManager.OPTION_STATE;
        //When player pressed shift, he runs
        if (code == KeyEvent.VK_SHIFT && !gameManager.playerRun) {
            gameManager.playerRun = true;
            gameManager.player.speed += 1;
        }
        //Debug menu
        if (code == KeyEvent.VK_F9) debugMode = !debugMode;
        if (code == KeyEvent.VK_F8) {
            switch (gameManager.currentMap) {
                case 0 -> gameManager.tileManager.loadMap("/maps/world01.txt", 0);
                case 1 -> gameManager.tileManager.loadMap("/maps/interior01.txt", 1);
            }
        }
    }

    private void titleState(int code) {
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
                    case 0 -> gameManager.ui.titleScreenState = 1;
                    case 1 -> {
                        //TODO add load menu in future
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
            if (code == KeyEvent.VK_C) gameManager.gameState = GameManager.CHARACTER_STATE;

            if (code == KeyEvent.VK_ENTER) {
                //Set character's stats and skin, depends on chose
                switch (gameManager.ui.commandNum) {
                    case 0 -> gameManager.player.createNewPlayer(0, 0, 3);
                    case 1 -> gameManager.player.createNewPlayer(1, 0, 4);
                    case 2 -> gameManager.ui.titleScreenState = 0;
                }
            }
        }
    }


    private void characterState(int code) {
        if (code == KeyEvent.VK_C) gameManager.gameState = GameManager.PLAY_STATE;
        if (code == KeyEvent.VK_ENTER) {
            gameManager.player.selectItem();
        }
        playerInventory(code);
    }

    private void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gameManager.ui.playerSlotRow != 0)
                gameManager.ui.playerSlotRow--;

            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_A) {
            if (gameManager.ui.playerSlotCol != 0)
                gameManager.ui.playerSlotCol--;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S) {
            if (gameManager.ui.playerSlotRow != 3)
                gameManager.ui.playerSlotRow++;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_D) {
            if (gameManager.ui.playerSlotCol != 4)
                gameManager.ui.playerSlotCol++;
            gameManager.playSE(12);
        }
    }

    private void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gameManager.ui.npcSlotRow != 0)
                gameManager.ui.npcSlotRow--;

            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_A) {
            if (gameManager.ui.npcSlotCol != 0)
                gameManager.ui.npcSlotCol--;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S) {
            if (gameManager.ui.npcSlotRow != 3)
                gameManager.ui.npcSlotRow++;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_D) {
            if (gameManager.ui.npcSlotCol != 4)
                gameManager.ui.npcSlotCol++;
            gameManager.playSE(12);
        }
    }

    private void dialogState(int code) {
        if (code == KeyEvent.VK_ENTER) gameManager.gameState = GameManager.PLAY_STATE;
    }

    private void pauseState(int code) {
        if (code == KeyEvent.VK_P) gameManager.gameState = GameManager.PLAY_STATE;
    }

}
