package jp.co.worksap.calculator.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class ShuntingYard {
	public static List<String> parseShuntingYard(String infix) {
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
    			while (!operatorsStack.peek().equals("(")) {
    				res.add(operatorsStack.pop());
    			}
    			operatorsStack.pop();
    			if (!operatorsStack.isEmpty() && isFunction(operatorsStack.peek())) {
    				res.add(operatorsStack.pop());
    			}
			} else if (token.equals(",")) {
				System.out.print("Separator: " + token);
				while (!operatorsStack.peek().equals("(")) {
					res.add(operatorsStack.pop());
				}
			} else if (isFunction(token)) {
				System.out.print("Function: " + token);
				operatorsStack.push(token);
			} else {
				System.out.print("Number: " + token);
				res.add(token);
    		}

    		System.out.print(" | " + listToString(operatorsStack));
    		System.out.println(" | " + listToString(res));
    	}

    	while (!operatorsStack.isEmpty()) {
    		// Check parenthesis
    		res.add(operatorsStack.pop());
    	}
    	return res;
    }

    public static void main(String[] args) {
        String infix = "sin ( tan ( 89 + 1 , cos ( 0 * 2 ) ) ) + ln ( e ) + log ( 100 ^ 2 ^ 3 )";
        System.out.printf("infix:   %s%n", infix);
        List<String> postFixes = parseShuntingYard(infix);

        System.out.println("postfix: " + listToString(postFixes));
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

    	Pattern functionPattern = Pattern.compile("\\D\\S*");
    	return functionPattern.matcher(token).matches();
    }

    private static String listToString(List<String> tokens) {
    	StringBuilder b = new StringBuilder();
    	for (String token:tokens) {
        	b.append(token).append(" ");
        }
    	return b.toString();
    }
}