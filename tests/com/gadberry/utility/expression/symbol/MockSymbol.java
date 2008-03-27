package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class MockSymbol extends Symbol {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		// Take no action
	}

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public Argument resolve() {
		return new Argument(1, resolver);
	}

}
