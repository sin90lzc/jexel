package com.gadberry.utility.expression.operator;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.ArgumentCastException;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class CeilOperator extends Operator {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"CeilOperator requires a single double.  Wrong number of arguments provided.");
		}

		for (Argument arg : args) {
			if (!arg.isDouble()) {
				throw new InvalidArgumentsException(
						"CeilOperator only accepts a double.  Wrong type of arguments provided.  Arg: "
								+ arg.toString());
			}
		}
	}

	public Argument resolve(Resolver resolver) {
		double n = 0;
		try {
			n = getArgument(0).toDouble();
		} catch (ArgumentCastException e) {
		}
		return new Argument(new Double(Math.ceil(n)), resolver);
	}

	public int getPriority() {
		return 20;
	}

	public String getType() {
		return Operator.FUNCTION;
	}
}
