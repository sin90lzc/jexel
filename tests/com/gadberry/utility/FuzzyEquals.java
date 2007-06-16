package com.gadberry.utility;

public class FuzzyEquals {
	
	public static double TOLERANCE = .0001;
	
	/**
	 * Overridden equals for doubles that takes a tolerance into account when
	 * determining equality.
	 */
	public static boolean equals(double d1, double d2) {
		if (d1 == d2) {
			return true;
		} else if (Math.abs(d2 - d1) < TOLERANCE) {
			return true;
		}
		return false;
	}

}
