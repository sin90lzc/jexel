package com.gadberry.utility.expression;

public class MockResolver implements Resolver {

	public boolean canResolve(String path) {
		return true;
	}

	public Object resolve(String path) {
		return path;
	}

}
