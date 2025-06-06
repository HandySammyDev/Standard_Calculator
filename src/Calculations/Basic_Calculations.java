package Test9.Calculations;

import java.util.Stack;

public class Basic_Calculations{
    String equation;

    public void RPN_method(){
        Stack<Double> numberStack = new Stack<>();

        String numberString = "";
        double number = 0.0;

        for(int i=0; i<equation.length(); i++){
            if(isOperator(equation.charAt(i))){
                numberString = "";
                continue;
            }

            if(Character.isDigit(equation.charAt(i))){
                numberString += equation.charAt(i);
                System.out.println(numberString);
            }

            try{
                number = Double.parseDouble(numberString);
            }
            catch(NumberFormatException e){
                System.out.println("Error");
                return;
            }

            numberStack.push(number);
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