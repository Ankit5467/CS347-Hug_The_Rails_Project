package htrPackage;

public class IOT extends Sensors {
	
	//data fields:
	//
	
	void writeToLog() {
		//some file io method – write/append into a file
	}
	
	String windReport() {
		if(getWindSpeed() >= 50) {
			return "The wind speed is " + getWindSpeed() + " mph. Recommendation: Reduce the speed of the train.";
		} else {
			return "The wind speed is " + getWindSpeed() + " mph.";
		}
	}
	
	String rainReport() {
		if(getRainRate() >= 0.3) {
			return "The rate of rainfall is " + 
					getRainRate() + 
					" inches per hour. Recommendation: Turn on the train headlights and reduce the speed of the train.";
		}
		return "";
	}
	
	String snowReport() {
		if(getSnowRate() >= 0.3) {
			return "The rate of snowfall is " + 
					getRainRate() + 
					" inches per hour. Recommendation: Turn on the train headlights and reduce the speed of the train." ;
		}
		return "";
	}
	
	String visibilityReport() {
		if(getVisibility() < 2.0) {
			return "The visibility is " + getVisibility() + " miles. Recommendation: Turn on the train headlights and reduce the speed of the train.";
		} else {
			return "The visibility is at least 2 miles.";
		}
	}
	
	String obtainWeather() {
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
						" miles away, is closed. Recommendation: Stop the train immediately & wait for the gate to open.";
			}
		}
	}
	
}
