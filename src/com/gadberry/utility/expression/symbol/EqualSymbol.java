package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class EqualSymbol extends Symbol {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 2) {
			throw new InvalidArgumentsException(
					"EqualOperator requires two arguments.  Wrong number of arguments provided.");
		}
	}

	public Argument resolve() {
		Argument lhs = getArgument(0);
		Argument rhs = getArgument(1);
		return new Argument(new Boolean(lhs.equals(rhs)), resolver);
	}

	public int getPriority() {
		return 2;
	}

}
