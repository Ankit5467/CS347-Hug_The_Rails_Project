package htrPackage;

//import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//import java.util.*;

public class LCS extends IOT {

	// data fields
	private static final String userOP = "operator";
	private static final String passOP = "qwerty";
	private static final String userAD = "admin";
	private static final String passAD = "password";
	private static boolean isConnected;

	/* Use a HashMap to store Login information. */
	/* Add default login info for operator & administrator */
	private static Map<String, String> login_info = new HashMap<String, String>();

	// methods:
	
	/**
	 * @returns the Map containing all usernames & passwords.
	 */
	static Map<String, String> getLoginInfo() {
		return login_info;
	}

	/**
	 * @param username
	 * @param password
	 * @return true if the pair (username,password) is a valid login credential,
	 * 		false otherwise.
	 */
	static boolean checkCredentials(String username, String password) {
//		for (Map.Entry<String, String> entry : getLoginInfo().entrySet()) {
//		    System.out.println(entry.getKey()+" : "+entry.getValue());
//		}
		
		return getLoginInfo().getOrDefault(username, "").equals(password);
	}

	/**
	 * 	If the given username and password are valid (at least 5 chars long), then
	 * 	add the (username,password) pair to the table of valid login credentials.
	 */
	static void addLoginInfo(String username, String password) {
		if (username.length() < 5 || password.length() < 5) {
			System.out.println("Error: Failed to add new user. Username " 
						+ "& password must be at least 5 characters.");
			return;
		}
		getLoginInfo().put(username, password);
	}

	static boolean isConnectedWifi() {
		return isConnected;
	}

	// (long,lat)
	static String displayLocation() {
		// call the sensor
		return "(" + getLocation() + ")";
	}

	static String displayWeather() {
		// call the sensor
		return obtainWeather();
	}

	static double displaySpeed() {
		// call the sensor
		return getSpeed();
	}

	static int displayRPM() {
		// call the sensor
		return getRPM();
	}

	static String recommend(String field) {
		// field = speed, weather, obstacle, gate
		return "";
	}

	static String getStatus() {
		// use stringbuilder

//		recommend(speed);
//		recommend(weather);
		// ...
		return "";
	}

	public static void main(String[] args) {

		getLoginInfo().put("operator", "qwerty");
		getLoginInfo().put("admin", "password");
		Scanner scan = new Scanner(System.in);

		boolean logoff = false; // true when the user wants to log off. false otherwise.
		while (!logoff) {

			while (true) {
				System.out.println("Enter your username: ");
				String username = scan.nextLine();
				System.out.println("Enter your password: ");
				String password = scan.nextLine();
				if (checkCredentials(username, password)) {
					break;
				} else {
					System.out.println("Incorrect credentials");
				}
			}

			boolean cont = true;
			while (cont && !isConnectedWifi() && !logoff) {
				// print status report:
				System.out.print("-------------------------------\nEnter a command: ");
				String command = scan.nextLine().toLowerCase();

				switch (command) { /* commands are case insensitive */
				case "":
					break;
				case "help":
					helpMessage();
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
					addLoginInfo(newUser, newPass);
					break;
				case "exit":
					cont = false;
					break;
				case "wifi":
					System.out.println("Connected to wifi: " + isConnectedWifi());
					break;
				case "location":
					System.out.println("Location: " + displayLocation());
					break;
				case "weather":
					System.out.println("Weather: " + displayWeather());
					break;
				case "speed":
					System.out.println("Speed: " + displaySpeed() + "mph.");
					break;
				case "rpm":
					System.out.println("Wheel rpm: " + displayRPM());
					break;
				case "status":
					System.out.println("Status Report: " + getStatus());
					break;
				default:
					System.out.println("Error: Unknown Command '" + command + "'. Please enter a valid command.\n");
					break;
				}

			}
			if (isConnectedWifi()) {
				System.out
						.println("LCS is connected to WiFi. LCS is only meant " + "to be used when there is no wifi.");
				System.exit(-1);
			}
			if (!cont) {
				break;
			} else if (logoff) {
				logoff = false; 
			}

		}
		scan.close();
		System.out.println("LCS has shut off successfully.");
	}

}