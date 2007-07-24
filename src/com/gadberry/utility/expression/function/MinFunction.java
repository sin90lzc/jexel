package com.gadberry.utility.expression.function;

import java.util.Iterator;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class MinFunction extends Function {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() < 2) {
			throw new InvalidArgumentsException(
					"MinOperator requires a minimum of two doubles.  Wrong number of arguments provided.");
		}

		for (Argument arg : args) {
			if (!arg.isDouble()) {
				throw new InvalidArgumentsException(
						"MinOperator only accepts doubles.  Wrong type of arguments provided.  Arg: "
								+ arg.toString());
			}
		}
	}

	public Argument resolve(Resolver resolver) {
		double min = 0;
		Iterator<Argument> iter = getArguments().iterator();
		min = iter.next().toDouble();
		while (iter.hasNext()) {
			Argument arg = iter.next();
			if (min > arg.toDouble()) {
				min = arg.toDouble();
			}
		}
		return new Argument(new Double(min), resolver);
	}

}
