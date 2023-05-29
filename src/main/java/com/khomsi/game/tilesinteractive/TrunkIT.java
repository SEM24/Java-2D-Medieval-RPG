package com.khomsi.game.tilesinteractive;

import com.khomsi.game.main.GameManager;

public class TrunkIT extends InteractiveTile {

    public TrunkIT(GameManager gameManager, int col, int row) {
        super(gameManager, col, row);
        this.worldX = GameManager.TILE_SIZE * col;
        this.worldY = GameManager.TILE_SIZE * row;
        down = setup("/tilesinteractive/pine_trunk_1",
                (int) (GameManager.TILE_SIZE * 1.95), GameManager.TILE_SIZE * 2 + 30);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
