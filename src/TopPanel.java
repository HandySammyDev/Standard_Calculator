package Test9;

import Test9.Calculations.Basic_Calculations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class TopPanel extends JPanel {
    final int TF_WIDTH = 500;
    final int TF_HEIGHT = 50;
    int sizeableTF_HEIGHT = TF_HEIGHT;

    JPanel TextFieldPanel = new JPanel();

    LinkedList<JTextField> textFieldList = new LinkedList<>();

    JTextField activeTF = null;
    int activeCaret = 0;

    public JTextField getActiveTextField(){
        return activeTF;
    }
    public String getTextInTextField(){
        return activeTF.getText();
    }
    public void setTextField(String command){
        activeTF.setText(activeTF.getText() + command);
    }

    public void createTextFields(){
        if(activeCaret==0 && !textFieldList.isEmpty()){
            return;
        }
        JTextField TF = new JTextField();
        TF.setFont(new Font(null, Font.PLAIN, 25));

        if(textFieldList.isEmpty()){
            TextFieldPanel.add(TF);
            textFieldList.add(TF);
        }
        else{
            expandPanel();
            TextFieldPanel.add(TF);
            textFieldList = modifyAddList(textFieldList, activeTF, TF);
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
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE && activeCaret==0 && textFieldList.size()>1){
                    shrinkPanel();
                    textFieldList.remove(activeTF);
                    changeTextFields();
                    printModifiedList();
                }
                if(e.getKeyCode()==KeyEvent.VK_E){
                    new Basic_Calculations(getTextInTextField());
                }
            }
        });
        TF.addCaretListener(e -> activeCaret = e.getDot());
        TF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeTF = (JTextField) e.getSource();
            }
        });
        changeTextFields();
        activeCaret = 0;
    }

    public void changeTextFields(){
        for(int i=0; i<textFieldList.size(); i++){
            TextFieldPanel.add(textFieldList.get(i));
        }
        repaint();
        revalidate();
    }

    public LinkedList<JTextField> modifyAddList
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

    public void expandPanel(){
        sizeableTF_HEIGHT += TF_HEIGHT;
        TextFieldPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public void shrinkPanel(){
        TextFieldPanel.removeAll();
        repaint();
        revalidate();
        sizeableTF_HEIGHT -= TF_HEIGHT;
        TextFieldPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public TopPanel(){
        this.setBackground(Color.decode("#F2F3F4"));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(TextFieldPanel, BorderLayout.SOUTH);

        TextFieldPanel.setLayout(new GridLayout(0,1));
        TextFieldPanel.setPreferredSize(new Dimension(TF_WIDTH,TF_HEIGHT));

        createTextFields();
    }

    public void printModifiedList(){
        for(int i=0; i<textFieldList.size(); i++){
            System.out.println(textFieldList.get(i).getText());
        }
        System.out.println("-------------------------------");
    }
}
