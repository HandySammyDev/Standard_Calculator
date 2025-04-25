package Main_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class launch extends JFrame implements ActionListener, KeyListener {
    JPanel bottomPanel = new JPanel();

    JPanel panel1 = new JPanel(); //for numbers and extras
    JPanel calcPanel = new JPanel(); //for +, -, *, /

    //-----------------------------------------------------------------------
    static final int NUM_NUMBER_BUTTONS = 10;

    public void createNumberButtons(){
        for(int i=0; i<NUM_NUMBER_BUTTONS; i++){
            JButton button = new JButton("" + i);
            button.addActionListener(this);
            button.setActionCommand("" + i);
            panel1.add(button);
        }
    }

    String[] extraButtonString = {".", "ans"};

    public void createExtraButtons(){
        for (String s : extraButtonString) {
            JButton extraButton = new JButton(s);
            extraButton.addActionListener(this);
            extraButton.setActionCommand(s);
            panel1.add(extraButton);
        }
    }

    String[] calcButtonString = {"+","-","*","/"};

    public void createCalcButtons(){
        for(String s : calcButtonString){
            JButton calcButton = new JButton(s);
            calcButton.addActionListener(this);
            calcButton.setActionCommand(s);
            calcPanel.add(calcButton);
        }
    }

    //-----------------------------------------------------------------------
    JTextField[] textFieldList = new JTextField[3];

    public void createTextFields(){
        for(int i=0; i<textFieldList.length; i++){
            textFieldList[i] = new JTextField();

            textFieldList[i].setActionCommand("" + i);
            textFieldList[i].setSize(WIDTH, 166);
            //textFieldList[i].addKeyListener(this);

            textPanels.add(textFieldList[i]);
        }
    }

    //-----------------------------------------------------------------------
    JPanel textPanels = new JPanel();

    //-----------------------------------------------------------------------

    launch(){
        final int HEIGHT = 500;
        final int WIDTH = 400;

        //setLayout(null); //remove this and the panel will expand with the window
        this.setLayout(new GridLayout(2,1));

        panel1.setLayout(new GridLayout(4,3));
        calcPanel.setLayout(new GridLayout(4,1));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.white);

        createTextFields();

        createNumberButtons();
        createExtraButtons();
        createCalcButtons();

        textPanels.setLayout(new GridLayout(3,0));

        this.add(textPanels);

        //NEED LAYOUT MANAGERS
        bottomPanel.add(panel1, BorderLayout.PAGE_START);
        bottomPanel.add(calcPanel, BorderLayout.PAGE_END);

        add(bottomPanel);
        //pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String jText;

        if (command.equals(e.getActionCommand())) {
            textFieldList[0].setText(textFieldList[0].getText() + command);
            System.out.print(command);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
