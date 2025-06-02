package Test9.Calculations;

import java.util.Arrays;

public class Basic_Calculations{
    String text;

    String finalAnswer;

    public void orderOfOperations(){
        int[] indexArr = new int[3];
        int count = 0;
        double number1 = 0;
        double number2 = 0;

        for(int i=0; i<text.length(); i++){
            if(text.charAt(i)=='*' || text.charAt(i)=='/'){
                indexArr[count] = i;
                count++;
                break;
            }
        }
        for(int i=0; i<text.length(); i++){
            if(!Character.isDigit(text.charAt(i))){
                indexArr[count] = i;
                count++;
                break;
            }
        }

        System.out.println(Arrays.toString(indexArr));

        String string1;
        String string2;

        if(count==1) {  //2+2
            string1 = text.substring(0, indexArr[0]);
            string2 = text.substring(indexArr[0] + 1);
            number1 = convertStringToDouble(string1);
            number2 = convertStringToDouble(string1);

            System.out.println(string1 + string2);
        }
        else if(count==2){  //2+2+2

        }

        switch(text.charAt(indexArr[0])){
            case '+':
                System.out.println(number1 + number2);
                break;
            case '-':
                System.out.println(number1 - number2);
                break;
            case '*':
                System.out.println(number1 * number2);
                break;
            case '/':
                System.out.println(number1 / number2);
                break;
        }
    }

    public double convertStringToDouble(String string){
        try{
            return Double.parseDouble(string);
        }
        catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return 10101;
    }

    public Basic_Calculations(String text){
        this.text = text;

        System.out.println("String: " + text);
        orderOfOperations();
    }
}