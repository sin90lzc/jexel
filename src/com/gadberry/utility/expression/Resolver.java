package com.gadberry.utility.expression;

public interface Resolver {

	public boolean canResolve(String path);

	public Object resolve(String path);
}
