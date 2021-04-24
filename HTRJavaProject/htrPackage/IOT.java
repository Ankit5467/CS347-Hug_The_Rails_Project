package htrPackage;

public class IOT extends Sensors {
	
	/**
	 * constructor for IOT object. 
	 */
	protected IOT() {
		// super(wheel_diameter);
		super();
	}

	String windReport() {
		String data = "The wind speed is " + this.getWindSpeed() + " mph. ";
		String rec = "";
		if(this.getWindSpeed() >= 50) {
			rec += "Recommendation: Reduce the speed of the train.";
		}
		return data + rec;
	}
	
	String rainReport() {
		String data = "The rate of rainfall is " +  this.getRainRate() + " inches per hour.";
		String rec = "";
		if (this.getRainRate() >= 0.3) {
			rec += " Recommendation: Turn on the train headlights and reduce the speed of the train.";
			return "The rate of rainfall is " +  this.getRainRate() + " inches per hour.\n" +
			"Recommendation: Turn on the train headlights and reduce the speed of the train.";
		} 
			return data + rec;
	}
	
	String snowReport() {
		String data = "The rate of snowfall is " +  this.getSnowRate() + " inches per hour.";
		String rec = "";
		if (this.getSnowRate() >= 0.3) {
			rec += " Recommendation: Turn on the train headlights and reduce the speed of the train.";
		} 
			return data + rec;
	}
	
	String visibilityReport() {
		if(this.getVisibility() < 2.0) {
			return "The visibility is " + this.getVisibility() + 
					" miles. Recommendation: Turn on the train" + 
					" headlights and reduce the speed of the train.";
		} else {
			return "The visibility is at least 2 miles.";
		}
	}
	
	String obtainWeather() {
		//print the weather report.
		String precipitation = "";
		if(this.getRainRate() < 0.3 && this.getSnowRate() < 0.3) {
			precipitation += "The rate of precipitation is " + 
				(this.getRainRate() + this.getSnowRate()) + " inches per hour.";
			return this.windReport() + "\n" + precipitation + "\n" + visibilityReport();
		} else {
			return this.windReport() + "\n" + this.rainReport() + "\n" 
				+ this.snowReport() + "\n" + this.visibilityReport();
		}
	}
	
	boolean isObstruction() {
		// return true;
		return this.getDetectStationaryObject() || getDetectMovingObject();
	}
	
	boolean ObjectPosition() {
			//true if object is in front of train. False otherwise.
		return this.obtainDistanceFromObject() >= 0;
	}
	
	/**
	 * @returns the speed of the object relative to the train.
	 * 	Positive speed indicates object is moving away from the train.
	 * 	Negative speed indicates object is moving closer to the train. 
	 * 	Zero speed indicates object is stationary relative to the train.
	 * 		ie: Its moving at the same speed & direction as the train. 
	 */
	double objectSpeed() {
		// get 2 distance measurements.
		//TODO
		return 4.0; /* return randomly generated number b/w 1 and 15 */
	}
	
	/**
	 * 
	 * @return the number of seconds to impact.
	 */
	double computeImpact() {
		//returns time to impact.
		return this.obtainDistanceFromObject()/ (88*this.getSpeed());
	}

	void ProcessObject() {

		if (!this.isObstruction()) {
			System.out.println("There is no obstruction.\n");
		} else {
			if (this.obtainDistanceFromObject() < 0) { /* obstruction behind train */
				System.out.println("There is an obstruction " + this.obtainDistanceFromObject() + " feet behind the train.\n");
				if (this.getDetectMovingObject()) {
					System.out.println("Obstruction is moving. It's speed is " + this.objectSpeed() + " mph.\n");
				} else {
					System.out.println("Obstruction is stationary.\n");
				}

			} else {	/* obstruction in front of train */
				System.out.println("There is an obstruction " + this.obtainDistanceFromObject() + " feet in front of the train.\n");
				if (this.getDetectMovingObject()) {
					System.out.println("Obstruction is moving. It's speed is " + this.objectSpeed() + " mph.\n");
				} else {
					System.out.println("Obstruction is stationary.\n");
				}
				System.out.println("Estimated time to impact: " + this.computeImpact() + " minutes.\n");
			}
		}
		
		/* ALGORITHM:
		 * if (!isObstruction)
		 * 		print "There is no obstruction"	
		 * else
		 * 		if object is in front of train
		 * 			Print whether object is moving or stationary
		 * 			If object is moving
		 * 				compute & print object speed	
		 * 			Compute & Print distance b/w train & Object
		 * 			Compute & Print Time to impact (if applicable)
		 * 
		 * 		else (object is behind train)
		 * 			Print whether object is moving or stationary
		 * 			If object is moving
		 * 				compute & print object speed
		 * 			Compute & Print distance b/w train & Object
		 * 		
		 */
		
	}
	
	String detectSlippage() {
		//calculate slippage. return true if slippage. false if none.
		double rpmSpeed = this.getRPM() * this.getWheelDiameter() * Math.PI * 60 / 63360;
		if (Math.abs(this.getSpeed() - rpmSpeed) > (this.getSpeed() * 0.05)) {
			return "The wheels are slipping. Recommendation: slow down or halt the train.";
		} else {
			return "The wheels are not slipping.";
		}
	}
	
	String gateStatus() {
		//true if gate is open, false if closed.
		if(getGateDistance() > 2.0) {
			if(this.getGateStatus()) {
				return "The next gate, which is " + getGateDistance() + " miles away, is open.";
			} else {
				return "The next gate, which is " + getGateDistance() + " miles away, is closed.";
			}
		} else {
			if(getGateStatus()) {
				return "The next gate, which is " + getGateDistance() + " miles away, is open.";
			} else {
				return "The next gate, which is " + getGateDistance() + 
						" miles away, is closed.\n" +
						" Recommendation: Stop the train immediately & wait for the gate to open.";
			}
		}
	}
	
	String helpMessage() {
		
		return "help Message:\n" + 
				"\tEnter \"help\" to display the help message\n" +
				"\tEnter \"exit\" to exit LCS\n" +
				"\tEnter \"log off\" to log out of LCS\n" +
				"\tEnter \"view log\" to view the log. (Only available for the operator.\n" +
				"\tWheel Diamater: " + this.getWheelDiameter() + " inches.\n" +
				"Command Options:\n" +
				"\twifi \n\tadd user \n\tlocation \n\tweather \n\tspeed" +
				"\n\trpm \n\trecommend \n\tstatus \n\tset diameter";
	}
	
}