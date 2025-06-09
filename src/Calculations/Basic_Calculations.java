package Calculations;

import java.util.ArrayList;
import java.util.Stack;

public class Basic_Calculations{
    String equation;

    public void RPN_method(){
        Stack<String> operators = new Stack<>();
        ArrayList<String> arrRPN = new ArrayList<>();

        String numberString;

        int i = 0;
        while(i<equation.length()) {
            char c = equation.charAt(i);
            numberString = "";

            while(i < equation.length() && Character.isDigit(equation.charAt(i))){
                numberString += equation.charAt(i);
                i++;
            }

            if(!numberString.isEmpty()){
                arrRPN.add(numberString);
                System.out.println(numberString);
            }

            if(isOperator(c)){
                // now add the operators in order to the string like 1, 2, 3 *, -
                while (!operators.isEmpty() && precedence(operators.peek().charAt(0)) >= precedence(c)) {
                    arrRPN.add(operators.pop());
                }
                operators.push(String.valueOf(c));
            }
            i++;

            //Operators never get added so we need to add them in this while loop
            while(!operators.isEmpty()){
                arrRPN.add(operators.pop());
            }
        }
        System.out.println(arrRPN);
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
        this.equation = text;

        RPN_method();

        System.out.println("String: " + text);
    }
}