package com.gadberry.utility.expression;

import java.util.List;

public interface Operator {

	abstract Argument getArgument(int index);

	abstract List<Argument> getArguments();

	abstract int getPriority();

	abstract List<Argument> parseArgs(List<String> tokens, int position);

	abstract Argument resolve();

	void setArguments(List<Argument> args) throws InvalidArgumentsException;

	abstract void setExpression(Expression expression);
}
