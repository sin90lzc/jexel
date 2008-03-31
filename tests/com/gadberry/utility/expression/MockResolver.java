package com.gadberry.utility.expression;

/**
 * @author Aaron Gadberry
 *
 */
public class MockResolver implements Resolver {

	public boolean canResolve(String path) {
		return true;
	}

	public Object resolve(String path) {
		return path;
	}

}
