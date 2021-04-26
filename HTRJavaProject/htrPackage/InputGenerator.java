/**
 * 
 */
package htrPackage;

/**
 * @author Ankit
 *
 */
public class InputGenerator {
	
	/**
	 * 
	 * @param time - duration at which you travel at that speed.
	 * @param speed in miles per hour.
	 * @return the distance traveled in miles.
	 */
	public static double computeDistTraveled(final int time, double speed) {
//		System.out.println("time : "+ time);
//		System.out.println("speed given : "+ speed);
		return ((double) time/3600)*speed;
	}
	
	public static double computeRpmSpeed(int rpm, int WD) {
		return rpm*WD*Math.PI*60/63360;
		
	}
	
	public static int computeRpm(double speed, int WD) {
		return (int) ((63360*speed)/((double)WD*60*Math.PI));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int timer = 0;			/* Keeps track of the elapsed time. */
		final int update_time = 15; /* How often new measurements are taken. */
		final int wd = 85; // wheel diameter
		final double weight = 0.30; /* weight % of the distance traveled contributes to the latitude. */

		// double lat_old = 0.0;
		// double long_old = 0.0;
		// double lat_new = 0.0;
		// double long_new = 0.0;
		
		double lat = 0.0;
		double lon = 0.0;
		double delta_lat = 0.0;
		double delta_lon = 0.0;
		
		int rpm = 0;
//		double speed_from_rpm = 0.0;
		double dist_traveled = 0.0; //stores the dist traveled in 
									//past 15 seconds (in miles).
		
//		int[] rpm_data = {534, 554, 90}; /* Enter the the rpm every 15 seconds. */
		
		/*****
		 * FILL THIS ARRAY WITH THE SPEEDS AT EVERY 15 SECOND INTERVAL. THIS PROGRAM WILL
		 * RETURN THE COORDINATES (LATITUDE & LONGITUDE) AT EVERY 15 SECOND INTERVAL.
		 ***********/
//		double[] speeds = {135, 140, 150}; /* Stores the speed at every 15 second interval */
		double[] speeds = {135, 140, 100, 100, 138, 134, 113, 110, 143,
				146,55, 0, 0, 135,138, 108, 104, 129, 134, 53, 0, 0, 124,
				146};
		
		for ( int i = 0; i < speeds.length; i++) {
			timer += 15;
//			rpm = rpm_data[i];
//			speed_from_rpm = computeRpmSpeed(rpm, wd);
			
			rpm = computeRpm(speeds[i], wd);
//			System.out.println(speed_from_rpm);
			
			dist_traveled = computeDistTraveled(update_time, speeds[i]);
//			System.out.println(dist_traveled);
			
			//update the coordinates

			delta_lat = (weight * dist_traveled);
			delta_lon = Math.sqrt(Math.pow(dist_traveled, 2.0) - 
			Math.pow(weight *dist_traveled, 2.0));

			lat += delta_lat;
			lon += delta_lon;

			delta_lat = 0;
			delta_lon = 0;

			// lat_new = lat_old + (weight * dist_traveled);
			// long_new = long_old + Math.sqrt(Math.pow(dist_traveled, 2.0) - 
			// 		Math.pow(weight *dist_traveled, 2.0));
			// lat_old = lat_new;
			// long_old = lat_new;
			//print the updated speed and the updated coordinates.
			System.out.println("Time: " + timer + " seconds. Distance Traveled: " + dist_traveled
					+ ". New Speed: " + speeds[i] +  ". New long: " + lon + 
					". New lat: " + lat + ". RPM: " + rpm);
			
		}
		
		
	}

}
