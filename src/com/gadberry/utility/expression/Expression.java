package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

public class Expression {

	private static OperatorSet opSet = OperatorSet.getStandardOperatorSet();

	public static void setOperatorSet(OperatorSet os) {
		opSet = os;
	}

	public static OperatorSet getOperatorSet() {
		return opSet;
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
	public static Double evaluateToDouble(String expression)
			throws InvalidExpressionException, ArgumentCastException {
		return new Double(Expression.evaluate(expression, null).toDouble());
	}

	/**
	 * This is an access method for evaluating an expression. It is equilivent
	 * to Expression.evaluate(stringExpression, null). Use this if you have no
	 * variables in your expression.
	 * 
	 * @param expression
	 * @return
	 * @throws InvalidExpressionException
	 */
	public static Argument evaluate(String expression)
			throws InvalidExpressionException {
		return Expression.evaluate(expression, null);
	}

	/**
	 * This is an access method for evaluating an expression with a resolver.
	 * Use this if you have variables in your expression.
	 * 
	 * @param expression
	 * @param resolver
	 * @return
	 * @throws InvalidExpressionException
	 */
	static Argument evaluate(String expression, Resolver resolver)
			throws InvalidExpressionException {
		if (!hasValidParenthesees(expression)) {
			throw new InvalidExpressionException("Invalid parenthesees in: "
					+ expression);
		}
		Operator operator = chooseDelimeter(expression, resolver);
		Argument result = null;
		if (operator == null) {
			result = new Argument(expression, resolver);
		} else {
			result = operator.resolve(resolver);
		}

		// System.out.println(expression + " -> " + result.toString());

		return result;
	}

	private static Operator chooseDelimeter(String expression, Resolver resolver) {
		List<Operator> potentialDelimeters = getPotentialDelimeters(expression,
				resolver);
		return getLowestPriorityDelimeter(potentialDelimeters);

	}

	private static List<Operator> getPotentialDelimeters(String expression,
			Resolver resolver) {
		List<Operator> potentialDelimeters = new ArrayList<Operator>();

		List<String> tokens = tokenize(expression, opSet.getOperators());
		//System.out.println(tokens.toString());

		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			Operator operator = opSet.findOperator(token);
			if (operator != null) {
				List<Argument> args = operator.parseArgs(tokens, i, resolver);

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
	

	private static Operator getLowestPriorityDelimeter(
			List<Operator> potentialOperators) {
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

	

	private static List<String> tokenize(String expression, List<String> symbols) {
		expression = trim(expression);
		int parentheticalDepth = 0;
		List<String> tokens = new ArrayList<String>();
		StringBuffer currentToken = new StringBuffer();
		for (int i = 0; i < expression.length(); i++) {
			String s = expression.substring(i);
			boolean add = true;
			// System.out.println("string " + i + ": " + s);
			if (s.startsWith("(")) {
				parentheticalDepth++;
				//System.out.println(s + "|" + parentheticalDepth);
			} else if (s.startsWith(")")) {
				parentheticalDepth--;
			} else if (parentheticalDepth == 0) {
				for (String symbol : symbols) {
					if (s.startsWith(symbol)) {
						/*
						 * System.out.println("Adding token 1: " +
						 * currentToken.toString());
						 */
						if (!currentToken.toString().trim().equals("")) {
							tokens.add(currentToken.toString().trim());
							currentToken = new StringBuffer();
						}
						/*
						 * System.out.println("Adding token 2: " +
						 * currentToken.toString());
						 */
						tokens.add(symbol);
						i += symbol.length() - 1;
						add = false;
					}
				}
			}
			if (add) {
				currentToken.append(s.charAt(0));
			}
		}
		/* System.out.println("Adding token 3: " + currentToken.toString()); */
		tokens.add(currentToken.toString().trim());
		return tokens;
	}

	public static String trim(String expression) {
		if (expression.startsWith("(") && expression.endsWith(")")) {
			if (hasValidParenthesees(expression.substring(1, expression
					.length() - 1))) {
				return trim(expression.substring(1, expression.length() - 1));
			}
		}
		return expression;
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
}
