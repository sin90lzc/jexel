package com.gadberry.utility.expression.operator;

import java.util.List;

import junit.framework.TestCase;

public class OperatorSetTests extends TestCase {

	public static double TOLERANCE = .0001;

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
		if (!(op instanceof AdditionOperator)) {
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
		if (!(op instanceof AdditionOperator)) {
			fail();
		}
		
		opSet = new OperatorSet();

		op = opSet.findOperator("+");
		if (!(op == null)) {
			fail();
		}
		
		opSet.addOperator("+", AdditionOperator.class);
		op = opSet.findOperator("+");
		if (!(op instanceof AdditionOperator)) {
			fail();
		}
	}
	
	public void testGetSymbol(){
		assertEquals(opSet.getSymbol(AdditionOperator.class), "+");
		
		assertNull(opSet.getSymbol(Operator.class));
	}
	
	public void testGetSymbols(){
		opSet = new OperatorSet();
		opSet.addOperator("+", AdditionOperator.class);
		opSet.addOperator("-", SubtractionOperator.class);
		
		List symbols = opSet.getSymbols();
		if(!symbols.contains("+")){
			fail();
		} else if(!symbols.contains("-")){
			fail();
		} else if(symbols.contains("*")){
			fail();
		}
	}
	
	public void testGetOperatorLength(){
		assertEquals(opSet.getOperatorLength(AdditionOperator.class), 1);
		assertEquals(opSet.getOperatorLength(MaxOperator.class), 3);
	}
	

}
