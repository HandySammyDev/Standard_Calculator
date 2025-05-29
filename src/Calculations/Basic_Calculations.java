package Test8.Calculations;

public class Basic_Calculations{
    private String text;
    private int number;

    public void adding(){

    }
    public void subtracting(){

    }
    public void multiplying(){

    }
    public void dividing(){

    }
    public void ans(){

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
        System.out.println("String: " + text);
        System.out.println(("Number: " + convertTextToDouble(getText())));
    }

    public String getText() {
        return text;
    }
}
