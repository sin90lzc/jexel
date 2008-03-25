package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class SinFunction extends Function {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"SinOperator requires a single doubles.  Wrong number of arguments provided.");
		}

		for (Argument arg : args) {
			if (!arg.isDouble()) {
				throw new InvalidArgumentsException(
						"SinOperator only accepts doubles.  Wrong type of arguments provided.  Arg: "
								+ arg.toString());
			}
		}
	}

	public Argument resolve() {
		return new Argument(Math.sin(getArgument(0).toDouble()), resolver);
	}
}
