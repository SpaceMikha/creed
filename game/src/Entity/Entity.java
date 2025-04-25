package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    
    public int x, y; // Position of the entity
    public int speed; // Speed of the entity

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; // Images for the entity's animations
    public String direction; // Direction the entity is facing

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Current sprite number for animation

}
