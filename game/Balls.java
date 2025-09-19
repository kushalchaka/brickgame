package game;

import java.util.*;
import java.awt.*;

public class Balls extends Rectangle
{
	Random rand = new Random();
	int xVelocity;
	int yVelocity;
	int speed = 2;
	
	Balls(int x, int y, int width, int height)
	{
		super(x, y, width, height); 
		int x1 = rand.nextInt(2);
		if (x1 == 0)
		{
			setxDirection(-speed);
		}
		else 
		{
			setxDirection(speed);
		}
		
		int y1 = rand.nextInt(2);
		if (y1 == 0) 
		{
			setyDirection(-speed);
		}
		else 
		{
			setyDirection(speed);
		}
	}
	
	public void setyDirection(int i)
	{
		yVelocity = i;
	}

	public void setxDirection(int i) 
	{
		xVelocity = i;
	}
	
	public void move()
	{
		x += xVelocity;
		if (x < 0)
		{
			x = 0;
			xVelocity *= -1;
		}
		if (x > BrickPanel.WIDTH - width)
		{
			x = BrickPanel.WIDTH - width;
			xVelocity *= -1;
		}
		y += yVelocity;
		if (y < 0)
		{
			y = 0;
			yVelocity *= -1;
		}
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
	}

	public void setSpeed(double d, double e)
	{
		xVelocity += d;
		yVelocity += e;
	}
}
