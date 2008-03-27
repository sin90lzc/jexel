package com.gadberry.utility.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gadberry.utility.expression.symbol.AdditionSymbol;
import com.gadberry.utility.expression.symbol.SubtractionSymbol;

public class OperatorImplTests {

	public static double TOLERANCE = .0001;

	private Operator op = null;

	@Before
	public void setUp() throws Exception {
		op = new MockOperator();
	}

	@After
	public void tearDown() throws Exception {
		op = null;
	}

	/**
	 * Testing getArgument(int) and argument resolution
	 * 
	 */
	@Test
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
	@Test
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

	@Test
	public void testGetOperatorSet() {
		OperatorSet operatorSet = new OperatorSet();
		operatorSet.addOperator("+", new AdditionSymbol());
		operatorSet.addOperator("-", new SubtractionSymbol());

		op.setOperatorSet(operatorSet);

		assertEquals(op.getOperatorSet(), operatorSet);
	}

	@Test
	public void testGetResolver() {
		Resolver resolver = new MockResolver();

		op.setResolver(resolver);

		assertEquals(op.getResolver(), resolver);
	}

	/**
	 * Testing set / get arguments
	 * 
	 */
	@Test
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

	@Test
	public void testSetOperatorSet() {
		OperatorSet operatorSet = new OperatorSet();
		operatorSet.addOperator("+", new AdditionSymbol());
		operatorSet.addOperator("-", new SubtractionSymbol());

		op.setOperatorSet(operatorSet);

		assertEquals(op.getOperatorSet(), operatorSet);
	}

	@Test
	public void testSetResolver() {
		Resolver resolver = new MockResolver();

		op.setResolver(resolver);

		assertEquals(op.getResolver(), resolver);
	}
}
