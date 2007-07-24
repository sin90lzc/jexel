package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

import junit.framework.TestCase;

public class OperatorTests extends TestCase {

	public static double TOLERANCE = .0001;

	private Operator op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new MockOperator();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}
	
	/**
	 * Testing set / get arguments
	 *
	 */
	public void testSetArguments() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Integer(1), null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.getArguments(), args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing argument resolution and getArgument(int)
	 *
	 */
	public void testResolveArguments() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("1 + 1", null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.getArgument(0), new Argument(new Double(2), null));
			assertEquals(op.getArgument(1), new Argument(new Double(2), null));
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
}
