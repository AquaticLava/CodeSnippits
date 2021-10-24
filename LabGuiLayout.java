package guiLayout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class LabGuiLayout extends JFrame {

	private JPanel contentPane;
	private int currentBlue = 1;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGuiLayout frame = new LabGuiLayout();
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
	 * Creates the frame and adds content to the frame by calling other methods.
	 */
	public LabGuiLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 300);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(20, 20));
		contentPane.setPreferredSize(new Dimension(11, 10));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//creates the title label
		JLabel lblTitle = newTitleLabel("Demo Layout");
		
		//creates the panel for main content, then adds the content by calling lblMainBlock
		JPanel mainPanel = newGridPanel(new GridLayout(0, 4, 10, 10));
		newlblMainBlock(mainPanel,"1");
		newlblMainBlock(mainPanel,"2");
		newlblMainBlock(mainPanel,"3");
		newlblMainBlock(mainPanel,"4");
		//sets the second block to blue
		mainPanel.getComponent(1).setBackground(Color.BLUE);
		
		//creates the panel for controls then adds the buttons.
		JPanel controlPanel = newGridPanel(new GridLayout(0,1,5,5));
		controlPanel.setBorder(new EmptyBorder(5,5,75,15));
		controlPanel.setPreferredSize(new Dimension(75, 22));
		newButton(controlPanel, "<==",true, mainPanel);
		newButton(controlPanel, "==>",false, mainPanel);
		newButton(controlPanel, "",true, mainPanel);
		//hides the third button that is there to help with getting button size correct.
		//I feel there is a easy way i don't know about for doing sizing...
		controlPanel.getComponent(2).setVisible(false);
		
		//adds panels and title label to the contentPane
		contentPane.add(lblTitle, BorderLayout.NORTH);
		contentPane.add(controlPanel, BorderLayout.WEST);
		contentPane.add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * 
	 * Creates a JPanel with the specified GridLayout.
	 * 
	 * @param gridlayout
	 * @return JPanel with the specified GridLayout
	 */
	private JPanel newGridPanel(GridLayout gridlayout) {
		JPanel panel = new JPanel();
		panel.setLayout(gridlayout);
		return panel;
	}
	
	/**
	 * Creates a new JLabel with the text specified.
	 * 
	 * Creates a new JLabel with the text specified as well as setting font and positioning.
	 * 
	 * @param titleText text to display.
	 * @return a JLabel with the text specified.
	 */
	private JLabel newTitleLabel(String titleText) {
		JLabel lblNewLabel = new JLabel(titleText);
		lblNewLabel.setBorder(new EmptyBorder(5, 0, 15, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		return lblNewLabel;
		
	}
	
	/**
	 * 
	 * Creates a button with specified text that is able to change colors of the contents in the variable targetPanel.
	 * Requires a direction that the button moves the color, as well as a panel to add the button to.
	 * 
	 * The color moves from one content object to the adjacent content object,
	 * left or right is determined by the direction boolean. Edges loop.
	 * Sets positioning and sizing.
	 * 
	 * @param JPanel Panel to add the button to.
	 * @param text text to display on the button.
	 * @param direction Direction that the button changes the color in. True is left.
	 * @param targetPanel JPanel containing content objects to change the background colors of.
	 */
	private void newButton(JPanel panel, String text, boolean direction, JPanel targetPanel) {
		JButton btnNewButton = new JButton(text);
		btnNewButton.setBorder(new EmptyBorder(10, 0, 10, 0));
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.setPreferredSize(new Dimension(55, 15));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setMargin(new Insets(0,0,0,0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component[] components = targetPanel.getComponents();
				components[currentBlue].setBackground(Color.yellow);
				if((currentBlue == 0 && direction)|| (currentBlue == components.length-1 && !direction)) {
					if(direction) {
						currentBlue = components.length-1;
					}
					else {
						currentBlue = 0;
					}
				}
				else {
					if(direction) {
						currentBlue -= 1;
					}
					else {
						currentBlue += 1;
					}
				}
				components[currentBlue].setBackground(Color.BLUE);
			}
		});
		panel.add(btnNewButton);
	}
	
	/**
	 * Creates a yellow JLabel with specified number displayed in the center.
	 * Adds the created JLabel to the specified panel.
	 * 
	 * @param panel JPannel to add the JLabel to.
	 * @param number Number of the label as a string.
	 */
	private void newlblMainBlock(JPanel panel, String number) {
		JLabel lblMainBlock = new JLabel(number);
		lblMainBlock.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblMainBlock.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMainBlock.setBackground(Color.YELLOW);
		lblMainBlock.setOpaque(true);
		lblMainBlock.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMainBlock);
	}

}
