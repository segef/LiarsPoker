import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
	JButton liarVoice, clickSound, shuffleSound;
	JLabel liarVoiceLabel, clickSoundLabel, shuffleSoundLabel;
	boolean lV = true, cS = true, sS = true;
	Sounds sound;

	public Settings(Sounds s) {
		sound = s;
		setLayout(new FlowLayout());
		setTitle("Settings");
		setSize(520, 320);

		JLabel back = new JLabel(new ImageIcon(getClass().getResource("backMainPlatform.jpg")));
		back.setLayout(new FlowLayout());
		JPanel panel = new JPanel(new FlowLayout());
		panel.setPreferredSize(new Dimension(400, 300));
		panel.setOpaque(false);
		add(back);
		back.add(panel);

		liarVoiceLabel = new JLabel("ON", SwingConstants.CENTER);
		liarVoiceLabel.setForeground(new Color(23, 198, 32));
		liarVoiceLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		liarVoiceLabel.setPreferredSize(new Dimension(170, 80));

		liarVoice = new JButton("Liar Voice");
		liarVoice.setFont(new Font("Calibri", Font.BOLD, 30));
		liarVoice.setBackground(new Color(188, 237, 189));
		liarVoice.setPreferredSize(new Dimension(220, 80));
		liarVoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lV) {
					lV = false;
					liarVoiceLabel.setForeground(new Color(232, 23, 23));
					liarVoiceLabel.setText("OFF");
					sound.liarV = false;
				} else {
					lV = true;
					liarVoiceLabel.setForeground(new Color(23, 198, 32));
					liarVoiceLabel.setText("ON");
					sound.liarV = true;
				}

			}
		});

		clickSoundLabel = new JLabel("ON", SwingConstants.CENTER);
		clickSoundLabel.setForeground(new Color(23, 198, 32));
		clickSoundLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		clickSoundLabel.setPreferredSize(new Dimension(170, 80));

		clickSound = new JButton("Click Sound");
		clickSound.setFont(new Font("Calibri", Font.BOLD, 30));
		clickSound.setBackground(new Color(188, 237, 189));
		clickSound.setPreferredSize(new Dimension(220, 80));
		clickSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cS) {
					cS = false;
					clickSoundLabel.setForeground(new Color(232, 23, 23));
					clickSoundLabel.setText("OFF");
					sound.clickV = false;
				} else {
					cS = true;
					clickSoundLabel.setForeground(new Color(23, 198, 32));
					clickSoundLabel.setText("ON");
					sound.clickV = true;
				}

			}
		});

		shuffleSoundLabel = new JLabel("ON", SwingConstants.CENTER);
		shuffleSoundLabel.setForeground(new Color(23, 198, 32));
		shuffleSoundLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		shuffleSoundLabel.setPreferredSize(new Dimension(170, 80));

		shuffleSound = new JButton("Shuffle Sound");
		shuffleSound.setFont(new Font("Calibri", Font.BOLD, 30));
		shuffleSound.setBackground(new Color(188, 237, 189));
		shuffleSound.setPreferredSize(new Dimension(220, 80));
		shuffleSound.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sS) {
					sS = false;
					shuffleSoundLabel.setForeground(new Color(232, 23, 23));
					shuffleSoundLabel.setText("OFF");
					sound.shuff = false;
				} else {
					sS = true;
					shuffleSoundLabel.setForeground(new Color(23, 198, 32));
					shuffleSoundLabel.setText("ON");
					sound.shuff = true;
				}
			}
		});
		
		panel.add(liarVoice);
		panel.add(liarVoiceLabel);
		panel.add(clickSound);
		panel.add(clickSoundLabel);
		panel.add(shuffleSound);
		panel.add(shuffleSoundLabel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// Settings s = new Settings();
	}
}
