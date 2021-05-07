package htrPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Sensors {

	/* hardware: */
	public final double WHEEL_DIAMETER = 85.0; /* Wheel diameter in inches */
	public final long time = 10; /* How often to refresh the data (in seconds) */

	/* Data fields: */
	private int lastoff = 0;
	private double longitude1; /* miles from origin */
	private double latitude1; /* miles from origin */

	private double longitude2; /* miles from origin */
	private double latitude2; /* miles from origin */

	private double gate_distance; /* miles */
	private boolean gate_status; /* true = gate open. false = gate closed. */

	private boolean moving_obstruction; /* true = is obstruction. */
	private boolean stationary_obstruction; /* true = is obstruction. */
	private double distance_from_obstruction; /* [-2,2] miles */

	private int rpm;

	private double speed; /* mph */
	private double wind_speed; /* mph */

	private double rate_rain; /* inches per hour */
	private double rate_snow; /* inches per hour */

	private double visibility; /* miles */
	private ArrayList<String> data;

	/**
	 * constructor for Sensor object.
	 */
	protected Sensors() {
		/* Initialize the sensor object with the following default values */

		this.longitude1 = 0.00000; /* most recent longitude */
		this.latitude1 = 0.00000; /* most recent latitude */
		this.longitude2 = 2.0000; /* older longitude */
		this.latitude2 = 3.0000; /* older latitude */
		this.gate_distance = 4.58; /* miles */
		this.gate_status = true; /* false = closed. true = open. */
		this.moving_obstruction = false;
		this.stationary_obstruction = false;
		this.distance_from_obstruction = 2.0;
		this.rpm = 514;
		this.speed = 130.0; /* mph */
		this.wind_speed = 20.0; /* mph */
		this.rate_rain = 0.0; /* inches per hour */
		this.rate_snow = 0.0; /* inches per hour */
		this.visibility = 2.0; /* miles */
		updateValues();
	}

	public void updateValues() {
		data = new ArrayList<String>();

		try {

			Scanner input = new Scanner(System.in);

			File file = new File("sensor_inputs.txt");

			input = new Scanner(file);

			while (input.hasNextLine()) {
				String line = input.nextLine();
				data.add(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Map of data's indices to the data it represents. 0-rpm 1-lat 2-long 3-gate distance 
	 * 4-gate status 5-moving obstruction 6-stationary 7-distance 
	 * 8-rain 9-snow 10-wind 11-visibility
	 */
	public void updateValuesSensors() {

		rpm = Integer.valueOf((data.get(lastoff)));
		lastoff++;
		longitude2 = longitude1;
		longitude1 = Double.valueOf((data.get(lastoff)));
		lastoff++;
		latitude2 = latitude1;
		latitude1 = Double.valueOf((data.get(lastoff)));
		lastoff++;
		gate_distance = Double.valueOf((data.get(lastoff)));
		lastoff++;
		if (data.get(lastoff).equals("True")) {
			gate_status = true;
			lastoff++;
		} else {
			gate_status = false;
			lastoff++;
		}
		if (data.get(lastoff).equals("True")) {
			moving_obstruction = true;
			lastoff++;
		} else {
			moving_obstruction = false;
			lastoff++;
		}
		if (data.get(lastoff).equals("True")) {
			stationary_obstruction = true;
			lastoff++;
		} else {
			stationary_obstruction = false;
			lastoff++;
		}
		distance_from_obstruction = Double.valueOf((data.get(lastoff)));
		lastoff++;
		rate_rain = Double.valueOf((data.get(lastoff)));
		lastoff++;
		rate_snow = Double.valueOf((data.get(lastoff)));
		lastoff++;
		wind_speed = Double.valueOf((data.get(lastoff)));
		lastoff++;
		visibility = Double.valueOf((data.get(lastoff)));
		lastoff++;
		setSpeed();
	}

	/**
	 * Getter for time.
	 */
	public long getTime() {
		return this.time;
	}

	/**
	 * @return the wheel diameter (in inches).
	 */
	double getWheelDiameter() {
		return WHEEL_DIAMETER;
	}

	/**
	 * @return the most recent latitude.
	 */
	double getLatitude1() {
		return this.latitude1;
	}

	/**
	 * @return the most recent longitude.
	 */
	double getLongitude1() {
		return this.longitude1;
	}

	/**
	 * @return the old latitude.
	 */
	double getLatitude2() {
		return this.latitude2;
	}

	/**
	 * @return the old longitude.
	 */
	double getLongitude2() {
		return this.longitude2;
	}

	/**
	 * Set the distance to the next gate.
	 */
	void setGateDistance(double dist) {
		this.gate_distance = dist;
	}

	/**
	 * @return get the distance to the next gate opening (in miles).
	 */
	double getGateDistance() {
		return this.gate_distance;
	}

	/**
	 * Set the gate_status data field to true if next gate is open, otherwise false.
	 */
	void setGateStatus() {
		this.gate_status = true;
	}

	/**
	 * @return true if next gate is open, false otherwise.
	 */
	boolean getGateStatus() {
		return this.gate_status;
	}

	/**
	 * @return true if a a stationary object is detected, false otherwise.
	 */
	boolean getDetectStationaryObject() {
		return this.stationary_obstruction;
	}

	/**
	 * @return true if a a moving object is detected, false otherwise.
	 */
	boolean getDetectMovingObject() {
		return this.moving_obstruction;
	}

	/**
	 * Sets then gets distance from object.
	 * @return a negative double to signify obstruction's distance behind the train,
	 *         and a non-negative double to signify distance in front of the train.
	 */
	double obtainDistanceFromObject() {
		return this.distance_from_obstruction;
	}

	/**
	 * Set the rpm.
	 */
	void setRPM(int rpm) {
		if (rpm < 0 || rpm > 5000) {
			return;
		}
		this.rpm = rpm;
	}

	/**
	 * @return the rpm.
	 */
	int getRPM() {
		return this.rpm;
	}

	/**
	 * Set the speed.
	 */
	void setSpeed() {
		double deltaLat = Math.abs(this.latitude1 - this.latitude2);
		double deltaLong = Math.abs(this.longitude1 - this.longitude2);
		this.speed = Math.rint((3600/this.getTime()) * Math.sqrt(Math.pow(deltaLat, 2.0) + Math.pow(deltaLong, 2.0)));
	}

	/**
	 * @return the speed in miles per hour.
	 */
	double getSpeed() {
		return this.speed;
	}

	/**
	 * Set the speed.
	 */
	void setWindSpeed(double wind_speed) {
		if (wind_speed < 0 || wind_speed > 400) {
			return;
		}
		this.wind_speed = wind_speed;
	}

	/**
	 * @return the wind speed (miles per hour).
	 */
	double getWindSpeed() {
		return this.wind_speed;
	}

	/**
	 * Set the rain rate.
	 */
	void setRainRate(double rate) {
		this.rate_rain = rate;
	}

	/**
	 * @return the rain rate (inches per hour).
	 */
	double getRainRate() {
		return this.rate_rain;
	}

	/**
	 * Set the snow rate.
	 */
	void setSnowRate(double rate) {
		this.rate_snow = rate;
	}

	/**
	 * @return the snow rate (inches per hour).
	 */
	double getSnowRate() {
		return this.rate_snow;
	}

	/**
	 * Set the visibility.
	 */
	void setVisibility(double visibility) {
		if (visibility < 0 || visibility > 2.0) {
			return;
		}
		this.visibility = visibility;
	}

	/**
	 * @return the visibility (in miles). Range of visibility is b/w 0.0 & 2.0 miles
	 *         inclusive.
	 */
	double getVisibility() {
		return this.visibility;
	}

}
