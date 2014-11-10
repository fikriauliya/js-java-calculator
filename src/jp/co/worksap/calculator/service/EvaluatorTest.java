package jp.co.worksap.calculator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class EvaluatorTest {
	private String calc(String expression, boolean isRadian) {
		return Evaluator.calculate(ShuntingYard.parse(expression), isRadian);
	}

	private String calc(String expression) {
		return Evaluator.calculate(ShuntingYard.parse(expression), true);
	}

	@Test
	public void testPlus() {
		assertEquals(calc("pi + -pi"), "0");
		assertEquals(calc("e + -e"), "0");
		assertEquals(calc("1 + 1"), "2");
		assertEquals(calc("0.1 + 0.2"), "0.3");
		assertEquals(calc("0.1 + -0.2"), "-0.1");
		assertEquals(calc("0.000000000000000000000000000000000000000000000000000000000001 + 0.1"), "0.100000000000000000000000000000000000000000000000000000000001");
	}

	@Test
	public void testMinus() {
		assertEquals(calc("0 - 1"), "-1");
		assertEquals(calc("0.3 - 0.2"), "0.1");
		assertEquals(calc("0.3 - 0.4"), "-0.1");
		assertEquals(calc("0.3 - -0.2"), "0.5");
		assertEquals(calc("0.000000000000000000000000000000000000000000000000000000000001 - 0.1"), "-0.099999999999999999999999999999999999999999999999999999999999");
	}

	@Test
	public void testMultiply() {
		assertEquals(calc("0 * 1"), "0");
		assertEquals(calc("1 * 0"), "0");
		assertEquals(calc("-1 * -1"), "1");
		assertEquals(calc("1 * -1"), "-1");
	}


	@Test
	public void testDivision() {
		assertEquals(calc("1 / 2"), "0.5");
		assertEquals(calc("0 / 2"), "0");
		assertEquals(calc("1.000000000000000000000000000000000000000000000000000000000001 / 1"), "1.000000000000000000000000000000000000000000000000000000000001");
		try { calc("1 / 0"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "Cannot divide by zero");}
        try { calc("0 / 0"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "Result is undefined");}
	}

	@Test
	public void testPow() {
		assertEquals(calc("1 ^ 1"), "1");
		assertEquals(calc("1 ^ 2"), "1");
		assertEquals(calc("2 ^ 2"), "4");
		assertEquals(calc("4 ^ 0.5"), "2");
		assertEquals(calc("0 ^ 2"), "0");
		assertEquals(calc("2 ^ 0"), "1");
		assertEquals(calc("2 ^ -1"), "0.5");
		assertEquals(calc("2 ^ 10"), "1024");
		assertEquals(calc("2 ^ 1000"), "10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376");
		assertEquals(calc("2 ^ -10"), "0.0009765625");
		assertEquals(calc("10 ^ 75"), "1000000000000000000000000000000000000000000000000000000000000000000000000000");
		assertEquals(calc("10 ^ 70"), "10000000000000000000000000000000000000000000000000000000000000000000000");
		try { calc("2 ^ 1000000"); fail();} catch (IllegalArgumentException ex){ assertEquals(ex.getMessage(), "Exponent is too big");}
	}

	@Test
	public void testPi() {
		assertEquals(calc("pi"), Evaluator.PI.toString());
		assertEquals(calc("0 * pi"), "0");
		assertEquals(calc("10 * pi"), "31.415926535897932384626433832795028841971693993751058209749445923078164062862");
	}

	@Test
	public void testE() {
		assertEquals(calc("e"), Evaluator.E.toString());
	}

	@Test
	public void testLn() {
		assertEquals(calc("ln e"), "1");
		assertEquals(calc("ln ( e ^ 2 )"), "2");
		try { calc("ln 0"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "ln argument can't be 0");}
		try { calc("ln -1"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "ln argument can't be less than 0");}
	}

	@Test
	public void testLog() {
		assertEquals(calc("log 10"), "1");
		assertEquals(calc("log ( 10 ^ 2 )"), "2");
		assertEquals(calc("log ( 10 ^ -1 )"), "-1");
		try { calc("log 0"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "log argument can't be 0");}
		try { calc("log -1"); fail(); } catch (IllegalArgumentException ex) {assertEquals(ex.getMessage(), "log argument can't be less than 0");}
	}

	@Test
	public void testFloor() {
		assertEquals(calc("floor ( 1 )"), "1");
		assertEquals(calc("floor ( -1 )"), "-1");
		assertEquals(calc("floor ( 1.1 )"), "1");
		assertEquals(calc("floor ( 1.9 )"), "1");
		assertEquals(calc("floor ( -1.1 )"), "-2");
		assertEquals(calc("floor ( -1.8 )"), "-2");
	}

	@Test
	public void testCeil() {
		assertEquals(calc("ceil ( 1 )"), "1");
		assertEquals(calc("ceil ( -1 )"), "-1");
		assertEquals(calc("ceil ( 1.1 )"), "2");
		assertEquals(calc("ceil ( 1.9 )"), "2");
		assertEquals(calc("ceil ( -1.1 )"), "-1");
		assertEquals(calc("ceil ( -1.8 )"), "-1");
	}

	@Test
	public void testRound() {
		assertEquals(calc("round ( 1 )"), "1");
		assertEquals(calc("round ( -1 )"), "-1");
		assertEquals(calc("round ( 1.1 )"), "1");
		assertEquals(calc("round ( 1.9 )"), "2");
		assertEquals(calc("round ( -1.1 )"), "-1");
		assertEquals(calc("round ( -1.8 )"), "-2");
	}

	@Test
	public void testMod() {
		assertEquals(calc("mod ( 2 , 3 )"), "2");
		assertEquals(calc("mod ( 4 , 3 )"), "1");
		assertEquals(calc("mod ( 5 , 3 )"), "2");
		assertEquals(calc("mod ( 5 , -3 )"), "2");
		assertEquals(calc("mod ( 6 , 3 )"), "0");
		assertEquals(calc("mod ( 6 , -3 )"), "0");
		assertEquals(calc("mod ( 0 , 3 )"), "0");
		assertEquals(calc("mod ( -2 , 3 )"), "-2");
		assertEquals(calc("mod ( -4 , 3 )"), "-1");
		assertEquals(calc("mod ( -4 , -3 )"), "-1");
		try { calc("mod ( -4 , 0 )"); fail(); } catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Cannot divide by zero"); }
	}

	@Test
	public void testSin() {
		assertEquals(calc("sin ( ( -1 / 2 ) * pi )"), "-1");
		assertEquals(calc("sin ( 0 )"), "0");
		assertEquals(calc("sin ( 0.5 * pi )"), "1");
		assertEquals(calc("sin ( 2 * pi )"), "0");
	}

	@Test
	public void testCos() {
		assertEquals(calc("cos ( ( -1 / 2 ) * pi )"), "0");
		assertEquals(calc("cos ( 0 )"), "1");
		assertEquals(calc("cos ( 0.5 * pi )"), "0");
		assertEquals(calc("cos ( 2 * pi )"), "1");
	}

	@Test
	public void testTan() {
		try { calc("tan ( ( -1 / 2 ) * pi )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "tan result is infinite"); }
		assertEquals(calc("tan ( 0 )"), "0");
		try { calc("tan ( 0.5 * pi )"); fail(); } catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "tan result is infinite"); }
		assertEquals(calc("tan ( 2 * pi )"), "0");
		assertEquals(calc("tan ( pi / 4 )"), "1");
	}

	@Test
	public void testSinDegree() {
		assertEquals(calc("sin ( 0 )", false), "0");
		assertEquals(calc("sin ( 90 )", false), "1");
		assertEquals(calc("sin ( 180 )", false), "0");
		assertEquals(calc("sin ( -90 )", false), "-1");
	}

	@Test
	public void testCosDegree() {
		assertEquals(calc("cos ( 0 )", false), "1");
		assertEquals(calc("cos ( 90 )", false), "0");
		assertEquals(calc("cos ( 180 )", false), "-1");
		assertEquals(calc("cos ( -90 )", false), "0");
	}

	@Test
	public void testTanDegree() {
		try { calc("tan ( -90 )", false); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "tan result is infinite"); }
		assertEquals(calc("tan ( 0 )", false), "0");
		try { calc("tan ( 90 )", false); fail(); } catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "tan result is infinite"); }
		assertEquals(calc("tan ( 180 )", false), "0");
		assertEquals(calc("tan ( 45 )", false), "1");
	}

	@Test
	public void testAsin() {
		assertEquals(calc("asin ( 0 )"), "0");
		assertEquals(calc("asin ( 1 )"), "1.570796326794897");
		assertEquals(calc("asin ( -1 )"), "-1.570796326794897");
	}

	@Test
	public void testAcos() {
		assertEquals(calc("acos ( 0 )"), "1.570796326794897");
		assertEquals(calc("acos ( 1 )"), "0");
		assertEquals(calc("acos ( -1 )"), "3.141592653589793");
	}

	@Test
	public void testAtan() {
		assertEquals(calc("atan ( 0 )"), "0");
		assertEquals(calc("atan ( 1 )"), "0.785398163397448");
		assertEquals(calc("atan ( -1 )"), "-0.785398163397448");
	}


	@Test
	public void testAsinDegree() {
		assertEquals(calc("asin ( 0 )", false), "0");
		assertEquals(calc("asin ( 1 )", false), "90");
		assertEquals(calc("asin ( -1 )", false), "-90");
	}

	@Test
	public void testAcosDegree() {
		assertEquals(calc("acos ( 0 )", false), "90");
		assertEquals(calc("acos ( 1 )", false), "0");
		assertEquals(calc("acos ( -1 )", false), "180");
	}

	@Test
	public void testAtanDegree() {
		assertEquals(calc("atan ( 0 )", false), "0");
		assertEquals(calc("atan ( 1 )", false), "45");
		assertEquals(calc("atan ( -1 )", false), "-45");
	}

	@Test
	public void testSquareRoot() {
		assertEquals(calc("sqrt ( 0 )"), "0");
		assertEquals(calc("sqrt ( 4 )"), "2");
		assertEquals(calc("sqrt ( 0.04 )"), "0.2");
		assertEquals(calc("sqrt ( 16 )"), "4");
		assertEquals(calc("sqrt ( 225 )"), "15");
		try {calc("sqrt ( -1 )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Root of negative is undefined"); }
		try {calc("sqrt ( -0.0000000000000000000000000000000000000000000000000000000000000000000001 )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Root of negative is undefined"); }
	}

	@Test
	public void testRoot() {
		assertEquals(calc("root ( 1 , 100 )"), "1");
		assertEquals(calc("root ( 4 , 2 )"), "2");
		assertEquals(calc("root ( 16 , 3 )"), "2.519842099789746");
		assertEquals(calc("root ( 225 , 3 )"), "6.082201995573399");
		try {calc("root ( -1 , 4 )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Root of negative is undefined"); }
		try {calc("root ( -0.0000000000000000000000000000000000000000000000000000000000000000000001 , 54 )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Root of negative is undefined"); }
		assertEquals(calc("root ( 4 , -2 )"), "0.5");
		try {calc("root ( 2 , 0 )"); fail();} catch (IllegalArgumentException ex) { assertEquals(ex.getMessage(), "Root with base 0 is undefined"); }
	}

	@Test
	public void testFact() {
		assertEquals(calc("fact ( 0 )"), "1");
		assertEquals(calc("fact ( 1 )"), "1");
		assertEquals(calc("fact ( 2 )"), "2");
		assertEquals(calc("fact ( 3 )"), "6");
		assertEquals(calc("fact ( 4 )"), "24");
//		assertEquals(calc("fact ( -1 )"), "-1");
	}

}

