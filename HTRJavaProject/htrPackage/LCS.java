
import java.util.Scanner;

public class LCS extends IOT{

	//data fields
	private static final String userOP = "Username";
	private static final String passOP = "Password";
	private static final String userAD = "admin";
	private static final String passAD = "password";
	private static boolean isConnected;
	
	//methods:
	
	/* static boolean checkCredentials(String username, String password) {
		if (username.equals(userOP) && password.equals(passOP)) {
			return true;
		} else if(username.equals(userAD) && password.equals(passAD)) {
			return true;
		}
		return false;
	}*/
	public static boolean checkCredential(String text, String text2) {
		if (text.equals(userOP) && text2.equals(passOP)) {
			return true;
		} else if(text.equals(userAD) && text2.equals(passAD)) {
			return true;
		}
		return false;
		
	}

	static boolean isConnectedWifi() {
		return isConnected;
	}
	
	//(long,lat)
	static String displayLocation() {
		//call the sensor
		return "(" + getLocation() + ")";
	}
	
	static String displayWeather() {
		//call the sensor
		return obtainWeather();
	}
	
	static double displaySpeed() {
		//call the sensor
		return getSpeed();
	}
	
	static int displayRPM() {
		//call the sensor
		return getRPM();
	}
	
	static String recommend(String field) {
		//field = speed, weather, obstacle, gate
		return "";
	}
	
	
	static String getStatus() {
		//use stringbuilder
		
//		recommend(speed);
//		recommend(weather);
		// ...
		return "";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			System.out.println("Enter your username: ");
			String username = scan.nextLine();
			System.out.println("Enter your password: ");
			String password = scan.nextLine();
			if(checkCredential(username,password)) {
				break;
			} else {
				System.out.println("Incorrect credentials");
			}
		}
		
		boolean cont = true;
		while (cont && !isConnectedWifi()) {
			//print status report:
			System.out.print("-------------------------------\nEnter a command: ");
			String command = scan.nextLine();
			
			switch(command) {
			case "help": 
				helpMessage();
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
			
//			break;
		}
		if (isConnectedWifi()) {
			System.out.println("LCS is connected to WiFi. LCS is only meant to be used when there is no wifi.");
			System.exit(-1);
		}
		
		scan.close();
		System.out.println("LCS has shut off successfully.");
	}

/*	public boolean checkCredential(String text, String text2) {
		if (text.equals(userOP) && text2.equals(passOP)) {
			return true;
		} else if(text.equals(userAD) && text2.equals(passAD)) {
			return true;
		}
		return false;
		
	}*/
	
}
