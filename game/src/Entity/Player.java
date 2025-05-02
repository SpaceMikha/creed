package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX; // X coordinate of the player on the screen
    public final int screenY; // Y coordinate of the player on the screen

    public int hasKey = 0; // Number of keys the player has

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Center the player on the screen

        solidArea = new Rectangle(); // Collision area for the player
        
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x; // Default X position of the collision area
        solidAreaDefaultY = solidArea.y; // Default Y position of the collision area
        
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 23; // Initial position of the player in the game world
        worldY = gp.tileSize * 21; // Initial position of the player in the game world;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";

            } else if (keyH.downPressed == true) {
                direction = "down";

            } else if (keyH.leftPressed == true) {
                direction = "left";

            } else if (keyH.rightPressed == true) {
                direction = "right";

            }

            // Check for collision with tiles
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // Check for collision with objects
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // if collision is false, move the player
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // sprite counter for animation
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    public void pickUpObject(int i){

        if (i != 999) { // If an object is found
           
           String objectNmae = gp.obj[i].name;

           switch(objectNmae){
            
            case "Key":
                gp.playSE(1);
                hasKey++;
                gp.obj[i] = null; // Remove the object from the game
                gp.ui.showMessage("You got a key!"); // Show message to the player
                break;

            case "Door":
                if (hasKey > 0) {
                    gp.playSE(3);
                    gp.obj[i] = null; // Remove the object from the game
                    hasKey--;
                    gp.ui.showMessage("Door unlocked!"); 
                } else {
                    gp.ui.showMessage("You need a key to open this door!");
                }
                break;

            case "Boots":
                gp.playSE(2);
                speed += 2; // Increase the player's speed
                gp.obj[i] = null; // Remove the object from the game
                gp.ui.showMessage("You got a pair of boots!"); // Show message to the playe
                break;
            
            case "Chest":
                gp.ui.gameFinished = true; // Set the game as finished
                gp.stopMusic();
                gp.playSE(4);
                break;
           }

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // Draw the solid area for debugging purposes
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height); 
    }
}
