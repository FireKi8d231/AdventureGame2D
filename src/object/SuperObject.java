package src.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import src.main.GamePanel;

public class SuperObject
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g, GamePanel gp)
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}