package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.FuzzyEquals;
import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.function.MinFunction;

import junit.framework.TestCase;

public class MinFunctionTests extends TestCase {

	private MinFunction op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new MinFunction();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic min
	 * 
	 * Test: 8, 3, 7
	 */
	public void testResolve1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Integer(8), null));
		args.add(new Argument(new Integer(3), null));
		args.add(new Argument(new Integer(7), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 3d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks a negative number
	 * 
	 * Test: -8, 2, 7
	 */
	public void testResolve2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(-8), null));
		args.add(new Argument(new Float(2), null));
		args.add(new Argument(new Float(7), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), -8d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks floating point min with one floating point
	 * 
	 * Test: 5, 2.5, 7
	 */
	public void testResolve3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(5), null));
		args.add(new Argument(new Float(2.5), null));
		args.add(new Argument(new Float(7), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 2.5d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * This checks a large number and a number < 1
	 * 
	 * Test: 1111, 0.27
	 */
	public void testResolve5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1111), null));
		args.add(new Argument(new Float(0.27), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 0.27d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks a positive small number and a negetive small number
	 * 
	 * Test: 0.15, -0.11
	 */
	public void testResolve6() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(0.15), null));
		args.add(new Argument(new Float(-0.11), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), -0.11d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This check one argument. Should throw an exception for no second
	 * argument.
	 * 
	 * Argument 1: 1
	 */
	public void testCheckArgs1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This check standard arguments. Should not throw an exception.
	 * 
	 * Argument 1: 1
	 * 
	 * Argument 2: 1
	 */
	public void testCheckArgs2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		args.add(new Argument(new Float(1), null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * This checks three arguments. Should not throw an exception.
	 * 
	 * Argument 1: 1
	 * 
	 * Argument 2: 1
	 * 
	 * Argument 3: 1
	 */
	public void testCheckArgs3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		args.add(new Argument(new Float(1), null));
		args.add(new Argument(new Integer(1), null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	/**
	 * This checks non-double arguments. Should throw an exception for a
	 * non-double argument.
	 * 
	 * Argument 1: 1
	 * 
	 * Argument 2: abc
	 */
	public void testCheckArgs4() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		args.add(new Argument("abc", null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This checks non-double arguments. Should throw an exception for a
	 * non-double argument.
	 * 
	 * Same as the previous test but the arguments are in reverse order.
	 * 
	 * Argument 1: abc
	 * 
	 * Argument 2: 1
	 */
	public void testCheckArgs5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abc", null));
		args.add(new Argument(new Double(1), null));
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
