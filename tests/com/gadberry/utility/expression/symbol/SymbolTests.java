package com.gadberry.utility.expression.symbol;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Resolver;

import junit.framework.TestCase;

public class SymbolTests extends TestCase {

	private Symbol op = null;

	protected void setUp() throws Exception {
		super.setUp();
		op = new MockSymbol();
	}

	protected void tearDown() throws Exception {
		op = null;
		super.tearDown();
	}

	/**
	 * This checks basic argument resolution
	 */
	public void testParseArgs() {
		List<String> tokens = new ArrayList<String>();
		tokens.add("1 - 2");
		tokens.add("+");
		tokens.add("max(3, 4)");
		int position = 1;
		Resolver resolver = null;
		List<Argument> args = op.parseArgs(tokens, position, resolver);
		assertEquals(args.get(0), new Argument("1 - 2", null));
		assertEquals(args.get(1), new Argument("max(3, 4)", null));
	}
}
