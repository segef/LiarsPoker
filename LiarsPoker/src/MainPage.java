import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import sun.audio.*;

public class MainPage extends JFrame{
	private JButton howToPlay,funFacts,startGame,music,settings;
	private JLabel liarsPoker;
	private ImageIcon liars;
	private JPanel p1,p2,p3,p4,set;
	private Sounds sound;
	private int musicVol = 2;
	Settings sett;
	
	public MainPage(Settings sett1,Sounds sound2,int mV){
		
		setSize(new Dimension(750,750));
		setLayout(new FlowLayout());
		JLabel back = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		JPanel panel = new JPanel(new GridLayout(2,1));
		back.setLayout(new FlowLayout());
		panel.setOpaque(false);
		add(back);
		back.add(panel);
	
		sound = sound2;
		musicVol = mV;
		sett = sett1;
		
		p1 = new JPanel(new BorderLayout());
		p2 = new JPanel(new GridLayout(1, 3, 5, 5));
		p3 = new JPanel(new	BorderLayout());
		p4 = new JPanel(new GridLayout(2, 1, 5, 5));
		set = new JPanel(new BorderLayout());
		
		music = new JButton();
		music.setPreferredSize(new Dimension(44, 44));
		try {
			Image lvl2;
			if(musicVol==2){
				music.setIcon(new ImageIcon(getClass().getResource("sound2.jpg")));
			}else if(musicVol==3){
				music.setIcon(new ImageIcon(getClass().getResource("sound3.jpg")));
			}else if(musicVol==0){
				music.setIcon(new ImageIcon(getClass().getResource("mute.jpg")));
			}else{
				music.setIcon(new ImageIcon(getClass().getResource("sound1.jpg")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		music.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(musicVol == 2){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound3.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.volume3();
					musicVol = 3;
				}else if(musicVol == 3){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("mute.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.stop();
					musicVol = 0;
				}else if(musicVol == 0){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound1.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.start();
					sound.volume1();
					musicVol = 1;
				}else{
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound2.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.volume2();
					musicVol = 2;
				}
			}
		});
		
		settings = new JButton();
		settings.setPreferredSize(new Dimension(44, 44));
		try {
			settings.setIcon(new ImageIcon(getClass().getResource("settings.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		settings.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sett.setVisible(true);
			}
		});
		
		howToPlay = new JButton("How to play?");
		howToPlay.setPreferredSize(new Dimension(300, 50));
		howToPlay.setBackground(new Color(51, 204, 132));
		howToPlay.setFont(new Font("Verdana", Font.BOLD, 30));
		
		funFacts = new JButton("Did you know?");
		funFacts.setPreferredSize(new Dimension(300, 50));
		funFacts.setBackground(new Color(51, 204, 132));
		funFacts.setFont(new Font("Verdana", Font.BOLD, 30));

		startGame = new JButton("<html><center>Play<br/> Liar's Poker</center></html>");
		startGame.setBackground(new Color(51, 204, 132));
		startGame.setFont(new Font("Verdana", Font.BOLD, 30));
		
		liars = new ImageIcon(getClass().getResource("liars-poker.jpg"));
		liarsPoker = new JLabel(liars);
		
		p1.setSize(500, 50);
		p1.add(howToPlay,BorderLayout.LINE_START);
		p1.add(funFacts,BorderLayout.LINE_END);
		
		p2.setSize(500, 100);
		p2.add(new JLabel(""));
		p2.add(startGame);
		p2.add(new JLabel(""));
		
		p4.add(p1);
		p4.add(p2);
		
		JPanel tmp = new JPanel(new BorderLayout());
		tmp.setPreferredSize(new Dimension(200, 44));
		
		p3.setSize(500, 200);
		p3.add(liarsPoker,BorderLayout.CENTER);
		p3.add(tmp,BorderLayout.PAGE_END);
		
		tmp.add(set, BorderLayout.LINE_END);
		set.setPreferredSize(new Dimension(88, 44));
		set.add(music,BorderLayout.LINE_END);
		set.add(settings,BorderLayout.LINE_START);

		howToPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				HowToPlay h = new HowToPlay();
				
			}
		});
		funFacts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				JFrame fun = new JFrame("Did You Know?");
				fun.setLayout(new FlowLayout());
				fun.add(new JLabel("These are fun facts!"));
				fun.setSize(400, 800);
				fun.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fun.setVisible(true);
			}
		});
		
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				PlayerSettings ps = new PlayerSettings(sound,musicVol,sett);
				dispose();
			}
		});
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		set.setOpaque(false);
		tmp.setOpaque(false);
		
		panel.add(p4);
		panel.add(p3);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setTitle("Liar's Poker");
		setVisible(true);
	}
	
	public MainPage(){
		setSize(new Dimension(750,750));
		setLayout(new FlowLayout());
		JLabel back = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		JPanel panel = new JPanel(new GridLayout(2,1));
		back.setLayout(new FlowLayout());
		panel.setOpaque(false);
		add(back);
		back.add(panel);
	
		sound = new Sounds();
		sound.backgroundMusic();

		sett = new Settings(sound);
		
		p1 = new JPanel(new BorderLayout());
		p2 = new JPanel(new GridLayout(1, 3, 5, 5));
		p3 = new JPanel(new	BorderLayout());
		p4 = new JPanel(new GridLayout(2, 1, 5, 5));
		set = new JPanel(new BorderLayout());
		
		music = new JButton();
		music.setPreferredSize(new Dimension(44, 44));
		try {
			music.setIcon(new ImageIcon(getClass().getResource("sound2.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		music.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(musicVol == 2){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound3.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.volume3();
					musicVol = 3;
				}else if(musicVol == 3){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("mute.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.stop();
					musicVol = 0;
				}else if(musicVol == 0){
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound1.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.start();
					sound.volume1();
					musicVol = 1;
				}else{
					try {
						music.setIcon(new ImageIcon(getClass().getResource("sound2.jpg")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.volume2();
					musicVol = 2;
				}
			}
		});
		
		settings = new JButton();
		settings.setPreferredSize(new Dimension(44, 44));
		try {
			settings.setIcon(new ImageIcon(getClass().getResource("settings.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		settings.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sett.setVisible(true);
			}
		});
		
		howToPlay = new JButton("How to play?");
		howToPlay.setPreferredSize(new Dimension(300, 50));
		howToPlay.setBackground(new Color(51, 204, 132));
		howToPlay.setFont(new Font("Verdana", Font.BOLD, 30));
		
		funFacts = new JButton("Did you know?");
		funFacts.setPreferredSize(new Dimension(300, 50));
		funFacts.setBackground(new Color(51, 204, 132));
		funFacts.setFont(new Font("Verdana", Font.BOLD, 30));

		startGame = new JButton("<html><center>Play<br/> Liar's Poker</center></html>");
		startGame.setBackground(new Color(51, 204, 132));
		startGame.setFont(new Font("Verdana", Font.BOLD, 30));
		
		liars = new ImageIcon(getClass().getResource("liars-poker.jpg"));
		liarsPoker = new JLabel(liars);
		
		p1.setSize(500, 50);
		p1.add(howToPlay,BorderLayout.LINE_START);
		p1.add(funFacts,BorderLayout.LINE_END);
		
		p2.setSize(500, 100);
		p2.add(new JLabel(""));
		p2.add(startGame);
		p2.add(new JLabel(""));
		
		p4.add(p1);
		p4.add(p2);
		
		JPanel tmp = new JPanel(new BorderLayout());
		tmp.setPreferredSize(new Dimension(200, 44));
		
		p3.setSize(500, 200);
		p3.add(liarsPoker,BorderLayout.CENTER);
		p3.add(tmp,BorderLayout.PAGE_END);
		
		tmp.add(set, BorderLayout.LINE_END);
		set.setPreferredSize(new Dimension(88, 44));
		set.add(music,BorderLayout.LINE_END);
		set.add(settings,BorderLayout.LINE_START);

		howToPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				
				HowToPlay htp = new HowToPlay();
				
			}
		});
		funFacts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				JFrame fun = new JFrame("Did You Know?");
				fun.setLayout(new FlowLayout());
				fun.add(new JLabel("These are fun facts!"));
				fun.setSize(400, 800);
				fun.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fun.setVisible(true);
			}
		});
		
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				PlayerSettings ps = new PlayerSettings(sound,musicVol,sett);
				dispose();
			}
		});
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		set.setOpaque(false);
		tmp.setOpaque(false);
		
		panel.add(p4);
		panel.add(p3);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setTitle("Liar's Poker");
		setVisible(true);
		
	}
}