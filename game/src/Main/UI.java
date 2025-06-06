package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

public class UI {
    
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    public boolean gameFinished = false;

    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00"); // Format to 2 decimal places 

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key objKey = new OBJ_Key(gp);
        keyImage = objKey.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        
        if(gameFinished == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);


            String text;

            int textLenght;
            int x;
            int y;


            // Draw the "You found the treasure!" message
            text = "You found the treasure!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 - (gp.tileSize*3);
            
            g2.drawString(text, x, y);



            // Draw the "Your playtime was: " message
            text = "Your playtime was: " + dFormat.format(playTime) + " seconds";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 + (gp.tileSize*4);
            
            g2.drawString(text, x, y);


            // Draw the "Congratulations!" message
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLenght / 2;
            y = gp.screenHeight / 2 + (gp.tileSize*2);
            
            g2.drawString(text, x, y);

            
            gp.gameThread = null; // Stop the game thread

        } else {

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.hasKey, 74, 65);

            // Draw the play time
            playTime += (double) 1 / 60; // Increment play time by 1/60 seconds
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 11, 65);
    
            // If message is on, draw the message
            if (messageOn == true) {
    
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize*5);
    
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false; // Hide the message after 120 frames (2 seconds)
                }
            }

        }

       
    }
}
