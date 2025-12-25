package src.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import src.main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    public void getTileImage()
    {
        try{
            tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png")));
            tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png")));
            tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png")));
            tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png")));
            tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png")));
            tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png")));

            tile[1].collision = true;
            tile[2].collision = true;
            tile[4].collision = true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String mapString)
    {
        try{
            InputStream is = getClass().getResourceAsStream(mapString);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String number[] = line.split(" ");

                    int num = Integer.parseInt(number[col]);

                    mapTileNum[col][row] = num;
                    
                    ++col;
                }
                col = 0;
                ++row;
            }
        }catch(Exception e) {}
    }

    public void draw(Graphics2D g)
    {
        for(int worldCol=0;worldCol<gp.maxWorldCol;++worldCol)
        {
            for(int worldRow=0;worldRow<gp.maxWorldRow;++worldRow)
            {
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                int numOfTile = mapTileNum[worldCol][worldRow];
                if(screenX + gp.tileSize >= 0 && screenX < gp.screenWidth && screenY + gp.tileSize >=0 && screenY < gp.screenHeight)
                {
                    g.drawImage(tile[numOfTile].image, screenX, screenY, gp.tileSize, gp.tileSize, null );
                }
            }
        }
    }
}
