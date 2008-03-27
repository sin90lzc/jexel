package com.gadberry.utility.expression.symbol;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Resolver;

public class SymbolTests {

	private Symbol op = null;

	@Before
	public void setUp() throws Exception {
		op = new MockSymbol();
	}

	@After
	public void tearDown() throws Exception {
		op = null;
	}

	/**
	 * This checks basic argument resolution
	 */
	@Test
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
