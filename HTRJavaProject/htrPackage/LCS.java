package htrPackage;

import java.util.Date;
//import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
//import java.util.*;

public class LCS extends IOT {
	
		/* data fields */

	private boolean isConnected;	/* tracks if the LCS system is connected to wifi */
	private boolean isLoggedIn;		/* tracks if the user is logged into LCS */
	private boolean wantToCont;		/* tracks if the user wants to exit from LCS */
	
	private Path logFileName;
	private File log;				/* LCS log stores lots of information */

	String user = "";
	String pass = "";

	/* Use a HashMap to store Login information. */
	/* Add default login info for operator & administrator */
	private Map<String, String> login_info = new HashMap<String, String>();

		/* Constructor */

	/**
	 * Constructor for LCS object.
	 */

	protected LCS() {
		super();
		this.isConnected = false; /* By default, assume there is no wifi connection. */
		this.isLoggedIn = false;
		this.wantToCont = true;

		/* Initialize the default login credentials. */
		this.addLoginInfo("operator", "qwerty");
		this.addLoginInfo("admin", "password");

		/* Initialize the log for this session. */
		try {
			this.initializeLog();
		} catch (Exception e) {
			System.out.println("Error: Unable to initialize log.\n");
			e.printStackTrace();
		}
	}

		/* Regular methods: */

	/* Log-related methods */

	void initializeLog() throws Exception {
		String filename = "LCS_Log.txt";
		
		//create the log file:
		try {
			this.log = new File(filename);
			this.log.createNewFile();
		} catch (Exception e) {
			System.out.println("An error occurred.\n");
		}

		// set its filename/path and write a line to it
		this.logFileName = Path.of(filename);
		// Files.writeString(this.logFileName, "******************************************\n");
		try {
			Files.write(this.logFileName, 
				 "******************************************\n".getBytes(), StandardOpenOption.APPEND);
			System.out.println("Log has been initialized for this session.\n");
		} catch (IOException e) {
			System.out.println("Error: Unable to initialize log.\n");
			e.printStackTrace();
		}

	}

	void writeToLog(String str) {
		try {
			Files.write(this.logFileName, str.getBytes(),StandardOpenOption.APPEND);
			// System.out.println("Updated Log.\n");
		} catch (IOException e) {
			System.out.println("Error: Unable to write to log.\n");
			e.printStackTrace();
		}
		// Files.writeString(this.logFileName,"hello World!\n");
	}

	void readFromLog(boolean isOperator) {
		if (isOperator) {
			try {
				// File myObj = new File("filename.txt");
				Scanner myReader = new Scanner(this.log);
				while (myReader.hasNextLine()) {
				  String data = myReader.nextLine();
				  System.out.println(data);
				}
				System.out.println("*************** END OF LOG ***************");
				myReader.close();
			  } catch (FileNotFoundException e) {
				System.out.println("Error: Unable to read from the log.\n");
				e.printStackTrace();
			  }
		} else {
			System.out.println("Error: Only the operator can access the log.\n");
		}
		
	}

	/* Log in Credential Methods */

	/**
	 * 	If the given username and password are valid (at least 5 chars long), then
	 * 	add the (username,password) pair to the table of valid login credentials.
	 */
	void addLoginInfo(String username, String password) {
		if (username.length() < 5 || password.length() < 5) {
			System.out.println("Error: Failed to add new user. Username " 
						+ "& password must be at least 5 characters.");
			return;
		}
		this.getLoginInfo().put(username, password);

	}

	/**
	 * @returns the Map containing all usernames & passwords.
	 */
	Map<String, String> getLoginInfo() {
		return this.login_info;
	}

	/**
	 * @param username
	 * @param password
	 * @return true if the pair (username,password) is a valid login credential,
	 * 		false otherwise.
	 */
	protected boolean checkCredentials(String username, String password) {
		if(this.getLoginInfo().getOrDefault(username, "").equals(password)) {
			this.user = username;
			this.pass = password;
			return true;
		} else {
			return false;
		}
	}

	public String getPassword() {
		return this.pass;
	}

	public String getUsername() {
		return this.user;
	}
	
	void setIsLoggedIn(boolean connection) {
		this.isLoggedIn = connection;
	}

	/* Wifi Connection Methods */

	@SuppressWarnings("unused")
	private void setWifi(boolean b) {
		this.isConnected = b;
	}

	private boolean getWifi() {
		return this.isConnected;
	}

	// (long,lat)
	String displayLocation() {
		return "(" + this.getLatitude() + ", " + this.getLongitude() + ")";
	}

	// private String recommend(String field) {
	// 	// field = speed, weather, obstacle, gate
	// 	return "";
	// }

	private String getStatus() {
		// use stringbuilder

//		recommend(speed);
//		recommend(weather);
		// ...
		return "";
	}



	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\tLocation: " + this.displayLocation());
		str.append("\tGate Distance: " + roundTwoDecimals(this.getGateDistance()));
		str.append("\tGate Status: " + this.getGateStatus());
		str.append("\tObstruction Present?: " + this.isObstruction());
		str.append("\n\tRPM: " + this.getRPM());
		str.append("\t\tSpeed: " + roundTwoDecimals(this.getSpeed()));
		str.append("\t\tWind Speed: " + roundTwoDecimals(this.getWindSpeed()));
		str.append("\tRain Rate: " + roundTwoDecimals(this.getRainRate()));
		str.append("\n\tSnow Rate: " + roundTwoDecimals(this.getSnowRate()));
		str.append("\t\tVisibility: " + roundTwoDecimals(this.getVisibility()) + "\n");
		return str.toString();
	}

	public static void main(String[] args) {
		
		LCS myTrain = new LCS();
		// Sensors mySens = new Sensors();
		Scanner scan = new Scanner(System.in);
//		myTrain.testDoc();

		Date date=java.util.Calendar.getInstance().getTime();
		myTrain.writeToLog("" + date + "-- LCS session started.\n");
		//TODO: Write LCS.toString() to log
		// System.out.println(myTrain.toString());

		myTrain.writeToLog(myTrain.toString());

		String username = "";
		String password = "";

		while (!myTrain.isLoggedIn) {

			while (true) {
				System.out.println("Enter your username: ");
				username = scan.nextLine();
				System.out.println("Enter your password: ");
				password = scan.nextLine();
				date=java.util.Calendar.getInstance().getTime();
				if (myTrain.checkCredentials(username, password)) {
					myTrain.isLoggedIn =true;
					myTrain.writeToLog("" + date + "-- User \'" + username + "\' logged in.\n");
					break;
				} else {
					System.out.println("Incorrect credentials");
					myTrain.writeToLog("" + date + "-- Failed login attempt.\n");
				}
			}
			
			// boolean cont = true;
			while (myTrain.wantToCont && !myTrain.getWifi() && myTrain.isLoggedIn) {
				System.out.print("-------------------------------\nEnter a command: ");
				String command = scan.nextLine().toLowerCase();

				switch (command) { /* commands are case insensitive */
				case "":
					break;
				case "help":
					System.out.println(myTrain.helpMessage());
					break;
				case "log off": /* log out (but don't exit the program) */
					System.out.println("Logging off ...");
					myTrain.isLoggedIn = false;
					break;
				case "add user":
					System.out.print("Enter a username: ");
					String newUser = scan.nextLine();
					System.out.print("Enter a password: ");
					String newPass = scan.nextLine();
					myTrain.addLoginInfo(newUser, newPass);
					break;
				case "exit":
					myTrain.wantToCont = false;
					break;
				case "wifi":
					System.out.println("Connected to wifi: " + myTrain.getWifi());
					break;
				case "location":
					System.out.println("Location: " + myTrain.displayLocation());
					break;
				case "weather":
					System.out.println("Weather: " + myTrain.obtainWeather());
					break;
				case "speed":
					System.out.println("Speed: " + myTrain.getSpeed() + " mph.");
					break;
				case "rpm":
					System.out.println("Wheel rpm: " + myTrain.getRPM());
					break;
				case "set diameter":
					System.out.print("The wheel diameter is currently set to "
							+ myTrain.getWheelDiameter() + " inches. " 
							+ "Enter the updated wheel diameter in inches: ");
					double newDiameter;
					try {
						newDiameter = Double.parseDouble(scan.nextLine());
					} catch (Exception e) {
						System.out.println("Error: Invalid wheel diameter. Wheel diameter must be numerical.\n");
						break;
					}
					
					if (myTrain.setWheelDiameter(newDiameter) == -1) {
						System.out.println("Error: Invalid wheel diameter. Wheel Diameter must be at least 1 inch.\n");
					} else {
						System.out.println("Sucess: The wheel diameter has been set to "
								+ myTrain.getWheelDiameter() + " inches.\n");
					}
					break;
				case "status":
					System.out.println("Status Report: " + myTrain.getStatus());
					break;
				case "recommend":
					System.out.println("No Recommendations.");
					break;
				case "view log":			/* Access the log */	
						myTrain.readFromLog(username.equals("operator"));
					break;
				case "obstruction":
					myTrain.ProcessObject();
					break;
				default:
					System.out.println("Error: Unknown Command '" + command + "'. Please enter a valid command.\n");
					break;
				}
				date = java.util.Calendar.getInstance().getTime();
				myTrain.writeToLog("" + date + "-- User entered the following command \'" + command + "\'.\n");
				myTrain.writeToLog(myTrain.toString());


			}
			if (myTrain.getWifi()) {
				System.out
						.println("LCS is connected to WiFi. LCS is only meant " + "to be used when there is no wifi.");
				System.exit(-1);
			}
			if (!myTrain.wantToCont) {
				System.out.println("Shutting off ...");
				date=java.util.Calendar.getInstance().getTime();
				myTrain.writeToLog("" + date + "-- User \'" + username + "\' terminated LCS session.\n");
				myTrain.writeToLog(myTrain.toString());
				break;
			} else if (!myTrain.isLoggedIn) {
				System.out.println("Logged off");
				date=java.util.Calendar.getInstance().getTime();
				myTrain.writeToLog("" + date + "-- User \'" + username + "\' logged off successfully.\n");
				myTrain.writeToLog(myTrain.toString());
			} else {
				//TODO: Write LCS.toString() to log.
			}
			
		}
		scan.close();
		System.out.println("LCS has shut off successfully.");

	}

	
}

