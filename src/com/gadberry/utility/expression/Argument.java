package com.gadberry.utility.expression;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Aaron Gadberry
 */
public class Argument {
	static String stripLiteral(String string) {
		String literalAsString = String.valueOf(Expression.LITERAL_CHARACTER);
		if (string.startsWith(literalAsString) && string.endsWith(literalAsString)) {
			String newString = string.substring(1, string.length() - 1);
			if(newString.contains(literalAsString)){
				return string;
			}
			return newString;
		}
		return string.replaceAll("''", "'");
	}

	private Object arg = null;

	/**
	 * Create an Argument for use in an Operator or to return from an Operator.
	 * 
	 * @param object
	 *            The subject of the Argument.
	 * 
	 * @param resolver
	 *            The {@link Resolver} to utilize on this argument. Null
	 *            indicates no resolver should be used.
	 */
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
			} else if(isLiteral() || a.isLiteral()) {
				return toString().equals(a.toString());
			} else {
				return getObject().equals(a.getObject());
			}
		}
		return false;
	}

	/**
	 * Get the base object of this Argument.
	 * 
	 * @return the unedited base object
	 */
	public Object getObject() {
		return arg;
	}

	/**
	 * Determine if the base object can be interpreted as a {@link Boolean}
	 * value.
	 * 
	 * @return whether or not the argument can be interpreted as a boolean
	 *         value.
	 */
	public boolean isBoolean() {
		try {
			toBoolean();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	/**
	 * Determine if the base object can be interpreted as a {@link Date} value.
	 * 
	 * @return whether or not the argument can be interpreted as a date value.
	 */
	public boolean isDate() {
		try {
			toDate();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	/**
	 * Determine if the base object can be interpreted as a {@link Double}
	 * value.
	 * 
	 * @return whether or not the argument can be interpreted as a double value.
	 */
	public boolean isDouble() {
		try {
			toDouble();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	/**
	 * Determine if the base object can be interpreted as a {@link Integer}
	 * value. This will return true in the case of a decimal number.
	 * 
	 * @return whether or not the argument can be interpreted as a integer
	 *         value.
	 */
	public boolean isInteger() {
		try {
			toInteger();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	/**
	 * Determine if the base object is a literal string.
	 * 
	 * @return whether or not the argument is surrounded by the
	 *         Expression.LITERAL_CHARACTER constant
	 */
	public boolean isLiteral() {
		return !isNull() && !stripLiteral(arg.toString()).equals(arg.toString());
	}

	/**
	 * Determine if the base object is null.
	 * 
	 * @return whether or not the argument is null
	 */
	public boolean isNull() {
		return arg == null;
	}

	boolean isResolved() {
		return isDate() || isDouble() || isInteger() || isBoolean() || isLiteral();
	}

	/**
	 * Return a {@link Boolean} interpretation of the base object if a direct
	 * conversion can take place.
	 * 
	 * @return Boolean interpretation of the base object.
	 * 
	 * @throws ArgumentCastException
	 *             is thrown if the base object is not a {@link Boolean} and the
	 *             base object is not a String with a value of "true" or
	 *             "false".
	 */
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
		throw new ArgumentCastException(ArgumentCastException.createMessage("boolean", this));
	}

	/**
	 * Return a {@link Date} interpretation of the base object if a direct
	 * conversion can take place.
	 * 
	 * @return Date interpretation of the base object.
	 * 
	 * @throws ArgumentCastException
	 *             is thrown if the base object is not a {@link Date} and the
	 *             base object is not a {@link Calendar}.
	 */
	public Date toDate() throws ArgumentCastException {
		if (arg instanceof Date) {
			return ((Date) arg);
		} else if (arg instanceof Calendar) {
			return ((Calendar) arg).getTime();
		}
		throw new ArgumentCastException(ArgumentCastException.createMessage("date", this));
	}

	/**
	 * Return a {@link Double} interpretation of the base object if a direct
	 * conversion can take place.
	 * 
	 * @return Double interpretation of the base object.
	 * 
	 * @throws ArgumentCastException
	 *             is thrown if the base object is not a {@link Double} and the
	 *             base object is not a {@link Integer} and the base object is
	 *             not a {@link Float} and the base object is not a {@link Long}.
	 */
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
		throw new ArgumentCastException(ArgumentCastException.createMessage("double", this));
	}

	/**
	 * Return a {@link Integer} interpretation of the base object if a direct
	 * conversion can take place.
	 * 
	 * @return Integer interpretation of the base object.
	 * 
	 * @throws ArgumentCastException
	 *             is thrown if the base object is not a {@link Double} and the
	 *             base object is not a {@link Integer} and the base object is
	 *             not a {@link Float} and the base object is not a {@link Long}.
	 */
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
		throw new ArgumentCastException(ArgumentCastException.createMessage("integer", this));
	}
	
	@Override
	public String toString() {
		return stripLiteral(arg.toString());
	}
}
