package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aaron Gadberry
 */

public class MockOperator extends OperatorImpl {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
	}

	public int getPriority() {
		return 0;
	}

	public List<Argument> parseArgs(List<String> tokens, int position) {
		return new ArrayList<Argument>();
	}

	public Argument resolve() {
		return null;
	}

}
