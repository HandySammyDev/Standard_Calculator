package Test6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    JPanel panel1 = new JPanel(); //for numbers and extras
    JPanel calcPanel = new JPanel(); //for +, -, *, /
    static final int NUM_NUMBER_BUTTONS = 10;

    JButton[] buttonsList = new JButton[NUM_NUMBER_BUTTONS];

    public void createNumberButtons(){
        for(int i=0; i<NUM_NUMBER_BUTTONS; i++){
            JButton button = new JButton("" + i);
            button.setActionCommand("" + i);
            button.addActionListener(this);
            buttonsList[i] = button;
            panel1.add(button);
        }
    }

    String[] extraButtonString = {".", "ans"};

    public void createExtraButtons(){
        for (String s : extraButtonString) {
            JButton extraButton = new JButton(s);
            extraButton.setActionCommand(s);
            extraButton.addActionListener(this);
            panel1.add(extraButton);
        }
    }

    String[] calcButtonString = {"+","-","*","/"};

    public void createCalcButtons(){
        for(String s : calcButtonString){
            JButton calcButton = new JButton(s);
            calcButton.setActionCommand(s);
            calcButton.addActionListener(this);
            calcPanel.add(calcButton);
        }
    }
    BottomPanel(){
        final int HEIGHT = 250; // 500/2
        final int WIDTH = 400;

        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        panel1.setLayout(new GridLayout(4,3));
        calcPanel.setLayout(new GridLayout(4,1));
        calcPanel.setPreferredSize(new Dimension(WIDTH/4, HEIGHT));

        this.add(panel1, BorderLayout.CENTER);
        this.add(calcPanel, BorderLayout.EAST);

        createNumberButtons();
        createExtraButtons();
        createCalcButtons();
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
