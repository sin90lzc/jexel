package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.function.NotFunction;

import junit.framework.TestCase;

public class NotFunctionTests extends TestCase {

	private NotFunction op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new NotFunction();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks one iteration of basic not
	 * 
	 * Test: true
	 */
	public void testResolve1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Boolean(true), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve().toBoolean(), false);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This checks one iteration of basic not
	 * 
	 * Test: false
	 */
	public void testResolve2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Boolean(false), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve().toBoolean(), true);
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
	 * Argument 1: boolean

	 */
	public void testCheckArgs1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Boolean(true), null));
		try {
			op.setArguments(args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * This check two arguments. Should throw an exception for a second
	 * argument.
	 * 
	 * Argument 1: boolean
	 * 
	 * Argument 2: boolean
	 */
	public void testCheckArgs2() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Boolean(false), null));
		args.add(new Argument(new Boolean(false), null));
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
	 * Argument 1: boolean
	 * 
	 * Argument 2: boolean
	 * 
	 * Argument 3: boolean
	 */
	public void testCheckArgs3() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Boolean(false), null));
		args.add(new Argument(new Boolean(false), null));
		args.add(new Argument(new Boolean(false), null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This checks non-boolean arguments. Should throw an exception for a
	 * non-boolean argument.
	 * 
	 * Argument 1: abc
	 */
	public void testCheckArgs4() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("abc", null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}

	/**
	 * This checks non-boolean arguments. Should throw an exception for a
	 * non-boolean argument.
	 * 
	 * Argument 2: 1
	 */
	public void testCheckArgs5() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Double(1), null));
		try {
			op.setArguments(args);
			fail();
		} catch (InvalidArgumentsException e) {
		}
	}
}
