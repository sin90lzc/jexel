package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class OrSymbol extends Symbol {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		if (args.size() != 2) {
			throw new InvalidArgumentsException(
					"OrOperator requires two booleans.  Wrong number of arguments provided.");
		}

		if (!args.get(0).isBoolean() || !args.get(1).isBoolean()) {
			throw new InvalidArgumentsException("OrOperator requires two booleans.  Wrong type of arguments provided.");
		}
	}

	@Override
	public int getPriority() {
		return 5;
	}

	@Override
	public Argument resolve() {
		boolean lhs = getArgument(0).toBoolean();
		boolean rhs = getArgument(1).toBoolean();
		return new Argument(new Boolean(lhs || rhs), resolver);
	}

}
