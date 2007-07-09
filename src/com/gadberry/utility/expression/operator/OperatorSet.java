package com.gadberry.utility.expression.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	public String getSymbol(Class<? extends Operator> c) {
		for (Entry<String, Class<? extends Operator>> entry : operators
				.entrySet()) {
			if (entry.getValue().equals(c)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public List<String> getSymbols() {
		return new ArrayList<String>(operators.keySet());
	}

	public int getOperatorLength(Class<? extends Operator> c) {
		return getSymbol(c).length();
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
		os.addOperator("max", MaxOperator.class);
		os.addOperator("min", MinOperator.class);
		os.addOperator("floor", FloorOperator.class);
		os.addOperator("ceil", CeilOperator.class);
		os.addOperator("neg", NegOperator.class);
		os.addOperator("abs", AbsOperator.class);
		os.addOperator("cos", CosOperator.class);
		os.addOperator("sin", SinOperator.class);
		os.addOperator("tan", TanOperator.class);
		os.addOperator("acos", AcosOperator.class);
		os.addOperator("asin", AcosOperator.class);
		os.addOperator("atan", AcosOperator.class);
		
		os.addOperator("AND", AndOperator.class);
		os.addOperator("&&", AndOperator.class);
		os.addOperator("OR", OrOperator.class);
		os.addOperator("||", OrOperator.class);
		os.addOperator("not", NotOperator.class);

		os.addOperator("substr", SubstrOperator.class);
		
		os.addOperator("dateDifference", DateDifferenceOperator.class);

		return os;
	}

}
