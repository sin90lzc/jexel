package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class AdditionSymbol extends Symbol {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		if (args.size() != 2) {
			throw new InvalidArgumentsException(
					"AdditionOperator requires two arguments.  Wrong number of arguments provided.");
		}

	}

	@Override
	public Argument resolve() {
		if (getArgument(0).isDouble() && getArgument(1).isDouble()) {
			double lhs = getArgument(0).toDouble();
			double rhs = getArgument(1).toDouble();
			return new Argument(new Double(lhs + rhs), resolver);
		} else {
			String lhs = getArgument(0).toString();
			String rhs = getArgument(1).toString();
			return new Argument(lhs + rhs, resolver);
		}
	}

	@Override
	public int getPriority() {
		return 5;
	}

}
