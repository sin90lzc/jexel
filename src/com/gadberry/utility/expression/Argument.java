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

	public String toString() {
		return this.arg.toString();
	}
	
	public boolean equals(Object o){
		if(o instanceof Argument){
			Argument a = (Argument) o;
			if(isDouble() && a.isDouble()){
				try {
					return toDouble() == a.toDouble();
				} catch (ArgumentCastException e) {
				}
			} else {
				return toString().equals(a.toString());
			}
		}
		return false;
	}
}
