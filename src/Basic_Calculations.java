package Calculations;

import java.util.ArrayList;
import java.util.Stack;

public class Basic_Calculations{
    String equation;

    public void RPN_method(){
        Stack<Double> numberStack = new Stack<>();
        String numberString = "";
        char highestOperator = ' ';

        double num1;
        double num2;

        for(int i=0; i<equation.length(); i++){
            if(isOperator(equation.charAt(i))){
                System.out.println("New number: " + numberString);
                numberStack.push(convertToDouble(numberString));
                highestOperator = equation.charAt(i);

                numberString = "";
                continue;
            }

            if(Character.isDigit(equation.charAt(i))){
                numberString += equation.charAt(i);
            }
        }
        System.out.println("New number: " + numberString);
        numberStack.push(convertToDouble(numberString));
        System.out.println(numberStack);

        //2+3*4
        if(highestOperator == '*'){
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            numberStack.push(num1 * num2);

            System.out.println(num1 * num2);
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