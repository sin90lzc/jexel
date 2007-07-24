package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.FuzzyEquals;
import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.function.Function;

import junit.framework.TestCase;

public class FunctionTests extends TestCase {

	private Function op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new MockFunction();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic argument resolution
	 * 
	 * Test: abs( 1 )
	 */
	public void testResolve1() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Integer(1), null));
		try {
			op.setArguments(args);
			assertEquals(op.resolve(null).toDouble(), 1d, FuzzyEquals.TOLERANCE);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Verify the priority
	 */
	public void testGetPriority() {
		assertEquals(op.getPriority(), 20);
	}
}
