package com.gadberry.utility.expression;

import java.util.List;

import junit.framework.TestCase;

import com.gadberry.utility.expression.symbol.AdditionSymbol;
import com.gadberry.utility.expression.symbol.SubtractionSymbol;

public class OperatorSetTests extends TestCase {

	private OperatorSet opSet = null;

	protected void setUp() throws Exception {
		super.setUp();
		opSet = OperatorSet.getStandardOperatorSet();
	}

	protected void tearDown() throws Exception {
		opSet = null;
		super.tearDown();
	}

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
	
	public void testGetSymbols(){
		opSet = new OperatorSet();
		opSet.addOperator("+", new AdditionSymbol());
		opSet.addOperator("-", new SubtractionSymbol());
		
		List<String> symbols = opSet.getDelimeters();
		if(!symbols.contains("+")){
			fail();
		} else if(!symbols.contains("-")){
			fail();
		} else if(symbols.contains("*")){
			fail();
		}
	}
}
