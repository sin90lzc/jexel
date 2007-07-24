package com.gadberry.utility.expression.function;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class SubstrFunction extends Function {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() == 2) {
			if (!args.get(1).isInteger()) {
				throw new InvalidArgumentsException(
						"SubstrOperator requires one string and one or two integers.  Wrong type of arguments provided.");
			}
			if (args.get(1).toInteger() < 0) {
				throw new InvalidArgumentsException(
						"SubstrOperator requires positive integers.");
			}
		} else if (args.size() == 3) {
			if (!args.get(1).isInteger() || !args.get(2).isInteger()) {
				throw new InvalidArgumentsException(
						"SubstrOperator requires one string and one or two integers.  Wrong type of arguments provided.");
			}
			if (args.get(2).toInteger() < 0) {
				throw new InvalidArgumentsException(
						"SubstrOperator requires positive integers.");
			}
			if (args.get(1).toInteger() > args.get(2).toInteger()) {
				throw new InvalidArgumentsException(
						"SubstrOperator requires the first integer be smaller than the second integer.");
			}
		} else {
			throw new InvalidArgumentsException(
					"SubstrOperator requires one string and one or two integers.  Wrong type of arguments provided.");
		}

	}

	public Argument resolve(Resolver resolver) {
		String s = getArgument(0).toString();
		int start = getArgument(1).toInteger();
		if (getArguments().size() == 2) {
			return new Argument(s.substring(start), resolver);
		} else if (getArguments().size() == 3) {
			int end = getArgument(2).toInteger();
			return new Argument(s.substring(start, end), resolver);
		}
		return null;

	}
}