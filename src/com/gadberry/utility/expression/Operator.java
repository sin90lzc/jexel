package com.gadberry.utility.expression;

import java.util.List;

public interface Operator {

	void setArguments(List<Argument> args) throws InvalidArgumentsException;

	abstract List<Argument> parseArgs(List<String> tokens, int position,
			Resolver resolver);

	Argument resolve(Resolver resolver);
	
	abstract int getPriority();
	
	abstract List<Argument> getArguments();
	
	abstract Argument getArgument(int i);
}
