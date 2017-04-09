import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Platform extends JFrame {

	private static String[] sortedGuesses = new String[480];
	private static int[][][] guessVals = new int[10][12][12];
	private Guess actuall;
	private Player[] players;
	private Player actPlayer;
	private Deck deck;
	private int numOfPlayers = 6,musicVol;
	private static int[][] possibleGuesses = { { 2 }, { 3 }, { 3, 2 }, { 4 }, { 5 }, { 5, 3 }, { 6 }, { 7 }, { 7, 5 },
			{ 8 } };
	private Sounds sound;
	private String currentCards;
	private boolean sH = true;

	private JPanel panel1, panel11, panel12, panel2, panel21, panel22, panelChoose,set;
	private JTextArea console, urHand;
	private JComboBox<String> firstGuess, firstTypes, secGuess, secTypes;
	private JLabel urGuess, curGuess, urCards, baseG, secondG, selGuess, warning, curGuess2, curPlayer, curPlayer2;
	private JButton guess, openCards, cardsOnTable, nextRound, cardsOfPlayers, showHand,mus,settings,home,mainPage;

	Settings sett;
	
	public Platform(Sounds sounds,Player[] plyers,int musVol,Settings st) {
		sett = st;
		musicVol = musVol;
		arrange();
		this.players = plyers;
		actPlayer = players[0];
		this.sound = sounds;

		setLayout(new FlowLayout());

		JLabel back = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		back.setLayout(new FlowLayout());
		JPanel panel = new JPanel(new FlowLayout());
		panel.setSize(new Dimension(1430, 1049));
		panel.setOpaque(false);
		add(back);
		back.add(panel);

		setTitle("Liar's Poker");

		String[] types = { "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King",
				"Ace" };
		String[] firstG = { "2", "3", "4", "5", "6", "7", "8" };
		String[] types2 = { "", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen",
				"King", "Ace" };
		String[] secG = { "", "2", "3", "5" };

		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel11 = new JPanel(new FlowLayout());
		panel12 = new JPanel(new FlowLayout());
		panel21 = new JPanel(new FlowLayout());
		panel22 = new JPanel(new FlowLayout());
		panelChoose = new JPanel(new GridLayout(2, 3));

		panel1.setOpaque(false);
		panel2.setOpaque(false);

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
		tmp.setPreferredSize(new Dimension(600, 44));
		tmp.add(set, BorderLayout.LINE_END);
		set.setPreferredSize(new Dimension(132, 44));
		set.add(home,BorderLayout.LINE_END);
		set.add(mus,BorderLayout.CENTER);
		set.add(settings,BorderLayout.LINE_START);
		
		curPlayer = new JLabel("Current Player:");
		curPlayer.setPreferredSize(new Dimension(250, 50));
		curPlayer.setForeground(new Color(200, 21, 100));
		curPlayer.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));

		curPlayer2 = new JLabel(actPlayer.getName());
		curPlayer2.setPreferredSize(new Dimension(250, 50));
		curPlayer2.setForeground(new Color(200, 21, 100));
		curPlayer2.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));

		selGuess = new JLabel();
		selGuess.setForeground(new Color(236, 204, 216));
		selGuess.setFont(new Font("Monotype Corsiva", 0, 35));

		urHand = new JTextArea(5, 15);
		urHand.setEditable(false);
		urHand.setFont(new Font("Verdana", Font.BOLD, 15));
		JScrollPane sPane2 = new JScrollPane(urHand);
		sPane2.setVisible(false);

		showHand = new JButton("Show");
		showHand.setFont(new Font("Monotype Corsiva", Font.BOLD, 19));
		showHand.setPreferredSize(new Dimension(80, 30));
		showHand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				if (sH) {
					sPane2.setVisible(true);
					sH = false;
					showHand.setText("Hide");
				} else {
					sPane2.setVisible(false);
					sH = true;
					showHand.setText("Show");
				}
			}
		});

		firstGuess = new JComboBox<>(firstG);
		firstGuess.setPreferredSize(new Dimension(70, 50));
		firstGuess.setFont(new Font("Verdana", 0, 20));
		firstGuess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSelGuess();
			}
		});

		firstTypes = new JComboBox<>(types);
		firstTypes.setPreferredSize(new Dimension(200, 50));
		firstTypes.setFont(new Font("Verdana", 0, 20));
		firstTypes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setSelGuess();
			}
		});

		secGuess = new JComboBox<>(secG);
		secGuess.setPreferredSize(new Dimension(70, 50));
		secGuess.setFont(new Font("Verdana", 0, 20));
		secGuess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setSelGuess();
			}
		});

		secTypes = new JComboBox<>(types2);
		secTypes.setPreferredSize(new Dimension(200, 50));
		secTypes.setFont(new Font("Verdana", 0, 20));
		secTypes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setSelGuess();
			}
		});

		setSelGuess();

		console = new JTextArea(25, 55);
		console.setFont(new Font("Calibri", 0, 18));
		console.setEditable(false);
		JScrollPane sPane = new JScrollPane(console);

		urGuess = new JLabel("Your Guess is: ");
		urGuess.setPreferredSize(new Dimension(300, 200));
		urGuess.setForeground(new Color(220, 220, 220));
		urGuess.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		
		mainPage = new JButton("Main Page");
		mainPage.setPreferredSize(new Dimension(250, 50));
		mainPage.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		mainPage.setVisible(false);
		mainPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage m = new MainPage(sett,sound,musicVol);
				dispose();
				
			}
		});

		cardsOnTable = new JButton("Cards On Table");
		cardsOnTable.setPreferredSize(new Dimension(250, 50));
		cardsOnTable.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		cardsOnTable.setVisible(false);
		cardsOnTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sound.click();
				
				JFrame allCards = new JFrame();
				JTextArea cards = new JTextArea(10, 30);
				cards.setEditable(false);
				cards.setFont(new Font("Verdana", Font.BOLD, 15));
				JScrollPane sPane3 = new JScrollPane(cards);
				allCards.setTitle("Cards On Table");
				allCards.setSize(300, 500);
				allCards.add(sPane3);
				allCards.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				cards.setText(currentCards);


				allCards.setVisible(true);
			}
		});

		nextRound = new JButton("Next Round");
		nextRound.setPreferredSize(new Dimension(250, 50));
		nextRound.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		nextRound.setVisible(false);
		nextRound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();

				sPane2.setVisible(false);
				sH = true;
				showHand.setText("Show");

				actPlayer.unsetMarks();
				actuall = null;
				curGuess2.setText("");
				setDeck();
				nextRound.setVisible(false);
				cardsOnTable.setVisible(false);
				guess.setEnabled(true);
				openCards.setEnabled(true);
				aiGuess();
			}
		});

		cardsOfPlayers = new JButton("Cards Of Players");
		cardsOfPlayers.setPreferredSize(new Dimension(250, 50));
		cardsOfPlayers.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));
		cardsOfPlayers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sound.click();

				JFrame playerssCards = new JFrame();
				JTextArea cards = new JTextArea(10, players.length);
				cards.setEditable(false);
				cards.setFont(new Font("Verdana", Font.BOLD, 15));
				JScrollPane sPane3 = new JScrollPane(cards);
				playerssCards.setTitle("Cards Of Players");
				playerssCards.setSize(300, 300);
				playerssCards.add(sPane3);
				playerssCards.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				for (Player p : players) {
					if(!p.getlost()){
						cards.setText(cards.getText() + p.getName() + ": " + p.getNumberOfCards() + " Cards\n");
					}
				}

				playerssCards.setVisible(true);

			}
		});

		guess = new JButton("Guess!");
		guess.setPreferredSize(new Dimension(600, 100));
		guess.setFont(new Font("Monotype Corsiva", Font.BOLD, 60));
		guess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sound.click();
				
				resetWarning();
				int x = -1;
				int[] guesses;
				if (secGuess.getSelectedIndex() != 0) {
					int[] guessestmp = { Integer.parseInt((String) (firstGuess.getSelectedItem())),
							Integer.parseInt((String) (secGuess.getSelectedItem())) };
					guesses = guessestmp;
				} else {
					int[] guessestmp = { Integer.parseInt((String) (firstGuess.getSelectedItem())) };
					guesses = guessestmp;
				}
				for (int i = 0; i < 10; i++) {
					if (guesses.length == possibleGuesses[i].length) {
						for (int j = 0; j < guesses.length; j++) {
							if (guesses[j] != possibleGuesses[i][j]) {
								break;
							}
							x = i;
						}
					}
				}

				int y = firstTypes.getSelectedIndex();
				int z = secTypes.getSelectedIndex() - 1;

				if (z == -1) {
					z = 0;
				}

				if (x == -1) {
					setWarning2();
					return;
				}

				if (guessVals[x][y][z] == 0) {
					setWarning1();
					return;
				}

				if ((secGuess.getSelectedIndex() != 0 && secTypes.getSelectedIndex() == 0)) {
					setWarning1();
					return;
				}

				if ((secGuess.getSelectedIndex() == 0 && secTypes.getSelectedIndex() != 0)) {
					setWarning1();
					return;
				}

				if (actuall == null) {

					sPane2.setVisible(false);
					sH = true;
					showHand.setText("Show");
					
					actuall = new Guess(actPlayer);
					actuall.setheight(x + " " + y + " " + z);
					curGuess2.setText(actuall.toString());
					actPlayer = actPlayer.getNextPlayer();
					curPlayer2.setText(actPlayer.getName());
					urHand.setText(actPlayer.toString());
				} else {

					Guess nActG = new Guess(actPlayer);
					nActG.setheight(x + " " + y + " " + z);
					if (nActG.getheight() <= actuall.getheight()) {
						setWarning1();
						return;
					}

					sPane2.setVisible(false);
					sH = true;
					showHand.setText("Show");

					actuall = nActG;
					curGuess2.setText(actuall.toString());
					actPlayer = actPlayer.getNextPlayer();
					curPlayer2.setText(actPlayer.getName());
					urHand.setText(actPlayer.toString());
				}
				writeConsole(actPlayer.getPrevPlayer().getName() + " guessed " + actuall.toString());
				writeConsoleTurn();
				aiGuess();
			}
		});

		openCards = new JButton("Open Cards!");
		openCards.setPreferredSize(new Dimension(600, 100));
		openCards.setFont(new Font("Monotype Corsiva", Font.BOLD, 60));
		openCards.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (actuall == null) {
					setWarning2();
				} else {
					guess.setEnabled(false);
					openCards.setEnabled(false);
					Player x = actPlayer;

					// JFrame allCards = new JFrame();
					// JTextArea cards = new JTextArea(10, 30);
					// cards.setEditable(false);
					// cards.setFont(new Font("Verdana", Font.BOLD, 15));
					// JScrollPane sPane3 = new JScrollPane(cards);
					// allCards.setTitle("Cards On Table");
					// allCards.setSize(300, 500);
					// allCards.add(sPane3);
					// allCards.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					// cards.setText(x.CardsOnTable().toString());

					writeConsole(x.getName() + " opened the cards...");
					actPlayer = openCards();
					writeConsole(actPlayer.message);
					curPlayer2.setText(actPlayer.getName());

					if(winCheck()){
						writeConsole(actPlayer.getName()+" has won the game!!!");
						warning.setText(actPlayer.getName()+" has won the game!!!");
						cardsOnTable.setVisible(true);
						mainPage.setVisible(true);
						return;
					}
					nextRound.setVisible(true);
					cardsOnTable.setVisible(true);

					// ActionListener action=new ActionListener() {
					//
					// @Override
					// public void actionPerformed(ActionEvent e) {
					// sound.click();
					// allCards.setVisible(true);
					// }
					// };
					// cardsOnTable.addActionListener(action);
					// nextRound.addActionListener(new ActionListener() {
					//
					// @Override
					// public void actionPerformed(ActionEvent e) {
					// sound.click();
					// actPlayer.unsetMarks();
					// actuall = null;
					// curGuess2.setText("");
					// setDeck();
					// nextRound.setVisible(false);
					// cardsOnTable.setVisible(false);
					// guess.setEnabled(true);
					// openCards.setEnabled(true);
					// cardsOnTable.removeActionListener(action);
					// System.out.println(actuall);
					// aiGuess();
					// }
					// });

				}
			}
		});

		curGuess = new JLabel("Current Guess: ");
		curGuess.setPreferredSize(new Dimension(250, 50));
		curGuess.setForeground(new Color(200, 21, 100));
		curGuess.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));

		curGuess2 = new JLabel("");
		curGuess2.setPreferredSize(new Dimension(250, 50));
		curGuess2.setForeground(new Color(200, 21, 100));
		curGuess2.setFont(new Font("Monotype Corsiva", Font.ITALIC, 30));

		urCards = new JLabel("<html>Your Cards: </html>");
		urCards.setPreferredSize(new Dimension(200, 300));
		urCards.setForeground(new Color(220, 220, 220));
		urCards.setFont(new Font("Monotype Corsiva", Font.BOLD, 30));

		baseG = new JLabel("Base Guess: ");
		baseG.setFont(new Font("Monotype Corsiva", 0, 40));
		baseG.setForeground(new Color(220, 220, 220));

		secondG = new JLabel("Secondary Guess: ");
		secondG.setFont(new Font("Monotype Corsiva", 0, 30));
		secondG.setForeground(new Color(220, 220, 220));

		warning = new JLabel("",SwingConstants.CENTER);
		warning.setFont(new Font("Calibri", Font.BOLD, 60));
		warning.setForeground(new Color(242, 0, 6));
		warning.setPreferredSize(new Dimension(700, 100));

		panel1.add(panel11, BorderLayout.PAGE_START);
		panel1.add(panel12, BorderLayout.PAGE_END);
		panel2.add(panel21, BorderLayout.PAGE_START);
		panel2.add(panel22, BorderLayout.PAGE_END);
		panel.add(panel1, BorderLayout.LINE_START);
		panel.add(panel2, BorderLayout.LINE_END);

		panel1.setPreferredSize(new Dimension(800, 1000));
		panel2.setPreferredSize(new Dimension(600, 1000));
		panel11.setPreferredSize(new Dimension(800, 600));
		panel12.setPreferredSize(new Dimension(800, 400));
		panel21.setPreferredSize(new Dimension(600, 160));
		panel22.setPreferredSize(new Dimension(600, 840));
		panelChoose.setPreferredSize(new Dimension(600, 100));

		setSize(1440, 1070);

		panel11.setBackground(new Color(255, 0, 128));
		panel12.setBackground(new Color(51, 204, 132));
		panel21.setBackground(new Color(255, 231, 97));
		panel22.setBackground(new Color(60, 92, 151));
		panelChoose.setBackground(new Color(60, 92, 151));

		panel12.add(mainPage);
		panel12.add(nextRound);
		panel12.add(cardsOfPlayers);
		panel12.add(cardsOnTable);
		panel12.add(warning);
		panel21.add(tmp);
		panel21.add(curPlayer);
		panel21.add(curPlayer2);
		panel21.add(curGuess);
		panel21.add(curGuess2);
		panel11.add(sPane, BorderLayout.CENTER);
		panel22.add(urCards);
		panel22.add(showHand);
		panel22.add(sPane2);
		panelChoose.add(baseG);
		panelChoose.add(firstGuess);
		panelChoose.add(firstTypes);
		panelChoose.add(secondG);
		panelChoose.add(secGuess);
		panelChoose.add(secTypes);
		panel22.add(panelChoose);
		panel22.add(urGuess);
		panel22.add(selGuess);
		panel22.add(guess);
		panel22.add(openCards);
		
		setDeck();
		setCards();
		aiGuess();
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setPlayers() {
		Player human = new Player("Arin", 2);
		AI ai1 = new AI("Deniz", 2);
		AI ai2 = new AI("Ege", 2);
		AI ai3 = new AI("Hasan", 2);
		AI ai4 = new AI("Kaan", 2);
		AI ai5 = new AI("Mert", 2);
		AI ai6 = new AI("Safa", 2);
		human.setNextPlayer(ai1);
		ai1.setNextPlayer(ai2);
		ai2.setNextPlayer(ai3);
		ai3.setNextPlayer(ai4);
		ai4.setNextPlayer(ai5);
		ai5.setNextPlayer(ai6);
		ai6.setNextPlayer(human);
		Player[] tmp = { human, ai1, ai2, ai3, ai4, ai5, ai6 };
		players = tmp;
	}

	private void setSelGuess() {
		if (secGuess.getSelectedIndex() == 0 || secTypes.getSelectedIndex() == 0) {
			selGuess.setText("<html>" + firstGuess.getSelectedItem().toString() + " times "
					+ firstTypes.getSelectedItem().toString());
		} else {
			selGuess.setText("<html>" + firstGuess.getSelectedItem().toString() + " times "
					+ firstTypes.getSelectedItem().toString() + "<br/>" + secGuess.getSelectedItem().toString()
					+ " times " + secTypes.getSelectedItem().toString() + "</html>");
		}
	}

	private void setWarning1() {
		warning.setText("Illegal Guess !!!");
	}

	private void setWarning2() {
		warning.setText("No Available Guess !!!");
	}

	private void resetWarning() {
		warning.setText("");
	}

	private static void arrange() {
		int i = 1;
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 12; y++) {
				for (int z = 0; z < 12; z++) {
					if (x == 2 || x == 5 || x == 8) {
						if (y != z) {
							sortedGuesses[i - 1] = x + " " + y + " " + z;
							i++;
						}
					} else {
						if (z == 0) {
							sortedGuesses[i - 1] = x + " " + y + " " + z;
							i++;
						}
					}
				}
			}
		}
		for (int a = 1; a <= 480; a++) {
			String[] temp = sortedGuesses[a - 1].split(" ");
			guessVals[Integer.parseInt(temp[0])][Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = a;
		}
	}

	public void setDeck() {
		Runnable run = new Runnable() {
			
			@Override
			public void run() {
				deck = new Deck(players);
				deck.initialize();
				writeConsole(deck.shuffle());
				writeConsole(deck.deal());
				writeConsole("Ready to play");
				writeConsoleTurn();
				urHand.setText(actPlayer.toString());
				sound.shuffle();				
			}
		};
		Thread t = new Thread(run);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCards() {
		urHand.setText(actPlayer.toString());
	}

	private Player openCards() {
		sound.liar();
		currentCards = actPlayer.CardsOnTable().toString();
		Player winner = actPlayer.openCards(actuall);
		writeConsole(winner.getName() + " won the bet.");
		Player ret = winner.getMarkedPlayer();
		return ret;
	}

	private void aiGuess() {
		guess.setEnabled(false);
		openCards.setEnabled(false);
		while (actPlayer instanceof AI) {
			if (actuall != null) {
				Guess tmp = ((AI) actPlayer).guess(actuall);
				if (tmp != null) {
					actuall = tmp;
					curGuess2.setText(actuall.toString());
					writeConsole(actPlayer.getName() + " guessed " + actuall.toString());
					actPlayer = actPlayer.getNextPlayer();
					curPlayer2.setText(actPlayer.getName());
					setCards();
					writeConsole("It's " + actPlayer.getName() + "'s turn");
				} else {
					AI x = (AI) actPlayer;

					// JFrame allCards = new JFrame();
					// JTextArea cards = new JTextArea(10, 30);
					// cards.setEditable(false);
					// cards.setFont(new Font("Verdana", Font.BOLD, 15));
					// JScrollPane sPane3 = new JScrollPane(cards);
					// allCards.setTitle("Cards On Table");
					// allCards.setSize(300, 500);
					// allCards.add(sPane3);
					// allCards.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					// cards.setText(x.CardsOnTable().toString());

					writeConsole(x.getName() + " opened the cards...");
					actPlayer = openCards();
					writeConsole(actPlayer.message);
					curPlayer2.setText(actPlayer.getName());

					urHand.setText("");
					nextRound.setVisible(true);
					cardsOnTable.setVisible(true);
					
					if(winCheck()){
						writeConsole(actPlayer.getName()+" has won the game!!!");
						warning.setText(actPlayer.getName()+" has won the game!!!");
						nextRound.setVisible(false);
						mainPage.setVisible(true);
						return;
					}

					// ActionListener action=new ActionListener() {
					//
					// @Override
					// public void actionPerformed(ActionEvent e) {
					// sound.click();
					// allCards.setVisible(true);
					//
					// }
					// };
					// cardsOnTable.addActionListener(action);
					// nextRound.addActionListener(new ActionListener() {
					//
					// @Override
					// public void actionPerformed(ActionEvent e) {
					// sound.click();
					// actPlayer.unsetMarks();
					// actuall = null;
					// curGuess2.setText("");
					// setDeck();
					// nextRound.setVisible(false);
					// cardsOnTable.setVisible(false);
					// guess.setEnabled(true);
					// openCards.setEnabled(true);
					// cardsOnTable.removeActionListener(action);
					// aiGuess();
					// }
					// });
					return;
				}
			} else {
				Guess tmp = ((AI) actPlayer).firstGuess();
				if (tmp != null) {
					actuall = tmp;
					curGuess2.setText(actuall.toString());
					writeConsole(actPlayer.getName() + " guessed " + actuall.toString());
					actPlayer = actPlayer.getNextPlayer();
					curPlayer2.setText(actPlayer.getName());
					setCards();
					writeConsole("It's " + actPlayer.getName() + "'s turn");
				} else {
					Player x = actPlayer;

					JFrame allCards = new JFrame();
					JTextArea cards = new JTextArea(10, 30);
					cards.setEditable(false);
					cards.setFont(new Font("Verdana", Font.BOLD, 15));
					JScrollPane sPane3 = new JScrollPane(cards);
					allCards.setTitle("Cards On Table");
					allCards.setSize(300, 500);
					allCards.add(sPane3);
					allCards.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					cards.setText(x.CardsOnTable().toString());

					writeConsole(x.getName() + " opened the cards...");
					actPlayer = openCards();
					curPlayer2.setText(actPlayer.getName());

					nextRound.setVisible(true);
					cardsOnTable.setVisible(true);

					ActionListener action = new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							allCards.setVisible(true);

						}
					};
					cardsOnTable.addActionListener(action);
					nextRound.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							actPlayer.unsetMarks();
							actuall = null;
							curGuess2.setText("");
							setDeck();
							nextRound.setVisible(false);
							cardsOnTable.setVisible(false);
							guess.setEnabled(true);
							openCards.setEnabled(true);
							cardsOnTable.removeActionListener(action);
							aiGuess();
						}
					});
					break;
				}
			}
		}
		guess.setEnabled(true);
		openCards.setEnabled(true);
	}

	public boolean winCheck(){
		int a = 0;
		for (Player p : players){
			if(!p.getlost()){
				a++;
			}
		}
		if(a == 1){
			return true;
		}
		return false;
	}
	
	public void writeConsoleTurn() {
		writeConsole("It's " + actPlayer.getName() + "'s turn");
	}

	private void writeConsole(String s) {
		console.setText(console.getText() + s + "\n");
	}
}
