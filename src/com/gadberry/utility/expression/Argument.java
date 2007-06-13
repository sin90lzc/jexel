package com.gadberry.utility.expression;

public class Argument {
	private Object arg = null;

	public Argument(Object object, Resolver resolver) {
		if (resolver != null && resolver.canResolve(object.toString())) {
			arg = resolver.resolve(object.toString());
		}
		arg = object;
	}

	Object getObject() {
		return arg;
	}

	public boolean isDouble() {
		try {
			toDouble();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public boolean isInt() {
		try {
			toInt();
			return true;
		} catch (ArgumentCastException e) {
			return false;
		}
	}

	public double toDouble() throws ArgumentCastException {
		if (this.arg instanceof Integer) {
			return ((Integer) this.arg).doubleValue();
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
		throw new ArgumentCastException(
				"Argument can not be interpreted as an double.  Arg: "
						+ this.arg.toString());
	}

	float toFloat() throws ArgumentCastException {
		if (this.arg instanceof Integer) {
			return ((Integer) this.arg).floatValue();
		} else if (this.arg instanceof Double) {
			return ((Double) this.arg).floatValue();
		} else if (this.arg instanceof Float) {
			return ((Float) this.arg).floatValue();
		} else if (this.arg instanceof String) {
			try {
				return Float.parseFloat(((String) this.arg));
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException(
				"Argument can not be interpreted as an float.  Arg: "
						+ this.arg.toString());
	}

	int toInt() throws ArgumentCastException {
		if (this.arg instanceof Integer) {
			return ((Integer) this.arg).intValue();
		} else if (this.arg instanceof Double) {
			return ((Double) this.arg).intValue();
		} else if (this.arg instanceof Float) {
			return ((Float) this.arg).intValue();
		} else if (this.arg instanceof String) {
			try {
				return Integer.parseInt(((String) this.arg));
			} catch (NumberFormatException e) {
				// Allow to pass through to ArgumentCastException
			}
		}
		throw new ArgumentCastException(
				"Argument can not be interpreted as an int.  Arg: "
						+ this.arg.toString());
	}

	public String toString() {
		return this.arg.toString();
	}
}
