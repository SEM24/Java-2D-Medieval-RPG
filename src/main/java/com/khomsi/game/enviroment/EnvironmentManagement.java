package main.java.com.khomsi.game.enviroment;

import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

public class EnvironmentManagement {
    GameManager gameManager;
    public Lightning lightning;

    public EnvironmentManagement(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setup() {
        lightning = new Lightning(gameManager);
    }

    public void update() {
        lightning.update();
    }

    public void draw(Graphics2D g2d) {
        lightning.draw(g2d);
    }
}
