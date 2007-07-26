package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class OperatorImpl implements Operator {

	private List<Argument> arguments = null;

	public void setArguments(List<Argument> args)
			throws InvalidArgumentsException {
		List<Argument> resolvedArgs = resolveArguments(args);
		checkArgs(resolvedArgs);
		this.arguments = resolvedArgs;
	}

	private List<Argument> resolveArguments(List<Argument> args) {
		List<Argument> resolvedArgs = new ArrayList<Argument>(args.size());
		Iterator<Argument> iter = args.iterator();
		while (iter.hasNext()) {
			Argument arg = iter.next();
			if (!arg.isResolved()) {
				try {
					arg = Expression.evaluate(arg.toString());
				} catch (InvalidExpressionException e) {
				}
			}
			resolvedArgs.add(arg);
		}
		return resolvedArgs;
	}

	public List<Argument> getArguments() {
		return this.arguments;
	}
	
	public Argument getArgument(int i) {
		return this.arguments.get(i);
	}
	
	protected abstract void checkArgs(List<Argument> args) throws InvalidArgumentsException;
	
	public abstract List<Argument> parseArgs(List<String> tokens, int position,
			Resolver resolver);
}
