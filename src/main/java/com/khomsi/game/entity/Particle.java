package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;

import java.awt.*;

public class Particle extends Entity {
    //Entity that produces particles
    Entity generator;
    Color color;
    int size;
    //In which direction we want to move particle
    int xDir;
    int yDir;

    public Particle(GameManager gameManager, Entity generator, Color color,
                    int size, int speed, int maxHp, int xDir, int yDir) {
        super(gameManager);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.xDir = xDir;
        this.yDir = yDir;
        this.maxHp = maxHp;
        this.speed = speed;

        hp = maxHp;
        int offset = (GameManager.TILE_SIZE / 2) - (size / 2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }

    @Override
    public void update() {
        hp--;
        if (hp < maxHp / 3) {
            yDir++;
            size--;
        }
        worldX += xDir * speed;
        worldY += yDir * speed;

        if (hp == 0) {
            alive = false;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int screenX = worldX - gameManager.player.worldX + gameManager.player.screenX;
        int screenY = worldY - gameManager.player.worldY + gameManager.player.screenY;

        graphics2D.setColor(color);
        graphics2D.fillRoundRect(screenX, screenY, size, size, 10,10);
    }
}
