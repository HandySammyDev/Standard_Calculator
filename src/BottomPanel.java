import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel implements ActionListener{
    final int WIDTH = 400;
    final int HEIGHT = 250;
    final int WIDTH_PER_CELL = 50;

    final int hGap = 4;
    final int vGap = 4;

    String[] buttonsList =
            {"π","%","|a|","(", //,")"
            "1","2","3","4","5","6","7","8","9","0",".", "ans",
            "+","-","*","/",
            "^", "<", "v", "<", "trash", "delete", "<--|"};

    public void createButtons(){
        for (String s : buttonsList) {
            JButton functionButton = new JButton(s);
            functionButton.setActionCommand(s);
            functionButton.addActionListener(this);
            this.add(functionButton);
        }
    }

    BottomPanel(){
        this.setLayout(new GridLayout(4,7));
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
