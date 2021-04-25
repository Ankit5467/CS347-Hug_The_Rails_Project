package htrPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Scrollbar;
import java.util.Date;
import java.util.Timer;

public class TestGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	LCS lcs;
	Date date = java.util.Calendar.getInstance().getTime();
	Timer loop = new Timer();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI frame = new TestGUI();
					frame.setTitle("Test GUI");
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
	public TestGUI() {
		lcs = new LCS();
		lcs.setIsLoggedIn(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		JTextArea display = new JTextArea();
		display.setEditable(false);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		display.setFont(new Font("Monospaced", Font.BOLD, 16));
		scroll.setViewportView(display);
		
//		if(userInput.equals("operator")) {
//			display.setText(lcs.readFromLog2());
//		} else {
//			display.setText("Welcome, admin.");
//		}
		display.setText("Welcome, Operator.");
		//display.setText(lcs.readFromLog2());
		
		JTextPane speedPane = new JTextPane();
		speedPane.setEditable(false);
		speedPane.setBackground(Color.GREEN);
		speedPane.setFont(new Font("Tahoma", Font.BOLD, 50));
		speedPane.setText(String.valueOf(lcs.getSpeed()));
		
		JLabel lblSpeed = new JLabel("SPEED");
		lblSpeed.setForeground(Color.YELLOW);
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpeed.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblRPM = new JLabel("RPM");
		lblRPM.setForeground(Color.YELLOW);
		lblRPM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRPM.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane rpmPane = new JTextPane();
		rpmPane.setEditable(false);
		rpmPane.setBackground(Color.GREEN);
		rpmPane.setFont(new Font("Tahoma", Font.BOLD, 50));
		rpmPane.setText(String.valueOf(lcs.getRPM()));
		JButton btnRain = new JButton("RAIN");
		btnRain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.rainReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "RAIN" + "\'.\n");
			}
		});
		
		JButton btnVisibility = new JButton("VISIBILITY");
		btnVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.visibilityReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "VISIBILITY" + "\'.\n");
			}
		});
		
		JButton btnWind = new JButton("WIND");
		btnWind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.windReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "WIND" + "\'.\n");
			}
		});
		
		JButton btnSnow = new JButton("SNOW");
		btnSnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.snowReport() + "\n");
				lcs.setSnowRate(0.25);
				display.setBackground(Color.WHITE);
				display.append(lcs.snowReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "SNOW" + "\'.\n");
			}
		});
		
		JButton btnObstr = new JButton("OBSTRUCTION");
		btnObstr.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnObstr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.ProcessObject() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "OBSTRUCTION" + "\'.\n");
			}
		});
		
		JButton btnSlip = new JButton("SLIPPAGE");
		btnSlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.detectSlippage() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "SLIPPAGE" + "\'.\n");
			}
		});
		
		JButton btnGate = new JButton("GATE");
		btnGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.gateStatus() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "GATE" + "\'.\n");
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
			
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(rpmPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(662, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblRPM, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(648, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(180)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSlip, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnObstr, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSnow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRain, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnVisibility, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnWind, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(btnGate, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(speedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scroll)))
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSpeed, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
					.addGap(679))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblSpeed, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(speedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(lblRPM, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rpmPane, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
						.addComponent(scroll))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnObstr, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRain, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnVisibility, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSlip, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSnow, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnWind, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(64)
							.addComponent(btnGate, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
