package Calculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionEvaluator {

    // Step 1: Convert Infix to RPN (Reverse Polish Notation)
    public static List<String> toRPN(String expression) {
        Stack<String> operators = new Stack<>();
        List<String> output = new ArrayList<>();

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            // If it's a digit, build the full number
            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    number.append(expression.charAt(i));
                    i++;
                }
                output.add(number.toString());
                continue; // skip incrementing i here
            }

            // If it's an operator
            if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(String.valueOf(c))) {
                    output.add(operators.pop());
                }
                operators.push(String.valueOf(c));
            }

            i++;
        }

        // Pop remaining operators
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    // Step 2: Evaluate RPN
    public static int evaluateRPN(List<String> rpn) {
        Stack<Integer> stack = new Stack<>();

        for (String token : rpn) {
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                int result = switch (token) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> throw new IllegalArgumentException("Unknown operator: " + token);
                };
                stack.push(result);
            }
        }

        return stack.pop();
    }

    // Helper methods
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int precedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> -1;
        };
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Main test
    public static void main(String[] args) {
        String expression = "2+34*2";  // Should result in 70

        List<String> rpn = toRPN(expression);
        System.out.println("RPN: " + String.join(" ", rpn));

        int result = evaluateRPN(rpn);
        System.out.println("Result: " + result);
    }
}
