package com.gadberry.utility.expression;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gadberry.utility.expression.symbol.AdditionSymbol;
import com.gadberry.utility.expression.symbol.SubtractionSymbol;

public class OperatorSetTests {

	private OperatorSet opSet = null;

	@Before
	public void setUp() throws Exception {
		opSet = OperatorSet.getStandardOperatorSet();
	}

	@After
	public void tearDown() throws Exception {
		opSet = null;
	}

	@Test
	public void testFindOperator() {
		Operator op = null;

		op = opSet.findOperator("+");
		if (!(op instanceof AdditionSymbol)) {
			fail();
		}

		op = opSet.findOperator("+a");
		if (!(op instanceof AdditionSymbol)) {
			fail();
		}

		op = opSet.findOperator("aaaaaaaa");
		if (!(op == null)) {
			fail();
		}
	}

	@Test
	public void testAddOperator() {
		Operator op = null;

		op = opSet.findOperator("+");
		if (!(op instanceof AdditionSymbol)) {
			fail();
		}

		opSet = new OperatorSet();

		op = opSet.findOperator("+");
		if (!(op == null)) {
			fail();
		}

		opSet.addOperator("+", new AdditionSymbol());
		op = opSet.findOperator("+");
		if (!(op instanceof AdditionSymbol)) {
			fail();
		}
	}

	@Test
	public void testGetSymbols() {
		opSet = new OperatorSet();
		opSet.addOperator("+", new AdditionSymbol());
		opSet.addOperator("-", new SubtractionSymbol());

		List<String> symbols = opSet.getDelimeters();
		if (!symbols.contains("+")) {
			fail();
		} else if (!symbols.contains("-")) {
			fail();
		} else if (symbols.contains("*")) {
			fail();
		}
	}
}
