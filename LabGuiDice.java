package guiDice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
/**
 * Creates a GUI with a button that changes a image of a die face randomly.
 * 
 * @author Eric R
 *
 */
public class LabGuiDice extends JFrame {
	private JPanel contentPane;
	private static ImageIcon[] faces = {
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-1.png")),
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-2.png")),
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-3.png")),
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-4.png")),
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-5.png")),
			new ImageIcon(LabGuiDice.class.getResource("/resorces/die-6.png"))
	};
	private static Random random = new Random();
	private int index = -1;
	
	
	

	/**
	 * Launch the GUI.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGuiDice frame = new LabGuiDice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame.
	 * 
	 * Creates the frame and adds the different elements to the frame.
	 * uses other methods to create the new elements.
	 */
	public LabGuiDice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblFace = newLblFace();
		JButton btnNewButton = newBtnRoll(lblFace);
		
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		contentPane.add(lblFace, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * Creates a new JLabel instance that represents the current die face.
	 * 
	 * Creates a new JLabel that represents the current die face.
	 * The initial die face is randomly chosen using the randomInt method.
	 * Sets all the initial settings for the JLabel
	 * 
	 * @see guiDice.LabGuiDice#randomInt(int, int)
	 * @return new JLabel instance that represents the current die face.
	 */
	private JLabel newLblFace() {
		JLabel lblFace = new JLabel("");
		lblFace.setHorizontalAlignment(SwingConstants.CENTER);
		lblFace.setIcon(faces[randomInt(faces.length,index)]);
		return lblFace;
	}
	
	/**
	 * Creates a button that updates the provided labels icon.
	 * 
	 * Creates a button that is able to change the provided labels icon randomly to simulate rolling a die.
	 * uses randomInt method for random die rolling.
	 * Sets all the initial settings for the JButton.
	 * 
	 * @see guiDice.LabGuiDice#randomInt(int, int)
	 * @param lblFace
	 * @return a new JButton instance that updates the provided labels icon.
	 */
	private JButton newBtnRoll(JLabel lblFace) {
		JButton btnRoll = new JButton("Roll 'Em");
		btnRoll.setFont(new Font("Lucida Console", Font.PLAIN, 40));
		btnRoll.setForeground(new Color(255, 69, 0));
		btnRoll.setBackground(new Color(0, 100, 0));
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = randomInt(faces.length, index);
				lblFace.setIcon(faces[index]);
			}
		});
		return btnRoll;
	}
	
	/**
	 * Generates a random number.
	 * 
	 * Generates a random number using the java util random generator.
	 * Insures that the returned random number is different then the current number.
	 * 
	 * @see java.util.Random
	 * @param bound of the random number
	 * @param currentNumber current number to not select in the bound
	 * @return random number that is different then currentNumber.
	 */
	private int randomInt(int bound, int currentNumber) {
		int num = random.nextInt(bound);
		while (num == currentNumber) {
			num = random.nextInt(bound);
		}
		return num;
		
	}

}
