import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static BottomPanel bottomPanel = new BottomPanel();
    public static TopPanel topPanel = new TopPanel();

    Window(){
        JScrollPane scroll_topPanel = new JScrollPane(topPanel);
        JScrollBar verticalScrollBar = scroll_topPanel.getVerticalScrollBar();

        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,500);
        this.setResizable(true);
        this.setTitle("Standard Calculator");

        scroll_topPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#1d2226"), 3));
        scroll_topPanel.setViewportBorder(null);
        scroll_topPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        verticalScrollBar.setUnitIncrement(20);
        this.add(scroll_topPanel); //This is TopPanel
        this.add(bottomPanel);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void changeTextInTextField(String command){
        JTextField activeTextField = topPanel.getActiveTextField();
        if(command.equals("<--|") || command.equals("ans") || command.equals("CE") ||
        command.equals("undo") || command.equals("redo") || command.equals("<--")){
            command = "";
        }
        if(activeTextField!=null){
            topPanel.setTextInTextField(command);
        }
    }

    public static void isEnterPressed(String command){
        if(command.equals("<--|")){
            topPanel.createPanels();
        }
    }

    public static void isAnsPressed(String command){
        if(command.equals("ans")){
            if(topPanel.getAns()!=null){
                changeTextInTextField(topPanel.getAns());
            }
        }
    }

    public static void isClearPressed(String command){
        if(command.equals("clear")){
            topPanel.clearTextField();
        }
    }

    public static void isClearAllPressed(String command){
        if(command.equals("CE")){
            topPanel.clearAllTextFields();
            topPanel.createPanels();
        }
    }

    public static void undoPressed(String command){
        if(command.equals("undo")){
            System.out.println("Undo");
        }
    }

    public static void redoPressed(String command){
        if(command.equals("redo")){
            System.out.println("Redo");
        }
    }

    public static void deleteButton(String command){
        if(command.equals("<--")){
            topPanel.backspaceDelete();
            System.out.println("<--");
        }
    }
}