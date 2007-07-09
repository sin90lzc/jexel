package com.gadberry.utility.expression.operator;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class NotOperator extends Operator {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"NotOperator requires one boolean.  Wrong number of arguments provided.");
		}

		if (!args.get(0).isBoolean()) {
			throw new InvalidArgumentsException(
					"NotOperator requires one boolean.  Wrong type of arguments provided.");
		}
	}

	public Argument resolve(Resolver resolver) {
		boolean arg = getArgument(0).toBoolean();
		return new Argument(new Boolean(!arg), resolver);
	}

	public int getPriority() {
		return 10;
	}

	public String getType() {
		return Operator.FUNCTION;
	}
}
