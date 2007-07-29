package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

public class Expression {
	
	public static Argument evaluate(String stringExpression) throws InvalidExpressionException{
		return new Expression(stringExpression).evaluate();
	}
	
	public static double evaluateToDouble(String stringExpression) throws InvalidExpressionException{
		return new Expression(stringExpression).evaluateToDouble();
	}

	private OperatorSet opSet = null;
	private Resolver resolver = null;
	
	private String stringExpression = null;
	
	
	public Expression(String stringExpression){
		this(stringExpression, null, null);
	}
	
	public Expression(String stringExpression, Resolver resolver){
		this(stringExpression, resolver, null);
	}

	public Expression(String stringExpression, OperatorSet operators){
		this(stringExpression, null, operators);
	}
	
	public Expression(String stringExpression, Resolver resolver, OperatorSet operators){
		if(operators == null){
			opSet = OperatorSet.getStandardOperatorSet();
		} else {
			opSet = operators;
		}
		
		this.resolver = resolver;
		
		this.stringExpression = stringExpression;
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
	public Double evaluateToDouble()
			throws InvalidExpressionException, ArgumentCastException {
		return new Double(evaluate().toDouble());
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
	Argument evaluate()
			throws InvalidExpressionException {
		if (!hasValidParenthesees(stringExpression)) {
			throw new InvalidExpressionException("Invalid parenthesees in: "
					+ stringExpression);
		}
		Operator operator = chooseDelimeter(stringExpression, resolver);
		Argument result = null;
		if (operator == null) {
			result = new Argument(stringExpression, resolver);
		} else {
			result = operator.resolve(resolver);
		}

		return result;
	}

	private Operator chooseDelimeter(String expression, Resolver resolver) {
		List<Operator> potentialOperators = getPotentialOperators(expression,
				resolver);
		return getLowestPriorityOperator(potentialOperators);
	}

	private List<Operator> getPotentialOperators(String expression,
			Resolver resolver) {
		List<Operator> potentialDelimeters = new ArrayList<Operator>();

		List<String> tokens = tokenize(expression, opSet.getDelimeters());

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
	

	private static Operator getLowestPriorityOperator(
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
			if (s.startsWith("(")) {
				parentheticalDepth++;
			} else if (s.startsWith(")")) {
				parentheticalDepth--;
			} else if (parentheticalDepth == 0) {
				for (String symbol : symbols) {
					if (s.startsWith(symbol)) {
						if (!currentToken.toString().trim().equals("")) {
							tokens.add(currentToken.toString().trim());
							currentToken = new StringBuffer();
						}
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
