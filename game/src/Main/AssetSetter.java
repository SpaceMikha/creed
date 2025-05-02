package main;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        
        //key
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize; // 23 tiles from left
        gp.obj[0].worldY = 7 * gp.tileSize; // 7 tiles from top

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize; // 23 tiles from left
        gp.obj[1].worldY = 40 * gp.tileSize; // 7 tiles from top 

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 38 * gp.tileSize; 
        gp.obj[2].worldY = 8 * gp.tileSize; 

        // door
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize; 
        gp.obj[3].worldY = 11 * gp.tileSize; 

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize; 
        gp.obj[4].worldY = 28 * gp.tileSize; 

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize; 
        gp.obj[5].worldY = 22 * gp.tileSize; 

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize; 
        gp.obj[6].worldY = 7 * gp.tileSize; 

        // boots
        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize; 
        gp.obj[7].worldY = 42 * gp.tileSize; 
        
    }
}
