package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

public class MockOperator extends OperatorImpl {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
	}

	public int getPriority() {
		return 0;
	}

	public Argument resolve() {
		return null;
	}

	@Override
	public List<Argument> parseArgs(List<String> tokens, int position, Resolver resolver) {
		return new ArrayList<Argument>();
	}

}
