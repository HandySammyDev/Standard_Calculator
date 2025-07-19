import CustomButtonDesign.GradientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class BottomPanel extends JPanel implements ActionListener{
    String[] buttonsStringArr =
            {"π","1","2","3","+",//"clear",
            "%", "4","5","6","-",//"<--",
            "|a|","7","8","9","x",//" ",
            "(", "0", ".","ans","/",//"<--|"
            "undo", "redo", "clear","<--","<--|"
            };

    final int BUTTON_ARRAY_SIZE = buttonsStringArr.length;
    JButton[] buttonsArr = new JButton[BUTTON_ARRAY_SIZE];

    public void createButtons(){
        for (String s : buttonsStringArr) {
            String fontColor = "#217c7d"; //"#59a6c0";
            String colorGradient1 = "#31383d";
            String colorGradient2 = "#121317";
            String colorHover1 = "#4b5359";
            String colorHover2 = "#2b2c31";
            String colorPressed1 = "#657078";
            String colorPressed2 = "#43454c";

            if(s.equals("+") || s.equals("-") || s.equals("x") || s.equals("/") ||
                s.equals("undo") || s.equals("redo") || s.equals("clear") || s.equals("<--") ||
                s.equals("CE")){
                fontColor = "#b55968";
            }
            if(s.equals("<--|") || s.equals("ans")){
                fontColor = "#eeeeee";
                colorGradient1 = "#055f60";
                colorGradient2 = "#033f40";
                colorHover1 = "#217c7d";
                colorHover2 = "#1f5d5e";
                colorPressed1 = "#3a999a";
                colorPressed2 = "#3b7b7c";
            }

            JButton button = new GradientButton
                    (s,
                    fontColor,
                    colorGradient1, colorGradient2,
                    colorHover1, colorHover2,
                    colorPressed1, colorPressed2);

            Arrays.fill(buttonsArr, button);

//            if(s.equals("clear")){
//                button.addActionListener(e -> {
//                    String current = button.getText();
//                    if(current.equals("clear") && Window.topPanel.textFieldIsEmpty()){
//                        button.setText("CE");
//                        button.setActionCommand("CE");
//                    }
//                    else{
//                        button.setText("clear");
//                        button.setActionCommand("clear");
//                    }
//                });
//            }
            if(s.equals("clear")){
                button.addActionListener(e -> {
                    if(changeClearButton()){
                        button.setText("CE");
                        button.setActionCommand("CE");
                    }
                    else{
                        button.setText("clear");
                        button.setActionCommand("clear");
                    }
                });
            }

            button.addActionListener(this);
            button.setActionCommand(s);
            this.add(button);
        }
    }

    BottomPanel(){
        final int hGap = 4;
        final int vGap = 4;
        this.setLayout(new GridLayout(5,5,hGap,vGap));
        this.setBackground(Color.decode("#1d2226"));
        createButtons();
    }

    public boolean changeClearButton(){
        if(clearButton.equals("clear") && Window.topPanel.textFieldIsEmpty()){
            button.setText("CE");
            button.setActionCommand("CE");
        }
        else{
            button.setText("clear");
            button.setActionCommand("clear");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        Window.changeTextInTextField(command);
        Window.isEnterPressed(command);
        Window.isAnsPressed(command);
        Window.isClearPressed(command);
        Window.isClearAllPressed(command);
    }
}
