package com.gadberry.utility.expression;

import java.util.List;

public interface Operator {

	abstract Argument getArgument(int i);

	abstract List<Argument> getArguments();

	abstract OperatorSet getOperatorSet();
	
	abstract int getPriority();
	
	abstract Resolver getResolver();
	
	abstract List<Argument> parseArgs(List<String> tokens, int position,
			Resolver resolver);
	
	Argument resolve();
	
	void setArguments(List<Argument> args) throws InvalidArgumentsException;
	
	abstract void setOperatorSet(OperatorSet operators);
	
	abstract void setResolver(Resolver resolver);
}
