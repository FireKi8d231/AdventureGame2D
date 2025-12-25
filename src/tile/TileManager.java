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
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/res/maps/map01.txt");
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
            while(col < gp.maxScreenCol && row < gp.maxScreenRow)
            {
                String line = br.readLine();

                while(col < gp.maxScreenCol)
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
        for(int col=0;col<gp.maxScreenCol;++col)
        {
            for(int row=0;row<gp.maxScreenRow;++row)
            {
                g.drawImage(tile[mapTileNum[col][row]].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null );
            }
        }
    }
}
