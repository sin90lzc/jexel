package com.gadberry.utility.expression.operator;

import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidExpressionException;

import junit.framework.TestCase;

public class CeilOperatorTests extends TestCase {
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
}
