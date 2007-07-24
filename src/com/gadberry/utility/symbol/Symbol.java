package com.gadberry.utility.expression.symbol;

import java.util.ArrayList;
import java.util.List;

import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.OperatorImpl;
import com.gadberry.utility.expression.Resolver;

public abstract class Symbol extends OperatorImpl {

	public abstract Argument resolve(Resolver resolver);

	public abstract int getPriority();

	@Override
	public List<Argument> parseArgs(List<String> tokens, int position,
			Resolver resolver) {
		List<Argument> args = new ArrayList<Argument>();
		if (position > 0) {
			// There is a left hand argument
			StringBuffer lhs = new StringBuffer();
			for (int j = 0; j < position; j++) {
				lhs.append(tokens.get(j));
			}
			args.add(new Argument(lhs.toString().trim(), resolver));
			// System.out.println("LHS:" + lhs.toString());
		}
		if (position < tokens.size()) {
			// There is a right hand argument
			StringBuffer rhs = new StringBuffer();
			for (int j = position + 1; j < tokens.size(); j++) {
				rhs.append(tokens.get(j));
			}
			args.add(new Argument(rhs.toString().trim(), resolver));
			// System.out.println("RHS:" + rhs.toString());
		}
		return args;
	}

}
