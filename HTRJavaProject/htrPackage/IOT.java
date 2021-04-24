package htrPackage;

public class IOT extends Sensors {
	
	/**
	 * constructor for IOT object. 
	 */
	protected IOT() {
		// super(wheel_diameter);
		super();
	}
	
	
	void writeToLog() {
	}
	
	String windReport() {
		if(this.getWindSpeed() >= 50) {
			return "The wind speed is " + this.getWindSpeed() + " mph. Recommendation:" +
			" Reduce the speed of the train.";
		} else {
			return "The wind speed is " + this.getWindSpeed() + " mph.";
		}
	}
	
	String rainReport() {
		if (this.getRainRate() >= 0.3) {
			return "The rate of rainfall is " +  this.getRainRate() + " inches per hour." +
			" Recommendation: Turn on the train headlights and reduce the speed of the train.";
		} 
			return "The rate of rainfall is " +  this.getRainRate() + " inches per hour.";
	}
	
	String snowReport() {
		if(this.getSnowRate() >= 0.3) {
			return "The rate of snowfall is " + this.getSnowRate() + " inches per hour." +
			" Recommendation: Turn on the train headlights and reduce the speed of the train." ;
		}
		return "The rate of snowfall is " + this.getSnowRate() + " inches per hour.";
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
		return obtainDistanceFromObject() >= 0;
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
		return 0.0;
	}
	
	void ProcessObject() {
		
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
	
	// /**
	//  * 
	//  * @return the wheel diameter
	//  */
	// double getWheelDiameter() {
	// 	return this.wheel_diameter;
	// }
	
	// /**
	//  * sets the wheel diameter to @param diameter.
	//  * @return 0 on success. -1 on failure.
	//  */
	// public int setWheelDiameter(double diameter) {
	// 	if (diameter < 1.0) {
	// 		return -1;
	// 	} else {
	// 		wheel_diameter = diameter;
	// 		return 0;
	// 	}
	// }	
	
	String detectSlippage() {
		//calculate slippage. return true if slippage. false if none.
		double rpmSpeed = this.getRPM() * this.getWheelDiameter() * Math.PI * 60 / 63360;
		if (Math.abs(this.getSpeed() - rpmSpeed) > (this.getSpeed() * 0.05)) {
			return "The wheels are slipping. Recommendation: slow down or halt the train.";
		} else {
			return "The wheels are not slipping.";
		}
	}
	
	double computeImpact() {
		//returns time to impact.
		return this.obtainDistanceFromObject()/this.getSpeed();
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
				"\tWheel Diamater: " + this.getWheelDiameter() + " inches.\n" +
				"Command Options:\n" +
				"\twifi \n\tadd user \n\tlocation \n\tweather \n\tspeed" +
				"\n\trpm \n\trecommend \n\tstatus \n\tset diameter";
	}
	
}