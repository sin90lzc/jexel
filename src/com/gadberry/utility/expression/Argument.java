package com.gadberry.utility.expression;

import java.util.Calendar;
import java.util.Date;

public class Argument {
	private static String stripLiteral(String string) {
		if (string.startsWith(Expression.LITERAL_CHARACTER) && string.endsWith(Expression.LITERAL_CHARACTER)) {
			return string.substring(1, string.length() - 1);
		}
		return string.replaceAll("''", "'");
	}

	private Object arg = null;

	public Argument(Object object, Resolver resolver) {
		if (resolver != null && resolver.canResolve(object.toString())) {
			arg = resolver.resolve(object.toString());
		} else {
			arg = object;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Argument) {
			Argument a = (Argument) o;
			if (isDouble() && a.isDouble()) {
				return toDouble() == a.toDouble();
			} else {
				return getObject().equals(a.getObject());
			}
		}
		return false;
	}

	public Object getObject() {
		return arg;
	}

	public boolean isBoolean() {
		try {
			toBoolean();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public boolean isDate() {
		try {
			toDate();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public boolean isDouble() {
		try {
			toDouble();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public boolean isInteger() {
		try {
			toInteger();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public boolean isLiteral() {
		return !stripLiteral(arg.toString()).equals(arg.toString());
	}

	public boolean isResolved() {
		return isDate() || isDouble() || isInteger() || isBoolean() || isLiteral();
	}

	public boolean toBoolean() throws ArgumentCastException {
		if (arg instanceof Boolean) {
			return ((Boolean) arg).booleanValue();
		} else if (arg instanceof String) {
			if (((String) arg).equalsIgnoreCase("true")) {
				return true;
			} else if (((String) arg).equalsIgnoreCase("false")) {
				return false;
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an boolean.  Arg: "
				+ arg.getClass().toString() + arg.toString());
	}

	public Date toDate() throws ArgumentCastException {
		if (arg instanceof Date) {
			return ((Date) arg);
		} else if (arg instanceof Calendar) {
			return ((Calendar) arg).getTime();
		}
		throw new ArgumentCastException("Argument can not be interpreted as a date.  Arg: " + arg.toString());
	}

	public double toDouble() throws ArgumentCastException {
		if (arg instanceof Integer) {
			return ((Integer) arg).doubleValue();
		} else if (arg instanceof Long) {
			return ((Long) arg).doubleValue();
		} else if (arg instanceof Double) {
			return ((Double) arg).doubleValue();
		} else if (arg instanceof Float) {
			return ((Float) arg).doubleValue();
		} else if (arg instanceof String) {
			try {
				return Double.parseDouble(((String) arg));
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an double.  Arg: " + arg.toString());
	}

	public int toInteger() throws ArgumentCastException {
		if (arg instanceof Integer) {
			return ((Integer) arg).intValue();
		} else if (arg instanceof Long) {
			return ((Long) arg).intValue();
		} else if (arg instanceof Double) {
			return ((Double) arg).intValue();
		} else if (arg instanceof Float) {
			return ((Float) arg).intValue();
		} else if (arg instanceof String) {
			try {
				Double parsed = Double.parseDouble(((String) arg));
				return parsed.intValue();
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an integer.  Arg: " + arg.toString());
	}

	@Override
	public String toString() {
		return stripLiteral(arg.toString());
	}
}
