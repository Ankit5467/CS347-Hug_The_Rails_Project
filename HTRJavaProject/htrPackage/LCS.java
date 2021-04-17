package htrPackage;

public class LCS extends IOT{

	//methods:
	
	boolean checkCredentials(String username, String password) {
		
		return true;
	}

	boolean isConnectedWifi() {
		return true;
	}
	
	String displayLocation() {
		//call the sensor
		return "";
	}
	
	String displayWeather() {
		//call the sensor
		return "";
	}
	
	double displaySpeed() {
		//call the sensor
		return 0.0;
	}
	
	int displayRPM() {
		//call the sensor
		return 0;
	}
	
	String recommend(String field) {
		//field = speed, weather, etc ...
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

	}

}
