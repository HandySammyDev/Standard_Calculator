package Test9.Calculations;

import java.util.LinkedList;

public class Basic_Calculations{
    private String text;
    LinkedList<String> textArr = new LinkedList<>();

    double num1;
    double num2;

    public void adding(int index){

    }
    public void subtracting(){

    }
    public void multiplying(){

    }
    public void dividing(){

    }
    public void ans(){

    }

    public void orderOfOperations(){
        LinkedList<String> orderOfOperationsList = new LinkedList<>();

        for(int i=0; i<text.length(); i++){
            if(text.charAt(i)=='+'){
                System.out.println("+");
            }
        }

        int getFirstSymbol;
        getFirstSymbol = text.indexOf('+');
    }

    public double convertTextToDouble(String text){
        try{
            return Double.parseDouble(text);
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
            return 0.00000;
        }
    }

    public Basic_Calculations(String text){
        this.text = text;

        for(int i=0; i<text.length(); i++){
            textArr.add(Character.toString(text.charAt(i)));
        }

        System.out.println("String: " + text);

        orderOfOperations();
    }

    public String getText() {
        return text;
    }
}