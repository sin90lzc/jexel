package com.gadberry.utility.expression.operator;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.FuzzyEquals;
import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;

import junit.framework.TestCase;

public class AdditionOperatorTests extends TestCase {

	private AdditionOperator op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new AdditionOperator();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic addition
	 * 
	 * Test: 1 + 2
	 */
	public void testResolve1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Integer(1), null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 3d);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks floating point addition
	 * 
	 * Test: 1.11 + 2.22
	 */
	public void testResolve2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1.11), null));
		args.add(new Argument(new Float(2.22), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 3.33d);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks floating point addition with one floating point
	 * 
	 * Test: 1001 + 2.22
	 */
	public void testResolve3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1001), null));
		args.add(new Argument(new Float(2.22), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 1003.22d);
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
	 * Test: 1111 + 0.11
	 */
	public void testResolve4() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1111), null));
		args.add(new Argument(new Float(0.11), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 1111.11);
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
	 * Test: 0.15 + -0.11
	 */
	public void testResolve5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(0.15), null));
		args.add(new Argument(new Float(-0.11), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 0.04);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This check standard arguments. Should not throw an exception.
	 * 
	 * Argument 1: 1
	 * 
	 * Argument 2: 1
	 */
	public void testCheckArgs1() {
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
	 * This check one argument. Should throw an exception for no second
	 * argument.
	 * 
	 * Argument 1: 1
	 */
	public void testCheckArgs2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This checks three arguments. Should throw an exception for a third
	 * argument.
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
			fail();
		} catch (InvalidArgumentsException e) {
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
		assertEquals(op.getPriority(), 0);
	}
	
	/**
	 * Verify the type
	 */
	public void testGetType() {
		assertEquals(op.getType(), Operator.STANDARD);
	}

	/**
	 * Overridden equals for doubles that takes a tolerance into account when
	 * determining equality.
	 */
	public boolean assertEquals(double d1, double d2) {
		return FuzzyEquals.equals(d1, d2);
	}
}
