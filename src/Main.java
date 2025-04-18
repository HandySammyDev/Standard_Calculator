import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<Double> inputList = new ArrayList<>();

        double answer;
        int i;

        inputList.add(scnr.nextDouble());

        for(i=0; inputList.get(i) != '='; i++){
            while(true){
                answer = inputList.get(i);
                System.out.print(answer);
                break;
            }
            inputList.add(scnr.nextDouble());
        }
    }
}