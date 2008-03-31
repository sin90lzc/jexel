package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.Function;
import com.gadberry.utility.expression.InvalidArgumentsException;

/**
 * @author Aaron Gadberry
 */

public class FloorFunction extends Function {

	/**
	 * Create a FloorFunction with a given parent expression.
	 * 
	 * @param expression
	 *            parent
	 */
	public FloorFunction(Expression expression) {
		super(expression);
	}

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		if (args.size() != 1) {
			throw new InvalidArgumentsException(
					"FloorOperator requires a single double.  Wrong number of arguments provided.");
		}

		for (Argument arg : args) {
			if (!arg.isDouble()) {
				throw new InvalidArgumentsException(
						"MaxOperator only accepts a double.  Wrong type of arguments provided.  Arg: " + arg.toString());
			}
		}
	}

	public Argument resolve() {
		double n = getArgument(0).toDouble();
		return new Argument(new Double(Math.floor(n)), getResolver());
	}
}
