package Test8;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static BottomPanel bottomPanel = new BottomPanel();
    public static TopPanel topPanel = new TopPanel();

    JScrollPane scroll_topPanel = new JScrollPane(topPanel);
    JScrollBar verticalScrollBar = scroll_topPanel.getVerticalScrollBar();

    public static void changeTextInTextField(){
        String command1 = bottomPanel.getButtonAction();
        JTextField activeTextField = topPanel.getActiveTextField();

        if(activeTextField!=null){
            topPanel.setTextField(command1);
        }
    }

    Window(){
        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);

        scroll_topPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        verticalScrollBar.setUnitIncrement(20);
        this.add(scroll_topPanel); //This is TopPanel
        this.add(bottomPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
