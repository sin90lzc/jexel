package com.gadberry.utility.expression;

import junit.framework.TestCase;

/**
 * @author Aaron Gadberry
 * 
 */
public class ExpressionTests extends TestCase {

	public void testAdditionOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("1+1"), new Double(2));
			assertEquals(Expression.evaluateToDouble("1 + 1 - 2"),
					new Double(0));
			assertEquals(Expression.evaluateToDouble("1000 + 1501"),
					new Double(2501));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testSubtractionOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("1- 1+ 2"), new Double(2));
			assertEquals(Expression.evaluateToDouble("1+ 1 -2"), new Double(0));
			assertEquals(Expression.evaluateToDouble("1000-1501"), new Double(
					-501));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testMultiplicationOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("1 * 1"), new Double(1));
			assertEquals(Expression.evaluateToDouble("1* 2"), new Double(2));
			assertEquals(Expression.evaluateToDouble("10*24"), new Double(240));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testDivisionOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("1      / 1"), new Double(
					1));
			assertEquals(Expression.evaluateToDouble("1 /2"), new Double(0.5));
			assertEquals(Expression.evaluateToDouble("24/4"), new Double(6));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testMaxOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("max(1, 2)"),
					new Double(2));
			assertEquals(Expression.evaluateToDouble("max(1,2)"), new Double(2));
			assertEquals(Expression.evaluateToDouble("max(1,2) - 5"),
					new Double(-3));
			assertEquals(Expression.evaluateToDouble("5 - max(1,2)+ 7"),
					new Double(10));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testMinOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("min(1, 2)"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("min(1,2)"), new Double(1));
			assertEquals(Expression.evaluateToDouble("min(1,2) - 5"),
					new Double(-4));
			assertEquals(Expression.evaluateToDouble("5 - min(1,2) + 7"),
					new Double(11));
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

	public void testFloorOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("floor(1.125)"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("floor(1.725)"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("floor(1.0)"), new Double(
					1));
			assertEquals(Expression.evaluateToDouble("floor(999.999)"),
					new Double(999));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}

	public void testCeilOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("ceil(1.125)"),
					new Double(2));
			assertEquals(Expression.evaluateToDouble("ceil(1.725)"),
					new Double(2));
			assertEquals(Expression.evaluateToDouble("ceil(1.0)"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("ceil(999.999)"),
					new Double(1000));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testModuloOperator() {
		try {
			assertEquals(Expression.evaluateToDouble("6 % 5"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("4 %1"),
					new Double(0));
			assertEquals(Expression.evaluateToDouble("19% 6"),
					new Double(1));
			assertEquals(Expression.evaluateToDouble("20%6"),
					new Double(2));
		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		} catch (ArgumentCastException e) {
			e.printStackTrace();
			fail();
		}
	}
}