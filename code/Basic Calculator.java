/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2

Example 2:

Input: " 2-1 + 2 "
Output: 3

Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

Note:

    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
*/

class Solution {
    private int index;      // current index within input string
    private int parenCount; // number of currently open parentheses
    
    // synchronized since we are using instance variables (non-reentrant).
    public synchronized int calculate(String s) {
        index = 0;
        parenCount = 0;
        
        int n = evaluate(s);
        
        if (parenCount != 0) {
            throw new IllegalArgumentException("Open parenthesis without close parenthesis");
        }
        return n;
    }
    
    // We are not using a tight validation for this method.
    // i.e. An expression like "1 + - 2" which is obviously wrong won't be flagged.
    private int evaluate(String s) {
        int number = 0;
        char lastOperator = '+';
        
        while (index < s.length()) {
            char c = s.charAt(index++);
            
            if (c == '(') {
                parenCount++;
                // We use the function call stack as our operation stack.
                int n = evaluate(s);
                number = operate(number, lastOperator, n);
            } else if (c == ')') {
                if (parenCount > 0) {
                    parenCount--;
                    return number;
                }
                throw new IllegalArgumentException("Close parenthesis without open parenthesis");
            } else if (c == '+' || c == '-') {
                lastOperator = c;
            } else if (c >= '0' && c <= '9') {
                int n = c - '0';
                while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                    n = (n * 10) + (s.charAt(index++) - '0');
                }
                number = operate(number, lastOperator, n);
            } else if (c == ' ') {
                continue;
            } else {
                throw new IllegalArgumentException("Unrecognized character `" + c + "'");
            }
        }
        
        return number;
    }
    
    private static int operate(int number1, char op, int number2) {
        return (op == '+') ? number1 + number2 : number1 - number2;
    }
}
