package Test8.Calculations;

import Test8.TopPanel;

import static Test8.Window.topPanel;

public class Basic_Calculations{

    String text;
    public void setText(){
        text = topPanel.getActiveTextField().getText();
    }
    public void printText(){
        System.out.println(text);
    }

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

    public Basic_Calculations(){
        printText();
    }
}
