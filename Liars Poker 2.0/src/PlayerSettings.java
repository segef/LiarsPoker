import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class PlayerSettings extends JFrame {

	Integer[] nums = { 3, 4, 5, 6, 7, 8, 9, 10 };
	String[] plyrs = { "Player", "Computer" };
	String[] ais = { "Medium" };
	String[] possNames = { "Rueger", "Rueggeberg", "Schwarzburger", "Bismark", "Feiten", "Schultz", "Tadken", "Langrock", "Schwarzkopf", "Terzi" };
	JComboBox<Integer> numOfPlayers;
	JLabel numberOfPlayers,music,soundEffects;
	JButton startGame,mus,settings,home;
	JPanel panel, panel1, panel3, set;
	JPanel[] panels;
	JComboBox<String>[] playerSorts, aiSorts;
	JTextArea[] names;
	Sounds sound;
	private int musicVol;
	Settings sett;

	public PlayerSettings(Sounds s, int musicV, Settings s2) {
		sett = s2;
		musicVol = musicV;
		sound = s;
		setLayout(new FlowLayout());
		setTitle("Settings");
		setSize(1150, 730);

		JLabel back = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		back.setLayout(new FlowLayout());

		playerArrange();
		
		panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		panel.setPreferredSize(new Dimension(1120, 680));

		mus = new JButton();
		mus.setPreferredSize(new Dimension(44, 44));
		try {
			Image lvl2;
			if(musicVol==2){
				lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound2.bmp"));
			}else if(musicVol==3){
				lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound3.bmp"));
			}else if(musicVol==0){
				lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\mute.bmp"));
			}else{
				lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound1.bmp"));
			}
			mus.setIcon(new ImageIcon(lvl2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(musicVol == 2){
					try {
						Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound3.bmp"));
						mus.setIcon(new ImageIcon(lvl2));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.volume3();
					musicVol = 3;
				}else if(musicVol == 3){
					try {
						Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\mute.bmp"));
						mus.setIcon(new ImageIcon(lvl2));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.stop();
					musicVol = 0;
				}else if(musicVol == 0){
					try {
						Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound1.bmp"));
						mus.setIcon(new ImageIcon(lvl2));
					} catch (Exception e) {
						e.printStackTrace();
					}
					sound.start();
					sound.volume1();
					musicVol = 1;
				}else{
					try {
						Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\sound2.bmp"));
						mus.setIcon(new ImageIcon(lvl2));
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
			Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\settings.bmp"));
			settings.setIcon(new ImageIcon(lvl2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		settings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sett.setVisible(true);
			}
		});
		
		home = new JButton();
		home.setPreferredSize(new Dimension(44, 44));
		try {
			Image lvl2 = ImageIO.read(new File("C:\\java\\projects\\Liars Poker 2.0\\home.bmp"));
			home.setIcon(new ImageIcon(lvl2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage m = new MainPage(sett,sound,musicVol);
				dispose();
			}
		});
		
		JPanel tmp = new JPanel(new BorderLayout());
		tmp.setOpaque(false);
		set = new JPanel(new BorderLayout());
		set.setOpaque(false);
		tmp.setPreferredSize(new Dimension(1100, 44));
		tmp.add(set, BorderLayout.LINE_END);
		set.setPreferredSize(new Dimension(132, 44));
		set.add(home,BorderLayout.LINE_END);
		set.add(mus,BorderLayout.CENTER);
		set.add(settings,BorderLayout.LINE_START);
		
		panel1 = new JPanel(new GridLayout(11, 1, 2, 2));
		panel1.setPreferredSize(new Dimension(1100, 400));
		panel1.setBackground(new Color(221, 167, 237));
		panel1.setOpaque(false);

		panel3 = new JPanel(new BorderLayout());
		panel3.setPreferredSize(new Dimension(1100, 200));
		panel3.setBackground(new Color(181, 172, 253));
		panel3.setOpaque(false);
		
		panel.add(panel1);
		panel.add(panel3);
		panel.add(tmp);

		JPanel p1 = new JPanel(new FlowLayout());
		p1.setOpaque(false);

		numberOfPlayers = new JLabel("Number Of Players:");
		numberOfPlayers.setFont(new Font("Verdana", Font.BOLD, 18));
		numberOfPlayers.setForeground(new Color(228, 206, 99));
		numberOfPlayers.setPreferredSize(new Dimension(200, 30));
		p1.add(numberOfPlayers);

		numOfPlayers = new JComboBox<>(nums);
		numOfPlayers.setPreferredSize(new Dimension(50, 30));
		numOfPlayers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < (int) (numOfPlayers.getSelectedItem()); i++) {
					panels[i].setVisible(true);
				}
				for (int i =  (int) (numOfPlayers.getSelectedItem()); i<10 ; i++){
					panels[i].setVisible(false);
				}
			}
		});

		p1.add(numOfPlayers);
		panel1.add(p1);

		panels = new JPanel[10];
		playerSorts = new JComboBox[10];
		aiSorts = new JComboBox[10];
		names = new JTextArea[10];
		arrange();

		startGame = new JButton("Start Game");
		startGame.setPreferredSize(new Dimension(500, 50));
		startGame.setFont(new Font("Verdana", Font.BOLD, 50));
		startGame.setBackground(new Color(171, 244, 136));
		startGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sound.click();
				Player[] players = new Player[(int)(numOfPlayers.getSelectedItem())];
				for(int i = 0;i<players.length;i++){
					if(playerSorts[i].getSelectedIndex()==0){
						players[i] = new Player(names[i].getText(), 2);
					}else{
						players[i] = new AI(names[i].getText(), 2);
					}
				}
				for(int i = 0; i<players.length;i++){
					players[i].setNextPlayer(players[(i+1)%players.length]);
				}
				Platform p = new Platform(sound,players,musicVol,sett);
//				p.setDeck();
//				p.setCards();
//				p.writeConsoleTurn();
				dispose();
			}
		});
		
		panel3.add(startGame,BorderLayout.CENTER);
		back.add(panel);
		add(back);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void arrange() {
		for (int i = 0; i < 10; i++) {
			int x = i;
			playerSorts[i] = new JComboBox<String>(plyrs);
			playerSorts[i].setFont(new Font("Verdana", 0, 18));
			playerSorts[i].setPreferredSize(new Dimension(150, 30));
			playerSorts[x].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (playerSorts[x].getSelectedItem().equals("Computer")) {
						aiSorts[x].setVisible(true);
						
						names[x].setEditable(false);
						names[x].setText(possNames[x]);
					} else {
						aiSorts[x].setVisible(false);
						
						names[x].setEditable(true);
						names[x].setText("Player" + x);
					}
				}
			});

			panels[i] = new JPanel(new FlowLayout());
			panels[i].setOpaque(false);

			panels[i].add(playerSorts[i]);

			aiSorts[x] = new JComboBox<>(ais);
			aiSorts[x].setFont(new Font("Verdana", 0, 18));
			aiSorts[x].setPreferredSize(new Dimension(100, 30));
			aiSorts[x].setVisible(false);
			panels[x].add(aiSorts[x]);

			names[x] = new JTextArea(1, 13);
			names[x].setEditable(true);
			names[x].setFont(new Font("Verdana", 0, 18));
			names[x].setPreferredSize(new Dimension(100, 30));
			names[x].setText("Player" + x);

			panels[x].add(names[x]);

			panel1.add(panels[i]);
		}
		for (JPanel p : panels) {
			p.setVisible(false);
		}
		for (int i = 0; i < (int) (numOfPlayers.getSelectedItem()); i++) {
			panels[i].setVisible(true);
		}
	}
	private void playerArrange(){
		Random ran = new Random();
		for(int i = 0; i<possNames.length ; i++){
			int rand = ran.nextInt(possNames.length);
			String a = possNames[i];
			possNames[i] = possNames[rand];
			possNames[rand] = a;			
		}
	}
}