package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.Expression;
import com.gadberry.utility.expression.OperatorImpl;
import com.gadberry.utility.expression.Resolver;

public abstract class Function extends OperatorImpl {

	public abstract Argument resolve();

	@Override
	public List<Argument> parseArgs(List<String> tokens, int position,
			Resolver resolver) {
		//System.out.println(tokens.toString());
		//System.out.println("Function: " + tokens.get(0));
		//System.out.println("Argument: " + tokens.get(1));
		//System.out.println();
		List<Argument> args = new ArrayList<Argument>();
		String argumentString = Expression.trim(tokens.get(1));
		List<String> stringArgs = tokenize(argumentString, ',');
		for (String stringArg : stringArgs) {
			args.add(new Argument(stringArg.trim(), resolver));
		}
		return args;
	}

	private static List<String> tokenize(String expression, char splitChar) {
		expression = Expression.trim(expression);
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

	public int getPriority() {
		return 20;
	}

}
