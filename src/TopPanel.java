package Test5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class TopPanel extends JPanel {
    final int TF_WIDTH = 500;
    final int TF_HEIGHT = 100;
    int sizeableTF_HEIGHT = TF_HEIGHT;

    JPanel TextFieldPanel = new JPanel();

    LinkedList<JTextField> textFieldList = new LinkedList<>();

    JTextField activeTF = null;
    JTextField MTF;

    public void mainTextField(){
        MTF = new JTextField("1");
        TextFieldPanel.setLayout(new GridLayout(0,1));
        TextFieldPanel.setPreferredSize(new Dimension(TF_WIDTH,TF_HEIGHT));
        TextFieldPanel.add(MTF);

        this.add(TextFieldPanel, BorderLayout.SOUTH);
        textFieldList.add(MTF);

        MTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    System.out.println("Works!");
                    createTextFields();
                    repaint();
                    revalidate();
                }
            }
        });

        MTF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                activeTF = MTF;
                System.out.println(activeTF.getText());
            }
        });
    }

    public JTextField getActiveTextField(){
        return activeTF;
    }
    public void setTextField(JTextField textField, String command){
        MTF.setText(textField.getText() + command);
    }

    JTextField TF;
    int count = 1;
    public void createTextFields(){
        count++;
        expandPanel();
        TF = new JTextField("" + count);

        TextFieldPanel.add(TF);
        textFieldList.add(TF);
    }

    public void expandPanel(){
        sizeableTF_HEIGHT += TF_HEIGHT;
        TextFieldPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public void shrinkPanel(){

    }

    TopPanel(){
        this.setBackground(Color.red);
        this.setLayout(new BorderLayout());

        mainTextField();
    }
}
