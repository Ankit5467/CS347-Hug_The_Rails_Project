package htrPackage;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class LCS extends IOT {

	/* data fields */
	private boolean isConnected; /* tracks if the LCS system is connected to wifi */
	private boolean isLoggedIn; /* tracks if the user is logged into LCS */
	private boolean wantToCont; /* tracks if the user wants to exit from LCS */

	private Path logFileName;
	private File log; /* LCS log stores lots of information */

	String user = "";
	String pass = "";

	/* Use a HashMap to store Login information. */
	/* Add default login info for operator & administrator */
	private Map<String, String> login_info = new HashMap<String, String>();

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

		/* create the log file: */
		try {
			this.log = new File(filename);
			this.log.createNewFile();
		} catch (Exception e) {
			System.out.println("An error occurred.\n");
		}

		/* set its filename/path and write a line to it */
		this.logFileName = Path.of(filename);
		try {
			/* Initialize the log to be written to & read from */
			Files.write(this.logFileName, "******************************************\n".getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			/* Unable to initialize log */
			e.printStackTrace();
		}

	}

	void writeToLog(String str) {
		try {
			Files.write(this.logFileName, str.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			/* Unable to write to log */
			e.printStackTrace();
		}
	}

	String readFromLog() {
		String data = "";
		try {
			Scanner myReader = new Scanner(this.log);
			while (myReader.hasNextLine()) {
				data += myReader.nextLine();
				data += "\n";
			}
			data += "*************** END OF LOG ***************\n";
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: Unable to read from the log.\n");
			e.printStackTrace();
		}
		return data;
	}

	/* Log in Credential Methods */

	/**
	 * If the given username and password are valid (at least 5 chars long), then
	 * add the (username,password) pair to the table of valid login credentials.
	 */
	void addLoginInfo(String username, String password) {
		if (username.length() < 5 || password.length() < 5) {
			System.out
					.println("Error: Failed to add new user. Username " + "& password must be at least 5 characters.");
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
	 *         false otherwise.
	 */
	protected boolean checkCredentials(String username, String password) {
		if (this.getLoginInfo().getOrDefault(username, "").equals(password)) {
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
	private void setWifi(boolean b) {
		this.isConnected = b;
	}

	/**
	 * 
	 * @return a string representation of the location in the form: (long,lat)
	 */
	String displayLocation() {
		return "(" + this.getLatitude1() + ", " + this.getLongitude1() + ")";
	}

	/**
	 * ToString for an LCS object.
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nLocation: " + this.displayLocation());
		str.append("\nGate Distance: " + roundTwoDecimals(this.getGateDistance()));
		str.append("\nGate Status: " + this.getGateStatus());
		str.append("\nObstruction Present?: " + this.isObstruction());
		str.append("\nRPM: " + this.getRPM());
		str.append("\nSpeed: " + roundTwoDecimals(this.getSpeed()));
		str.append("\nWind Speed: " + roundTwoDecimals(this.getWindSpeed()));
		str.append("\nRain Rate: " + roundTwoDecimals(this.getRainRate()));
		str.append("\nSnow Rate: " + roundTwoDecimals(this.getSnowRate()));
		str.append("\nVisibility: " + roundTwoDecimals(this.getVisibility()) + "\n");
		return str.toString();
	}
}