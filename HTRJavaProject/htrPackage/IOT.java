

public class IOT extends Sensors {
	
	//data fields:
	
	void writeToLog() {
<<<<<<< HEAD
		//some file io method � write/append into a file
=======
		//some file io method to write/append into a file
>>>>>>> 1e8a3143d157f6ad2bb4da395eeb6bc8ed303da7
	}
	
	static String windReport() {
		if(getWindSpeed() >= 50) {
			return "The wind speed is " + getWindSpeed() + " mph. Recommendation: Reduce the speed of the train.";
		} else {
			return "The wind speed is " + getWindSpeed() + " mph.";
		}
	}
	
	static String rainReport() {
		if(getRainRate() >= 0.3) {
			return "The rate of rainfall is " + 
					getRainRate() + 
					" inches per hour. Recommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return "";
	}
	
	static String snowReport() {
		if(getSnowRate() >= 0.3) {
			return "The rate of snowfall is " + 
					getRainRate() + 
					" inches per hour. Recommendation: Turn on the train headlights and reduce the speed of the train." ;
		}
		return "";
	}
	
	static String visibilityReport() {
		if(getVisibility() < 2.0) {
			return "The visibility is " + getVisibility() + 
					" miles. Recommendation: Turn on the train headlights and reduce the speed of the train.";
		} else {
			return "The visibility is at least 2 miles.";
		}
	}
	
	static String obtainWeather() {
		//print the weather report.
		String precipitation = "";
		if(getRainRate() < 0.3 && getSnowRate() < 0.3) {
			precipitation += "The rate of precipitation is " + (getRainRate() + getSnowRate()) + " inches per hour.";
			return windReport() + "\n" + precipitation + "\n" + visibilityReport();
		} else {
			return windReport() + "\n" + rainReport() + "\n" + snowReport() + "\n" + visibilityReport();
		}
	}
	
	boolean isObstruction() {
//		return (detectStationaryObject() || detectMovingObject());
		return true;
	}
	
	boolean ObjectPosition() {
			//true if object is in front of train. False otherwise.
		return false;
	}
	
	double objectSpeed() {
		// 
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
	
	/**
	 * 
	 * @return the wheel diameter
	 */
	static double getWheelDiameter() {
		return wheel_diameter;
	}
	
	/**
	 * sets the wheel diameter to @param diameter.
	 * @return 0 on success. -1 on failure.
	 */
	static int setWheelDiameter(double diameter) {
		if (diameter < 1.0) {
			return -1;
		} else {
			wheel_diameter = diameter;
			return 0;
		}
	}	
	
	String detectSlippage() {
		//calculate slippage. return true if slippage. false if none.
		double rpmSpeed = getRPM() * wheel_diameter * Math.PI * 60 / 63360;
		if (Math.abs(getSpeed() - rpmSpeed) > (getSpeed() * 0.05)) {
			return "The wheels are slipping. Recommendation: slow down or halt the train.";
		} else {
			return "The wheels are not slipping.";
		}
	}
	
	double computeImpact() {
		//returns time to impact.
		return obtainDistanceFromObject()/getSpeed();
	}
	
	String gateStatus() {
		//true if gate is open, false if closed.
		if(getGateDistance() > 2.0) {
			if(isGateOpen()) {
				return "The next gate, which is " + getGateDistance() + " miles away, is open.";
			} else {
				return "The next gate, which is " + getGateDistance() + " miles away, is closed.";
			}
		} else {
			if(isGateOpen()) {
				return "The next gate, which is " + getGateDistance() + " miles away, is open.";
			} else {
				return "The next gate, which is " + getGateDistance() + 
						" miles away, is closed.\n" +
						" Recommendation: Stop the train immediately & wait for the gate to open.";
			}
		}
	}
	
	static void helpMessage() {
		
		System.out.println("help Message:\n" + 
				"\tEnter \"help\" to display the help message\n" +
				"\tEnter \"exit\" to exit LCS\n" +
				"\tEnter \"log off\" to log out of LCS\n" +
				"\tWheel Diamater: " + getWheelDiameter() + " inches.\n" +
				"Command Options:\n" +
				"\twifi \n\tadd user \n\tlocation \n\tweather \n\tspeed" +
				"\n\trpm \n\trecommend \n\tstatus \n\tset diameter");
	}
	
}