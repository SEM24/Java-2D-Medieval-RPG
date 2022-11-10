package main.java.com.khomsi.game.entity;

import java.awt.image.BufferedImage;

//parent class for player, monster ect
public class Entity {
    //Set default position
    public int x, y;
    public int speed;
    //we store our images in this variables
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int standCounter = 0;
    public int spriteNum = 1;
}
