import CustomButtonDesign.GradientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BottomPanel extends JPanel implements ActionListener{
    private final String[] buttonsStringArr =
            {"π","1","2","3","+",//"clear",
            "%", "4","5","6","-",//"<--",
            "|a|","7","8","9","x",//" ",
            "(", "0", ".","ans","/",//"<--|"
            "undo", "redo", "clear","<--","<--|"
            };
    private final int NUMBER_OF_BUTTONS = buttonsStringArr.length;
    public JButton[] buttonLists = new JButton[NUMBER_OF_BUTTONS];
    public int indexOfClear = 22;

    public void createJButtons(){
        for(int i=0; i<NUMBER_OF_BUTTONS; i++){
            String buttonText = buttonsStringArr[i];
            String fontColor = "#217c7d"; //"#59a6c0";
            String colorGradient1 = "#31383d";
            String colorGradient2 = "#121317";
            String colorHover1 = "#4b5359";
            String colorHover2 = "#2b2c31";
            String colorPressed1 = "#657078";
            String colorPressed2 = "#43454c";

            if(buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("x") || buttonText.equals("/") ||
                    buttonText.equals("undo") || buttonText.equals("redo") || buttonText.equals("clear") || buttonText.equals("<--") ||
                    buttonText.equals("CE")){
                fontColor = "#b55968";
            }
            if(buttonText.equals("<--|") || buttonText.equals("ans")){
                fontColor = "#eeeeee";
                colorGradient1 = "#055f60";
                colorGradient2 = "#033f40";
                colorHover1 = "#217c7d";
                colorHover2 = "#1f5d5e";
                colorPressed1 = "#3a999a";
                colorPressed2 = "#3b7b7c";
            }

            JButton button = new GradientButton
                    (buttonText,
                    fontColor,
                    colorGradient1, colorGradient2,
                    colorHover1, colorHover2,
                    colorPressed1, colorPressed2);

            buttonLists[i] = button;

            button.addActionListener(this);
            button.setActionCommand(buttonText);
            this.add(button);
        }
    }

    BottomPanel(){
        final int hGap = 4;
        final int vGap = 4;
        this.setLayout(new GridLayout(5,5,hGap,vGap));
        this.setBackground(Color.decode("#1d2226"));
        createJButtons();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        Window.changeTextInTextField(command);
        Window.isEnterPressed(command);
        Window.isAnsPressed(command);
        Window.isClearPressed(command);
        Window.isClearAllPressed(command);
        Window.undoPressed(command);
        Window.redoPressed(command);
    }
}
