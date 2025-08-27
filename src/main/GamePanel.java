package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //TODO: Set screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // We must scale 16x16 tiles so they are visible on higher resolutions

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // Ratio 4x3
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;

    //TODO: Creates a key handler for keyboard input
    KeyHandler keyH = new KeyHandler();
    //TODO: Construct a game clock
    Thread gameThread;
    //TODO: Create player entity
    Player player = new Player(this, keyH);

    //TODO: Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // call run method below
    }

//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        // When we create a Thread object, the object automatically calls this run method.
//        while (gameThread != null) {
//
//            long currentTime = System.nanoTime();
//            System.out.println("Current time: " + currentTime);
//
//            // 1 UPDATE: update information such as character positions
//            update();
//
//            // 2 DRAW: draw the screen with the updated information
//            repaint(); // call paint Component method
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 =  (Graphics2D) g; // type cast to Graphics 2D

        player.draw(g2);

        g2.dispose();
    }
}
