package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Aaron Gadberry
 */

public abstract class OperatorImpl implements Operator {

	private List<Argument> arguments = null;

	protected Expression parentExpression = null;

	protected abstract void checkArgs(List<Argument> args) throws InvalidArgumentsException;

	public Argument getArgument(int i) {
		return arguments.get(i);
	}

	public List<Argument> getArguments() {
		return arguments;
	}

	private List<Argument> resolveArguments(List<Argument> args) {
		List<Argument> resolvedArgs = new ArrayList<Argument>(args.size());
		Iterator<Argument> iter = args.iterator();
		while (iter.hasNext()) {
			Argument arg = iter.next();
			if (!arg.isResolved()) {
				try {
					Expression expression = new Expression(arg.toString());
					expression.setOperatorSet(parentExpression.getOperatorSet());
					expression.setResolver(parentExpression.getResolver());

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

	public void setExpression(Expression expression) {
		parentExpression = expression;
	}
	
	protected Resolver getResolver(){
		return parentExpression.getResolver();
	}
}
