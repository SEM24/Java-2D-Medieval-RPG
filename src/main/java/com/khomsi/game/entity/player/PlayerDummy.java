package com.khomsi.game.entity.player;

import com.khomsi.game.entity.Entity;
import com.khomsi.game.main.GameManager;

import static com.khomsi.game.entity.player.Player.PLAYER_PATH;

public class PlayerDummy extends Entity {
    public static final String NPC_NAME = "Dummy";

    public PlayerDummy(GameManager gameManager) {
        super(gameManager);
        name = NPC_NAME;
        getImage();
    }
    public void getImage() {
        int skin = gameManager.player.playerSkin;
        up = setup(PLAYER_PATH[skin] + "player_up");
        up1 = setup(PLAYER_PATH[skin] + "player_up_1");
        up2 = setup(PLAYER_PATH[skin] + "player_up_2");
        up3 = setup(PLAYER_PATH[skin] + "player_up_3");
        down = setup(PLAYER_PATH[skin] + "player_down");
        down1 = setup(PLAYER_PATH[skin] + "player_down_1");
        down2 = setup(PLAYER_PATH[skin] + "player_down_2");
        down3 = setup(PLAYER_PATH[skin] + "player_down_3");
        left = setup(PLAYER_PATH[skin] + "player_left");
        left1 = setup(PLAYER_PATH[skin] + "player_left_1");
        left2 = setup(PLAYER_PATH[skin] + "player_left_2");
        left3 = setup(PLAYER_PATH[skin] + "player_left_3");
        right = setup(PLAYER_PATH[skin] + "player_right");
        right1 = setup(PLAYER_PATH[skin] + "player_right_1");
        right2 = setup(PLAYER_PATH[skin] + "player_right_2");
        right3 = setup(PLAYER_PATH[skin] + "player_right_3");
    }
}
