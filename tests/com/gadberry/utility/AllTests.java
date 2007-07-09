package com.gadberry.utility;

import com.gadberry.utility.expression.ArgumentTests;
import com.gadberry.utility.expression.ExpressionTests;
import com.gadberry.utility.expression.operator.AbsOperatorTests;
import com.gadberry.utility.expression.operator.AcosOperatorTests;
import com.gadberry.utility.expression.operator.AdditionOperatorTests;
import com.gadberry.utility.expression.operator.AndOperatorTests;
import com.gadberry.utility.expression.operator.AsinOperatorTests;
import com.gadberry.utility.expression.operator.AtanOperatorTests;
import com.gadberry.utility.expression.operator.CeilOperatorTests;
import com.gadberry.utility.expression.operator.CosOperatorTests;
import com.gadberry.utility.expression.operator.DateDifferenceOperatorTests;
import com.gadberry.utility.expression.operator.DivisionOperatorTests;
import com.gadberry.utility.expression.operator.FloorOperatorTests;
import com.gadberry.utility.expression.operator.MaxOperatorTests;
import com.gadberry.utility.expression.operator.MinOperatorTests;
import com.gadberry.utility.expression.operator.ModuloOperatorTests;
import com.gadberry.utility.expression.operator.MultiplicationOperatorTests;
import com.gadberry.utility.expression.operator.NegOperatorTests;
import com.gadberry.utility.expression.operator.NotOperatorTests;
import com.gadberry.utility.expression.operator.OperatorSetTests;
import com.gadberry.utility.expression.operator.OperatorTests;
import com.gadberry.utility.expression.operator.OrOperatorTests;
import com.gadberry.utility.expression.operator.SinOperatorTests;
import com.gadberry.utility.expression.operator.SubstrOperatorTests;
import com.gadberry.utility.expression.operator.SubtractionOperatorTests;
import com.gadberry.utility.expression.operator.TanOperatorTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for com.gadberry.utility.expression");

		// Utils Tests
		suite.addTestSuite(CalendarUtilsTests.class);

		// Expression Package Tests
		suite.addTestSuite(ArgumentTests.class);
		suite.addTestSuite(OperatorTests.class);
		suite.addTestSuite(OperatorSetTests.class);
		suite.addTestSuite(ExpressionTests.class);

		// Math Operators

		// Standard Operators
		suite.addTestSuite(AdditionOperatorTests.class);
		suite.addTestSuite(SubtractionOperatorTests.class);
		suite.addTestSuite(MultiplicationOperatorTests.class);
		suite.addTestSuite(DivisionOperatorTests.class);
		suite.addTestSuite(ModuloOperatorTests.class);

		// Function Operators
		suite.addTestSuite(MaxOperatorTests.class);
		suite.addTestSuite(MinOperatorTests.class);
		suite.addTestSuite(CeilOperatorTests.class);
		suite.addTestSuite(FloorOperatorTests.class);
		suite.addTestSuite(NegOperatorTests.class);
		suite.addTestSuite(AbsOperatorTests.class);

		// Trig
		suite.addTestSuite(CosOperatorTests.class);
		suite.addTestSuite(SinOperatorTests.class);
		suite.addTestSuite(TanOperatorTests.class);
		suite.addTestSuite(AcosOperatorTests.class);
		suite.addTestSuite(AsinOperatorTests.class);
		suite.addTestSuite(AtanOperatorTests.class);

		// Boolean Operators
		suite.addTestSuite(AndOperatorTests.class);
		suite.addTestSuite(OrOperatorTests.class);
		suite.addTestSuite(NotOperatorTests.class);

		// String Operators
		suite.addTestSuite(SubstrOperatorTests.class);

		// Date Operators
		suite.addTestSuite(DateDifferenceOperatorTests.class);

		return suite;
	}

}
