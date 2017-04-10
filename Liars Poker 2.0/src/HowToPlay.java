import javax.swing.*;
import java.awt.*;

public class HowToPlay extends JFrame{

	JPanel panel;
	JLabel rules,roundRules,roundRules1,roundRules2,roundRules3,guessRules,guessRules1,guessRules2,guessRules3,otherRules,otherRules1,otherRules2,otherRules3;
	JTextArea roundRulesT,guessRulesT,otherRulesT;
	
	public HowToPlay(){
		setLayout(new FlowLayout());
		setSize(1000, 800);
		setTitle("How To Play");
		
		JLabel back1 = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		panel = new JPanel(new FlowLayout());
		panel.setPreferredSize(new Dimension(950, 750));
		back1.setLayout(new FlowLayout());
		panel.setOpaque(false);
		add(back1);
		back1.add(panel);
		
		rules = new JLabel("RULES", SwingConstants.CENTER);
		rules.setPreferredSize(new Dimension(950, 40));
		rules.setFont(new Font("Monotype Corsiva", Font.BOLD, 50));
		rules.setForeground(new Color(228, 206, 99));
		
		roundRules = new JLabel("Round Rules:", SwingConstants.LEFT);
		roundRules.setPreferredSize(new Dimension(950, 40));
		roundRules.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		roundRules.setForeground(new Color(242, 232, 181));
		
		String t1 = "1-) First player makes a guess.\n2-) Second player has two choices: She/He can claim that the last guess made is false and open all cards of players or\n make a new guess which is higher than the last guess.\n3-) When a player opens the cards the round ends and the cards are counted and if the last guess is right then the last \nplayer loses the round, else the one who made the last guess lose the round.";
		roundRulesT = new JTextArea(6, 60);
		roundRulesT.setPreferredSize(new Dimension(950, 100));
		roundRulesT.setEditable(false);
		roundRulesT.setText(t1);
		roundRulesT.setOpaque(false);
		roundRulesT.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		roundRulesT.setForeground(new Color(231, 199, 224));
		
		guessRules = new JLabel("Guess Rules:", SwingConstants.LEFT);
		guessRules.setPreferredSize(new Dimension(950, 40));
		guessRules.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		guessRules.setForeground(new Color(242, 232, 181));
		
		String t2 = "1-) Every guess has a compulsory base part. Which claims that there are 2, 3, 4, 5, 6, 7 or 8 cards of a specific value in\n cards of all players.\n2-) If the base part of a guess is 3, 5 or 7, it has an optional secondary part which claims additional to the base part that\n there are respectively 2,3 or 5 cards of a specific value in cards of all players.\n3-) All guesses have a specific height. The guess with bigger base part is higher. If the base parts are equal, the one with\n secondary part is higher. If both have secondary part or both don't have a secondary part, the one with bigger value of\n base part is higher (heigth of values: Three<Four<Five<Six<Seven<Eight<Nine<Ten<Jack<Queen<King<Ace) If the\n values of both base parts are equal, the one with bigger value of secondary part is higher.";
		guessRulesT = new JTextArea(9,60);
		guessRulesT.setPreferredSize(new Dimension(950, 100));
		guessRulesT.setEditable(false);
		guessRulesT.setText(t2);
		guessRulesT.setOpaque(false);
		guessRulesT.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		guessRulesT.setForeground(new Color(231, 199, 224));
		
		
		otherRules = new JLabel("Other Rules", SwingConstants.LEFT);
		otherRules.setPreferredSize(new Dimension(950, 40));
		otherRules.setFont(new Font("Monotype Corsiva", Font.BOLD, 35));
		otherRules.setForeground(new Color(242, 232, 181));
		
		String t3 = "1-) Two's may represenent all other cards which means when the cards are counted, the two's may counted as any other\n card that is needed.\n2-) Two's are forbitten to use in guesses.\n3-) The player who lost the last round starts on the new round. If a player is eliminated on the last round, the player\n who won the last round against the loser starts on the new round.";
		otherRulesT = new JTextArea(6,60);
		otherRulesT.setPreferredSize(new Dimension(950, 100));
		otherRulesT.setEditable(false);
		otherRulesT.setText(t3);
		otherRulesT.setOpaque(false);
		otherRulesT.setFont(new Font("Monotype Corsiva", Font.BOLD, 20));
		otherRulesT.setForeground(new Color(231, 199, 224));
		
		otherRules1 = new JLabel("1-) Two's may represenent all other cards which means when the cards are counted, the two's may counted as any other card that is needed.", SwingConstants.CENTER);
		otherRules2 = new JLabel("2-) Two's are forbitten to use in guesses.", SwingConstants.CENTER);
		otherRules3 = new JLabel("3-) The player who lost the last round starts on the new round. If a player is eliminated on the last round, the player who won the last round against the loser starts on the new round.", SwingConstants.CENTER);
	
		panel.add(rules);
		panel.add(roundRules);
		panel.add(roundRulesT);
		panel.add(guessRules);
		panel.add(guessRulesT);
		panel.add(otherRules);
		panel.add(otherRulesT);
//		panel.add(otherRules2);
//		panel.add(otherRules3);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		HowToPlay h = new HowToPlay();
	}
	
}
