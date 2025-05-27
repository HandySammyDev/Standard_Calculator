package Test6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    JPanel ButtonsPanel = new JPanel(); //for ALL buttons

    JPanel panel1 = new JPanel();
    JPanel numberPanel = new JPanel(); //for numbers and extras
    JPanel FunctionPanel = new JPanel(); // for (, ), %, |a|, n

    JPanel panel2 = new JPanel();
    JPanel calcPanel = new JPanel(); //for +, -, *, /

    final int HEIGHT = 250; // 500/2
    final int WIDTH = 400;
    final int widthPerButton = 50;

    int[] numberButtonsList = {1,2,3,4,5,6,7,8,9,0};
    public void createNumberButtons(){
        for (int numberButton : numberButtonsList) {
            JButton button = new JButton("" + numberButton);
            button.setActionCommand("" + numberButton);
            button.addActionListener(this);
            numberPanel.add(button);
        }
    }

    String[] extraButtonsList = {".", "ans"};
    public void createExtraButtons(){
        for (String s : extraButtonsList) {
            JButton extraButton = new JButton(s);
            extraButton.setActionCommand(s);
            extraButton.addActionListener(this);
            numberPanel.add(extraButton);
        }
    }

    String[] calcButtonList = {"+","-","*","/"};
    public void createCalcButtons(){
        for(String s : calcButtonList){
            JButton calcButton = new JButton(s);
            calcButton.setActionCommand(s);
            calcButton.addActionListener(this);
            calcPanel.add(calcButton);
        }
    }

    String[] functionButtonsList =
            {"mean","π","median","%","mode","|a|","(",")"};
    public void createFunctionButtons(){
        for (String s : functionButtonsList) {
            JButton usefulCalcButton = new JButton(s);
            usefulCalcButton.setActionCommand(s);
            usefulCalcButton.addActionListener(this);
            FunctionPanel.add(usefulCalcButton);
        }
    }

    public void Panel1_Settings(){
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        panel1.setBackground(Color.red);
        FunctionPanel.setLayout(new GridLayout(4,2));
        numberPanel.setLayout(new GridLayout(4,3));

        createNumberButtons();
        createExtraButtons();
        createFunctionButtons();

        panel1.add(FunctionPanel, BorderLayout.LINE_START);
        panel1.add(numberPanel, BorderLayout.LINE_END);

        ButtonsPanel.add(panel1);
    }

    public void Panel2_Settings(){
        panel2.setPreferredSize(new Dimension(WIDTH/2, HEIGHT));
        panel2.setBackground(Color.blue);

        ButtonsPanel.add(panel2);
    }

    BottomPanel(){
        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());

        ButtonsPanel.setLayout(new GridLayout(1,2));

        Panel1_Settings();
        Panel2_Settings();

        this.add(ButtonsPanel, BorderLayout.CENTER);
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
