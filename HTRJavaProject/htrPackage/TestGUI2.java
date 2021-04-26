package htrPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.util.Date;

public class TestGUI2 extends JFrame {

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
					TestGUI2 frame = new TestGUI2();
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
	public TestGUI2() {
		lcs = new LCS();
		date = java.util.Calendar.getInstance().getTime();
		lcs.writeToLog("" + date + "-- LCS session started.\n");
		lcs.writeToLog(lcs.toString());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUser = new JLabel("USERNAME:");
		lblUser.setBounds(154, 86, 88, 33);
		
		JLabel lblPass = new JLabel("PASSWORD:");
		lblPass.setBounds(154, 137, 88, 33);
		
		userField = new JTextField(20);
		userField.setBounds(246, 93, 231, 19);
		passField = new JPasswordField(20);
		passField.setBounds(246, 144, 231, 19);
		
		JButton newWindow = new JButton("ENTER");
		newWindow.setBounds(305, 206, 63, 21);
		newWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lcs.checkCredentials(userField.getText(), String.valueOf(passField.getPassword())) 
						&& (userField.getText().length() != 0 && String.valueOf(passField.getPassword()).length() != 0)) {
					date = java.util.Calendar.getInstance().getTime();
					lcs.writeToLog("" + date + "-- User \'" + userField.getText() + "\' logged in.\n");
					TestGUI main = new TestGUI();
					main.setVisible(true);
					dispose();
				} else {
					date = java.util.Calendar.getInstance().getTime();
					lcs.writeToLog("" + date + "-- Failed login attempt.\n");
				}
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(lblPass);
		contentPane.add(lblUser);
		contentPane.add(passField);
		contentPane.add(userField);
		contentPane.add(newWindow);
	}
}
