package com.gadberry.utility.expression.function;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gadberry.utility.CalendarUtils;
import com.gadberry.utility.expression.Argument;
import com.gadberry.utility.expression.InvalidArgumentsException;
import com.gadberry.utility.expression.Resolver;

public class DateDifferenceFunction extends Function {

	protected void checkArgs(List<Argument> args)
			throws InvalidArgumentsException {
		if (!args.get(0).isDate() || !args.get(1).isDate()) {
			throw new InvalidArgumentsException(
					"DateDifferenceOperator requires two dates and one int.  Wrong type of arguments provided.");
		}

		differenceCheckUnit(args.get(2).toInteger());
	}

	public Argument resolve(Resolver resolver) {
		Date d1 = getArgument(0).toDate();
		Date d2 = getArgument(1).toDate();
		int unit = getArgument(2).toInteger();
		return new Argument(CalendarUtils.difference(d1, d2, unit), resolver);
	}

	private static void differenceCheckUnit(int unit)
			throws InvalidArgumentsException {
		List<Integer> validUnits = new ArrayList<Integer>();
		validUnits.add(Calendar.YEAR);
		validUnits.add(Calendar.MONTH);
		validUnits.add(Calendar.DAY_OF_MONTH);
		validUnits.add(Calendar.HOUR_OF_DAY);
		validUnits.add(Calendar.MINUTE);
		validUnits.add(Calendar.SECOND);
		validUnits.add(Calendar.MILLISECOND);

		if (!validUnits.contains(unit)) {
			throw new InvalidArgumentsException(
					"CalendarUtils.difference requires a unit int in the list of Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND,Calendar.MILLISECOND.");
		}
	}
}
