package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Function;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class NotFunction extends Function {

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"NotOperator requires one boolean.  Wrong number of arguments provided.");
		}

		if (!args.get(0).isBoolean()) {
			throw new InvalidArgumentsException("NotOperator requires one boolean.  Wrong type of arguments provided.");
		}
	}

	public Argument resolve() {
		boolean arg = getArgument(0).toBoolean();
		return new Argument(new Boolean(!arg), getResolver());
	}
}
