package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

public class Expression {

	private static OperatorSet defaultOperatorSet = OperatorSet.getStandardOperatorSet();

	private static Resolver defaultResolver = null;

	static final String LITERAL_CHARACTER = "'";

	public static Argument evaluate(String stringExpression) throws InvalidExpressionException {
		return new Expression(stringExpression).evaluate();
	}

	public static OperatorSet getDefaultOperatorSet() {
		return defaultOperatorSet;
	}

	public static Resolver getDefaultResolver() {
		return defaultResolver;
	}

	private static Operator getLowestPriorityOperator(List<Operator> potentialOperators) {
		Operator lowestPriorityOperator = null;
		for (Operator op : potentialOperators) {
			if (lowestPriorityOperator == null) {
				lowestPriorityOperator = op;
			} else if (op.getPriority() < lowestPriorityOperator.getPriority()) {
				lowestPriorityOperator = op;
			}
		}
		return lowestPriorityOperator;
	}

	private static boolean hasValidParenthesees(String expression) {
		int parentheticalDepth = 0;
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '(') {
				parentheticalDepth++;
			} else if (c == ')') {
				if (parentheticalDepth == 0) {
					return false;
				}
				parentheticalDepth--;
			}
		}
		if (parentheticalDepth != 0) {
			return false;
		}
		return true;
	}

	public static void setDefaultOperatorSet(OperatorSet defaultOperatorSet) {
		Expression.defaultOperatorSet = defaultOperatorSet;
	}

	public static void setDefaultResolver(Resolver defaultResolver) {
		Expression.defaultResolver = defaultResolver;
	}

	private static List<String> tokenize(String expression, List<String> symbols) {
		expression = trim(expression);

		int parentheticalDepth = 0;
		boolean insideLiteral = false;
		List<String> tokens = new ArrayList<String>();
		StringBuffer currentToken = new StringBuffer();
		for (int i = 0; i < expression.length(); i++) {
			String s = expression.substring(i);
			boolean add = true;
			if (s.startsWith("(")) {
				parentheticalDepth++;
			} else if (s.startsWith(")")) {
				parentheticalDepth--;
			} else if (s.startsWith(LITERAL_CHARACTER)) {
				insideLiteral = !insideLiteral;
			} else if (parentheticalDepth == 0 && !insideLiteral) {
				String maxSymbol = null;
				for (String symbol : symbols) {
					if (s.startsWith(symbol)) {
						if ((maxSymbol == null) || (maxSymbol.length() < symbol.length())) {
							maxSymbol = symbol;
						}
					}
				}
				if (maxSymbol != null) {
					if (!currentToken.toString().trim().equals("")) {
						tokens.add(currentToken.toString().trim());
						currentToken = new StringBuffer();
					}
					tokens.add(maxSymbol);
					i += maxSymbol.length() - 1;
					add = false;
				}
			}
			if (add) {
				currentToken.append(s.charAt(0));
			}
		}
		tokens.add(currentToken.toString().trim());
		return tokens;
	}

	static String trim(String expression) {
		if (expression.startsWith("(") && expression.endsWith(")")) {
			if (hasValidParenthesees(expression.substring(1, expression.length() - 1))) {
				return trim(expression.substring(1, expression.length() - 1));
			}
		}
		return expression;
	}

	private OperatorSet opSet = Expression.defaultOperatorSet;

	private Resolver resolver = Expression.defaultResolver;

	private String stringExpression = null;

	public Expression(String stringExpression) {
		this.stringExpression = stringExpression;
	}

	private Operator chooseDelimeter(String expression) {
		List<Operator> potentialOperators = getPotentialOperators(expression);
		return getLowestPriorityOperator(potentialOperators);
	}

	/**
	 * This is an access method for evaluating an expression with a resolver.
	 * Use this if you have variables in your expression.
	 * 
	 * @param expression
	 * @return Argument result
	 * @throws InvalidExpressionException
	 */
	public Argument evaluate() throws InvalidExpressionException {
		if (!hasValidParenthesees(stringExpression)) {
			throw new InvalidExpressionException("Invalid parenthesees in: " + stringExpression);
		}
		Operator operator = chooseDelimeter(stringExpression);
		Argument result = null;
		if (operator == null) {
			result = new Argument(stringExpression, resolver);
		} else {
			result = operator.resolve();
		}

		return result;
	}

	/**
	 * This is an access method for evaluating an expression. It is equilivent
	 * to Expression.evaluate(stringExpression, null). Use this if you have no
	 * variables in your expression.
	 * 
	 * @param expression
	 * @return
	 * @throws InvalidExpressionException
	 * @throws ArgumentCastException
	 */
	public OperatorSet getOperatorSet() {
		return opSet;
	}

	private List<Operator> getPotentialOperators(String expression) {
		List<Operator> potentialDelimeters = new ArrayList<Operator>();

		List<String> tokens = tokenize(expression, opSet.getDelimeters());

		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			Operator operator = opSet.findOperator(token, this);

			if (operator != null) {
				List<Argument> args = operator.parseArgs(tokens, i);

				try {
					operator.setArguments(args);
					potentialDelimeters.add(operator);
				} catch (InvalidArgumentsException e) {
					/*
					 * Arguments are invalid for this operator. Move on.
					 */
				}
			}
		}
		return potentialDelimeters;
	}

	public Resolver getResolver() {
		return resolver;
	}

	public void setOperatorSet(OperatorSet operators) {
		opSet = operators;
	}

	public void setResolver(Resolver resolver) {
		this.resolver = resolver;
	}
}
