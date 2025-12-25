package src.main;

import src.entity.Entity;

public class CollisionChecker {
    
    public GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + gp.tileSize - entity.solidArea.x;
        
        int entityUpWorldY = entity.worldY + entity.solidArea.y;
        int entityDownWorldY = entity.worldY + gp.tileSize;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityUpRow = entityUpWorldY / gp.tileSize;
        int entityDownRow = entityDownWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction)
        {
            case "up":
                entityUpRow = (entityUpWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityUpRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityUpRow];
                if(gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityDownRow = (entityDownWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityDownRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityDownRow];
                if(gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityUpRow];
                tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityDownRow];
                if(gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tm.mapTileNum[entityRightCol][entityUpRow];
                tileNum2 = gp.tm.mapTileNum[entityRightCol][entityDownRow];
                if(gp.tm.tile[tileNum1].collision == true || gp.tm.tile[tileNum2].collision == true)
                {
                    entity.collisionOn = true;
                }
                break;    
        }
    }
}
