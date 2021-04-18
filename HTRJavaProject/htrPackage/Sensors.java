package htrPackage;
//import java.util.*;

public class Sensors {

	//hardware:
	protected double wheel_diameter;
	
	//Data fields:
	private double longitude;
	private double latitude;
	
	private double gate_distance;
	private boolean gate_status;
	
	private boolean moving_obstruction;
	private boolean stationary_obstruction;
	private double distance_from_obstruction;
	
	private int rpm;
	
	private double speed;
	private double wind_speed;
	
	private double rate_rain;
	private double rate_snow;
	
	private double visibility;
	
	
	void setLocation() {
		longitude = 0.0;
		latitude = 0.0;
	}
	
	String getLocation() {
		//update longitude,latitude.
		return "" + longitude + ", " + latitude;
	}
	
	//combine the get & sets:
	
	double getGateDistance() {
		return 0.0;
	}
	
	boolean isGateOpen() {
		return false;
	}
	
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
	
	//CHECK BACK ON LATER
	double obtainDistanceFromObject() {
		//distance b/w train and obstacle.
		double dist = 0.0;
		if(detectStationaryObject()) {
			//some math
			dist = 0.0;
		}
		if(detectMovingObject()) {
			//some math
			dist = 0.0;
		}
		return dist;
	}
	
	int getRPM() {
		// update data field rpm w/ new rpm
		// return rpm
		return 0;
	}
	
	double getSpeed() {
		// update: 
		// data field speed, GPS speed;
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
		//inches per hour
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
