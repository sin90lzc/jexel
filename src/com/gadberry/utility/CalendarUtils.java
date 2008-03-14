package com.gadberry.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarUtils {

	public enum Unit {
		DAY(Calendar.DAY_OF_MONTH, 1000l * 60 * 60 * 24), HOUR(
				Calendar.HOUR_OF_DAY, 1000l * 60 * 60), MILLISECOND(
				Calendar.MILLISECOND, 1), MINUTE(Calendar.MINUTE, 1000l * 60), MONTH(
				Calendar.MONTH, 1000l * 60 * 60 * 24 * 30), SECOND(
				Calendar.SECOND, 1000l), YEAR(Calendar.YEAR, 1000l * 60 * 60
				* 24 * 365);

		private final int calendarUnit;
		private final long estimate;

		Unit(int calendarUnit, long estimate) {
			this.calendarUnit = calendarUnit;
			this.estimate = estimate;
		}
	}

	public static void add(Calendar c, int unit, long increment) {
		while (increment > Integer.MAX_VALUE) {
			c.add(unit, Integer.MAX_VALUE);
			increment -= Integer.MAX_VALUE;
		}
		c.add(unit, (int) increment);
	}

	public static long difference(Calendar c1, Calendar c2, Unit unit) {

		Calendar first = (Calendar) c1.clone();
		Calendar last = (Calendar) c2.clone();

		long difference = c2.getTimeInMillis() - c1.getTimeInMillis();

		long increment = (long) Math.floor((double) difference
				/ (double) unit.estimate);
		increment = Math.max(increment, 1);

		long total = 0;

		while (increment > 0) {
			add(first, unit.calendarUnit, increment);
			if (first.after(last)) {
				add(first, unit.calendarUnit, increment * -1);
				increment = (long) Math.floor(increment / 2);
			} else {
				total += increment;
			}
		}

		return total;
	}

	public static long difference(Date d1, Date d2, Unit unit) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		return difference(c1, c2, unit);
	}

	public static double exactDifference(Calendar c1, Calendar c2, Unit unit) {
		long unitDifference = difference(c1, c2, unit);
		Calendar mid = (Calendar) c1.clone();
		CalendarUtils.add(mid, unit.calendarUnit, unitDifference);

		Calendar end = (Calendar) mid.clone();
		end.add(unit.calendarUnit, 1);

		long millisPassed = CalendarUtils.difference(mid, c2, Unit.MILLISECOND);
		long millisTotal = CalendarUtils.difference(mid, end, Unit.MILLISECOND);

		double remainder = (double) millisPassed / (double) millisTotal;

		return unitDifference + remainder;
	}
	
	public static Map<Unit, Long> tieredDifference(Calendar c1, Calendar c2) {
		return tieredDifference(c1, c2,Arrays.asList(Unit.values()));		
	}

	public static Map<Unit, Long> tieredDifference(Calendar c1, Calendar c2, List<Unit> units) {
		Calendar first = (Calendar) c1.clone();
		Calendar last = (Calendar) c2.clone();
		
		Map<Unit, Long> differences = new HashMap<Unit, Long>();
		
		List<Unit> allUnits = new ArrayList<Unit>();
		allUnits.add(Unit.YEAR);
		allUnits.add(Unit.MONTH);
		allUnits.add(Unit.DAY);
		allUnits.add(Unit.HOUR);
		allUnits.add(Unit.MINUTE);
		allUnits.add(Unit.SECOND);
		allUnits.add(Unit.MILLISECOND);
		
		for(Unit unit : allUnits){
			if(units.contains(unit)){
				long difference = difference(first, last, unit);
				differences.put(unit, difference);
				CalendarUtils.add(first, unit.calendarUnit, difference);
			}
		}
		
		return differences;
	}

}
