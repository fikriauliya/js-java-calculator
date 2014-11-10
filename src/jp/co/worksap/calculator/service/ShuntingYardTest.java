package jp.co.worksap.calculator.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ShuntingYardTest {

	@Test
	public void testBasicOperations() {
		assertArrayEquals(ShuntingYard.parse("1 + 2"), new String[] {"1", "2", "+"});
		assertArrayEquals(ShuntingYard.parse("1 - 2"), new String[] {"1", "2", "-"});
		assertArrayEquals(ShuntingYard.parse("1 * 2"), new String[] {"1", "2", "*"});
		assertArrayEquals(ShuntingYard.parse("1 / 2"), new String[] {"1", "2", "/"});
		assertArrayEquals(ShuntingYard.parse("1 ^ 2"), new String[] {"1", "2", "^"});
	}

	@Test
	public void testAssociations() {
		assertArrayEquals(ShuntingYard.parse("1 + 2 + 3"), new String[] {"1", "2", "+", "3", "+"});
		assertArrayEquals(ShuntingYard.parse("1 - 2 - 3"), new String[] {"1", "2", "-", "3", "-"});
		assertArrayEquals(ShuntingYard.parse("1 * 2 * 3"), new String[] {"1", "2", "*", "3", "*"});
		assertArrayEquals(ShuntingYard.parse("1 / 2 / 3"), new String[] {"1", "2", "/", "3", "/"});
		assertArrayEquals(ShuntingYard.parse("1 ^ 2 ^ 3"), new String[] {"1", "2", "3", "^", "^"});
	}

	@Test
	public void testOrders() {
		assertArrayEquals(ShuntingYard.parse("1 + 2 - 3"), new String[] {"1", "2", "+", "3", "-"});
		assertArrayEquals(ShuntingYard.parse("1 + 2 * 3"), new String[] {"1", "2", "3", "*", "+"});
		assertArrayEquals(ShuntingYard.parse("1 * 2 / 3 ^ 4"), new String[] {"1", "2", "*", "3", "4", "^", "/"});
		assertArrayEquals(ShuntingYard.parse("1 ^ 2 / 3 ^ 4"), new String[] {"1", "2", "^", "3", "4", "^", "/"});
		assertArrayEquals(ShuntingYard.parse("1 * 2 / 3 / 4"), new String[] {"1", "2", "*", "3", "/", "4", "/"});
	}

	@Test
	public void testExponents() {
		assertArrayEquals(ShuntingYard.parse("1 ^ 2 ^ 3 ^ 4"), new String[] {"1", "2", "3", "4", "^", "^", "^"});
		assertArrayEquals(ShuntingYard.parse("1 ^ 2 ^ 3 - 4"), new String[] {"1", "2", "3", "^", "^", "4", "-"});
	}

	@Test
	public void testFunctions() {
		assertArrayEquals(ShuntingYard.parse("1 + sin ( 4 )"), new String[] {"1", "4", "sin", "+"});
		assertArrayEquals(ShuntingYard.parse("cos ( 1 ) + sin ( 4 )"), new String[] {"1", "cos", "4", "sin", "+"});
		assertArrayEquals(ShuntingYard.parse("cos ( 1 ^ 2 )"), new String[] {"1", "2", "^", "cos"});
		assertArrayEquals(ShuntingYard.parse("cos ( 1 ) ^ 2"), new String[] {"1", "cos", "2", "^"});assertArrayEquals(ShuntingYard.parse("cos ( 1 ^ 2 )"), new String[] {"1", "2", "^", "cos"});
		assertArrayEquals(ShuntingYard.parse("cos ( sin ( 1 + tan ( 4 ) ) ^ 2 ^ 3 ) + 4"), new String[] {"1", "4", "tan", "+", "sin", "2", "3", "^", "^", "cos", "4", "+"});
	}

	@Test
	public void testBrackets() {
		assertArrayEquals(ShuntingYard.parse("1 + 2 ^ 3"), new String[] {"1", "2", "3", "^", "+"});
		assertArrayEquals(ShuntingYard.parse("( 1 + 2 ) ^ 3"), new String[] {"1", "2", "+", "3", "^"});
		assertArrayEquals(ShuntingYard.parse("1 ^ 2 ^ 3"), new String[] {"1", "2", "3", "^", "^"});
		assertArrayEquals(ShuntingYard.parse("( 1 ^ 2 ) ^ 3"), new String[] {"1", "2", "^", "3", "^"});
		assertArrayEquals(ShuntingYard.parse("1 ^ ( 2 + 3 ) * ( 4 + 5 ) ^ 6"), new String[] {"1", "2", "3", "+", "^", "4", "5", "+", "6", "^", "*"});
		assertArrayEquals(ShuntingYard.parse("( 1 ^ 2 )"), new String[] {"1", "2", "^"});
	}

	@Test
	public void testInbalanceBrackets() {
		try { ShuntingYard.parse("1 , 2"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse("( 1 , 2"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse("1 ( 2"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse("1 2 )"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse("1 + 2 )"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse("( 1 + 2 ) )"); fail(); } catch (IllegalArgumentException ex) {}
		try { ShuntingYard.parse(") ("); fail(); } catch (IllegalArgumentException ex) {}
	}
}
