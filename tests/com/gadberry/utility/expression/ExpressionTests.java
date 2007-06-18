package com.gadberry.utility.expression;

import junit.framework.TestCase;

/**
 * @author Aaron Gadberry
 * 
 */
public class ExpressionTests extends TestCase {

	public void testEvaluate() {
		try {
			assertEquals(Expression.evaluate("max( ( 1 + 2 ), 2 + 3 )"),
					new Argument(new Double(5), null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testEvaluateToDouble() {
		try {
			assertEquals(
					Expression.evaluateToDouble("max( ( 1 + 2 ), 2 + 3 )"), 5d);
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testParenthesees() {
		try {
			assertEquals(
					Expression.evaluateToDouble("max( ( 1 + 2 ), 2 + 3 )"),
					new Double(5));
			assertEquals(Expression.evaluateToDouble("min(max(1,2),2 + 3)"),
					new Double(2));
			assertEquals(Expression.evaluateToDouble("2 * ( 2 + 3 )"),
					new Double(10));
			assertEquals(Expression.evaluateToDouble("( 1 + 1 ) * ( 2 + 3 )"),
					new Double(10));

			assertEquals(Expression.evaluateToDouble("( 1 * 5 ) + ( 6 / 3 )"),
					new Double(7));

			assertEquals(Expression.evaluateToDouble("1 + 2 * 6 / 3"),
					new Double(5));

			assertEquals(Expression.evaluateToDouble("( 1 + 2) * 6 / 3"),
					new Double(6));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}
}