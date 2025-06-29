import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel1 extends JPanel implements ActionListener{
    final int WIDTH = 400;
    final int HEIGHT = 250;
    final int WIDTH_PER_CELL = 50;
    final int WIDTH_FOR_FUNC = WIDTH_PER_CELL;
    final int WIDTH_FOR_NUM = WIDTH_PER_CELL*3;
    final int WIDTH_FOR_CALC = WIDTH_PER_CELL;
    final int WIDTH_FOR_COMM = WIDTH_PER_CELL*3;
    JPanel functionPanel = new JPanel();
    JPanel numberPanel = new JPanel();
    JPanel calcPanel = new JPanel();
    JPanel commandPanel = new JPanel();

    final int hGap = 4;
    final int vGap = 4;

    String[] functionButtonsList =
            {"π","%","|a|","(",")"};
    public void createFunctionButtons(){
        functionPanel.setLayout(new GridLayout(4, 2));
        functionPanel.setPreferredSize(new Dimension(WIDTH_FOR_FUNC, HEIGHT));

        for (String s : functionButtonsList) {
            JButton functionButton = new JButton(s);
            functionButton.setActionCommand(s);
            functionButton.addActionListener(this);
            functionPanel.add(functionButton);
        }
    }
    String[] numberButtonsList = {"1","2","3","4","5","6","7","8","9","0"};
    String[] extraButtonsList = {".", "ans"};
    public void createNumberButtons(){
        numberPanel.setLayout(new GridLayout(4,3));
        numberPanel.setPreferredSize(new Dimension(WIDTH_FOR_NUM, HEIGHT));

        for (String numberButton : numberButtonsList) {
            JButton digitButton = new JButton(numberButton);
            digitButton.setActionCommand(numberButton);
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

    String[] calcButtonList = {"+","-","*","/"};
    public void createCalcButtons(){
        calcPanel.setLayout(new GridLayout(4,1));
        calcPanel.setPreferredSize(new Dimension(WIDTH_FOR_CALC, HEIGHT));

        for(String s : calcButtonList){
            JButton calcButton = new JButton(s);
            calcButton.setActionCommand(s);
            calcButton.addActionListener(this);
            calcPanel.add(calcButton);
        }
    }

    String[] commandButtonList = {"^", "<", "v", "<", "trash", "delete", "<--|"};
    public void createCommandButtons(){
        commandPanel.setLayout(new GridLayout(4,2));
        commandPanel.setPreferredSize(new Dimension(WIDTH_FOR_COMM, HEIGHT));

        for(String s : commandButtonList){
            JButton commandButton = new JButton(s);
            commandButton.setActionCommand(s);
            commandButton.addActionListener(this);
            commandPanel.add(commandButton);
        }
    }

    BottomPanel1(){
        JPanel MainPanel = new JPanel();

        MainPanel.setLayout(new FlowLayout());
        MainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        MainPanel.setBackground(Color.blue);

        createFunctionButtons();
        createNumberButtons();
        createCalcButtons();
        createCommandButtons();

        MainPanel.add(functionPanel);
        MainPanel.add(numberPanel);
        MainPanel.add(calcPanel);
        MainPanel.add(commandPanel);

        this.setLayout(new BorderLayout());
        this.add(MainPanel, BorderLayout.CENTER);
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
