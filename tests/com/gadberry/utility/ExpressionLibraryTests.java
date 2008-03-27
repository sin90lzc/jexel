package com.gadberry.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidExpressionException;
import com.gadberry.utility.expression.SimpleResolver;

public class ExpressionLibraryTests {

	@Test
	public void testOperators() {
		try {
			assertEquals(Expression.evaluate("1 + 1").toDouble(), 2d);
			assertEquals(Expression.evaluate("1 - 1").toDouble(), 0d);
			assertEquals(Expression.evaluate("1 * 2").toDouble(), 2d);
			assertEquals(Expression.evaluate("6 / 3").toDouble(), 2d);
			assertEquals(Expression.evaluate("6 % 4").toDouble(), 2d);

			assertEquals(Expression.evaluate("true && true").toBoolean(), true);
			assertEquals(Expression.evaluate("true AND false").toBoolean(), false);
			assertEquals(Expression.evaluate("true || true").toBoolean(), true);
			assertEquals(Expression.evaluate("true OR false").toBoolean(), true);
			assertEquals(Expression.evaluate("not(true)").toBoolean(), false);

			assertEquals(Expression.evaluate("max(2,3,6)").toDouble(), 6d);
			assertEquals(Expression.evaluate("min(2,3,6)").toDouble(), 2d);
			assertEquals(Expression.evaluate("floor(2.2)").toDouble(), 2d);
			assertEquals(Expression.evaluate("ceil(3.6)").toDouble(), 4d);
			assertEquals(Expression.evaluate("neg(3)").toDouble(), -3d);
			assertEquals(Expression.evaluate("abs(neg(3))").toDouble(), 3d);
			assertEquals(Expression.evaluate("cos(rad(180))").toDouble(), Math.cos(Math.toRadians(180)));
			assertEquals(Expression.evaluate("sin(rad(90))").toDouble(), Math.sin(Math.toRadians(90)));
			assertEquals(Expression.evaluate("tan(rad(45))").toDouble(), Math.tan(Math.toRadians(45)));
			assertEquals(Expression.evaluate("acos(0)").toDouble(), Math.acos(0));
			assertEquals(Expression.evaluate("asin(180)").toDouble(), Math.asin(180));
			assertEquals(Expression.evaluate("atan(1)").toDouble(), Math.atan(1));

			assertEquals(Expression.evaluate("1 == 1").toBoolean(), true);
			assertEquals(Expression.evaluate("1 > 0").toBoolean(), true);
			assertEquals(Expression.evaluate("1 < 2").toBoolean(), true);
			assertEquals(Expression.evaluate("1 <= 2").toBoolean(), true);

			assertEquals(Expression.evaluate("1 >= 1").toBoolean(), true);

			assertEquals(Expression.evaluate("substr(abcd,1,2)").toString(), "b");

			Expression exp = new Expression("dateDifference(01/01/2006, 01/05/2006, |DAY|)");
			exp.setResolver(new SimpleResolver());
			assertEquals(exp.evaluate().toDouble(), 4d);

		} catch (InvalidExpressionException e) {
			e.printStackTrace();
			fail();
		}
	}
}
