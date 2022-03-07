package runtime;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * {@code Number} emulates original {@code CNumeric}
 * @author sliezzz
 *
 */
public class Number {
	protected MathContext mc = MathContext.UNLIMITED;
	protected BigDecimal value_decimal;
	protected int div_scale = 11;
	protected boolean is_readonly = false;
	
	public static Number ZERO = new Number(0);
	public static Number ONE = new Number(1);

	/**
	 * Inits object by {@code int}
	 */
	public Number(int new_value) {
		set(new BigDecimal(new_value));
	}

	/**
	 * Inits object by {@code int} & read-only flag
	 */
	public Number(int new_value, boolean is_readonly) {
		set(new BigDecimal(new_value));
		this.is_readonly = is_readonly;
	}

	/**
	 * Inits object by {@code BigDecimal}
	 */
	public Number(BigDecimal new_value) {
		set(new_value);
	}
	public void set(BigDecimal new_value) {
		if (is_readonly) {
			throw new ArithmeticException("Number is read-only!");
		}
		value_decimal = new_value;
	}

	/**
	 * Add
	 * @return new {@code Number} for {@code (this + num)}
	 */
	public Number add(Number num) {
		return new Number( value_decimal.add(num.value_decimal, mc) );
	}

	/**
	 * Subtract
	 * @return new {@code Number} for {@code (this - num)}
	 */
	public Number sub(Number num) {
		return new Number( value_decimal.subtract(num.value_decimal, mc) );
	}
	
	/**
	 * Multiply
	 * @return new {@code Number} for {@code (this * num)}
	 */
	public Number mul(Number num) {
		return new Number( value_decimal.multiply(num.value_decimal, mc) );
	}

	/**
	 * Divide
	 * @return new {@code Number} for {@code (this - num)}
	 */
	public Number div(Number num) {
		return new Number( value_decimal.divide(num.value_decimal, div_scale, RoundingMode.HALF_UP) );
	}
	
	/**
	 * Returns remainder of division
	 * @return new Number for {@code (this % num)}
	 */
	public Number rem(Number num) {
		return new Number( value_decimal.remainder(num.value_decimal) );
	}

	/**
	 * Checks if the number represented by this object is equal to num 
	 * @return true or false
	 */
	public boolean is_equal(Number num) {
		return (value_decimal.compareTo(num.value_decimal) == 0) ? true : false;
	}

	/**
	 * Checks if the number represented by this object is greater than num 
	 * @return true or false
	 */
	public boolean is_greater(Number num) {
		return (value_decimal.compareTo(num.value_decimal) > 0) ? true : false;
	}

	/**
	 * Checks if the number represented by this object is lesser than num 
	 * @return true or false
	 */
	public boolean is_lesser(Number num) {
		return (value_decimal.compareTo(num.value_decimal) < 0) ? true : false;
	}

	/**
	 * Returns string's representation of {@code Number}
	 */
	public String toString(int scale, int precision) {
		DecimalFormat df = new DecimalFormat();
		if (scale > 0) {
			df.setMaximumIntegerDigits(scale-1-precision);
			df.setMaximumFractionDigits(precision);
			df.setMinimumFractionDigits(precision);
		}
		df.setGroupingUsed(false);
		return df.format(value_decimal);
	}
}
