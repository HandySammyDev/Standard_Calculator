package Test8.Calculations;

public class Basic_Calculations{
    private String text;
    String[] textArr;

    double num1;
    double num2;

    public double adding(int index){
        num1 = convertTextToDouble(textArr[index-1]);
        num2 = convertTextToDouble(textArr[index+1]);

        return num1 + num2;
    }
    public void subtracting(){

    }
    public void multiplying(){

    }
    public void dividing(){

    }
    public void ans(){

    }

    public double orderOfOperations(){
        double answer = 0;
        for(int i=0; i<textArr.length; i++){
            switch(textArr[i]){
                case "+":
                    answer = adding(i);
                    break;
                case "-":
                    answer = 1;
                    break;
                case "*":
                    answer = 2;
                    break;
                case "/":
                    answer = 3;
                    break;
                default:
                    answer = 0;
            }
        }
        return answer;
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

        textArr = new String[text.length()];
        for(int i=0; i<textArr.length; i++){
            textArr[i] = Character.toString(text.charAt(i));
        }

        System.out.println("String: " + text);
        System.out.println("Answer: " + orderOfOperations() + "\n");
    }

    public String getText() {
        return text;
    }
}