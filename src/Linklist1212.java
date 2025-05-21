package PracticeCode;

import java.util.LinkedList;
import java.util.Scanner;

public class Linklist1212 {
    static String inputLetter;
    static String inputNewLetter;

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        LinkedList<String> list = new LinkedList<>();

        String a1, a2, a3;

        a1 = "a";
        a2 = "b";
        a3 = "c";

        list.add(a1);
        list.add(a2);
        list.add(a3);

        System.out.println("Original list: " + list);

        System.out.print("Enter letter to shift: ");
        inputLetter = scnr.next();
        System.out.print("Enter new letter:");
        inputNewLetter = scnr.next();

        list = modifiedList(list, inputLetter, inputNewLetter);
        System.out.println("New list: " + list);
    }

    public static LinkedList<String> modifiedList(LinkedList<String> OriginalList, String letter, String newLetter){
        LinkedList<String> newList = new LinkedList<>();

        for(int i=0; i<OriginalList.size(); i++){
            if(letter.equals(OriginalList.get(i))){
                newList.add(newLetter);
            }
            newList.add(OriginalList.get(i));
        }
        return newList;
    }
}
