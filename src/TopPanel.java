import Calculations.Basic_Calculations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class TopPanel extends JPanel {
    final int TF_WIDTH = 500;
    final int TF_HEIGHT = 100;
    int sizeableTF_HEIGHT = TF_HEIGHT;
    int sizeableLabel_WIDTH = 0;

    JPanel MainTopPanel = new JPanel();

    LinkedList<JTextField> textFieldList = new LinkedList<>();
    LinkedList<JPanel> jPanelList = new LinkedList<>();

    JTextField activeTF = null;
    int activeCaret = 0;
    JPanel parentPanel;

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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextField TF = new JTextField();
        JLabel answerDisplay = new JLabel();

        TF.setFont(new Font(null, Font.PLAIN, 25));

        if(textFieldList.isEmpty()){
            MainTopPanel.add(panel);
            panel.add(TF, BorderLayout.CENTER);

            jPanelList.add(panel);
            textFieldList.add(TF);
        }
        else{
            expandPanel();
            MainTopPanel.add(panel);
            panel.add(TF, BorderLayout.EAST);

            jPanelList = modifyJPanelList(jPanelList, parentPanel, panel);
            textFieldList = modifyAddList(textFieldList, activeTF, TF);
        }

        TF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                activeTF = TF;
                parentPanel = (JPanel) activeTF.getParent();
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
                    changeTextFieldsAndPanel();
                    printModifiedList();
                }
                if(e.getKeyCode()==KeyEvent.VK_E){
                    if(sizeableLabel_WIDTH <= 200){
                        sizeableLabel_WIDTH += 50;
                    }
                    answerDisplay.setPreferredSize(new Dimension(sizeableLabel_WIDTH, TF_HEIGHT));
                    repaint();
                    revalidate();

                    Basic_Calculations calculations = new Basic_Calculations(getTextInTextField());
                    answerDisplay.setText(calculations.getCalculations());
                    panel.add(answerDisplay);
                    repaint();
                    revalidate();
                }
            }
        });
        TF.addCaretListener(e -> activeCaret = e.getDot());
        TF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeTF = (JTextField) e.getSource();
                parentPanel = (JPanel) activeTF.getParent();
            }
        });
        changeTextFieldsAndPanel();
        activeCaret = 0;
    }

    public void changeTextFieldsAndPanel(){
        for(int i=0; i<textFieldList.size(); i++){
            jPanelList.get(i).add(textFieldList.get(i));
        }
        repaint();
        revalidate();
    }

    public LinkedList<JTextField> modifyAddList
            (LinkedList<JTextField> OriginalList, JTextField textField, JTextField newTextField) {
        LinkedList<JTextField> newList = new LinkedList<>();

        for (JTextField jTextField : OriginalList) {
            newList.add(jTextField);
            if (textField == jTextField) {
                newList.add(newTextField);
            }
        }
        return newList;
    }

    public LinkedList<JPanel> modifyJPanelList
            (LinkedList<JPanel> OriginalList, JPanel jPanel, JPanel newJPanel) {
        LinkedList<JPanel> newList = new LinkedList<>();

        for (JPanel panel : OriginalList) {
            newList.add(panel);
            if (jPanel == panel) {
                newList.add(newJPanel);
            }
        }
        return newList;
    }

    public void expandPanel(){
        sizeableTF_HEIGHT += TF_HEIGHT;
        MainTopPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public void shrinkPanel(){
        MainTopPanel.removeAll();
        repaint();
        revalidate();
        sizeableTF_HEIGHT -= TF_HEIGHT;
        MainTopPanel.setPreferredSize(new Dimension(WIDTH, sizeableTF_HEIGHT));
    }

    public TopPanel(){
        this.setBackground(Color.decode("#F2F3F4"));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(MainTopPanel, BorderLayout.SOUTH);

        MainTopPanel.setLayout(new GridLayout(0,1));
        MainTopPanel.setPreferredSize(new Dimension(TF_WIDTH,TF_HEIGHT));

        createTextFields();
    }

    public void printModifiedList(){
        for(int i=0; i<textFieldList.size(); i++){
            System.out.println(textFieldList.get(i).getText());
        }
        System.out.println("-------------------------------");
    }
}
