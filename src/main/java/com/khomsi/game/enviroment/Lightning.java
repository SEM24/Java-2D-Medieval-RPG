package com.khomsi.game.enviroment;

import com.khomsi.game.main.GameManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lightning {
    BufferedImage darknessFilter;
    GameManager gameManager;
    public int dayCounter;

    public float filterAlfa = 0F;
    //Day states
    public static final int DAY = 0;
    public static final int NIGHTFALL = 1;
    public static final int NIGHT = 2;
    public static final int DAWN = 3;
    public int dayState = DAY;

    public Lightning(GameManager gameManager) {
        this.gameManager = gameManager;
        setLightSource();
    }

    public void setLightSource() {
        //Create a buf image
        darknessFilter = new BufferedImage(GameManager.SCREEN_WIDTH,
                GameManager.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) darknessFilter.getGraphics();
        if (gameManager.player.currentLight == null) {
            g2d.setColor(new Color(0, 0, 0.1F, 0.98F));
        } else {
            //Get the center x and y of the light circle
            int centerX = gameManager.player.screenX + (GameManager.TILE_SIZE) / 2;
            int centerY = gameManager.player.screenY + (GameManager.TILE_SIZE) / 2;

            //Create a gradation effect within the light circle
            Color[] color = new Color[12];
            float[] fraction = new float[12];
            color[0] = new Color(0, 0, 0.1F, 0.1F);
            color[1] = new Color(0, 0, 0.1F, 0.42F);
            color[2] = new Color(0, 0, 0.1F, 0.52F);
            color[3] = new Color(0, 0, 0.1F, 0.61F);
            color[4] = new Color(0, 0, 0.1F, 0.69F);
            color[5] = new Color(0, 0, 0.1F, 0.76F);
            color[6] = new Color(0, 0, 0.1F, 0.82F);
            color[7] = new Color(0, 0, 0.1F, 0.87F);
            color[8] = new Color(0, 0, 0.1F, 0.91F);
            color[9] = new Color(0, 0, 0.1F, 0.92F);
            color[10] = new Color(0, 0, 0.1F, 0.93F);
            color[11] = new Color(0, 0, 0.1F, 0.94F);

            fraction[0] = 0F;
            fraction[1] = 0.4F;
            fraction[2] = 0.5F;
            fraction[3] = 0.6F;
            fraction[4] = 0.65F;
            fraction[5] = 0.7F;
            fraction[6] = 0.75F;
            fraction[7] = 0.8F;
            fraction[8] = 0.85F;
            fraction[9] = 0.9F;
            fraction[10] = 0.95F;
            fraction[11] = 1F;

            //Create a gradation paint settings for the light circle
            RadialGradientPaint paint = new RadialGradientPaint(centerX, centerY,
                    gameManager.player.currentLight.lightRadius, fraction, color);
            //Set the gradient data on g2d
            g2d.setPaint(paint);
        }

        //Draw the screen rect without the light circle area
        g2d.fillRect(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        g2d.dispose();
    }

    public void resetDay() {
        dayState = DAY;
        dayCounter = 0;
        filterAlfa = 0F;
    }

    public void update() {
        if (gameManager.player.lightUpdated) {
            setLightSource();
            gameManager.player.lightUpdated = false;
        }
        //Check the state of the day
        if (dayState == DAY) {
            dayCounter++;
            if (dayCounter > 3200) {
                dayState = NIGHTFALL;
                dayCounter = 0;
            }
        }
        if (dayState == NIGHTFALL) {
            filterAlfa += 0.0003F;
            if (filterAlfa > 1F) {
                filterAlfa = 1F;
                dayState = NIGHT;
            }
        }
        if (dayState == NIGHT) {
            dayCounter++;
            if (dayCounter > 3200) {
                dayState = DAWN;
                dayCounter = 0;
            }
        }
        if (dayState == DAWN) {
            filterAlfa -= 0.0003F;
            if (filterAlfa < 0F) {
                filterAlfa = 0;
                dayState = DAY;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        if (gameManager.currentArea == GameManager.OUTSIDE
                || gameManager.currentArea == GameManager.LIGHT_DUNGEON
                || gameManager.gameState == GameManager.SLEEP_STATE) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlfa));
        }

        if (gameManager.currentArea == GameManager.OUTSIDE || gameManager.currentArea == GameManager.DUNGEON
                || gameManager.currentArea == GameManager.BOSS_DUNGEON) {
            g2d.drawImage(darknessFilter, 0, 0, null);
        }

        if (gameManager.gameState == GameManager.DYING_STATE
                && gameManager.currentArea == GameManager.LIGHT_DUNGEON
                || gameManager.gameState == GameManager.SLEEP_STATE)
            g2d.drawImage(darknessFilter, 0, 0, null);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        debugDayState(g2d);

        if (gameManager.gameState == GameManager.PLAY_STATE
                || gameManager.gameState == GameManager.PAUSE_STATE)
            gameManager.ui.hud.drawClock(dayState, filterAlfa);
    }

    private void debugDayState(Graphics2D g2d) {
        if (gameManager.keyHandler.debugMode) {
            String situation = "";
            switch (dayState) {
                case DAY -> situation = "Day";
                case NIGHTFALL -> situation = "NightFall";
                case NIGHT -> situation = "Night";
                case DAWN -> situation = "Dawn";
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(45F));
            g2d.drawString(situation, 750, 500);
        }
    }
}
