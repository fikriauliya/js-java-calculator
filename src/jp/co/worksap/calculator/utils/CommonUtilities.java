package jp.co.worksap.calculator.utils;

import java.util.List;

public class CommonUtilities {

    public static <T extends Object> String listToString(List<T> tokens) {
    	StringBuilder b = new StringBuilder();
    	for (Object token:tokens) {
        	b.append(token.toString()).append(" ");
        }
    	return b.toString();
    }
}
