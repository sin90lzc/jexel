package com.gadberry.utility.expression.operator;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class MockOperator extends Operator {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
	}

	public int getPriority() {
		return 0;
	}

	public String getType() {
		return null;
	}

	public Argument resolve(Resolver resolver) {
		return null;
	}

}
