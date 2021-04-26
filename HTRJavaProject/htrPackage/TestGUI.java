package htrPackage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.UIManager;

public class TestGUI extends JFrame {

	private JPanel contentPane;
	LCS lcs;
	Date date = java.util.Calendar.getInstance().getTime();
	int totalMilliseconds;

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
		Timer loop = new Timer();
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(285, 42, 576, 245);
		JTextArea display = new JTextArea();
		display.setForeground(Color.GREEN);
		scroll.setViewportView(display);
		display.setEditable(false);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		display.setBackground(Color.BLACK);
		display.setFont(new Font("Monospaced", Font.BOLD, 16));
		display.setText("Welcome, Operator.");
		
		JTextPane speedPane = new JTextPane();
		speedPane.setBounds(57, 83, 152, 67);
		speedPane.setEditable(false);
		speedPane.setBackground(Color.BLACK);
		speedPane.setForeground(Color.GREEN);
		speedPane.setFont(new Font("Tahoma", Font.PLAIN, 50));
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
		rpmPane.setBackground(Color.BLACK);
		rpmPane.setForeground(Color.GREEN);
		rpmPane.setFont(new Font("Tahoma", Font.PLAIN, 50));
		rpmPane.setText(String.valueOf(lcs.getRPM()));
		
		
		TimerTask task = new TimerTask() {
			public void run() {
				totalMilliseconds += 15000;
				lcs.updateValuesSensors();
				
				speedPane.setText(String.valueOf(lcs.getSpeed()));
				lcs.writeToLog("" + date + "-- Speed data has been updated \'" + "\'.\n");
				rpmPane.setText(String.valueOf(lcs.getRPM()));
				lcs.writeToLog("" + date + "-- RPM data has been updated \'" + "\'.\n");
				
				display.setText("");
				
				if(lcs.getRainRate() >= 0.3) {
					display.append(lcs.rainReport() + "\n");
				}
				lcs.writeToLog("" + date + "-- Rain data has been updated \'" + "\'.\n");
				
				if(lcs.getWindSpeed() >= 50.0) {
					display.append(lcs.windReport() + "\n");
				}
				lcs.writeToLog("" + date + "-- Wind data has been updated \'" + "\'.\n");
				
				if(lcs.getSnowRate() >= 0.3) {
					display.append(lcs.snowReport() + "\n");
				}
				lcs.writeToLog("" + date + "-- Snow data has been updated \'" + "\'.\n");
				
				if(lcs.getVisibility() < 2.0) {
					display.append(lcs.visibilityReport() + "\n");
				}
				lcs.writeToLog("" + date + "-- Visibility data has been updated \'" + "\'.\n");
				
				if(lcs.isObstruction()) {
					display.append(lcs.ProcessObject() + "\n");
				}
				lcs.writeToLog("" + date + "-- Obstruction data has been updated \'" + "\'.\n");
				
				display.append(lcs.detectSlippage() + "\n");
				lcs.writeToLog("" + date + "-- Wheels' status has been updated \'" + "\'.\n");
				
				display.append(lcs.gateStatus() + "\n");
				lcs.writeToLog("" + date + "-- Gate status has been updated \'" + "\'.\n");
				
				lcs.writeToLog(lcs.toString());
					
			}
		};
		
		if(totalMilliseconds == 420000) {
			loop.cancel();
		} else {
			loop.scheduleAtFixedRate(task, 15000, 15000);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnRain = new JButton("RAIN");
		btnRain.setForeground(Color.BLACK);
		btnRain.setBounds(443, 311, 118, 71);
		btnRain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.rainReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "RAIN" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnVisibility = new JButton("VISIBILITY");
		btnVisibility.setBounds(599, 311, 118, 71);
		btnVisibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.visibilityReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "VISIBILITY" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnWind = new JButton("WIND");
		btnWind.setBounds(599, 409, 118, 71);
		btnWind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.windReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "WIND" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnSnow = new JButton("SNOW");
		btnSnow.setForeground(Color.BLACK);
		btnSnow.setBounds(443, 409, 118, 71);
		btnSnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.snowReport() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "SNOW" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnObstr = new JButton("OBSTRUCTION");
		btnObstr.setForeground(Color.BLACK);
		btnObstr.setBackground(UIManager.getColor("Button.background"));
		btnObstr.setBounds(285, 311, 118, 71);
		btnObstr.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnObstr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.ProcessObject() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "OBSTRUCTION" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnSlip = new JButton("SLIPPAGE");
		btnSlip.setForeground(Color.BLACK);
		btnSlip.setBounds(285, 411, 118, 71);
		btnSlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.detectSlippage() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "SLIPPAGE" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnGate = new JButton("GATE");
		btnGate.setBounds(743, 313, 118, 71);
		btnGate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(display.getText().equals("Welcome, Operator."))
					display.setText("");
				display.append(lcs.gateStatus() + "\n");
				lcs.writeToLog("" + date + "-- User entered the following command \'" + "GATE" + "\'.\n");
				lcs.writeToLog(lcs.toString());
			}
		});
		
		JButton btnLog = new JButton("LOG");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText("");
				display.setText(lcs.readFromLog2());
				lcs.writeToLog(lcs.toString());
			}
		});
		btnLog.setBounds(743, 411, 118, 71);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(Color.RED);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(87, 311, 118, 71);
		
		JButton btnLogOff = new JButton("LOG OFF");
		btnLogOff.setForeground(Color.RED);
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lcs.writeToLog("" + date + "-- User \'" + "operator" + "\' logged off successfully.\n");
				lcs.writeToLog(lcs.toString());
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
}
