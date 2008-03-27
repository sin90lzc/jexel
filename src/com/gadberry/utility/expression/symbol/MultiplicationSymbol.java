package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class MultiplicationSymbol extends Symbol {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		if (args.size() != 2) {
			throw new InvalidArgumentsException(
					"MultiplicationOperator requires two doubles.  Wrong number of arguments provided.");
		}

		if (!args.get(0).isDouble() || !args.get(1).isDouble()) {
			throw new InvalidArgumentsException(
					"MultiplicationOperator requires two doubles.  Wrong type of arguments provided.");
		}
	}

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public Argument resolve() {
		double lhs = getArgument(0).toDouble();
		double rhs = getArgument(1).toDouble();
		return new Argument(new Double(lhs * rhs), resolver);
	}

}
