package com.gadberry.utility.expression;

import java.util.List;

/**
 * @author Aaron Gadberry
 */
public interface Operator {

	/**
	 * Get the {@link Argument} at the specified index.
	 * 
	 * @param index
	 * 
	 * @return the {@link Argument}
	 */
	abstract Argument getArgument(int index);

	/**
	 * Get the list of {@link Argument}s to be interpreted.
	 * 
	 * @return the list of {@link Argument}s
	 */
	abstract List<Argument> getArguments();

	/**
	 * Get the {@link OperatorSet} passed on to any created child Expressions.
	 * This will be the same as the {@link OperatorSet} of the Expression
	 * creating this operator.
	 * 
	 * @return OperatorSet
	 */

	abstract int getPriority();

	/**
	 * Utilize the tokens and current token position to determine the applicable
	 * arguments for this operator.
	 * 
	 * @param tokens
	 *            List of tokens to consider as arguments
	 * 
	 * @param position
	 *            Current token position
	 * 
	 * @return List of {@link Argument}s parsed by this operator
	 */
	abstract List<Argument> parseArgs(List<String> tokens, int position);

	/**
	 * Evaluate the application of this operator to the set arguments.
	 * 
	 * @return the {@link Argument} result
	 */
	abstract Argument resolve();

	/**
	 * Set the arguments to apply this operator on.
	 * 
	 * @param args
	 *            List of {@link Argument}s
	 * 
	 * @throws InvalidArgumentsException
	 *             if the arguments are not valid for this operator. For example
	 *             adding two dates together makes no sense and therefore would
	 *             throw this exception.
	 */
	void setArguments(List<Argument> args) throws InvalidArgumentsException;
}
