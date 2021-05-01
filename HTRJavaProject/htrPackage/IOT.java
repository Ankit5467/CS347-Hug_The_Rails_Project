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
		return (1 + (Math.random() * ((15 - 1) + 1)));	/* generate random speed b/w [1,15] */
	}

	/**
	 * 
	 * @return the number of seconds to impact.
	 */
	double computeImpact() {
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
						"There is an obstruction " + roundTwoDecimals(this.obtainDistanceFromObject()) + " miles behind the train.");
				if (this.getDetectMovingObject()) {
					str.append("Obstruction is moving. Its speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
				} else {
					str.append("Obstruction is stationary.");
				}
				return str.toString();
			} else { /* obstruction in front of train */
				str.append("There is an obstruction " + roundTwoDecimals(this.obtainDistanceFromObject())
						+ " miles in front of the train.");
				if (this.getDetectMovingObject()) {
					str.append("Obstruction is moving. Its speed is " + roundTwoDecimals(this.objectSpeed()) + " mph.");
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
		StringBuilder str = new StringBuilder();

		if (this.getGateDistance() > 2.0) {
			if (this.getGateStatus()) {
				str.append( "The next gate, which is " + roundTwoDecimals(this.getGateDistance()) + " miles away, is open.");
			} else {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance()) + " miles away, is closed.");
			}
		} else {
			if (this.getGateStatus()) {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance()) + " miles away, is open. \n"
				+ "You may proceed.");
			} else {
				str.append("The next gate, which is " + roundTwoDecimals(this.getGateDistance()) + " miles away, is closed.\n"
						+ "Recommendation: Stop the train immediately & wait for the gate to open.");
			}
		}
		// if we do that, then if the train is going rlly fast, theres a chance it may not even recomemdn to honk.
		// eg if we make the range (0,0.2) for the 2nd honk
		//yes
		//    we can have the range be that small, as long as the train isn't going too fast 
		// 	it cannt travel more than 0.2 miles in 10 seconds.
		// 			72 mph (?)
		// if speed = 150mph: train travels 0.42 miles in 10 seconds. right..... changing the speed changes the long/lat.
		// wait faraz. U said it prints out an addition recommendation=?
		// how many 10 second intervals is it at 0 speed. 
		// only honks once. (supposed to)

		//TEST THIS
		if (this.getSpeed() != 0) {
				double deltaDist = 0.0; /* approximation of distance the train will travel in the next 'time' seconds. */
				deltaDist = (this.getTime()*this.getSpeed()/3600);

				/*  We have to approximate the gate dist w/ our curr speed. yes. Gate dist after 'time' sec.
					getGateDistance = current gate dist
					getGateDistance - deltaDist = approximation of gatedist in 15 second

					recommend honking the horn when
					gateDistance - approx15sec > 1 and gateDist - approx30sec < 1.
					ie: when gateDist is currently at least 1, but is projected to be less than 1 in 30 seconds.
				*/
				if (this.getGateDistance() - deltaDist > 1 && this.getGateDistance() - 2*deltaDist < 1) {
					str.append("Gate is about 1 mile away. Recommendation: Honk the horn for 15 seconds.\n");
				}
				
				/* 
					recommend honking the horn when
					gateDist - deltaDist > 0 && gateDist - 2*deltaDist == 0
					ie: when gateDist is currently greater than 0, but we are projected to cross the gate in the next 15 sec. 
				*/
				if (this.getGateDistance() - deltaDist >= 0 && this.getGateDistance() - 2*deltaDist == 0) {
					str.append("About to cross a gate. Recommendation: Honk the horn for 5 seconds.\n");
				}
			}
		
		return str.toString();
	}
}