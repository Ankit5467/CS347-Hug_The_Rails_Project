package htrPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.util.Date;

public class LoginGUI extends JFrame {

	/**
	 * Data fields
	 */
	private JPanel contentPane;
	private JTextField userField;
	private	 JPasswordField passField;
	Date date = java.util.Calendar.getInstance().getTime();
	LCS lcs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		/**
		 * Create new intance of LCS
		 * Update date
		 * Write to log that session has started
		 */
		
		lcs = new LCS();
		date = java.util.Calendar.getInstance().getTime();
		lcs.writeToLog("" + date + "-- LCS session started.\n");
		lcs.writeToLog(lcs.toString()); // ask about this toString
		
		/**
		 * Default block of code with JFrame
		 */
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(325, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/**
		 * Code that adds a train
		 * to our login window
		 */
		
		File img = new File("train.png");
		JLabel lblImage = new JLabel();
		lblImage.setBounds(310, 60, 256, 169);
		lblImage.setText("");
		lblImage.setIcon(new ImageIcon(img.getAbsolutePath()));
		
		/**
		 * Sets up the username 
		 * and password labels
		 */
		
		JLabel lblUser = new JLabel("USERNAME:");
		lblUser.setForeground(Color.GREEN);
		lblUser.setBounds(226, 269, 88, 33);
		JLabel lblPass = new JLabel("PASSWORD:");
		lblPass.setForeground(Color.GREEN);
		lblPass.setBounds(226, 312, 88, 33);
		userField = new JTextField(20);
		userField.setBounds(335, 276, 231, 19);
		passField = new JPasswordField(20);
		passField.setBounds(335, 319, 231, 19);
		
		/**
		 * Code for the log in button
		 * (style, size, functionality, etc.)
		 */
		
		JButton logIn = new JButton("LOG IN");
		logIn.setBounds(373, 359, 150, 40);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lcs.checkCredentials(userField.getText(), String.valueOf(passField.getPassword())) 
						&& (userField.getText().length() != 0 && String.valueOf(passField.getPassword()).length() != 0) 
						&& (userField.getText().equals("operator"))) {
					date = java.util.Calendar.getInstance().getTime();
					lcs.writeToLog("" + date + "-- User \'" + userField.getText() + "\' logged in.\n");
					OperatorGUI op = new OperatorGUI();
					op.setVisible(true);
					dispose();
				} else if (lcs.checkCredentials(userField.getText(), String.valueOf(passField.getPassword())) 
						&& (userField.getText().length() != 0 && String.valueOf(passField.getPassword()).length() != 0) 
						&& (userField.getText().equals("admin"))) {
					date = java.util.Calendar.getInstance().getTime();
					lcs.writeToLog("" + date + "-- User \'" + userField.getText() + "\' logged in.\n");
					AdminGUI ad = new AdminGUI();
					ad.setVisible(true);
					dispose();
				} else {
					date = java.util.Calendar.getInstance().getTime();
					lcs.writeToLog("" + date + "-- Failed login attempt.\n");
					LoginFailed fail = new LoginFailed();
					fail.setVisible(true);
				}
			}
		});
		
		/**
		 * Block of code that adds 
		 * the components onto the GUI 
		 */
		
		contentPane.setLayout(null);
		contentPane.add(lblPass);
		contentPane.add(lblUser);
		contentPane.add(passField);
		contentPane.add(userField);
		contentPane.add(logIn);
		contentPane.add(lblImage);
	}
}
