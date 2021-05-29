package com.healthycoderapp;

public class ActivityCalculator {
	
	private static final int WORKOUT_DURATION_MIN = 45;
	
	public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSessions) {
		
		int totalMinutes = weeklyCardioMin + weeklyWorkoutSessions * WORKOUT_DURATION_MIN;
		double avgDailyActivityMins = totalMinutes / 7.0;
		
		if (avgDailyActivityMins < 20) {
			return "bad";
		}
		if (avgDailyActivityMins < 40) {
			return "average";
		}
		
		return "";
	}

}
