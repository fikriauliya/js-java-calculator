package jp.co.worksap.calculator.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import jp.co.worksap.calculator.utils.CommonUtilities;

public class ShuntingYard {
	public static String[] parse(String infix) {
    	List<String> res = new LinkedList<String>();
    	Stack<String> operatorsStack = new Stack<String>();

    	for (String token : infix.split("\\s")) {
    		if (isOperator(token)) {
    			System.out.print("Operator: " + token);
    			char operator = token.charAt(0);
    			int tokenPrecedence = getPrecendence(operator);

				while (!operatorsStack.isEmpty()) {
					if (isOperator(operatorsStack.peek())) {
						int stackTopPrecendence = getPrecendence(operatorsStack.peek().charAt(0));
						if (stackTopPrecendence > tokenPrecedence || (stackTopPrecendence == tokenPrecedence && operator != '^')) {
							res.add(operatorsStack.pop());
						} else {
							break;
						}
					} else {
						break;
					}
				}
				operatorsStack.push(token);
    		} else if (token.equals("(")) {
    			System.out.print("Bracket:" + token);
    			operatorsStack.push(token);
    		} else if (token.equals(")")) {
    			System.out.print("Bracket:" + token);

				if (operatorsStack.isEmpty()) {
					throw new IllegalArgumentException("Mismatched parenthesis");
				}

    			while (!operatorsStack.peek().equals("(")) {
    				res.add(operatorsStack.pop());
    				if (operatorsStack.isEmpty()) {
    					throw new IllegalArgumentException("Mismatched parenthesis");
					}
    			}

    			operatorsStack.pop();
    			if (!operatorsStack.isEmpty() && isFunction(operatorsStack.peek())) {
    				res.add(operatorsStack.pop());
    			}
			} else if (token.equals(",")) {
				System.out.print("Separator: " + token);

				if (operatorsStack.isEmpty()) {
					throw new IllegalArgumentException("Mismatched parenthesis");
				}

				while (!operatorsStack.peek().equals("(")) {
					res.add(operatorsStack.pop());
					if (operatorsStack.isEmpty()) {
						throw new IllegalArgumentException("Mismatched parenthesis");
					}
				}
			} else if (isFunction(token)) {
				System.out.print("Function: " + token);
				operatorsStack.push(token);
			} else {
				System.out.print("Number: " + token);
				res.add(token);
    		}

    		System.out.print(" | " + CommonUtilities.listToString(operatorsStack));
    		System.out.println(" | " + CommonUtilities.listToString(res));
    	}

    	while (!operatorsStack.isEmpty()) {
    		if (operatorsStack.peek().equals("(") || operatorsStack.peek().equals(")"))
    			throw new IllegalArgumentException("Mismatched parenthesis");
    		res.add(operatorsStack.pop());
    	}

    	System.out.println("= " + CommonUtilities.listToString(res));
    	return res.toArray(new String[0]);
    }

    public static void main(String[] args) {
//        String infix = "sin ( tan ( 89 + 1 , cos ( 0 * 2 ) ) ) + ln ( e ) + log ( 100 ^ 2 ^ 3 ) + pi";
    	String infix = "1 2 +";
        System.out.printf("infix:   %s%n", infix);
        String[] postFixes = parse(infix);

        System.out.println("postfix: " + CommonUtilities.arrayToString(postFixes));
    }

    private static int getPrecendence(char operator) {
    	final String operators = "-+/*^";
    	return operators.indexOf(operator) / 2;
    }

    private static boolean isOperator(String token) {
    	final String operators = "-+/*^";
    	return token.length() == 1 && operators.contains(token);
    }

    private static boolean isConstant(String token) {
    	return token.equals("pi") || token.equals("e");
    }

    private static boolean isFunction(String token) {
    	if (isConstant(token)) return false;
    	if (isOperator(token)) return false;

    	Pattern functionPattern = Pattern.compile("[a-zA-Z_]\\w*");
    	return functionPattern.matcher(token).matches();
    }
}