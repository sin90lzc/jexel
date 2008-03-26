package com.gadberry.utility.expression.symbol;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;

public class LessThanOrEqualSymbol extends Symbol {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (args.size() != 2) {
			throw new InvalidArgumentsException(
					"LessThanOrEqualOperator requires two arguments.  Wrong number of arguments provided.");
		}
	}

	public Argument resolve() {
		Argument lhs = getArgument(0);
		Argument rhs = getArgument(1);
		List<Argument> args = new ArrayList<Argument>();
		args.add(lhs);
		args.add(rhs);
		
		LessThanSymbol lts = new LessThanSymbol();
		try {
			lts.setResolver(resolver);
			lts.setArguments(args);
		} catch (InvalidArgumentsException e) {}
		boolean lessThan = lts.resolve().toBoolean();
		
		EqualSymbol es = new EqualSymbol();
		try {
			es.setResolver(resolver);
			es.setArguments(args);
		} catch (InvalidArgumentsException e) {}
		boolean equal = es.resolve().toBoolean();
		return new Argument(new Boolean(lessThan || equal), resolver);
	}

	public int getPriority() {
		return 2;
	}

}
