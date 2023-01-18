package main.java.com.khomsi.game.entity;

import main.java.com.khomsi.game.main.GameManager;

public class ProjectTile extends Entity {
    Entity entity;

    public ProjectTile(GameManager gameManager) {
        super(gameManager);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity entity) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.entity = entity;
        //Reset hp to max every time you shoot the projectile
        this.hp = this.maxHp;
    }

    @Override
    public void update() {
        hittingDetection();
        switch (direction) {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        //After n-frames it disappears
        hp--;
        if (hp <= 0) {
            alive = false;
        }
        spriteMovement();
    }

    //Check hit collision
    public void hittingDetection() {
        if (entity == gameManager.player) {
            int mobIndex = gameManager.checkCollision.checkEntity(this, gameManager.mobs);
            if (mobIndex != gameManager.player.playerIndex) {
                gameManager.player.damageMob(mobIndex, attack);
                //After hitting the mob, projectile disappear
                alive = false;
            }
        }
        if (entity != gameManager.player) {
            boolean contactPlayer = gameManager.checkCollision.checkPlayer(this);
            if (!gameManager.player.invincible && contactPlayer) {
                damagePlayer(attack);
                alive = false;
            }
        }
    }

    public boolean haveResource(Entity entity) {
        // Empty method because it's overridden in children classes with some logic.
        //No sense to add here basic logic.
        return false;
    }

    public void subtractResource(Entity entity) {
        // Empty method because it's overridden in children classes with some logic.
        //No sense to add here basic logic.
    }
}
