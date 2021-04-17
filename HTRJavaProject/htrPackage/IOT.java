package htrPackage;

public class IOT extends Sensors {
	
	//data fields:
	//
	
	void writeToLog() {
		//some file io method – write/append into a file
	}
	
	String obtainWeather() {
		//print the weather report.
		return "";
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

	double obtainTimeToImpact() {
		//distance b/w train and obstacle.
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
	
	
	boolean detectSlippage() {
		//calculate slippage. return true if slippage. false if none.
		return false;
	}
	
	double computeImpact() {
		//returns time to impact.
		return 0.0;
	}
	
	boolean gateStatus() {
		//true if gate is open, false if closed.
		return false;
	}
	
}
