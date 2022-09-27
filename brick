package game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
import java.awt.*;

public class Brick 
{
	private int xPos, yPos;
	private BufferedImage brick;
	private Random rand = new Random();
	public boolean visible = true;
	
	Brick(int row, int col)
	{
		createBrick(row, col);
	}

	private void createBrick(int row, int col) 
	{
		try 
		{
			int i = rand.nextInt(9);//10
			brick = ImageIO.read(new File("src/images/b" + i + ".png"));
			xPos = col * brick.getWidth();
			yPos = row * brick.getHeight();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void specialBrick(int row, int col)
	{
		try 
		{
			brick = ImageIO.read(new File("src/images/b9.png"));
			xPos = col * brick.getWidth();
			yPos = row * brick.getHeight();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(brick, xPos, yPos, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, brick.getWidth(), brick.getHeight());
	}
	
}
