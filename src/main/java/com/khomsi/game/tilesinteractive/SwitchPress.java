package com.khomsi.game.tilesinteractive;

import com.khomsi.game.main.GameManager;

public class SwitchPress extends InteractiveTile {
    public static final String IT_NAME = "Switch Press";
    GameManager gameManager;

    public SwitchPress(GameManager gameManager, int col, int row) {
        super(gameManager, col, row);
        this.gameManager = gameManager;
        this.worldX = GameManager.TILE_SIZE * col;
        this.worldY = GameManager.TILE_SIZE * row;
        name = IT_NAME;
        down = setup("/tilesinteractive/dungeon/switch_press");

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
