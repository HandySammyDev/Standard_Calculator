import CustomButtonDesign.GradientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    String[] buttonsArr =
            {"π","1","2","3","+",//"clear",
            "%", "4","5","6","-",//"<--",
            "|a|","7","8","9","x",//" ",
            "(", "0", ".","ans","/",//"<--|"
            "undo", "redo", "clear","<--","<--|"
            };

    public void createButtons(){
        for (String s : buttonsArr) {
            String fontColor = "#217c7d";//"#59a6c0";
            String colorGradient1 = "#31383d";
            String colorGradient2 = "#121317";
            String colorHover1 = "#4b5359";
            String colorHover2 = "#2b2c31";
            String colorPressed1 = "#657078";
            String colorPressed2 = "#43454c";

            if(s.equals("+") || s.equals("-") || s.equals("x") || s.equals("/") ||
                s.equals("undo") || s.equals("redo") || s.equals("clear") || s.equals("<--")){
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

            button.setActionCommand(s);
            button.addActionListener(this);
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

    String command;
    @Override
    public void actionPerformed(ActionEvent e) {
        command = e.getActionCommand();
        Window.changeTextInTextField();
    }
    public String getButtonAction(){
        return command;
    }
}
