package Test6;

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

    public void firstTextField(){
        JTextField MTF = new JTextField("1");
        MTF.setFont(new Font(null, Font.PLAIN, 25));

        TextFieldPanel.setLayout(new GridLayout(0,1));
        TextFieldPanel.setPreferredSize(new Dimension(TF_WIDTH,TF_HEIGHT));
        TextFieldPanel.add(MTF);

        this.add(TextFieldPanel, BorderLayout.SOUTH);
        textFieldList.add(MTF);

        MTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
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
            }
        });
    }

    public JTextField getActiveTextField(){
        return activeTF;
    }
    public void setTextField(String command){
        activeTF.setText(activeTF.getText() + command);
    }

    int count = 1;
    public void createTextFields(){
        count++;
        expandPanel();
        JTextField TF = new JTextField("" + count);
        TF.setFont(new Font(null, Font.PLAIN, 25));

        TextFieldPanel.add(TF);
        textFieldList = modifyList(textFieldList, activeTF, TF);

        TF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                activeTF = TF;
            }
        });
        TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    createTextFields();
                    repaint();
                    revalidate();
                }
            }
        });
    }

    public void shiftTextFields(){

    }

    public LinkedList<JTextField> modifyList
            (LinkedList<JTextField> OriginalList, JTextField textField, JTextField newTextField) {
        LinkedList<JTextField> newList = new LinkedList<>();

        for(int i=0; i<OriginalList.size(); i++){
            if(textField==OriginalList.get(i)){
                newList.add(newTextField);
            }
            newList.add(OriginalList.get(i));
        }
        return newList;
    }

    public void expandPanel(){
        sizeableTF_HEIGHT += TF_HEIGHT;
        TextFieldPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public void shrinkPanel(){

    }

    TopPanel(){
        this.setBackground(Color.decode("#F2F3F4"));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());

        firstTextField();
    }
}
