package game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.util.*;

public class Paddle 
{
	private int xPos, yPos;
	private int xSpeed = 10;
	private int velocity = 0;
	private Random rand = new Random();
	private BufferedImage paddle;
	public boolean cheat = false;
	
	Paddle()
	{
		loadImage();
	}

	private void loadImage() 
	{
		try 
		{
			paddle = ImageIO.read(new File("src/images/paddle.png"));
			xPos = rand.nextInt(BrickPanel.WIDTH - paddle.getWidth());
			yPos = BrickPanel.HEIGHT - paddle.getHeight();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			setXDirection(-xSpeed);
			move();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(xSpeed);
			move();
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			cheat = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			cheat = false;
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT|| e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			setXDirection(0);
			move();
		}
	}
	
	public void setXDirection(int xDirection)
	{
		velocity = xDirection;
	}
	
	public void move() 
	{
		xPos += velocity;
		if (xPos <= 0)
		{
			xPos = 0;
		}
		if (xPos > BrickPanel.WIDTH - paddle.getWidth())
		{
			xPos = BrickPanel.WIDTH - paddle.getWidth();
		}	
	}
	public void setPos(int x)
	{
		if (cheat)
		{
			xPos = x - paddle.getWidth()/3;
		}
		
	}
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(paddle, xPos, yPos, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(xPos, yPos, paddle.getWidth(), paddle.getHeight());
	}
	
}
