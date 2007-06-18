package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.operator.Operator;
import com.gadberry.utility.expression.operator.OperatorSet;

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
		Operator operator = chooseOperator(expression, resolver);
		Argument result = null;
		if (operator == null) {
			result = new Argument(expression, resolver);
		} else {
			result = operator.resolve(resolver);
		}

		// System.out.println(expression + " -> " + result.toString());

		return result;
	}

	private static Operator chooseOperator(String expression, Resolver resolver) {
		List<Operator> potentialOperators = getPotentialOperators(expression,
				resolver);
		return getLowestPriorityOperator(potentialOperators);

	}

	private static List<Operator> getPotentialOperators(String expression,
			Resolver resolver) {
		List<Operator> potentialOperators = new ArrayList<Operator>();

		List<String> tokens = tokenize(expression, opSet.getSymbols());
		// System.out.println(tokens.toString());

		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			Operator operator = opSet.findOperator(token);
			if (operator != null) {
				List<Argument> args = null;
				if (operator.getType().equals(Operator.STANDARD)) {
					/*
					 * Get a left hand side and a right hand side
					 */
					args = getStandardArgs(tokens, i, resolver);
				} else if (operator.getType().equals(Operator.FUNCTION)) {
					/*
					 * Get the comma seperated args within the following ()
					 */
					args = getFunctionArgs(token + tokens.get(i + 1), resolver);
					i++;
				}

				try {
					operator.setArguments(args);
					potentialOperators.add(operator);
				} catch (InvalidArgumentsException e) {
					/*
					 * Arguments are invalid for this operator. Move on.
					 */
				}
			}
		}

		return potentialOperators;
	}

	private static List<Argument> getStandardArgs(List<String> tokens, int i,
			Resolver resolver) {
		List<Argument> args = new ArrayList<Argument>();
		if (i > 0) {
			// There is a left hand argument
			StringBuffer lhs = new StringBuffer();
			for (int j = 0; j < i; j++) {
				lhs.append(tokens.get(j));
			}
			args.add(new Argument(lhs.toString().trim(), resolver));
			// System.out.println("LHS:" + lhs.toString());
		}
		if (i < tokens.size()) {
			// There is a right hand argument
			StringBuffer rhs = new StringBuffer();
			for (int j = i + 1; j < tokens.size(); j++) {
				rhs.append(tokens.get(j));
			}
			args.add(new Argument(rhs.toString().trim(), resolver));
			// System.out.println("RHS:" + rhs.toString());
		}
		return args;
	}

	private static List<Argument> getFunctionArgs(String choppedExp,
			Resolver resolver) {
		int closingParen = getClosingParentheseeLocation(choppedExp);
		choppedExp = choppedExp.substring(choppedExp.indexOf("("),
				closingParen + 1);
		List<Argument> args = new ArrayList<Argument>();
		choppedExp = trim(choppedExp);
		List<String> tokens = tokenize(choppedExp, ',');
		for (String token : tokens) {
			args.add(new Argument(token.trim(), resolver));
			// System.out.println(arg.trim());
		}
		return args;
	}

	private static int getClosingParentheseeLocation(String tempExp) {
		int parentheticalDepth = 0;
		for (int j = 0; j < tempExp.length(); j++) {
			char c = tempExp.charAt(j);
			if (c == '(') {
				parentheticalDepth++;
			} else if (c == ')') {
				parentheticalDepth--;
				if (parentheticalDepth == 0) {
					return j;
				}
			}
		}
		return parentheticalDepth;
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

	private static List<String> tokenize(String expression, char splitChar) {
		expression = trim(expression);
		int parentheticalDepth = 0;
		List<String> tokens = new ArrayList<String>();
		StringBuffer currentToken = new StringBuffer();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '(') {
				parentheticalDepth++;
			} else if (c == ')') {
				parentheticalDepth--;
			} else if (parentheticalDepth == 0 && c == splitChar) {
				tokens.add(currentToken.toString());
				currentToken = new StringBuffer();

				// No need to add the splitchar so we'll fast forward
				continue;
			}
			currentToken.append(c);
		}
		tokens.add(currentToken.toString());
		return tokens;
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

	private static String trim(String expression) {
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
