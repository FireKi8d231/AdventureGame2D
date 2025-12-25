package src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

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
        speed = 4;
        direction = "down";
        // for animation
        spriteCounter = 0;
        spriteNum = 1;
        animationSpeed = 10;
        // for collision
        solidArea = new Rectangle();
        solidArea.x = gp.tileSize / 6;
        solidArea.y = gp.tileSize / 3;
        solidArea.width = gp.tileSize * 2 / 3;
        solidArea.height = gp.tileSize * 2 / 3;
    }

    public void update()
    {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
        {
            if(keyH.upPressed == true)
            {
                direction = "up";
            }
            if(keyH.downPressed == true)
            {
                direction = "down";
            }
            if(keyH.leftPressed == true)
            {
                direction = "left";
            }
            if(keyH.rightPressed == true)
            {
                direction = "right";
            }

            // collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            if(collisionOn == false)
            { 
                switch(direction)
                {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
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
