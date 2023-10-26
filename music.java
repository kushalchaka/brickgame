package game;

import java.io.*;
import javax.sound.sampled.*;

public class Music extends Thread
{
	File file;
	AudioInputStream audioStream;
	Clip clip;
	
	@Override
	public void run()
	{
		try 
		{
			file = new File("src/images/atari.wav");
			audioStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} 
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
		{
			e.printStackTrace();
		} 
	}
}

