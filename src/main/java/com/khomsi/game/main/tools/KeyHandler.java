package com.khomsi.game.main.tools;

import com.khomsi.game.main.GameManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GameManager gameManager;
    public boolean isUpPressed, isDownPressed, isLeftPressed,
            isRightPressed, isEnterPressed, isShootKeyPressed, isSpacePressed;
    //Debug
    public boolean debugMode = false;
    public boolean godMode = false;

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
        if (gameManager.gameState == GameManager.START_STATE) {
            startState(code);
        }
        if (gameManager.gameState == GameManager.TUTORIAL_STATE) {
            tutorialState(code);
        }
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
        else if (gameManager.gameState == GameManager.DIALOGUE_STATE
                || gameManager.gameState == GameManager.CUTSCENE_STATE) {
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
        //Map state
        else if (gameManager.gameState == GameManager.MAP_STATE) {
            mapState(code);
        }
    }

    private void mapState(int code) {
        if (code == KeyEvent.VK_M) gameManager.gameState = GameManager.PLAY_STATE;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // Update the state of the controller
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) isUpPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) isDownPressed = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) isLeftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) isRightPressed = false;
        if (code == KeyEvent.VK_ENTER) isEnterPressed = false;
        if (code == KeyEvent.VK_SPACE) isSpacePressed = false;
        //When player unpressed shift, he walks
        if (code == KeyEvent.VK_SHIFT && gameManager.playerRun) {
            gameManager.player.speed -= 1;
            gameManager.playerRun = false;
        }
        if (code == KeyEvent.VK_CONTROL) isShootKeyPressed = false;
    }

    private void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            isEnterPressed = true;
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
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gameManager.ui.commandNum--;
            if (gameManager.ui.commandNum < 0) {
                gameManager.ui.commandNum = 1;
            }
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gameManager.ui.commandNum++;
            if (gameManager.ui.commandNum > 1) {
                gameManager.ui.commandNum = 0;
            }
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gameManager.ui.commandNum == 0) {
                gameManager.gameState = GameManager.PLAY_STATE;
                gameManager.resetGame(false);
                //fixme check the se
                gameManager.playMusic(0);
            } else if (gameManager.ui.commandNum == 1) {
                gameManager.resetGame(true);
                System.exit(0);
            }
        }
    }

    private void optionState(int code) {
        if (code == KeyEvent.VK_ESCAPE) gameManager.gameState = GameManager.PLAY_STATE;
        if (code == KeyEvent.VK_ENTER) isEnterPressed = true;

        int maxCommandNum = 0;
        switch (gameManager.ui.subState) {
            case 0 -> maxCommandNum = 5;
            case 3 -> maxCommandNum = 1;
        }
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gameManager.ui.commandNum--;
            gameManager.playSE(12);
            if (gameManager.ui.commandNum < 0) gameManager.ui.commandNum = maxCommandNum;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gameManager.ui.commandNum++;
            gameManager.playSE(12);
            if (gameManager.ui.commandNum > maxCommandNum) gameManager.ui.commandNum = 0;
        }
        //Change volume
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
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
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
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
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) isUpPressed = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) isDownPressed = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) isLeftPressed = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) isRightPressed = true;
        if (code == KeyEvent.VK_CONTROL) isShootKeyPressed = true;
        if (code == KeyEvent.VK_ENTER) isEnterPressed = true;
        if (code == KeyEvent.VK_SPACE) isSpacePressed = true;
        //Show character stats
        if (code == KeyEvent.VK_E) gameManager.gameState = GameManager.CHARACTER_STATE;
        //Pause the game
        if (code == KeyEvent.VK_P) gameManager.gameState = GameManager.PAUSE_STATE;
        if (code == KeyEvent.VK_ESCAPE) gameManager.gameState = GameManager.OPTION_STATE;
        //Opens the map
        if (code == KeyEvent.VK_M && gameManager.nextArea != GameManager.DUNGEON)
            gameManager.gameState = GameManager.MAP_STATE;
        if (code == KeyEvent.VK_X)
            gameManager.map.miniMapOn = !gameManager.map.miniMapOn;
        //When player pressed shift, he runs
        if (code == KeyEvent.VK_SHIFT && !gameManager.playerRun) {
            gameManager.playerRun = true;
            gameManager.player.speed += 1;
        }
        //Debug menu

        //TODO disable on production
        if (code == KeyEvent.VK_F9) debugMode = !debugMode;
        if (code == KeyEvent.VK_F8) godMode = !godMode;
        if (code == KeyEvent.VK_F7) {
            switch (gameManager.currentMap) {
                case 0 -> gameManager.tileManager.loadMap("/maps/world00.txt", 0);
            }
        }
    }

    private void startState(int code) {
        gameManager.saveLoad.loadTitleData();
        if (code == KeyEvent.VK_ENTER) {
            if (!gameManager.saveLoad.hasFile) {
                gameManager.ui.titleScreenState = 0;
                gameManager.gameState = GameManager.TUTORIAL_STATE;
            } else {
                gameManager.ui.titleScreenState = 1;
                gameManager.gameState = GameManager.TITLE_STATE;
            }
            isEnterPressed = true;
        }
    }

    private void tutorialState(int code) {
        // Play music only once in tutorial screen
        if (!gameManager.isMusicPlaying) {
            gameManager.playMusic(25);
            gameManager.isMusicPlaying = true;
        }
        if (gameManager.ui.titleScreenState == 0) {
            if (!isEnterPressed && code == KeyEvent.VK_ENTER) {
                gameManager.ui.titleScreenState = 2;
                gameManager.gameState = GameManager.TITLE_STATE;
                isEnterPressed = true;
            }
        }
    }


    private void titleState(int code) {
        // Play music only once in title screen
        if (!gameManager.isMusicPlaying) {
            gameManager.playMusic(25);
            gameManager.isMusicPlaying = true;
        }
        if (gameManager.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gameManager.ui.commandNum = 2; // Move cursor to "EXIT GAME" (commandNum = 2)
            } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                gameManager.ui.commandNum = 0; // Move cursor to "NEW GAME" (commandNum = 0)
            } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                gameManager.ui.commandNum = 1; // Move cursor to "LOAD GAME" (commandNum = 1)
            }
            if (!isEnterPressed && code == KeyEvent.VK_ENTER) {
                switch (gameManager.ui.commandNum) {
                    case 0 -> gameManager.ui.titleScreenState = 2;
                    case 2 -> System.exit(0);
                    case 1 -> {
                        if (gameManager.saveLoad.load()) {
                            gameManager.stopTitleMusic();
                            gameManager.saveLoad.load();
                            gameManager.gameState = GameManager.PLAY_STATE;
                            gameManager.playMusic(0);
                        } else gameManager.ui.titleScreenState = 2;
                    }
                }
            }
        } else if (gameManager.ui.titleScreenState == 2) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gameManager.ui.commandNum--;
                if (gameManager.ui.commandNum < 0) gameManager.ui.commandNum = 2;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gameManager.ui.commandNum++;
                if (gameManager.ui.commandNum > 2) gameManager.ui.commandNum = 0;
            }

            if (!isEnterPressed && code == KeyEvent.VK_ENTER) {
                gameManager.stopTitleMusic();
                if (gameManager.saveLoad.hasFile) {
                    gameManager.saveLoad.file.delete();
                    gameManager.resetTimer();
                }
                //Set character's stats and skin, depends on chose
                switch (gameManager.ui.commandNum) {
                    case 0 -> gameManager.player.createNewPlayer(0, 6, 2);
                    case 1 -> gameManager.player.createNewPlayer(1, 5, 3);
                    case 2 -> System.exit(0);
                }
            }
        }
    }


    private void characterState(int code) {
        if (code == KeyEvent.VK_E || code == KeyEvent.VK_ESCAPE) gameManager.gameState = GameManager.PLAY_STATE;
        if (code == KeyEvent.VK_ENTER) {
            gameManager.player.selectItem();
        }
        playerInventory(code);
    }

    private void playerInventory(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gameManager.ui.playerSlotRow != 0)
                gameManager.ui.playerSlotRow--;

            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gameManager.ui.playerSlotCol != 0)
                gameManager.ui.playerSlotCol--;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gameManager.ui.playerSlotRow != 3)
                gameManager.ui.playerSlotRow++;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gameManager.ui.playerSlotCol != 4)
                gameManager.ui.playerSlotCol++;
            gameManager.playSE(12);
        }
    }

    private void npcInventory(int code) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (gameManager.ui.npcSlotRow != 0)
                gameManager.ui.npcSlotRow--;

            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gameManager.ui.npcSlotCol != 0)
                gameManager.ui.npcSlotCol--;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (gameManager.ui.npcSlotRow != 3)
                gameManager.ui.npcSlotRow++;
            gameManager.playSE(12);
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gameManager.ui.npcSlotCol != 4)
                gameManager.ui.npcSlotCol++;
            gameManager.playSE(12);
        }
    }

    private void dialogState(int code) {
        if (code == KeyEvent.VK_ENTER) isEnterPressed = true;
    }

    private void pauseState(int code) {
        if (code == KeyEvent.VK_P) gameManager.gameState = GameManager.PLAY_STATE;
    }
}
