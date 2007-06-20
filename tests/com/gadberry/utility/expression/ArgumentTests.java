package com.gadberry.utility.expression;

import com.gadberry.utility.FuzzyEquals;

import junit.framework.TestCase;

public class ArgumentTests extends TestCase {

	private Argument arg = null;

	/**
	 * Testing constructor
	 * and getObject
	 */
	public void testConstructor() {
		Object o = new Integer(0);
		arg = new Argument(o, new MockResolver());
		assertNotNull(arg);

		assertEquals(arg.getObject(), o);
	}

	public void testIsDouble() {
		arg = new Argument(new Integer(0), new MockResolver());
		assertTrue(arg.isDouble());

		arg = new Argument(new Float(1.1), new MockResolver());
		assertTrue(arg.isDouble());

		arg = new Argument(new Double(1.2), new MockResolver());
		assertTrue(arg.isDouble());

		arg = new Argument("abc", new MockResolver());
		assertFalse(arg.isDouble());
	}

	public void testToDouble() {
		arg = new Argument(new Integer(0), new MockResolver());
		try {
			assertEquals(arg.toDouble(), 0d, FuzzyEquals.TOLERANCE);
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}

		arg = new Argument(new Float(1.1), new MockResolver());
		try {
			assertEquals(arg.toDouble(), 1.1d, FuzzyEquals.TOLERANCE);
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}

		arg = new Argument(new Double(1.2), new MockResolver());
		try {
			assertEquals(arg.toDouble(), 1.2d, FuzzyEquals.TOLERANCE);
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}

		arg = new Argument("abc", new MockResolver());
		try {
			arg.toDouble();
			fail();
		} catch (ArgumentCastException e) {
		}
	}

	public void testToString() {
		arg = new Argument(new Integer(0), new MockResolver());
		assertEquals(arg.toString(), "0");

		arg = new Argument(new Double(1.2), new MockResolver());
		assertEquals(arg.toString(), "1.2");

		arg = new Argument("abc", new MockResolver());
		assertEquals(arg.toString(), "abc");
	}

	public void testEquals() {
		Argument arg2;

		arg = new Argument(new Integer(0), new MockResolver());
		arg2 = new Argument(new Integer(0), new MockResolver());
		assertTrue(arg.equals(arg2));

		arg = new Argument(new Double(1.2), new MockResolver());
		arg2 = new Argument(new Double(1.2), new MockResolver());
		assertTrue(arg.equals(arg2));
		
		arg = new Argument(new Double(1.3), new MockResolver());
		arg2 = new Argument(new Double(1.2), new MockResolver());
		assertFalse(arg.equals(arg2));

		arg = new Argument("abc", new MockResolver());
		arg2 = new Argument("abc", new MockResolver());
		assertTrue(arg.equals(arg2));
		
		arg = new Argument("abc", new MockResolver());
		arg2 = new Argument("def", new MockResolver());
		assertFalse(arg.equals(arg2));
	}
}
