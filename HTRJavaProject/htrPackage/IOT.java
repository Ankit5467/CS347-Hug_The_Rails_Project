package htrPackage;

public class IOT extends Sensors {

	/**
	 * constructor for IOT object.
	 */
	protected IOT() {
		super();
	}

	/**
	 * Rounds a double to 2 decimal places.
	 */
	static double roundTwoDecimals(double db) {
		return ((double) ((int) (db * 100))) / 100;
	}

	/**
	 * @return the windreport. Includes recommendation if windspeed is >= 50.
	 */
	String windReport() {
		String data = "The wind speed is " + roundTwoDecimals(this.getWindSpeed()) + " mph. ";
		String rec = "";
		if (this.getWindSpeed() >= 50.0) {
			rec += "\nRecommendation: Reduce the speed of the train.";
		}
		return data + rec;
	}

	/**
	 * @return the rain report. Includes recommendation if rainRate is >= 0.3.
	 */
	String rainReport() {
		String data = "The rate of rainfall is " + roundTwoDecimals(this.getRainRate()) + " inches per hour.";
		String rec = "";
		if (this.getRainRate() >= 0.3) {
			rec += "\nRecommendation: Turn on the train headlights and reduce the speed of the train.";
			return "The rate of rainfall is " + roundTwoDecimals(this.getRainRate()) + " inches per hour."
					+ "\nRecommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return data + rec;
	}

	/**
	 * @return the snow report. Includes recommendation if snowRate is >= 0.3.
	 */
	String snowReport() {
		String data = "The rate of snowfall is " + roundTwoDecimals(this.getSnowRate()) + " inches per hour.";
		String rec = "";
		if (this.getSnowRate() >= 0.3) {
			rec += "\nRecommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return data + rec;
	}

	/**
	 * @return the visibility report. Includes recommendation if visibility is < 2.0
	 *         miles.
	 */
	String visibilityReport() {
		if (this.getVisibility() < 2.0) {
			return "The visibility is " + roundTwoDecimals(this.getVisibility())
					+ " miles.\nRecommendation: Turn on the train" + " headlights and reduce the speed of the train.";
		} else {
			return "The visibility is at least 2 miles.";
		}
	}

	// String obtainWeather() {
	// // print the weather report.
	// String precipitation = "";
	// if (this.getRainRate() < 0.3 && this.getSnowRate() < 0.3) {
	// precipitation += "The rate of precipitation is " +
	// roundTwoDecimals(this.getRainRate() + this.getSnowRate())
	// + " inches per hour.";
	// return this.windReport() + "\n" + precipitation + "\n" + visibilityReport();
	// } else {
	// return this.windReport() + "\n" + this.rainReport() + "\n" +
	// this.snowReport() + "\n"
	// + this.visibilityReport();
	// }
	// }

	/**
	 * @return true if there is an obstruction. False otherwise.
	 */
	boolean isObstruction() {
		// return true;
		return this.getDetectStationaryObject() || getDetectMovingObject();
	}

	/**
	 * @returns the speed of the object relative to the train.
	 */
	double objectSpeed() {
		return (1 + (Math.random() * ((15 - 1) + 1))); /* generate random speed b/w [1,15] */
	}

	/**
	 * 
	 * @return the number of seconds to impact.
	 */
	double computeImpact() {
		return (((this.obtainDistanceFromObject() * 5280) / (88 * this.getSpeed())));
	}

	/*
	 * Object processing ALGORITHM: if (!isObstruction) print
	 * "There is no obstruction" else if object is in front of train Print whether
	 * object is moving or stationary If object is moving compute & print object
	 * speed Compute & Print distance b/w train & Object Compute & Print Time to
	 * impact (if applicable)
	 * 
	 * else (object is behind train) Print whether object is moving or stationary If
	 * object is moving compute & print object speed Compute & Print distance b/w
	 * train & Object
	 */
	String ProcessObject() {
		StringBuilder str = new StringBuilder();

		if (!this.isObstruction()) {
			str.append("There is no obstruction.");
			return str.toString();
		} else {
			if (this.obtainDistanceFromObject() < 0) { /* obstruction behind train */
				str.append("There is an obstruction " + Math.abs(roundTwoDecimals(this.obtainDistanceFromObject()))
						+ " miles behind the train.");
				if (this.getDetectMovingObject()) {
					str.append(" Obstruction is moving. Its speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
				} else {
					str.append(" Obstruction is stationary.");
				}
				return str.toString();
			} else { /* obstruction in front of train */
				str.append("There is an obstruction " + roundTwoDecimals(this.obtainDistanceFromObject())
						+ " miles in front of the train.");
				if (this.getDetectMovingObject()) {
					str.append(" Obstruction is moving. Its speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
				} else {
					str.append(" Obstruction is stationary.");
				}
				if (this.getSpeed() != 0) {
					str.append(" Estimated time to impact: " + roundTwoDecimals(this.computeImpact()) + " minutes.");
				}
//				str.append("Estimated time to impact: " + roundTwoDecimals(this.computeImpact()) + " minutes.");
				return str.toString();
			}
		}

	}

	/**
	 * Calculate wheel slippage. If the wheels are slipping, then print out a
	 * recommendation to slow down the train.
	 * LOOK INTO IT A BIT MORE!
	 */
	String detectSlippage() {
		/*
		 * Wheel slippage occurs when speed calculated from rpm if more than .001%
		 * different from normal speed, where normal speed is speed calculated using gps
		 * coordinates.
		 */
//		double rpmSpeed = (double) this.getRPM() * this.getWheelDiameter() * Math.PI * (double) 60 / (double) 63360;
//		if (Math.abs(
//				this.getSpeed() - rpmSpeed) > (this.getSpeed() * 0.005)) { /* would this work? CHANGED FROM 0.05 */
//			return "The wheels are slipping. Recommendation: slow down or halt the train.";
//		} else {
//			return "The wheels are not slipping.";
//		}
		if (this.getSnowRate() >= 0.2 || this.getRainRate() >= 0.3) {
			return "The wheels are slipping.\nRecommendation: slow down or halt the train.";
		} else {
			return "The wheels are not slipping.";
		}
	}

	/**
	 * Processed the gate status.
	 * 
	 * @returns a string which contains details about upcoming gate crossings.
	 *          String will contain the appropriate recommendations, if any.
	 * NEEDS MORE WORK!!!
	 */
	String gateStatus() {
		StringBuilder str = new StringBuilder();

		if (this.getGateDistance() > 2.0) {
			if (this.getGateStatus()) { // true if gate is open, false if closed.
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance())
						+ " miles away, is open. ");
			} else {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance())
						+ " miles away, is closed.");
			}
		} else {
			if (this.getGateStatus()) {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance())
						+ " miles away, is open. ");
				if (!this.isObstruction()) {
					str.append("You may proceed.");
				} /*else {*/
				// 	str.append("\n");
				// }
			} else {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance())
						+ " miles away, is closed."
						+ "\nRecommendation: Stop the train immediately & wait for the gate to open.\n");
			}
		}

		// TEST THIS
		/* Dont honk if the gate is closed & there is an obstruction */
		if (this.getSpeed() != 0) {
			double deltaDist = 0.0; 
			/* approximation of distance the train will travel in miles in the next 'time' seconds. */
			deltaDist = (this.getTime() * this.getSpeed() / 3600);

			/*
			 * We have to approximate the gate dist w/ our curr speed. yes. Gate dist after
			 * 'time' sec.
			 * 
			 * Honk the horn if the gate distance is b/w [1, 1 + deltaDist).
			 *   1 + deltaDist is the approximation of the gateDistance after 15 seconds.
			 */
			if (this.getGateDistance() >= 1 && this.getGateDistance() < 1 + deltaDist) {
				str.append("Gate is about 1 mile away.\nRecommendation: Honk the horn for 15 seconds.");
			}

			// if (this.getGateDistance() >=1 && this.getGateDistance() - deltaDist < 1 && this.getGateDistance() < 1 + deltaDist) {
			// 	str.append("Gate is about 1 mile away. Recommendation: Honk the horn for 15 seconds.\n");
			// }

			/*
			 * Honk the horn if the gate distance is b/w [0, deltaDist).
			 * deltaDist is the approximation of the gateDistance after 15 seconds.
			 */
			if ( (this.getGateDistance() >= 0 && this.getGateDistance() <  deltaDist) || (this.getGateDistance() <= 0.1)) {
				str.append(" About to cross a gate.\nRecommendation: Honk the horn for 5 seconds.");
			}
		}

		return str.toString();
	}
}