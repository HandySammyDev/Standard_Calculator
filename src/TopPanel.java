package Test4;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
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
    int activeCaret = 0;

    public JTextField getActiveTextField(){
        return activeTF;
    }
    public void setTextField(String command){
        activeTF.setText(activeTF.getText() + command);
    }

    int count = 1;
    public void createTextFields(){
        JTextField TF = new JTextField();
        TF.setFont(new Font(null, Font.PLAIN, 25));

        if(textFieldList.isEmpty()){
            TextFieldPanel.add(TF);
            textFieldList.add(TF);
        }
        else{
            //FIX ME
            if(activeTF.getText().trim().isEmpty()){
                return;
            }
            expandPanel();
            TextFieldPanel.add(TF);
            textFieldList = modifyList(textFieldList, activeTF, TF);
            printModifiedList();
        }

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
        TF.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                activeCaret = e.getDot();
            }
        });
        shiftTextFields();
        count++;
    }

    public void shiftTextFields(){
        LinkedList<JTextField> modifiedList = new LinkedList<>(textFieldList);

        for(int i=0; i<textFieldList.size(); i++){
            TextFieldPanel.add(modifiedList.get(i));
        }
        repaint();
        revalidate();
    }

    public LinkedList<JTextField> modifyList
            (LinkedList<JTextField> OriginalList, JTextField textField, JTextField newTextField) {
        LinkedList<JTextField> newList = new LinkedList<>();

        for(int i=0; i<OriginalList.size(); i++){
            newList.add(OriginalList.get(i));
            if(textField==OriginalList.get(i)){
                newList.add(newTextField);
            }
        }
        return newList;
    }

    public void printModifiedList(){
        for(int i=0; i<textFieldList.size(); i++){
            System.out.println(textFieldList.get(i).getText());
        }
        System.out.println();
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
        this.add(TextFieldPanel, BorderLayout.SOUTH);

        TextFieldPanel.setLayout(new GridLayout(0,1));
        TextFieldPanel.setPreferredSize(new Dimension(TF_WIDTH,TF_HEIGHT));

        createTextFields();
    }
}
