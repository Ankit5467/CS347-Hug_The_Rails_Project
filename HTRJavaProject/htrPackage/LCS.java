package htrPackage;
import java.util.Scanner;

public class LCS extends IOT{

	//data fields
	private static final String userOP = "yo";
	private static final String passOP = "mama";
	private static final String userAD = "";
	private static final String passAD = "";
	private boolean isConnected;
	
	//methods:
	
	static boolean checkCredentials(String username, String password) {
		if (username.equals(userOP) && password.equals(passOP)) {
			return true;
		} else if(username.equals(userAD) && password.equals(passAD)) {
			return true;
		}
		return false;
	}

	boolean isConnectedWifi() {
		return isConnected;
	}
	
	//(long,lat)
	String displayLocation() {
		//call the sensor
		return "(" + getLocation() + ")";
	}
	
	String displayWeather() {
		//call the sensor
		return obtainWeather();
	}
	
	double displaySpeed() {
		//call the sensor
		return getSpeed();
	}
	
	int displayRPM() {
		//call the sensor
		return getRPM();
	}
	
	String recommend(String field) {
		//field = speed, weather, obstacle, gate
		return "";
	}
	
	
	String getStatus() {
		//use stringbuilder
		
//		recommend(speed);
//		recommend(weather);
		// ...
		return "";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("Enter your username: ");
			String username = scan.nextLine();
			System.out.println("Enter your password: ");
			String password = scan.nextLine();
			if(checkCredentials(username,password)) {
				break;
			} else {
				System.out.println("Incorrect credentials");
			}
		}
		scan.close();
	}

}
