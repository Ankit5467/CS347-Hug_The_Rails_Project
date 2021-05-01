package htrPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFailed extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFailed frame = new LoginFailed();
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
	public LoginFailed() {

		/**
		 * Default block of code with JFrame
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 350, 200);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/**
		 * The text to denote wrong credentials.
		 */

		JLabel lblIncorrect = new JLabel("Incorrect credentials.");
		lblIncorrect.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIncorrect.setForeground(Color.YELLOW);
		lblIncorrect.setHorizontalAlignment(JLabel.CENTER);
		lblIncorrect.setBounds(63, 49, 212, 33);

		/**
		 * Code for the close button (style, size, functionality)
		 */

		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(123, 92, 85, 21);

		contentPane.add(lblIncorrect);
		contentPane.add(btnClose);
	}
}
