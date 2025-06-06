package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorlRow = 50;
  

    // FPS
    int FPS = 60;

    // SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    //sound
    Sound music = new Sound();
    Sound se = new Sound();


    // Game state
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;


    //entity and object
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //display up to 10 objects on the screen (0-9)

    // Game state
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject(); // set up objects

        playMusic(0); // play background music
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // 0.016666... seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            // update information such as character position
            update();

            // draw the screen with the updated information
            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        //TILE
        tileM.draw(g2);

        //OBJECTS
        for (int i = 0; i < obj.length; i++) {

            if (obj[i] != null) {
                obj[i].draw(g2, this); // pass the GamePanel instance to the draw method
            }

        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("draw time: " + passed, 10, 400);
        }

        g2.dispose();

    }

    // Method to play sound
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    // Method to stop sound
    public void stopMusic() {
        music.stop();
    }

    // Method to play sound effect
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
