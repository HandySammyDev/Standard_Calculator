package Calculations;

import java.util.ArrayList;
import java.util.Stack;

public class Basic_Calculations{
    String equation;

    public void RPN_method(){
        Stack<Double> operators = new Stack<>();
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
                i++;
            }
        }
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