package com.gadberry.utility.expression.operator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.InvalidExpressionException;
import com.gadberry.utility.expression.Resolver;

public abstract class Operator {

	public static final String STANDARD = "standard";

	public static final String FUNCTION = "function";

	private List<Argument> arguments = null;

	public void setArguments(List<Argument> args) throws InvalidArgumentsException {
		List<Argument> resolvedArgs = resolveArgs(args);
		checkArgs(resolvedArgs);
		this.arguments = resolvedArgs;
	}

	private List<Argument> resolveArgs(List<Argument> args) {
		List<Argument> resolvedArgs = new ArrayList<Argument>(args.size());
		Iterator<Argument> iter = args.iterator();
		while (iter.hasNext()) {
			Argument arg = null;
			try {
				arg = Expression.evaluate(iter.next().toString());
			} catch (InvalidExpressionException e) {
				e.printStackTrace();
			}
			resolvedArgs.add(arg);
		}
		return resolvedArgs;
	}

	protected List<Argument> getArguments() {
		return this.arguments;
	}

	protected abstract void checkArgs(List<Argument> args)
			throws InvalidArgumentsException;

	public abstract Argument resolve(Resolver resolver);

	public abstract int getPriority();

	protected Argument getArgument(int i) {
		return this.arguments.get(i);
	}

	public abstract String getType();

}
