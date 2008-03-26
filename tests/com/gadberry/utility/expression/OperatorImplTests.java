package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.symbol.AdditionSymbol;
import com.gadberry.utility.expression.symbol.SubtractionSymbol;

import junit.framework.TestCase;

public class OperatorImplTests extends TestCase {

	public static double TOLERANCE = .0001;

	private Operator op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new MockOperator();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}
	
	/**
	 * Testing set / get arguments
	 *
	 */
	public void testSetArguments() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(new Integer(1), null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.getArguments(), args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing getArgument(int) and argument resolution
	 *
	 */
	public void testGetArgument() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("1 + 1", null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.getArgument(0), new Argument(new Double(2), null));
			assertEquals(op.getArgument(1), new Argument(new Double(2), null));
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing getArguments()
	 *
	 */
	public void testGetArguments() {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument("4", null));
		args.add(new Argument(new Integer(2), null));
		try {
			op.setArguments(args);
			assertEquals(op.getArguments(), args);
		} catch (InvalidArgumentsException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	public void testGetResolver(){
		Resolver resolver = new MockResolver();
		
		op.setResolver(resolver);
		
		assertEquals(op.getResolver(), resolver);
	}
	
	public void testSetResolver(){
		Resolver resolver = new MockResolver();
		
		op.setResolver(resolver);
		
		assertEquals(op.getResolver(), resolver);
	}
	
	public void testGetOperatorSet(){
		OperatorSet operatorSet = new OperatorSet();
		operatorSet.addOperator("+", new AdditionSymbol());
		operatorSet.addOperator("-", new SubtractionSymbol());
		
		op.setOperatorSet(operatorSet);
		
		assertEquals(op.getOperatorSet(), operatorSet);
	}
	
	public void testSetOperatorSet(){
		OperatorSet operatorSet = new OperatorSet();
		operatorSet.addOperator("+", new AdditionSymbol());
		operatorSet.addOperator("-", new SubtractionSymbol());
		
		op.setOperatorSet(operatorSet);
		
		assertEquals(op.getOperatorSet(), operatorSet);
	}
}
