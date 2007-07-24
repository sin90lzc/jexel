package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gadberry.utility.expression.function.AbsFunction;
import com.gadberry.utility.expression.function.AcosFunction;
import com.gadberry.utility.expression.function.CeilFunction;
import com.gadberry.utility.expression.function.CosFunction;
import com.gadberry.utility.expression.function.DateDifferenceFunction;
import com.gadberry.utility.expression.function.FloorFunction;
import com.gadberry.utility.expression.function.MaxFunction;
import com.gadberry.utility.expression.function.MinFunction;
import com.gadberry.utility.expression.function.NegFunction;
import com.gadberry.utility.expression.function.NotFunction;
import com.gadberry.utility.expression.function.SinFunction;
import com.gadberry.utility.expression.function.SubstrFunction;
import com.gadberry.utility.expression.function.TanFunction;
import com.gadberry.utility.expression.symbol.AdditionOperator;
import com.gadberry.utility.expression.symbol.AndOperator;
import com.gadberry.utility.expression.symbol.DivisionOperator;
import com.gadberry.utility.expression.symbol.ModuloOperator;
import com.gadberry.utility.expression.symbol.MultiplicationOperator;
import com.gadberry.utility.expression.symbol.OrOperator;
import com.gadberry.utility.expression.symbol.SubtractionOperator;

public class OperatorSet {

	private Map<String, Class<? extends Operator>> operators = new HashMap<String, Class<? extends Operator>>();

	private Class<? extends Operator> findOperatorClass(String symbol) {
		// If we have a direct match
		if (operators.get(symbol) != null) {
			return operators.get(symbol);
		}

		// If we have a starts with match (functions)
		for (String s : operators.keySet()) {
			if (symbol.startsWith(s)) {
				return operators.get(s);
			}
		}

		return null;
	}

	private Operator createOperator(Class<? extends Operator> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Operator findOperator(String symbol) {
		Class<? extends Operator> operatorClass = findOperatorClass(symbol);
		if (operatorClass != null) {
			return createOperator(operatorClass);
		}
		return null;
	}

	public void addOperator(String symbol, Class<? extends Operator> c) {
		operators.put(symbol, c);
	}

	public String getOperator(Class<? extends Operator> c) {
		for (Entry<String, Class<? extends Operator>> entry : operators
				.entrySet()) {
			if (entry.getValue().equals(c)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public List<String> getOperators() {
		return new ArrayList<String>(operators.keySet());
	}

	public int getOperatorLength(Class<? extends Operator> c) {
		return getOperator(c).length();
	}

	public static OperatorSet getStandardOperatorSet() {
		OperatorSet os = new OperatorSet();
		// Standard Operators
		os.addOperator("+", AdditionOperator.class);
		os.addOperator("-", SubtractionOperator.class);
		os.addOperator("*", MultiplicationOperator.class);
		os.addOperator("/", DivisionOperator.class);
		os.addOperator("%", ModuloOperator.class);

		// Functions
		os.addOperator("max", MaxFunction.class);
		os.addOperator("min", MinFunction.class);
		os.addOperator("floor", FloorFunction.class);
		os.addOperator("ceil", CeilFunction.class);
		os.addOperator("neg", NegFunction.class);
		os.addOperator("abs", AbsFunction.class);
		os.addOperator("cos", CosFunction.class);
		os.addOperator("sin", SinFunction.class);
		os.addOperator("tan", TanFunction.class);
		os.addOperator("acos", AcosFunction.class);
		os.addOperator("asin", AcosFunction.class);
		os.addOperator("atan", AcosFunction.class);
		
		os.addOperator("AND", AndOperator.class);
		os.addOperator("&&", AndOperator.class);
		os.addOperator("OR", OrOperator.class);
		os.addOperator("||", OrOperator.class);
		os.addOperator("not", NotFunction.class);

		os.addOperator("substr", SubstrFunction.class);
		
		os.addOperator("dateDifference", DateDifferenceFunction.class);

		return os;
	}

}
