import CustomButtonDesign.GradientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    String[] buttonsArr =
            {"π","1","2","3","+","clear",
            "%", "4","5","6","-","<--",
            "|a|","7","8","9","x"," ",
            "(", "0", ".","ans","/","<--|"
            };

    public void createButtons(){
        for (String s : buttonsArr) {
            String fontColor = "#59a6c0";
            if(s.equals("+") || s.equals("-") || s.equals("x") || s.equals("/")){
                fontColor = "#b55968";
            }
            JButton button = new GradientButton(s, fontColor, "#31383d", "#121317");
            button.setActionCommand(s);
            button.addActionListener(this);
            this.add(button);
        }
    }

    BottomPanel(){
        final int hGap = 4;
        final int vGap = 4;
        this.setLayout(new GridLayout(4,6,hGap,vGap));
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
