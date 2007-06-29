package com.gadberry.utility.expression.operator;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;

import junit.framework.TestCase;

public class SubstrOperatorTests extends TestCase {

	private SubstrOperator op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new SubstrOperator();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic substr
	 * 
	 * Test: substr( abcd, 2 )
	 */
	public void testResolve1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toString(), "cd");
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks substr with argument of 0
	 * 
	 * Test: substr( abcd, 0 )
	 */
	public void testResolve2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(0), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toString(), "abcd");
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks substr with two arguments of 0
	 * 
	 * Test: substr( abcd, 0, 0 )
	 */
	public void testResolve3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(0), null));
		args.add(new Argument(new Integer(0), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toString(), "");
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
	 * Argument 1: abcd
	 * 
	 * Argument 2: 1
	 */
	public void testCheckArgs1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(1), null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks three arguments. Should throw an exception for a third
	 * argument.
	 * 
	 * Argument 1: abcd
	 * 
	 * Argument 2: 1
	 * 
	 * Argument 3: 1
	 */
	public void testCheckArgs3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(1), null));
		args.add(new Argument(new Integer(1), null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks two string arguments. Should throw an exception for a
	 * non-integer second argument.
	 * 
	 * Argument 1: abcd
	 * 
	 * Argument 2: abcd
	 * 
	 * Argument 3: 1
	 */
	public void testCheckArgs4() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(1), null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}
	
	/**
	 * This checks two string arguments. Should throw an exception for a
	 * non-integer third argument.
	 * 
	 * Argument 1: abcd
	 * 
	 * Argument 2: 1
	 * 
	 * Argument 3: abcd
	 */
	public void testCheckArgs5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abcd", null));
		args.add(new Argument(new Integer(1), null));
		args.add(new Argument("abcd", null));
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
	public void testCheckArgs2() {
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

	/**
	 * Verify the type
	 */
	public void testGetType() {
		assertEquals(op.getType(), Operator.FUNCTION);
	}
}
