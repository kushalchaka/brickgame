package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class BrickPanel extends JPanel implements Runnable
{
	static final int WIDTH = 700, HEIGHT = 650;
	private final int numRows = 7, numCols = 7;
	private Image image;
	private Graphics graphics;
	private Brick [][] bricks;
	private Paddle paddle;
	private Ball ball;
	private Thread thread;
	public static int score = 0;
	public double speed = 2;
	final ImageIcon icon = new ImageIcon("src/images/win.gif");
    static final int BALLDIAMETER = 30;
    private Balls balls;
    private Random rand = new Random();
    private int rand1, rand2;
    private int temp = 0;
    private boolean shouldDrawRect = false;
    
	public BrickPanel() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setFocusable(true);
		Music music = new Music();
		music.start();
		addKeyListener(new MyKey());
		gameSetup();
		this.thread = new Thread(this);
		thread.start();
	}

	private void gameSetup() 
	{
		paddle = new Paddle();
		ball = new Ball();
		score = 0;
		speed = 2;
		bricks = new Brick[numCols][numRows];
		shouldDrawRect = false;
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				bricks[row][col] = new Brick(row, col);
			}
		}
		rand1 = rand.nextInt(7);
		rand2 = rand.nextInt(7);
		Brick b = new Brick(rand1, rand2);
		b.specialBrick(rand1, rand2);
		bricks[rand1][rand2] = b;
		repaint();
	}
	
	public void move()
	{
		paddle.move();
		ball.move();
		if(shouldDrawRect && temp >= 2)
		{
			balls.move();
		}
		if (paddle.cheat) {
        paddle.setPos(ball.getBounds().x);
    }
	}
	
	private void newBall() 
	{
		if (shouldDrawRect)
		{
			balls = new Balls(300, 250, BALLDIAMETER, BALLDIAMETER);
			temp++;
		}
	}
	
	public void checkCollision()
	{
		Rectangle ballRect = ball.getBounds();
		Rectangle pRect = paddle.getBounds();
		if (ballRect.y + ballRect.getHeight() > pRect.y && ballRect.getCenterX() > pRect.x && ballRect.getCenterX() < pRect.x + pRect.getWidth()) 
		{
			ball.ySpeed *= -1;
		}
		if (bricks[rand1][rand2].visible == false)
		{
			temp++;
		}
		if (shouldDrawRect && temp >= 2 && balls.intersects(pRect)) 
		{
			balls.yVelocity *= -1;
		}
		if (ballRect.y > HEIGHT - ballRect.getHeight())
		{
			int restart = JOptionPane.showConfirmDialog(null, "You have crashed. Play Again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (restart == 0)
			{
				ball.setPos(300, 250, 1);
				ball.move();
				gameSetup();
				repaint();
			}
			else
			{
				System.exit(0);
			}
		}
		if (shouldDrawRect && temp >= 2 && balls.y > HEIGHT - balls.getHeight())
		{
			int restart = JOptionPane.showConfirmDialog(null, "You have crashed. Play Again?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (restart == 0)
			{
				ball.setPos(300, 250, 1);
				ball.move();
				gameSetup();
				repaint();
			}
			else
			{
				System.exit(0);
			}
		}
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				if (bricks[row][col].visible)
				{
					Rectangle brickRect = bricks[row][col].getBounds();
					if (ballRect.y <= brickRect.getHeight() + brickRect.y && ballRect.getCenterX() > brickRect.x && ballRect.getCenterX()  < brickRect.width + brickRect.x)
					{
						bricks[row][col].visible = false;
						ball.ySpeed *= -1;
						ball.setSpeed(0.25,0.25);
						speed += 0.25;
						score++;
					}
				}
			}
		}
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				if (bricks[row][col].visible)
				{
					Rectangle brickRect = bricks[row][col].getBounds();
					if (shouldDrawRect && temp >= 2 && balls.y <= brickRect.getHeight() + brickRect.y && balls.getCenterX() > brickRect.x && balls.getCenterX()  < brickRect.width + brickRect.x)
					{
						bricks[row][col].visible = false;
						balls.yVelocity *= -1;
						balls.setSpeed(0.25,0.25);
						speed += 0.25;
						score++;
					}
				}
			}
		}
		if (score == 49)
		{
			int restart = JOptionPane.showConfirmDialog(null, "You Won!. Press OK to Play Again or Cancel to Exit", "Winner", JOptionPane.OK_CANCEL_OPTION, JOptionPane.NO_OPTION, icon);
			if (restart == 0)
			{
				ball.setPos(300, 250, 1);
				ball.move();
				gameSetup();
				repaint();
			}
			else
			{
				System.exit(0);
			}
		} 
		if (temp == 1)
		{
			shouldDrawRect = true;
			newBall();
		}
	}
	
	public void paint(Graphics g)
	{
		
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	public void draw(Graphics g)
	{
		for (int row = 0; row < numRows; row++)
		{
			for (int col = 0; col < numCols; col++)
			{
				if (bricks[row][col].visible)
				{
					bricks[row][col].draw(g);
				}
			}
		}
		paddle.draw(g);
		ball.draw(g);
		if (shouldDrawRect && temp >= 2)
		{
			balls.draw(g);
		}
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 35));
		g.drawString("Score: " + score,0,400);
		g.drawString("Speed: " + speed,0,440);
	}
	
	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		int fps = 60;
		double ns = 1000000000/fps;
		double delta = 0;
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public class MyKey extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			paddle.keyPressed(e);
		}
		public void keyReleased(KeyEvent e)
		{
			paddle.keyReleased(e);
		}
	}
}
