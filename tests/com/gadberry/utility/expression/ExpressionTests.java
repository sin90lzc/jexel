package com.gadberry.utility.expression;

import junit.framework.TestCase;

/**
 * @author Aaron Gadberry
 * 
 */
public class ExpressionTests extends TestCase {

	public void testConstructor() {
		try {
			assertEquals(new Expression("max( ( 1 + 2 ), 2 + 3 ) + 1",
					new MockResolver()).evaluate(), new Argument(new Double(6),
					null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}

		try {
			assertEquals(new Expression("max( ( 1 + 2 ), 2 + 3 ) + 1",
					OperatorSet.getStandardOperatorSet()).evaluate(),
					new Argument(new Double(6), null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testEvaluate() {
		try {
			assertEquals(new Expression("max( ( 1 + 2 ), 2 + 3 ) + 1")
					.evaluate(), new Argument(new Double(6), null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testStaticEvaluate() {
		try {
			assertEquals(Expression.evaluate("max( ( 1 + 2 ), 2 + 3 ) + 1"),
					new Argument(new Double(6), null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testEvaluateInvalidExpression() {
		try {
			assertEquals(Expression.evaluate("max( ( 1 + 2 , 2 + 3 )"),
					new Argument(new Double(5), null));
			fail();
		} catch (InvalidExpressionException e) {
		}
	}

	public void testEvaluateExpressionWithInvalidArguments() {
		try {
			assertEquals(Expression.evaluate("max( 1, a, 2 )"), new Argument(
					"max( 1, a, 2 )", null));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testEvaluateNonExpression() {
		try {
			assertEquals(Expression.evaluate("1"), new Argument(new Double(1),
					null));

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