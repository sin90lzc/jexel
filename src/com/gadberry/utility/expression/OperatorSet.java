package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gadberry.utility.expression.function.AbsFunction;
import com.gadberry.utility.expression.function.AcosFunction;
import com.gadberry.utility.expression.function.AsinFunction;
import com.gadberry.utility.expression.function.AtanFunction;
import com.gadberry.utility.expression.function.CeilFunction;
import com.gadberry.utility.expression.function.CosFunction;
import com.gadberry.utility.expression.function.DateDifferenceFunction;
import com.gadberry.utility.expression.function.DegreesToRadiansFunction;
import com.gadberry.utility.expression.function.FloorFunction;
import com.gadberry.utility.expression.function.MaxFunction;
import com.gadberry.utility.expression.function.MinFunction;
import com.gadberry.utility.expression.function.NegFunction;
import com.gadberry.utility.expression.function.NotFunction;
import com.gadberry.utility.expression.function.RadiansToDegreesFunction;
import com.gadberry.utility.expression.function.SinFunction;
import com.gadberry.utility.expression.function.SubstrFunction;
import com.gadberry.utility.expression.function.TanFunction;
import com.gadberry.utility.expression.symbol.AdditionSymbol;
import com.gadberry.utility.expression.symbol.AndSymbol;
import com.gadberry.utility.expression.symbol.DivisionSymbol;
import com.gadberry.utility.expression.symbol.EqualSymbol;
import com.gadberry.utility.expression.symbol.GreaterThanOrEqualSymbol;
import com.gadberry.utility.expression.symbol.GreaterThanSymbol;
import com.gadberry.utility.expression.symbol.LessThanOrEqualSymbol;
import com.gadberry.utility.expression.symbol.LessThanSymbol;
import com.gadberry.utility.expression.symbol.ModuloSymbol;
import com.gadberry.utility.expression.symbol.MultiplicationSymbol;
import com.gadberry.utility.expression.symbol.OrSymbol;
import com.gadberry.utility.expression.symbol.SubtractionSymbol;

/**
 * @author Aaron Gadberry
 */

public class OperatorSet {

	private static OperatorSet standardOperatorSet = null;

	/**
	 * Get the standard set of operators included
	 * 
	 * @return the standard set of operators
	 */
	public static OperatorSet getStandardOperatorSet() {
		if (standardOperatorSet == null) {
			standardOperatorSet = new OperatorSet();

			// Standard Operators
			standardOperatorSet.addOperator("+", AdditionSymbol.class);
			standardOperatorSet.addOperator("-", SubtractionSymbol.class);
			standardOperatorSet.addOperator("*", MultiplicationSymbol.class);
			standardOperatorSet.addOperator("/", DivisionSymbol.class);
			standardOperatorSet.addOperator("%", ModuloSymbol.class);

			// Boolean
			standardOperatorSet.addOperator("AND", AndSymbol.class);
			standardOperatorSet.addOperator("&&", AndSymbol.class);
			standardOperatorSet.addOperator("OR", OrSymbol.class);
			standardOperatorSet.addOperator("||", OrSymbol.class);
			standardOperatorSet.addOperator("not", NotFunction.class);

			// Math
			standardOperatorSet.addOperator("max", MaxFunction.class);
			standardOperatorSet.addOperator("min", MinFunction.class);
			standardOperatorSet.addOperator("floor", FloorFunction.class);
			standardOperatorSet.addOperator("ceil", CeilFunction.class);
			standardOperatorSet.addOperator("neg", NegFunction.class);
			standardOperatorSet.addOperator("abs", AbsFunction.class);
			standardOperatorSet.addOperator("cos", CosFunction.class);
			standardOperatorSet.addOperator("sin", SinFunction.class);
			standardOperatorSet.addOperator("tan", TanFunction.class);
			standardOperatorSet.addOperator("acos", AcosFunction.class);
			standardOperatorSet.addOperator("asin", AsinFunction.class);
			standardOperatorSet.addOperator("atan", AtanFunction.class);
			standardOperatorSet.addOperator("rad", DegreesToRadiansFunction.class);
			standardOperatorSet.addOperator("deg", RadiansToDegreesFunction.class);

			// Comparison
			standardOperatorSet.addOperator("==", EqualSymbol.class);
			standardOperatorSet.addOperator(">=", GreaterThanOrEqualSymbol.class);
			standardOperatorSet.addOperator(">", GreaterThanSymbol.class);
			standardOperatorSet.addOperator("<=", LessThanOrEqualSymbol.class);
			standardOperatorSet.addOperator("<", LessThanSymbol.class);

			// String
			standardOperatorSet.addOperator("substr", SubstrFunction.class);

			// Date
			standardOperatorSet.addOperator("dateDifference", DateDifferenceFunction.class);
		}

		return standardOperatorSet;
	}

	private Map<String, Class<? extends Operator>> operators = new HashMap<String, Class<? extends Operator>>();

	/**
	 * Add an {@link Operator} to this OperatorSet with a given delimiter
	 * 
	 * @param delimiter
	 *            The symbol to use to parse this {@link Operator}, such as "+"
	 *            or "cos".
	 * 
	 * @param c
	 *            The class implementing {@link Operator} to add to this
	 *            OperatorSet.
	 */
	public void addOperator(String delimiter, Class<? extends Operator> c) {
		operators.put(delimiter, c);
	}

	private Operator createOperator(Class<? extends Operator> c, Expression expression) {
		try {
			Operator o = c.newInstance();
			o.setExpression(expression);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	Operator findOperator(String symbol, Expression expression) {
		// If we have a direct match
		if (operators.get(symbol) != null) {
			return createOperator(operators.get(symbol), expression);
		}

		// If we have a starts with match (functions)
		for (String s : operators.keySet()) {
			if (symbol.startsWith(s)) {
				return createOperator(operators.get(s), expression);
			}
		}

		return null;
	}

	List<String> getDelimeters() {
		return new ArrayList<String>(operators.keySet());
	}

}
