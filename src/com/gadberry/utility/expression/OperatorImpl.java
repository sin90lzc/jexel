package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class OperatorImpl implements Operator {

	private List<Argument> arguments = null;

	protected OperatorSet operatorSet = OperatorSet.getStandardOperatorSet();

	protected Resolver resolver = null;

	protected abstract void checkArgs(List<Argument> args) throws InvalidArgumentsException;

	public Argument getArgument(int i) {
		return arguments.get(i);
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	public OperatorSet getOperatorSet() {
		return operatorSet;
	}

	public Resolver getResolver() {
		return resolver;
	}

	public abstract List<Argument> parseArgs(List<String> tokens, int position, Resolver resolver);

	private List<Argument> resolveArguments(List<Argument> args) {
		List<Argument> resolvedArgs = new ArrayList<Argument>(args.size());
		Iterator<Argument> iter = args.iterator();
		while (iter.hasNext()) {
			Argument arg = iter.next();
			if (!arg.isResolved()) {
				try {
					Expression expression = new Expression(arg.toString());
					expression.setOperatorSet(operatorSet);
					expression.setResolver(resolver);

					arg = expression.evaluate();
				} catch (InvalidExpressionException e) {
				}
			}
			resolvedArgs.add(arg);
		}
		return resolvedArgs;
	}

	public void setArguments(List<Argument> args) throws InvalidArgumentsException {
		List<Argument> resolvedArgs = resolveArguments(args);
		checkArgs(resolvedArgs);
		arguments = resolvedArgs;
	}

	public void setOperatorSet(OperatorSet operatorSet) {
		this.operatorSet = operatorSet;
	}

	public void setResolver(Resolver resolver) {
		this.resolver = resolver;
	}
}
