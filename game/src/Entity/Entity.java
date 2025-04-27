package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldX, worldY; // Position of the entity in the game world
    public int speed; // Speed of the entity

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Images for the entity's animations
    public String direction; // Direction the entity is facing

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Current sprite number for animation

    public Rectangle solidArea;  // Rectangle for collision detection
    public boolean collisionOn = false; // Flag for collision detection
}
