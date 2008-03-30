package com.gadberry.utility.expression;

/**
 * @author Aaron Gadberry
 */

public interface Resolver {

	/**
	 * Determine if this resolver can resolve the given variable to an Object.
	 * 
	 * @param variable
	 *            to resolve
	 * 
	 * @return whether or not this variable can be resolved.
	 */
	public boolean canResolve(String variable);

	/**
	 * Resolve this variable to an Object.
	 * 
	 * @param variable
	 *            to resolve
	 * 
	 * @return the resolved object or null if no object can be resolved.
	 */
	public Object resolve(String variable);
}
