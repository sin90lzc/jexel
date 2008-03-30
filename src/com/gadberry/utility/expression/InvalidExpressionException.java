package com.gadberry.utility.expression;

/**
 * @author Aaron Gadberry
 */

public class InvalidExpressionException extends Exception {

	private static final long serialVersionUID = 5888985509049144819L;

	InvalidExpressionException(String string) {
		super(string);
	}

}
