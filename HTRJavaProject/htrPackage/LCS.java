package htrPackage;

//import java.io.*;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;
//import java.util.*;

public class LCS extends IOT {
	
		/* data fields */

	private boolean isConnected;
	private boolean isLoggedIn;
	// static final Scanner scan = new Scanner(System.in);

	/* Use a HashMap to store Login information. */
	/* Add default login info for operator & administrator */
	private Map<String, String> login_info = new HashMap<String, String>();
	// this.login_info.addLoginInfo("operator", "qwerty");
	// this.login_info.addLoginInfo("admin", "password");

		/* Constructor & object-initializer methods */

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
		// this.login_info.put(username, password);

	}

	/**
	 * Constructor for LCS object.
	 */
	private LCS() {
//		super(wheel_diameter); /* set wheel diameter */
		super();
		this.isConnected = false; /* By default, assume there is no wifi connection. */
		this.isLoggedIn = false;

		/* Initialize the default login credentials. */
		// addLoginInfo("operator", "qwerty");
		this.addLoginInfo("operator", "qwerty");
		this.addLoginInfo("admin", "password");
	}

		/* Regular methods: */

	/* Log in Credential Methods */

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
	private boolean checkCredentials(String username, String password) {
		return this.getLoginInfo().getOrDefault(username, "").equals(password);
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

	// String displayWeather() {
	// 	return obtainWeather();
	// }

	// double displaySpeed() {
	// 	return getSpeed();
	// }

	// int displayRPM() {
	// 	return getRPM();
	// }

	private String recommend(String field) {
		// field = speed, weather, obstacle, gate
		return "";
	}

	private String getStatus() {
		// use stringbuilder

//		recommend(speed);
//		recommend(weather);
		// ...
		return "";
	}

	public static void main(String[] args) {
		
		LCS myTrain = new LCS();
//		myObj.isConnected = true;
		Sensors mySens = new Sensors();
		
		// System.out.println("is connected: " + myObj.getWifi());
		// myObj.setWifi(true);
		// System.out.println("is connected: " + myObj.getWifi());

//		/* LCS initialized with default values. */
//		addLoginInfo("operator", "qwerty");
//		addLoginInfo("admin", "password");
		// setWheelDiameter(40.0);
		
		
		Scanner scan = new Scanner(System.in);

		boolean logoff = false; // true when the user wants to log off. false otherwise.
		while (!logoff) {

			while (true) {
				System.out.println("Enter your username: ");
				String username = scan.nextLine();
				System.out.println("Enter your password: ");
				String password = scan.nextLine();
				if (myTrain.checkCredentials(username, password)) {
					break;
				} else {
					System.out.println("Incorrect credentials");
				}

			}

			boolean cont = true;
			while (cont && !myTrain.getWifi() && !logoff) {
				System.out.print("-------------------------------\nEnter a command: ");
				String command = scan.nextLine().toLowerCase();

				switch (command) { /* commands are case insensitive */
				case "":
					break;
				case "help":
					System.out.println(myTrain.helpMessage());
					break;
				case "log off":
					// log out (but dont exit the program)
					System.out.println("Logged off");
					logoff = true;
					break;
				case "add user":
					System.out.print("Enter a username: ");
					String newUser = scan.nextLine();
					System.out.print("Enter a password: ");
					String newPass = scan.nextLine();
					myTrain.addLoginInfo(newUser, newPass);
					break;
				case "exit":
					cont = false;
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
					System.out.println("Speed: " + myTrain.getSpeed() + "mph.");
					break;
				case "rpm":
					System.out.println("Wheel rpm: " + myTrain.getRPM());
					break;
//				case "set diameter":
//					System.out.println("The wheel diameter is currently set to "
//							+ getWheelDiameter() + " inches. " 
//							+ "Enter the updated wheel diameter in inches: ");
//					double newDiameter = scan.nextDouble(); /* BUG: This causes "enter a command ..." to be printed twice.*/
//					if (setWheelDiameter(newDiameter) == -1) {
//						System.out.println("Error: Invalid wheel diameter. Wheel Diameter must be at least 1 inch.\n");
//					} else {
//						System.out.println("Sucess: The wheel diameter has been set to "
//								+ getWheelDiameter() + " inches.\n");
//					}
//					break;
				case "status":
					System.out.println("Status Report: " + myTrain.getStatus());
					break;
				case "recommend":
					System.out.println("No Recommendations.");
					break;
				default:
					System.out.println("Error: Unknown Command '" + command + "'. Please enter a valid command.\n");
					break;
				}

			}
			if (myTrain.getWifi()) {
				System.out
						.println("LCS is connected to WiFi. LCS is only meant " + "to be used when there is no wifi.");
				System.exit(-1);
			}
			if (!cont) {
				System.out.println("Shutting off ...");
				break;
			} else if (logoff) {
				System.out.println("Logging off ...");
				logoff = false; 
			}

		}
		scan.close();
		System.out.println("LCS has shut off successfully.");
	}
}

