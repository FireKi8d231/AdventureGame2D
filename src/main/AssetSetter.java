package src.main;

import java.awt.Graphics2D;

import src.object.OBJ_Chest;
import src.object.OBJ_Door;
import src.object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }
    // 32645583 parola net iustin
    public void setObjects()
    {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 10 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 38 * gp.tileSize;
        gp.obj[3].worldY = 13 * gp.tileSize;
    }

    public void drawObjects(Graphics2D g)
    {
        for(int i=0;i<gp.obj.length;++i)
        {
            if(gp.obj[i] != null)
            {
                int screenX = gp.obj[i].worldX - gp.player.worldX + gp.player.screenX;
                int screenY = gp.obj[i].worldY - gp.player.worldY + gp.player.screenY;

                g.drawImage(gp.obj[i].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }
}
