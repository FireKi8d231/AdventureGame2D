package src.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import src.entity.Player;
import src.tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile size
    final int scale = 3; // aceste setari sunt traditionale.

    public int tileSize = originalTileSize * scale; // 48x48 tile size
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;

    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // FPS
    int FPS = 60;

    TileManager tm = new TileManager(this);

    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);

    public GamePanel() // constructor
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // pentru performanta
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void zoomInOut(int zoomDirection)
    {
        double oldWorldWidth = tileSize * maxWorldCol;
        tileSize += zoomDirection;
        int newWorldWidth = tileSize * maxWorldCol;

        player.speed = (double)newWorldWidth / 600;

        double modifier = (double)newWorldWidth/oldWorldWidth;

        double newPlayerWorldX = player.worldX * modifier;
        double newPlayerWorldY = player.worldY * modifier;

        player.worldX = newPlayerWorldX;
        player.worldY = newPlayerWorldY;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double lastTime = System.nanoTime();
        double delta = 0;
        double currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1)
            {
                update();
                repaint();
                delta = 0;
                ++drawCount;
            }

            if(timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // g2 este mai mult ca si un creion

        tm.draw(g2);

        player.draw(g2);
        
        g2.dispose();
    }
}
