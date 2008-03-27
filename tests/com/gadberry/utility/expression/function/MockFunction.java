package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class MockFunction extends Function {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		// Take no action
	}

	@Override
	public Argument resolve() {
		return new Argument(1, resolver);
	}
}
