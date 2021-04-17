package htrPackage;
//import java.util.*;

public class Sensors {

	//hardware:
	double wheel_diameter;
	
	//Data fields:
	double longitude;
	double latitude;
	
	boolean moving_obstruction;
	boolean stationary_obstruction;
	
	int rpm;
	
	double speed;
	double wind_speed;
	
	double rate_rain;
	double rate_snow;
	
	double visibility;
	
	
	void setLocation() {
		longitude = 0.0;
		latitude = 0.0;
	}
	
	String getLocation() {
		//update longitude,latitude.
		return "" + longitude + ", " + latitude;
	}
	
	//combine the get & sets:
	
	boolean detectStationaryObject() {
		//update:
		//stationary_obstruction;
		return false;
	}
	
	boolean detectMovingObject() {
		//update:
		//data field moving_obstruction;
		return false;
	}
	
	int getRPM() {
		// update data field rpm w/ new rpm
		// return rpm
		return 0;
	}
	
	double getSpeed() {
		// update: 
		// data field speed;
		return 0.0;
	}
	
	double getWindSpeed() {
		// update: 
		// data field wind_speed
		return 0.0;
	}
	
	double getRainRate() {
		// update: 
		// data field rain_rate
		return 0.0;
	}
	
	double getSnowRate() {
		// update: 
		// data field snow_rate
		return 0.0;
	}
	
	double getVisibility() {
		// update: 
		// data field visibility
		return 0.0;	
	}
	
}
