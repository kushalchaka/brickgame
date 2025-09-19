package game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
import java.awt.*;

public class Brick {
    private int xPos, yPos;
    private BufferedImage brick;
    private Random rand = new Random();
    public boolean visible = true;

    // fixed brick size
    public static final int BRICK_WIDTH = 100;
    public static final int BRICK_HEIGHT = 30;

    Brick(int row, int col) {
        createBrick(row, col);
    }

    private void createBrick(int row, int col) {
        try {
            int i = rand.nextInt(9); // pick a random brick image
            brick = ImageIO.read(new File("images/b" + i + ".png"));

            // position based on fixed width/height
            xPos = col * BRICK_WIDTH;
            yPos = row * BRICK_HEIGHT;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void specialBrick(int row, int col) {
        try {
            brick = ImageIO.read(new File("images/b9.png"));
            xPos = col * BRICK_WIDTH;
            yPos = row * BRICK_HEIGHT;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw scaled to the fixed width/height
        g2d.drawImage(brick, xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, BRICK_WIDTH, BRICK_HEIGHT);
    }
}
