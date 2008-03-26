package com.gadberry.utility.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class OperatorSet {

	private Map<String, Operator> operators = new HashMap<String, Operator>();

	public Operator findOperator(String symbol) {
		// If we have a direct match
		if (operators.get(symbol) != null) {
			return createOperator(operators.get(symbol).getClass());
		}

		// If we have a starts with match (functions)
		for (String s : operators.keySet()) {
			if (symbol.startsWith(s)) {
				return createOperator(operators.get(s).getClass());
			}
		}

		return null;
	}

	private Operator createOperator(Class<? extends Operator> c) {
		try {
			Operator o = c.newInstance();
			o.setOperatorSet(this);
			return o;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addOperator(String delimeter, Operator o) {
		operators.put(delimeter, o);
	}

	public List<String> getDelimeters() {
		return new ArrayList<String>(operators.keySet());
	}

	public static OperatorSet opSet = null;
	
	public static OperatorSet getStandardOperatorSet() {
		if(opSet == null){
			opSet = new OperatorSet();
			
			// Standard Operators
			opSet.addOperator("+", new AdditionSymbol());
			opSet.addOperator("-", new SubtractionSymbol());
			opSet.addOperator("*", new MultiplicationSymbol());
			opSet.addOperator("/", new DivisionSymbol());
			opSet.addOperator("%", new ModuloSymbol());
			
			// Boolean
			opSet.addOperator("AND", new AndSymbol());
			opSet.addOperator("&&", new AndSymbol());
			opSet.addOperator("OR", new OrSymbol());
			opSet.addOperator("||", new OrSymbol());
			opSet.addOperator("not", new NotFunction());
			
			// Math
			opSet.addOperator("max", new MaxFunction());
			opSet.addOperator("min", new MinFunction());
			opSet.addOperator("floor", new FloorFunction());
			opSet.addOperator("ceil", new CeilFunction());
			opSet.addOperator("neg", new NegFunction());
			opSet.addOperator("abs", new AbsFunction());
			opSet.addOperator("cos", new CosFunction());
			opSet.addOperator("sin", new SinFunction());
			opSet.addOperator("tan", new TanFunction());
			opSet.addOperator("acos", new AcosFunction());
			opSet.addOperator("asin", new AcosFunction());
			opSet.addOperator("atan", new AcosFunction());
			
			// Comparison
			opSet.addOperator("==", new EqualSymbol());
			opSet.addOperator(">=", new GreaterThanOrEqualSymbol());
			opSet.addOperator(">", new GreaterThanSymbol());
			opSet.addOperator("<=", new LessThanOrEqualSymbol());
			opSet.addOperator("<", new LessThanSymbol());
			
			
			// String
			opSet.addOperator("substr", new SubstrFunction());
			
			// Date
			opSet.addOperator("dateDifference", new DateDifferenceFunction());
		}

		return opSet;
	}

}
