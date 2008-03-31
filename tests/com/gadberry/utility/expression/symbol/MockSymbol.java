package com.gadberry.utility.expression.symbol;

import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Symbol;

/**
 * @author Aaron Gadberry
 *
 */
public class MockSymbol extends Symbol {

	/**
	 * @param expression
	 */
	public MockSymbol(Expression expression) {
		super(expression);
	}

	@Override
	protected void checkArgs(List<Argument> args) throws InvalidArgumentsException {
		// Take no action
	}

	public int getPriority() {
		return 10;
	}

	public Argument resolve() {
		return new Argument(1, getResolver());
	}

}
