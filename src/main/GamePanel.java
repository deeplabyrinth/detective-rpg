package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //TODO: Set screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // We must scale 16x16 tiles so they are visible on higher resolutions

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // Ratio 4x3
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

}
