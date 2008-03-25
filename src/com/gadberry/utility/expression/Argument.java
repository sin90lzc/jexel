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

	public boolean equals(Object o) {
		if (o instanceof Argument) {
			Argument a = (Argument) o;
			if(isDouble() && a.isDouble()){
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
		return !stripLiteral(this.arg.toString()).equals(this.arg.toString());
	}

	public boolean isResolved() {
		return isDate() || isDouble() || isInteger() || isBoolean() || isLiteral();
	}

	public boolean toBoolean() throws ArgumentCastException {
		if (this.arg instanceof Boolean) {
			return ((Boolean) this.arg).booleanValue();
		} else if (this.arg instanceof String) {
			if (((String) this.arg).equalsIgnoreCase("true")) {
				return true;
			} else if (((String) this.arg).equalsIgnoreCase("false")) {
				return false;
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an boolean.  Arg: "
				+ this.arg.getClass().toString() + this.arg.toString());
	}

	public Date toDate() throws ArgumentCastException {
		if (this.arg instanceof Date) {
			return ((Date) this.arg);
		} else if (this.arg instanceof Calendar) {
			return ((Calendar) this.arg).getTime();
		}
		throw new ArgumentCastException("Argument can not be interpreted as a date.  Arg: " + this.arg.toString());
	}

	public double toDouble() throws ArgumentCastException {
		if (this.arg instanceof Integer) {
			return ((Integer) this.arg).doubleValue();
		} else if (this.arg instanceof Long) {
			return ((Long) this.arg).doubleValue();
		} else if (this.arg instanceof Double) {
			return ((Double) this.arg).doubleValue();
		} else if (this.arg instanceof Float) {
			return ((Float) this.arg).doubleValue();
		} else if (this.arg instanceof String) {
			try {
				return Double.parseDouble(((String) this.arg));
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an double.  Arg: " + this.arg.toString());
	}

	public int toInteger() throws ArgumentCastException {
		if (this.arg instanceof Integer) {
			return ((Integer) this.arg).intValue();
		} else if (this.arg instanceof Long) {
			return ((Long) this.arg).intValue();
		} else if (this.arg instanceof Double) {
			return ((Double) this.arg).intValue();
		} else if (this.arg instanceof Float) {
			return ((Float) this.arg).intValue();
		} else if (this.arg instanceof String) {
			try {
				Double parsed = Double.parseDouble(((String) this.arg));
				return parsed.intValue();
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException("Argument can not be interpreted as an integer.  Arg: " + this.arg.toString());
	}

	public String toString() {
		return stripLiteral(this.arg.toString());
	}
}
