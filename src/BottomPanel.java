package Test6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    JPanel CenterButtonsPanel = new JPanel(); //BorderLayout
    JPanel ButtonsPanel = new JPanel(); //for ALL buttons

    JPanel panel1 = new JPanel();
    JPanel numberPanel = new JPanel(); //for numbers and extras
    JPanel usefulCalcPanel = new JPanel(); // for (, ), %, |a|, n

    JPanel panel2 = new JPanel();
    JPanel calcPanel = new JPanel(); //for +, -, *, /

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

    String[] usefulCalcButtonsList = {"(",")","%","|a|","π"};
    public void createUsefulCalcButtons(){
        for (String s : usefulCalcButtonsList) {
            JButton usefulCalcButton = new JButton(s);
            usefulCalcButton.setActionCommand(s);
            usefulCalcButton.addActionListener(this);
            usefulCalcPanel.add(usefulCalcButton);
        }
    }

    public void Panel1_Settings(){
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING));
        usefulCalcPanel.setLayout(new GridLayout(5,1));
        numberPanel.setLayout(new GridLayout(4,3));

        createNumberButtons();
        createExtraButtons();
        createUsefulCalcButtons();

        ButtonsPanel.add(panel1);
        panel1.setLayout(new GridBagLayout());

    }

    BottomPanel(){
        final int HEIGHT = 250; // 500/2
        final int WIDTH = 400;
        final int widthPerButton = 50;

        this.setBackground(Color.gray);
        this.setLayout(new BorderLayout());
        CenterButtonsPanel.setLayout(new BorderLayout());

        ButtonsPanel.setLayout(new GridLayout(1,2));

        Panel1_Settings();

        CenterButtonsPanel.add(ButtonsPanel);
        createBorderSpace();
        this.add(CenterButtonsPanel, BorderLayout.CENTER);
    }

    public void createBorderSpace(){
        int spacing = 10;
        JPanel borderSpace1 = new JPanel();
        JPanel borderSpace2 = new JPanel();
        JPanel borderSpace3 = new JPanel();
        JPanel borderSpace4 = new JPanel();
        borderSpace1.setPreferredSize(new Dimension(WIDTH, spacing));
        borderSpace2.setPreferredSize(new Dimension(WIDTH, spacing));
        borderSpace3.setPreferredSize(new Dimension(spacing, HEIGHT));
        borderSpace4.setPreferredSize(new Dimension(spacing, HEIGHT));
        this.add(borderSpace1, BorderLayout.NORTH);
        this.add(borderSpace2, BorderLayout.SOUTH);
        this.add(borderSpace3, BorderLayout.EAST);
        this.add(borderSpace4, BorderLayout.WEST);
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
