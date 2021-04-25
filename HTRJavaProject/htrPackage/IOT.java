package htrPackage;

public class IOT extends Sensors {

	/**
	 * constructor for IOT object.
	 */
	protected IOT() {
		// super(wheel_diameter);
		super();
	}

	static double roundTwoDecimals(double db) {
		return ((double) ((int)(db*100))) / 100;
	}

	String windReport() {
		String data = "The wind speed is " + roundTwoDecimals(this.getWindSpeed()) + " mph. ";
		String rec = "";
		if (this.getWindSpeed() >= 50.0) {
			rec += "Recommendation: Reduce the speed of the train.";
		}
		return data + rec;
	}

	String rainReport() {
		String data = "The rate of rainfall is " + roundTwoDecimals(this.getRainRate()) + " inches per hour.";
		String rec = "";
		if (this.getRainRate() >= 0.3) {
			rec += " Recommendation: Turn on the train headlights and reduce the speed of the train.";
			return "The rate of rainfall is " + roundTwoDecimals(this.getRainRate()) + " inches per hour.\n"
					+ "Recommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return data + rec;
	}

	String snowReport() {
		String data = "The rate of snowfall is " + roundTwoDecimals(this.getSnowRate()) + " inches per hour.";
		String rec = "";
		if (this.getSnowRate() >= 0.3) {
			rec += " Recommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return data + rec;
	}

	String visibilityReport() {
		if (this.getVisibility() < 2.0) {
			return "The visibility is " + roundTwoDecimals(this.getVisibility()) + " miles. Recommendation: Turn on the train"
					+ " headlights and reduce the speed of the train.";
		} else {
			return "The visibility is at least 2 miles.";
		}
	}

	String obtainWeather() {
		// print the weather report.
		String precipitation = "";
		if (this.getRainRate() < 0.3 && this.getSnowRate() < 0.3) {
			precipitation += "The rate of precipitation is " + roundTwoDecimals(this.getRainRate() + this.getSnowRate())
					+ " inches per hour.";
			return this.windReport() + "\n" + precipitation + "\n" + visibilityReport();
		} else {
			return this.windReport() + "\n" + this.rainReport() + "\n" + this.snowReport() + "\n"
					+ this.visibilityReport();
		}
	}

	boolean isObstruction() {
		// return true;
		return this.getDetectStationaryObject() || getDetectMovingObject();
	}

	/**
	 * @returns the speed of the object relative to the train. Positive speed
	 *          indicates object is moving away from the train. Negative speed
	 *          indicates object is moving closer to the train. Zero speed indicates
	 *          object is stationary relative to the train. ie: Its moving at the
	 *          same speed & direction as the train.
	 */
	double objectSpeed() {
		// get 2 distance measurements.
		// return ((double) ((int) ((1 + (Math.random() * ((15 - 1) + 1))) * 100))) / 100; /* generate random speed b/w [1,15] */
		return (1 + (Math.random() * ((15 - 1) + 1)));
	}

	/**
	 * 
	 * @return the number of seconds to impact.
	 */
	double computeImpact() {
		// returns time to impact.
		// return ((double) ((int)((this.obtainDistanceFromObject() / (88 * this.getSpeed())) * 100))) / 100;
		return ((this.obtainDistanceFromObject() / (88 * this.getSpeed())));
	}

	String ProcessObject() {
		StringBuilder str = new StringBuilder();

		if (!this.isObstruction()) {
			str.append("There is no obstruction.");
			return str.toString();
		} else {
			if (this.obtainDistanceFromObject() < 0) { /* obstruction behind train */
				str.append(
						"There is an obstruction " + roundTwoDecimals(this.obtainDistanceFromObject()) + " feet behind the train.");
				if (this.getDetectMovingObject()) {
					str.append("Obstruction is moving. It's speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
				} else {
					str.append("Obstruction is stationary.");
				}
				return str.toString();
			} else { /* obstruction in front of train */
				str.append("There is an obstruction " + roundTwoDecimals(this.obtainDistanceFromObject())
						+ " feet in front of the train.");
				if (this.getDetectMovingObject()) {
					str.append("Obstruction is moving. It's speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
				} else {
					str.append("Obstruction is stationary.");
				}
				str.append("Estimated time to impact: " + roundTwoDecimals(this.computeImpact()) + " minutes.");
				return str.toString();
			}
		}

		/*
		 * ALGORITHM: if (!isObstruction) print "There is no obstruction" else if object
		 * is in front of train Print whether object is moving or stationary If object
		 * is moving compute & print object speed Compute & Print distance b/w train &
		 * Object Compute & Print Time to impact (if applicable)
		 * 
		 * else (object is behind train) Print whether object is moving or stationary If
		 * object is moving compute & print object speed Compute & Print distance b/w
		 * train & Object
		 * 
		 */

	}

	String detectSlippage() {
		// calculate slippage. return true if slippage. false if none.
		double rpmSpeed = (double)this.getRPM() * this.getWheelDiameter() * Math.PI * (double)60 / (double)63360;
		if (Math.abs(this.getSpeed() - rpmSpeed) > (this.getSpeed() * 0.05)) {
			return "The wheels are slipping. Recommendation: slow down or halt the train.";
		} else {
			return "The wheels are not slipping.";
		}
	}

	String gateStatus() {
		// true if gate is open, false if closed.
		if (getGateDistance() > 2.0) {
			if (this.getGateStatus()) {
				return "The next gate, which is " + roundTwoDecimals(getGateDistance()) + " miles away, is open.";
			} else {
				return "The next gate, which is " + roundTwoDecimals(getGateDistance()) + " miles away, is closed.";
			}
		} else {
			if (getGateStatus()) {
				return "The next gate, which is " + roundTwoDecimals(getGateDistance()) + " miles away, is open.";
			} else {
				return "The next gate, which is " + roundTwoDecimals(getGateDistance()) + " miles away, is closed.\n"
						+ "Recommendation: Stop the train immediately & wait for the gate to open.";
			}
		}
	}

	String helpMessage() {

		return "help Message:\n" + "\tEnter \"help\" to display the help message\n" + "\tEnter \"exit\" to exit LCS\n"
				+ "\tEnter \"log off\" to log out of LCS\n"
				+ "\tEnter \"view log\" to view the log. (Only available for the operator.\n" + "\tWheel Diamater: "
				+ roundTwoDecimals(this.getWheelDiameter()) + " inches.\n" + "Command Options:\n"
				+ "\twifi \n\tadd user \n\tlocation \n\tweather \n\tspeed"
				+ "\n\trpm \n\trecommend \n\tstatus \n\tset diameter";
	}

}