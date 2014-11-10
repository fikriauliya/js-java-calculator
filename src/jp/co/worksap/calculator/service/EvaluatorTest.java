package jp.co.worksap.calculator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class EvaluatorTest {

	private String calc(String expression) {
		return Evaluator.calculate(ShuntingYard.parse(expression));
	}

	@Test
	public void testPlus() {
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
		assertEquals(calc("2 ^ -10"), "0.0009765625");
		try { calc("2 ^ 1000000"); fail();} catch (IllegalArgumentException ex){ assertEquals(ex.getMessage(), "Exponent is too big");}
	}

	@Test
	public void testPi() {
		assertEquals(calc("pi"), Evaluator.PI.toString());
		assertEquals(calc("0 * pi"), "0");
		assertEquals(calc("10 * pi"), "31.4159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196442881097566593344612847564823378678316527120190914564856692346034861045432664821339360726024914127372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160943305727036575959195309218611738193261179310511854807446237996274956735188575272489122793818301194912983367336244065664308602139494639522473719070217986094370277053921717629317675238467481846766940513200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892354201995611212902196086403441815981362977477130996051870721134999999837297804995105973173281609631859502445945534690830264252230825334468503526193118817101000313783875288658753320838142061717766914730359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019893809525720106548586327886593615338182796823030195203530185296899577362259941389124972177528347913151557485724245415069595082953311686172785588907509838175463746493931925506040092770167113900984882401285836160356370766010471018194295559619894676783744944825537977472684710404753464620804668425906949129331367702898915210475216205696602405803815019351125338243003558764024749647326391419927260426992279678235478163600934172164121992458631503028618297455570674983850549458858692699569092721079750930295532116534498720275596023648066549911988183479775356636980742654252786255181841757467289097777279380008164706001614524919217321721477235014");
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
		assertEquals(calc("mod ( -4 , 0 )"), "-1");
	}
}

