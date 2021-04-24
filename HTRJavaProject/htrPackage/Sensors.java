package htrPackage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Sensors {

	/* hardware: */
	
	public double wheel_diameter;	/** Wheel diameter in inches **/
	
	/* Data fields: */

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
	private Scanner doc;
	private ArrayList data;

	/**
	 * constructor for Sensor object.
	 */
	protected Sensors() {
		/* Initialize the sensor object with the following default values */
		//TODO: Changes these values to 0 after testing.
		/*this.wheel_diameter = 40.0;
		this.longitude = 1.0000;
		this.latitude = 1.0000;
		this.gate_distance = 1.0;
		this.gate_status = false;
		this.moving_obstruction = false;
		this.stationary_obstruction = false;
		this.distance_from_obstruction = 2.0;
		this.rpm = 100;
		this.speed = 10.0;
		this.wind_speed = 100.0;
		this.rate_rain = 1.0;
		this.rate_snow = 0.2;
		this.visibility = 2.0;*/
		data = new ArrayList<>();
		try
		{
			doc = new Scanner(new File("inputText.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not Found");
		}
		while(doc.hasNextLine())
		{

			data.add(doc.nextLine());
		}

		
	}
	void updateValues()
	{



	}
	void testDoc()
	{
		for(int i = 0; i<data.size();i++)
		{
			System.out.println(data.get(i));
		}
	}

	/**
	 * sets the wheel diameter to @param diameter.
	 * @return 0 on success. -1 on failure.
	 */
	int setWheelDiameter(double diameter) {
		if (diameter < 1.0) {
//			System.out.println("Error: Diameter must be at least 1 inch!");
			return -1;
		} else {
			this.wheel_diameter = diameter;
			return 0;
		}
	}

	/**
	 * @return the wheel diameter (in inches).
	 */
	double getWheelDiameter() {
		return this.wheel_diameter;		/* use randomizer only once? */
	}
	
	/**
	 * sets the longitude & latitude.
	 * Returns 0 on success, -1 on failure.
	 */
	int setLocation(double latitude, double longitude) {
		if (Math.abs(latitude) > 90 || longitude < 0 || longitude > 180) {
			System.out.println("Error: Invalid coordinates");
			return -1;
		}
		this.longitude = longitude;	/* use randomizer */
		this.latitude = latitude;	/* use randomizer */
		return 0;
	}
	
	/**
	 * @return the latitude.
	 */
	double getLatitude() {
		return this.latitude;
	}

	/**
	 * @return the longitude.
	 */
	double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Set the distance to the next gate.
	 * Returns 0 on success and -1 on failure.
	 */
	int setGateDistance(double dist) {
		if (dist > 0 || dist < 250.0) {
			return -1;
		}
		this.gate_distance = dist;	/* use randomizer */
		return 0;
	}

	/**
	 * @return get the distance to the next gate opening (in miles).
	 */
	double getGateDistance() {
		return this.gate_distance;
	}
	
	/**
	 * Set the gate_status data field to true if next gate is open,
	 * 	otherwise false.
	 */
	void setGateStatus() {
		this.gate_status = true; /* use randomizer */
	}

	/**
	 * @return true if next gate is open, false otherwise.
	 */
	boolean getGateStatus() {
		return this.gate_status;
	}

	/**
	 * If a stationary object is detected, set the data field
	 * 	 stationary_obstruction to true, otherwise false.
	 */
	void setDetectStationaryObject() {
		this.stationary_obstruction = true; /* use randomizer */
	}

	/**
	 * @return true if a a stationary object is detected, false otherwise.
	 */
	boolean getDetectStationaryObject() {
		return this.stationary_obstruction;
	}
	
	/**
	 * If a moving object is detected, set the data field
	 * 	 moving_obstruction to true, otherwise false.
	 */
	void setDetectMovingObject() {
		this.moving_obstruction = true; /* use randomizer */
	}

	/**
	 * @return true if a a moving object is detected, false otherwise.
	 */
	boolean getDetectMovingObject() {
		return this.moving_obstruction;
	}
	
	// TODO
	/**
	 * Sets then gets distance from object.
	 * @return a negative double to signify obstruction's distance behind the train,
	 * 	and a non-negative double to signify distance in front of the train.
	 */
	double obtainDistanceFromObject() {
		//distance b/w train and obstacle.
		double dist = -1.0;
		if(getDetectStationaryObject()) {
			//some math
			dist = 2.0;
		}
		if(getDetectMovingObject()) {
			//some math
			dist = 3.0;
		}
		this.distance_from_obstruction = dist;
		return this.distance_from_obstruction;
	}
	
	/**
	 * Set the rpm.
	 * Return 0 on success & -1 on failure.
	 */
	int setRPM(int rpm) {
		if (rpm < 0 || rpm > 5000) {
			return -1;
		}
		this.rpm = rpm; /* use randomizer */
		return 0;
	}

	/**
	 * @return the rpm. 
	 */
	int getRPM() {
		return this.rpm;
	}
	
	/**
	 * Set the speed.
	 * Return 0 on success & -1 on failure.
	 */
	int setSpeed(double speed) {
		if (speed < 0.0 || speed > 400.0) {
			return -1;
		}
		this.speed = speed; /* use randomizer */
		return 0;
	}

	/**
	 * @return the speed in miles per hour. 
	 */
	double getSpeed() {
		return this.speed;
	}
	
	/**
	 * Set the speed.
	 * Return 0 on success & -1 on failure.
	 */
	int setWindSpeed(double wind_speed) {
		if (wind_speed < 0 || wind_speed > 400) {
			return -1;
		}
		this.wind_speed = wind_speed; /* use randomizer */
		return 0;
	}

	/**
	 * @return the wind speed (miles per hour). 
	 */
	double getWindSpeed() {
		return this.wind_speed;
	}
	
	/**
	 * Set the rain rate.
	 * Return 0 on success & -1 on failure.
	 */
	int setRainRate(double rate) {
		if (rate < 0 || rate > 6) {
			return -1;
		}
		this.rate_rain = rate; /* use randomizer */
		return 0;
	}

	/**
	 * @return the rain rate (inches per hour). 
	 */
	double getRainRate() {
		return this.rate_rain;
	}
	
	/**
	 * Set the snow rate.
	 * Return 0 on success & -1 on failure.
	 */
	int setSnowRate(double rate) {
		if (rate < 0 || rate > 6) {
			return -1;
		}
		this.rate_snow = rate; /* use randomizer */
		return 0;
	}

	/**
	 * @return the snow rate (inches per hour). 
	 */
	double getSnowRate() {
		return this.rate_snow;
	}
	
	/**
	 * set the visibility.
	 * Return 0 on success & -1 on failure.
	 */
	int setVisibility(double visibility) {
		if (visibility < 0 || visibility > 2.0) {
			return -1;
		}
		this.visibility = visibility; /* use randomizer */
		return 0;
	}

	/**
	 * @return the visibility (in miles).
	 * 	Range of visibility is b/w 0.0 & 2.0 miles inclusive.
	 */
	double getVisibility() {
		return this.visibility;	
	}
	
}
