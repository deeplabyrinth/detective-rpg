package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //TODO: Create a window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Detective RPG");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Displays the panel in the window

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
