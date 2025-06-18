package Calculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Basic_Calculations{
    String answer;

    public List<String> RPN_method(String expression){
        Stack<String> operators = new Stack<>();
        ArrayList<String> arrRPN = new ArrayList<>();

        String numberString;

        int i = 0;
        while(i < expression.length()) {
            char c = expression.charAt(i);
            numberString = "";

            //Build our number
            if (Character.isDigit(c)) {
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    numberString += expression.charAt(i);
                    i++;
                }
                arrRPN.add(numberString);
                continue; //Jumps back up
            }

            if (isOperator(c)) {
                // now add the operators in order to the string like 1, 2, 3 *, -
                while (!operators.isEmpty() && precedence(operators.peek().charAt(0)) >= precedence(c)) {
                    arrRPN.add(operators.pop());
                }
                operators.push(String.valueOf(c));
            }
            i++;
        }
            //The remaining operators never get added so we need to add them in this while loop
            while(!operators.isEmpty()){
                arrRPN.add(operators.pop());
            }

        System.out.println(arrRPN);
        return arrRPN;
    }

    public int evaluateRPN(List<String> rpn){
        Stack<Integer> stack = new Stack<>();

        for(String token : rpn){
            if(isNumeric(token)){
                stack.push(Integer.parseInt(token));
            }
            else{
                int b = stack.pop();
                int a = stack.pop();
                int result = switch (token){
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default -> throw new IllegalArgumentException("Unknown operator: " + token);
                };
                stack.push(result);
            }
        }
        return stack.pop(); //final answer
    }

    public boolean isNumeric(String str){
        return str.matches("\\d+");
    }

    public double convertToDouble(String string){
        try{
            return Double.parseDouble(string);
        }
        catch(NumberFormatException e){
            System.out.println("Error");
            return -1;
        }
    }

    public int precedence(char operator){
        if(operator == '*' || operator == '/'){
            return 1;
        }
        if(operator == '+' || operator == '-'){
            return 2;
        }
        return -1;
    }

    public boolean isOperator(char operator){
        switch (operator){
            case '+', '-', '*', '/':
                return true;
        }
        return false;
    }

    public Basic_Calculations(String text){
        List<String> rpn = RPN_method(text);
        int result = evaluateRPN(rpn);

        this.answer = Integer.toString(result);
        System.out.println(result);
    }

    public String getCalculations(){
        return answer;
    }
}