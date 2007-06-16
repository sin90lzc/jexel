package com.gadberry.utility.expression;

import com.gadberry.utility.expression.operator.AdditionOperatorTests;
import com.gadberry.utility.expression.operator.CeilOperatorTests;
import com.gadberry.utility.expression.operator.DivisionOperatorTests;
import com.gadberry.utility.expression.operator.FloorOperatorTests;
import com.gadberry.utility.expression.operator.MaxOperatorTests;
import com.gadberry.utility.expression.operator.MinOperatorTests;
import com.gadberry.utility.expression.operator.ModuloOperatorTests;
import com.gadberry.utility.expression.operator.MultiplicationOperatorTests;
import com.gadberry.utility.expression.operator.OperatorSetTests;
import com.gadberry.utility.expression.operator.OperatorTests;
import com.gadberry.utility.expression.operator.SubtractionOperatorTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for com.gadberry.utility.expression");
		
		// Expression Package Tests
		suite.addTestSuite(ArgumentTests.class);
		suite.addTestSuite(OperatorTests.class);
		suite.addTestSuite(OperatorSetTests.class);
		
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
		
		// suite.addTestSuite(ExpressionTests.class);
		return suite;
	}

}
