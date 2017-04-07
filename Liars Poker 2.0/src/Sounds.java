
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.*;
public class Sounds {

	Clip clip1;
	Clip clip2;
	public boolean shuff = true;
	public boolean clickV = true;
	public boolean liarV = true;
	public void backgroundMusic(){
		String liar = "C:\\java\\projects\\Liars Poker 2.0\\Liar.wav";
		try {
			clip1 = AudioSystem.getClip();
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(liar));
			clip1.open(input);
			FloatControl gainControl = 
				    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-5.0f);
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void volume1(){
		FloatControl gainControl = 
			    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f);
	}
	
	public void volume2(){
		FloatControl gainControl = 
			    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5.0f);
	}
	
	public void volume3(){
		FloatControl gainControl = 
			    (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(5.0f);
	}
	
	public void stop(){
		clip1.stop();
	}
	public void start(){
		clip1.start();
	}
	public void shuffle(){
		
		if(shuff){
			String shuffle = "C:\\java\\projects\\Liars Poker 2.0\\shuffle3.wav";
			try {
				clip2 = AudioSystem.getClip();
				AudioInputStream input = AudioSystem.getAudioInputStream(new File(shuffle));
				clip2.open(input);
				FloatControl gainControl = 
					    (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-20.0f);
				clip2.start();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public void click(){
		if(clickV){
			String click = "C:\\java\\projects\\Liars Poker 2.0\\click.wav";
			try {
				clip2 = AudioSystem.getClip();
				AudioInputStream input = AudioSystem.getAudioInputStream(new File(click));
				clip2.open(input);
				FloatControl gainControl = 
					    (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-30.0f);
				clip2.start();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void liar(){
		if(liarV){
			String open = "C:\\java\\projects\\Liars Poker 2.0\\open.wav";
			try {
				clip2 = AudioSystem.getClip();
				AudioInputStream input = AudioSystem.getAudioInputStream(new File(open));
				clip2.open(input);
				FloatControl gainControl = 
					    (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-30.0f);
				clip2.start();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
