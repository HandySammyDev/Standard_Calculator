package Test7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    final int WIDTH = 400;
    final int HEIGHT = 250;
    final int WIDTH_PER_CELL = 40;

    JPanel MainPanel = new JPanel();
    GridBagConstraints gbc = new GridBagConstraints();
    public void mainPanelSettings(){
        MainPanel.setLayout(new GridBagLayout());
        MainPanel.setBackground(Color.blue);

        createFunctionButtons();
        createNumberButtons();
        createCalcButtons();
        createCommandButtons();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = .5;
        MainPanel.add(functionPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = .5;
        MainPanel.add(numberPanel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = .5;
        MainPanel.add(calcPanel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = .5;
        MainPanel.add(commandPanel, gbc);

        this.add(MainPanel);
    }

    final int hGap = 4;
    final int vGap = 4;

    JPanel functionPanel = new JPanel();
    String[] functionButtonsList =
            {"mean","π","median","%","mode","|a|","(",")"};
    public void createFunctionButtons(){
        functionPanel.setLayout(new GridLayout(4, 2,hGap,vGap));
        functionPanel.setPreferredSize(new Dimension(WIDTH_PER_CELL*2, HEIGHT));

        for (String s : functionButtonsList) {
            JButton functionButton = new JButton(s);
            functionButton.setActionCommand(s);
            functionButton.addActionListener(this);
            functionPanel.add(functionButton);
        }
    }

    JPanel numberPanel = new JPanel();
    int[] numberButtonsList = {1,2,3,4,5,6,7,8,9,0};
    String[] extraButtonsList = {".", "ans"};
    public void createNumberButtons(){
        numberPanel.setLayout(new GridLayout(4,3,hGap,vGap));
        functionPanel.setPreferredSize(new Dimension(WIDTH_PER_CELL*3, HEIGHT));

        for (int numberButton : numberButtonsList) {
            JButton digitButton = new JButton("" + numberButton);
            digitButton.setActionCommand("" + numberButton);
            digitButton.addActionListener(this);
            numberPanel.add(digitButton);
        }
        for (String s : extraButtonsList) {
            JButton extraButton = new JButton(s);
            extraButton.setActionCommand(s);
            extraButton.addActionListener(this);
            numberPanel.add(extraButton);
        }
    }

    JPanel calcPanel = new JPanel();
    String[] calcButtonList = {"+","-","*","/"};
    public void createCalcButtons(){
        calcPanel.setLayout(new GridLayout(4,1,hGap,vGap));
        functionPanel.setPreferredSize(new Dimension(WIDTH_PER_CELL, HEIGHT));

        for(String s : calcButtonList){
            JButton calcButton = new JButton(s);
            calcButton.setActionCommand(s);
            calcButton.addActionListener(this);
            calcPanel.add(calcButton);
        }
    }

    JPanel commandPanel = new JPanel();
    String[] commandButtonList = {"^", "<", "v", "<", "trash", "delete", "<--|"};
    public void createCommandButtons(){
        commandPanel.setLayout(new GridLayout(4,2,hGap,vGap));
        functionPanel.setPreferredSize(new Dimension(WIDTH_PER_CELL*3, HEIGHT));

        for(String s : commandButtonList){
            JButton commandButton = new JButton(s);
            commandButton.setActionCommand(s);
            commandButton.addActionListener(this);
            commandPanel.add(commandButton);
        }
    }

    BottomPanel(){
        mainPanelSettings();
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
