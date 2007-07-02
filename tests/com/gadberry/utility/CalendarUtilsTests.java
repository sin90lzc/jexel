package com.gadberry.utility;

import java.util.Calendar;

import junit.framework.TestCase;

public class CalendarUtilsTests extends TestCase {

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceInvalidUnit() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		try {
			assertEquals(
					CalendarUtils.difference(c1, c2, Calendar.DAY_OF_WEEK), 1);
			fail();
		} catch (RuntimeException e) {
		}

		try {
			assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
					Calendar.DAY_OF_WEEK), 1);
			fail();
		} catch (RuntimeException e) {
		}

	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceYear1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2001);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.YEAR), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.YEAR), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceYear2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 11);
		c2.set(Calendar.DAY_OF_MONTH, 31);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.YEAR), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.YEAR), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.YEAR), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.YEAR), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceMonth1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 1);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MONTH), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MONTH), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceMonth2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 31);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MONTH), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MONTH), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MONTH), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MONTH), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceDay1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 2);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.DAY_OF_MONTH), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.DAY_OF_MONTH), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceDay2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 23);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.DAY_OF_MONTH), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.DAY_OF_MONTH), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.DAY_OF_MONTH), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.DAY_OF_MONTH), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceHour1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 1);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.HOUR_OF_DAY), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.HOUR_OF_DAY), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceHour2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 59);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.HOUR_OF_DAY), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.HOUR_OF_DAY), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.HOUR_OF_DAY), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.HOUR_OF_DAY), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceMinute1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 1);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MINUTE), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MINUTE), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceMinute2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 59);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MINUTE), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MINUTE), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MINUTE), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MINUTE), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceSecond1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 1);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.SECOND), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.SECOND), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceSecond2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 999);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.SECOND), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.SECOND), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.SECOND), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.SECOND), 1);
	}

	/**
	 * Test for exactly one unit difference between two calendars
	 */
	public void testDifferenceMillisecond1() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MILLISECOND), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MILLISECOND), 1);
	}

	/**
	 * Test for one millisecond short of exactly one unit difference between two
	 * calendars
	 */
	public void testDifferenceMillisecond2() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MILLISECOND), 0);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MILLISECOND), 0);

		c2.add(Calendar.MILLISECOND, 1);

		assertEquals(CalendarUtils.difference(c1, c2, Calendar.MILLISECOND), 1);
		assertEquals(CalendarUtils.difference(c1.getTime(), c2.getTime(),
				Calendar.MILLISECOND), 1);
	}

	public void testAdd() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, 2000);
		c1.set(Calendar.MONTH, 0);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, 2000);
		c2.set(Calendar.MONTH, 0);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		long increment = Integer.MAX_VALUE * 5l + 123456;

		c1.add(Calendar.MILLISECOND, Integer.MAX_VALUE);
		c1.add(Calendar.MILLISECOND, Integer.MAX_VALUE);
		c1.add(Calendar.MILLISECOND, Integer.MAX_VALUE);
		c1.add(Calendar.MILLISECOND, Integer.MAX_VALUE);
		c1.add(Calendar.MILLISECOND, Integer.MAX_VALUE);
		c1.add(Calendar.MILLISECOND, 123456);

		CalendarUtils.add(c2, Calendar.MILLISECOND, increment);

		assertEquals(c1, c2);
	}
}
