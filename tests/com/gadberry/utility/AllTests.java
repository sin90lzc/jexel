package com.gadberry.utility;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.gadberry.utility.expression.ArgumentTests;
import com.gadberry.utility.expression.ExpressionTests;
import com.gadberry.utility.expression.OperatorSetTests;
import com.gadberry.utility.expression.OperatorImplTests;
import com.gadberry.utility.expression.function.AbsFunctionTests;
import com.gadberry.utility.expression.function.AcosFunctionTests;
import com.gadberry.utility.expression.function.AsinFunctionTests;
import com.gadberry.utility.expression.function.AtanFunctionTests;
import com.gadberry.utility.expression.function.CeilFunctionTests;
import com.gadberry.utility.expression.function.CosFunctionTests;
import com.gadberry.utility.expression.function.DateDifferenceFunctionTests;
import com.gadberry.utility.expression.function.FloorFunctionTests;
import com.gadberry.utility.expression.function.MaxFunctionTests;
import com.gadberry.utility.expression.function.MinFunctionTests;
import com.gadberry.utility.expression.function.NegFunctionTests;
import com.gadberry.utility.expression.function.NotFunctionTests;
import com.gadberry.utility.expression.function.SinFunctionTests;
import com.gadberry.utility.expression.function.SubstrFunctionTests;
import com.gadberry.utility.expression.function.TanFunctionTests;
import com.gadberry.utility.expression.symbol.AdditionSymbolTests;
import com.gadberry.utility.expression.symbol.AndSymbolTests;
import com.gadberry.utility.expression.symbol.DivisionSymbolTests;
import com.gadberry.utility.expression.symbol.ModuloSymbolTests;
import com.gadberry.utility.expression.symbol.MultiplicationSymbolTests;
import com.gadberry.utility.expression.symbol.OrSymbolTests;
import com.gadberry.utility.expression.symbol.SubtractionSymbolTests;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for com.gadberry.utility.expression");

		// Utils Tests
		suite.addTestSuite(CalendarUtilsTests.class);

		// Expression Package Tests
		suite.addTestSuite(ArgumentTests.class);
		suite.addTestSuite(OperatorImplTests.class);
		suite.addTestSuite(OperatorSetTests.class);
		suite.addTestSuite(ExpressionTests.class);

		// Math Operators

		// Standard Operators
		suite.addTestSuite(AdditionSymbolTests.class);
		suite.addTestSuite(SubtractionSymbolTests.class);
		suite.addTestSuite(MultiplicationSymbolTests.class);
		suite.addTestSuite(DivisionSymbolTests.class);
		suite.addTestSuite(ModuloSymbolTests.class);

		// Function Operators
		suite.addTestSuite(MaxFunctionTests.class);
		suite.addTestSuite(MinFunctionTests.class);
		suite.addTestSuite(CeilFunctionTests.class);
		suite.addTestSuite(FloorFunctionTests.class);
		suite.addTestSuite(NegFunctionTests.class);
		suite.addTestSuite(AbsFunctionTests.class);

		// Trig
		suite.addTestSuite(CosFunctionTests.class);
		suite.addTestSuite(SinFunctionTests.class);
		suite.addTestSuite(TanFunctionTests.class);
		suite.addTestSuite(AcosFunctionTests.class);
		suite.addTestSuite(AsinFunctionTests.class);
		suite.addTestSuite(AtanFunctionTests.class);

		// Boolean Operators
		suite.addTestSuite(AndSymbolTests.class);
		suite.addTestSuite(OrSymbolTests.class);
		suite.addTestSuite(NotFunctionTests.class);

		// String Operators
		suite.addTestSuite(SubstrFunctionTests.class);

		// Date Operators
		suite.addTestSuite(DateDifferenceFunctionTests.class);

		return suite;
	}

}
