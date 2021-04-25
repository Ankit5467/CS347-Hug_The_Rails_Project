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
import java.util.TimerTask;

public class TestGUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	LCS lcs;
	Date date = java.util.Calendar.getInstance().getTime();
	//Timer loop = new Timer();
	Timer loop;

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
		loop = new Timer();
		//TimerTask task = new TimerTask();
		lcs = new LCS();
		lcs.setIsLoggedIn(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(285, 42, 576, 245);
		JTextArea display = new JTextArea();
		scroll.setViewportView(display);
		display.setEditable(false);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		display.setFont(new Font("Monospaced", Font.BOLD, 16));
		display.setText("Welcome, Operator.");
		
		JTextPane speedPane = new JTextPane();
		speedPane.setBounds(72, 83, 128, 67);
		speedPane.setEditable(false);
		speedPane.setBackground(Color.GREEN);
		speedPane.setFont(new Font("Tahoma", Font.BOLD, 50));
		speedPane.setText(String.valueOf(lcs.getSpeed()));
		
		JLabel lblSpeed = new JLabel("SPEED");
		lblSpeed.setBounds(92, 39, 87, 34);
		lblSpeed.setForeground(Color.YELLOW);
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpeed.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblRPM = new JLabel("RPM");
		lblRPM.setBounds(72, 160, 128, 34);
		lblRPM.setForeground(Color.YELLOW);
		lblRPM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRPM.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane rpmPane = new JTextPane();
		rpmPane.setBounds(82, 193, 103, 61);
		rpmPane.setEditable(false);
		rpmPane.setBackground(Color.GREEN);
		rpmPane.setFont(new Font("Tahoma", Font.BOLD, 50));
		rpmPane.setText(String.valueOf(lcs.getRPM()));
		
		JButton btnRain = new JButton("RAIN");
		btnRain.setBounds(443, 311, 118, 71);
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
		btnVisibility.setBounds(599, 311, 118, 71);
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
		btnWind.setBounds(599, 409, 118, 71);
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
		btnSnow.setBounds(443, 409, 118, 71);
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
		btnObstr.setBounds(285, 311, 118, 71);
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
		btnSlip.setBounds(285, 411, 118, 71);
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
		btnGate.setBounds(743, 313, 118, 71);
		btnGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.setBackground(Color.WHITE);
				display.append(lcs.gateStatus() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "GATE" + "\'.\n");
			}
		});
		
		JButton btnLog = new JButton("LOG");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText("");
				display.setText(lcs.readFromLog2());
			}
		});
		btnLog.setBounds(743, 411, 118, 71);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnClear.setBounds(87, 311, 118, 71);
		
		JButton btnLogOff = new JButton("LOG OFF");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestGUI2 login = new TestGUI2();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogOff.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogOff.setBounds(87, 411, 118, 71);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setForeground(Color.BLACK);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(839, 0, 47, 36);
		
		contentPane.setLayout(null);
		contentPane.add(rpmPane);
		contentPane.add(lblRPM);
		contentPane.add(btnSlip);
		contentPane.add(btnObstr);
		contentPane.add(btnSnow);
		contentPane.add(btnRain);
		contentPane.add(btnVisibility);
		contentPane.add(btnWind);
		contentPane.add(btnGate);
		contentPane.add(speedPane);
		contentPane.add(scroll);
		contentPane.add(lblSpeed);
		contentPane.add(btnLog);
		contentPane.add(btnExit);
		contentPane.add(btnLogOff);
		contentPane.add(btnClear);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
