package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import src.main.KeyHandler;
import src.main.GamePanel;

public class Player extends Entity
{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage()
    {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues()
    {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = gp.worldWidth / 600; // tot 4 o sa fie
        direction = "down";
        // for animation
        spriteCounter = 0;
        spriteNum = 1;
        animationSpeed = 10;
    }

    public void update()
    {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if(keyH.upPressed == true)
            {
                worldY -= speed;
                direction = "up";
            }
            if(keyH.downPressed == true)
            {
                worldY += speed;
                direction = "down";
            }
            if(keyH.leftPressed == true)
            {
                worldX -= speed;
                direction = "left";
            }
            if(keyH.rightPressed == true)
            {
                worldX += speed;
                direction = "right";
            }

            // for animation
            ++spriteCounter;
            if(spriteCounter > animationSpeed)
            {
                spriteNum *= -1;
                spriteCounter = 0;
            }
        }
        else { spriteNum = 1; }
    }
    public void draw(Graphics2D g)
    {
        BufferedImage image = null;
        
        switch(direction)
        {
            case "up":
                if(spriteNum == 1) { image = up1; }
                else { image = up2; }
                break;
            case "down":
                if(spriteNum == 1) { image = down1; }
                else {image = down2; }
                break;
            case "left":
                if(spriteNum == 1) { image = left1; }
                else { image = left2; }
                break;
            case "right":
                if(spriteNum == 1) { image = right1; }
                else { image = right2; }
                break;
            default:
                image = down1;
                break;
        }

        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
