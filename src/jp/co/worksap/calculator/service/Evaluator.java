package jp.co.worksap.calculator.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.worksap.calculator.utils.CommonUtilities;

public class Evaluator {
	static BigDecimal E = new BigDecimal(
			"2.71828182845904523536028747135266249775724709369995957496696762772407663035354");


	static BigDecimal PI = new BigDecimal(
			"3.1415926535897932384626433832795028841971693993751058209749445923078164062862");

	private static BigInteger factorial(BigInteger n) {
		BigInteger f = BigInteger.ONE;
		BigInteger g = new BigInteger(n.toString());

		while (g.compareTo(BigInteger.ONE) == 1) {
			f = f.multiply(g);
			g = g.subtract(BigInteger.ONE);
		}

		return f;
	}

	public static String calculate(String[] postFix, boolean isRadian) {
		if (postFix.length == 0) { return "0"; }

		try {
			Stack<BigDecimal> s = new Stack<BigDecimal>();

			for (String token : postFix) {
//				System.out.println(token);
				try {
					BigDecimal number = new BigDecimal(token);
					s.push(number);
				} catch (NumberFormatException ex) {
					boolean toNegate = false;
					if (token.length() > 1 && token.charAt(0) == '-') {
						toNegate = true;
						token = token.substring(1);
					}

					BigDecimal res = null;

					if (token.equals("pi")) {
						res = PI;
					} else if (token.equals("e")) {
						res = E;
					} else if (token.equals("negate")) {
						BigDecimal operand1 = s.pop();
						res = operand1.negate();
					} else if (token.equals("*")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();
						res = operand1.multiply(operand2).setScale(90, RoundingMode.HALF_DOWN);
					} else if (token.equals("/")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();

						if (operand2.compareTo(BigDecimal.ZERO) == 0 && operand1.compareTo(BigDecimal.ZERO) == 0) {
							throw new IllegalArgumentException("Result is undefined");
						}
						res = operand1.divide(operand2, 100, RoundingMode.HALF_UP).setScale(100, RoundingMode.HALF_UP);
					} else if (token.equals("+")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();
						res = operand1.add(operand2);
					} else if (token.equals("-")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();
						res = operand1.subtract(operand2);
					} else if (token.equals("^")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();

						boolean isCalculated = false;
						if (operand2.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
							if (operand2.compareTo(new BigDecimal(1001)) == -1 && operand2.compareTo(BigDecimal.ONE) == 1) {
								res = operand1.pow(operand2.intValue());
								isCalculated = true;
							}
						}

						if (!isCalculated) {
							Double tRes = Math.pow(operand1.doubleValue(), operand2.doubleValue());
							if (tRes.isNaN()) {
								throw new IllegalArgumentException("Invalid exponent");
							} else if (tRes.isInfinite()) {
								throw new IllegalArgumentException("Exponent is too big or invalid exponent");
							}
							res = new BigDecimal(tRes.toString());
						}
					} else if (token.equals("sin")) {
						BigDecimal operand1 = s.pop();

						if (isRadian) {
							res = (new BigDecimal(Double.toString(Math.sin(operand1.doubleValue())))).setScale(14, RoundingMode.HALF_UP);
						} else {
							res = (new BigDecimal(Double.toString(Math.sin(Math.toRadians(operand1.doubleValue()))))).setScale(14, RoundingMode.HALF_UP);
						}

					} else if (token.equals("cos")) {
						BigDecimal operand1 = s.pop();
						if (isRadian) {
							res = (new BigDecimal(Double.toString(Math.cos(operand1.doubleValue())))).setScale(14, RoundingMode.HALF_UP);
						} else {
							res = (new BigDecimal(Double.toString(Math.cos(Math.toRadians(operand1.doubleValue()))))).setScale(14, RoundingMode.HALF_UP);
						}
					} else if (token.equals("tan")) {
						BigDecimal operand1 = s.pop();
						BigDecimal opSin, opCos;

						if (isRadian) {
							opSin = new BigDecimal(Double.toString(Math.sin(operand1.doubleValue()))).setScale(15, RoundingMode.HALF_UP);
							opCos = new BigDecimal(Double.toString(Math.cos(operand1.doubleValue()))).setScale(15, RoundingMode.HALF_UP);
						} else {
							opSin = new BigDecimal(Double.toString(Math.sin(Math.toRadians(operand1.doubleValue())))).setScale(15, RoundingMode.HALF_UP);
							opCos = new BigDecimal(Double.toString(Math.cos(Math.toRadians(operand1.doubleValue())))).setScale(15, RoundingMode.HALF_UP);
						}

						if (opCos.compareTo(BigDecimal.ZERO) == 0) {
							throw new IllegalArgumentException("tan result is infinite");
						}
						res = opSin.divide(opCos, 14, RoundingMode.HALF_UP);
					} else if (token.equals("asin")) {
						BigDecimal operand1 = s.pop();

						if (isRadian) {
							res = (new BigDecimal(Double.toString(Math.asin(operand1.doubleValue())))).setScale(15, RoundingMode.HALF_UP);
						} else {
							res = (new BigDecimal(Double.toString(Math.toDegrees(Math.asin(operand1.doubleValue()))))).setScale(15, RoundingMode.HALF_UP);
						}
					} else if (token.equals("acos")) {
						BigDecimal operand1 = s.pop();
						if (isRadian) {
							res = (new BigDecimal(Double.toString(Math.acos(operand1.doubleValue())))).setScale(15, RoundingMode.HALF_UP);
						} else {
							res = (new BigDecimal(Double.toString(Math.toDegrees(Math.acos(operand1.doubleValue()))))).setScale(15, RoundingMode.HALF_UP);
						}
					} else if (token.equals("atan")) {
						BigDecimal operand1 = s.pop();
						if (isRadian) {
							res = (new BigDecimal(Double.toString(Math.atan(operand1.doubleValue())))).setScale(15, RoundingMode.HALF_UP);
						} else {
							res = (new BigDecimal(Double.toString(Math.toDegrees(Math.atan(operand1.doubleValue()))))).setScale(15, RoundingMode.HALF_UP);
						}
					} else if (token.equals("round")) {
						BigDecimal operand1 = s.pop();
						res = operand1.setScale(0, RoundingMode.HALF_UP);
					} else if (token.equals("ceil")) {
						BigDecimal operand1 = s.pop();
						res = operand1.setScale(0, RoundingMode.CEILING);
					} else if (token.equals("floor")) {
						BigDecimal operand1 = s.pop();
						res = operand1.setScale(0, RoundingMode.FLOOR);
					} else if (token.equals("mod")) {
						BigDecimal operand2 = s.pop();
						BigDecimal operand1 = s.pop();
						res = operand1.remainder(operand2);
					} else if (token.equals("fact")) {
						BigDecimal operand1 = s.pop();
						res = new BigDecimal(factorial(operand1
								.toBigIntegerExact()));
					} else if (token.equals("sqrt")) {
						BigDecimal operand1 = s.pop();
						if (operand1.compareTo(BigDecimal.ZERO) == -1) {
							throw new IllegalArgumentException("Square root of negative is undefined");
						}
						res = new BigDecimal(Double.toString(Math.sqrt(operand1.doubleValue()))).setScale(15, RoundingMode.HALF_UP);
					} else if (token.equals("cbrt")) {
						BigDecimal operand1 = s.pop();

						res = new BigDecimal(Double.toString(Math.cbrt(operand1.doubleValue()))).setScale(15, RoundingMode.HALF_UP);
					} else if (token.equals("log")) {
						BigDecimal operand1 = s.pop();
						Double tRes = Math.log10(operand1.doubleValue());
						if (tRes.isNaN()) {
							throw new IllegalArgumentException("log argument can't be less than 0");
						} else if (tRes == Double.NEGATIVE_INFINITY) {
							throw new IllegalArgumentException("log argument can't be 0");
						}
						res = new BigDecimal(Double.toString(tRes));
					} else if (token.equals("ln")) {
						BigDecimal operand1 = s.pop();
						Double tRes = Math.log(operand1.doubleValue());
						if (tRes.isNaN()) {
							throw new IllegalArgumentException("ln argument can't be less than 0");
						} else if (tRes == Double.NEGATIVE_INFINITY) {
							throw new IllegalArgumentException("ln argument can't be 0");
						}
						res = new BigDecimal(Double.toString(tRes));
					}

					if (res != null) {
						if (toNegate) {
							s.push(res.negate());
						} else {
							s.push(res);
						}
					}
				}
//				System.out.println(CommonUtilities.listToString(s));
			}
			String res = s.pop().stripTrailingZeros().toPlainString();

//			System.out.println("== " + res);
			Pattern p1 = Pattern.compile("(^\\d+\\.\\d*?)0+$");
			Matcher m1 = p1.matcher(res);
			if (m1.matches()) {
				Pattern p2 = Pattern.compile("(^\\d+)\\.0+$");
				Matcher m2 = p2.matcher(res);
				if (m2.matches()) {
					return m2.group(1);
				} else {
					return m1.group(1);
				}
			} else {
				return res;
			}

		} catch (ArithmeticException ex) {
			if (ex.getMessage().equals("Division by zero") || ex.getMessage().equals("BigInteger divide by zero")) {
				throw new IllegalArgumentException("Cannot divide by zero");
			} else if (ex.getMessage().equals("Division undefined")) {
				throw new IllegalArgumentException("Result is undefined");
			} else {
//				System.out.println(ex.getMessage());
				throw new IllegalArgumentException("Invalid");
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Invalid");
		}
	}

	public static void main(String[] args) {
		String exp = "0.1 0.2 +";
		try {
			String[] postFix = ShuntingYard.parse(exp);
			System.out.println(CommonUtilities.arrayToString(postFix));
			String res = calculate(postFix, true);
			System.out.println(res);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
