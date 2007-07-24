package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gadberry.utility.CalendarUtils;
import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.function.DateDifferenceFunction;

import junit.framework.TestCase;

public class DateDifferenceFunctionTests extends TestCase {

	private DateDifferenceFunction op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new DateDifferenceFunction();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic date difference
	 * 
	 * Test: dateDifference( c1, c2, month )
	 */
	public void testResolve1() {
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
		c2.set(Calendar.DAY_OF_MONTH, 31);
		c2.set(Calendar.HOUR_OF_DAY, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);

		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(c1, null));
		args.add(new Argument(c2, null));
		args.add(new Argument(Calendar.MONTH, null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), (double) CalendarUtils
					.difference(c1, c2, Calendar.MONTH));
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks large date difference
	 * 
	 * Test: dateDifference( c1, c2, millisecond )
	 */
	public void testResolve2() {
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

		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(c1, null));
		args.add(new Argument(c2, null));
		args.add(new Argument(Calendar.MILLISECOND, null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), (double) CalendarUtils
					.difference(c1, c2, Calendar.MILLISECOND));
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks standard calendar arguments. Should not throw an exception.
	 * 
	 * Argument 1: Calendar
	 * 
	 * Argument 2: Calendar
	 * 
	 * Argument 3: Calendar.YEAR
	 */
	public void testCheckArgs1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(Calendar.YEAR, null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks standard date arguments. Should not throw an exception.
	 * 
	 * Argument 1: Date
	 * 
	 * Argument 2: Date
	 * 
	 * Argument 3: Calendar.YEAR
	 */
	public void testCheckArgs2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Date(), null));
		args.add(new Argument(new Date(), null));
		args.add(new Argument(Calendar.YEAR, null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks a mix of a calendar and date arguments. Should not throw an
	 * exception.
	 * 
	 * Argument 1: Date
	 * 
	 * Argument 2: Date
	 * 
	 * Argument 3: Calendar.YEAR
	 */
	public void testCheckArgs3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(new Date(), null));
		args.add(new Argument(Calendar.YEAR, null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks an invalid unit argument. Should throw an exception.
	 * 
	 * Argument 1: Calendar
	 * 
	 * Argument 2: Calendar
	 * 
	 * Argument 3: Calendar.DAY_OF_WEEK
	 */
	public void testCheckArgs4() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(Calendar.DAY_OF_WEEK, null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This checks an invalid string argument. Should throw an exception.
	 * 
	 * Argument 1: String
	 * 
	 * Argument 2: Date
	 * 
	 * Argument 3: Calendar.DAY_OF_WEEK
	 */
	public void testCheckArgs5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(Calendar.getInstance(), null));
		args.add(new Argument(Calendar.YEAR, null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This check one argument. Should throw an exception for no second
	 * argument.
	 * 
	 * Argument 1: abcd
	 */

	public void testCheckArgs6() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * Verify the priority
	 */

	public void testGetPriority() {
		assertEquals(op.getPriority(), 20);
	}

}
