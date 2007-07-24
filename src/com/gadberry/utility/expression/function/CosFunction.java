package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class CosFunction extends Function {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"CosOperator requires a single doubles.  Wrong number of arguments provided.");
		}

		for (Argument arg : args) {
			if (!arg.isDouble()) {
				throw new InvalidArgumentsException(
						"CosOperator only accepts doubles.  Wrong type of arguments provided.  Arg: "
								+ arg.toString());
			}
		}
	}

	public Argument resolve(Resolver resolver) {
		return new Argument(Math.cos(getArgument(0).toDouble()), resolver);
	}
}