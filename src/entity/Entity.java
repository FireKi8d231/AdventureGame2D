package src.entity;

import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX, worldY;
    public int speed;

    // for sprite rendering
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    // for animation
    public int spriteCounter;
    public int spriteNum;
    public int animationSpeed;
}