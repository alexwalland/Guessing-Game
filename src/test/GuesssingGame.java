package test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GuesssingGame extends JFrame implements ActionListener {

	public JPanel contentPane;
	int nGuesses = 0;
	int rNumber = new Random().nextInt(100) + 1;
	JButton Bguess = new JButton("Guess");
	JLabel GLabel = new JLabel(nGuesses + " guesses");
	JLabel rules = new JLabel("Guess a number between 1 and 100");
	JButton resetBtn = new JButton("Reset");
	JSpinner guess = new JSpinner();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuesssingGame frame = new GuesssingGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GuesssingGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Bguess.setBounds(91, 33, 85, 21);
		contentPane.add(Bguess);
		Bguess.addActionListener(this);
		GLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		GLabel.setBounds(83, 64, 93, 27);
		contentPane.add(GLabel);
		
		rules.setBounds(20, 10, 246, 13);
		contentPane.add(rules);
		resetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
			}
		});
		resetBtn.setBounds(181, 33, 85, 21);
		resetBtn.setVisible(false);
		contentPane.add(resetBtn);
		guess.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		guess.setBounds(30, 32, 51, 20);
		contentPane.add(guess);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		rules.setText("");
		Integer iGuess = (Integer) guess.getValue();
		if (iGuess < 101)
		{
			if (iGuess == rNumber) 
			{
				Bguess.setVisible(false);
				guess.setEnabled(false);
				resetBtn.setVisible(true);
				rules.setText("Congrats you guessed the number");
			} else if (iGuess < rNumber) {
					rules.setText("Higher");
			} else if (iGuess > rNumber) {
				rules.setText("Lower");
			 
			}
			nGuesses ++;
		}
		String guessword = (nGuesses == 1) ? " Guess" : " Guesses";
		GLabel.setText(nGuesses + guessword);
	}
	
	public void reset()
	{
		nGuesses = 0;
		rules.setText("Guess a number between 1 and 100");
		resetBtn.setEnabled(false);
		Bguess.setEnabled(true);
		rNumber = new Random().nextInt(100) + 1;
		guess.setEnabled(true);
		guess.setValue(1);
		nGuesses = 0;
		GLabel.setText("0 Guesses");
	}
}
