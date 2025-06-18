import Calculations.Basic_Calculations;
import Record.MixedData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class TopPanel extends JPanel {
    final int WIDTH = 500;
    final int HEIGHT = 50;
    int sizeableHEIGHT = HEIGHT;
    final int TEXT_FIELD_WIDTH = WIDTH;
    final int LABEL_WIDTH = 180;
    int sizeableLabelWIDTH = LABEL_WIDTH;
    JPanel panelBorderSouth = new JPanel();
    JTextField activeField = null;
    int activeCaret = 0;
    LinkedList<MixedData> mixedDataLinkedList = new LinkedList<>();

    public JTextField getActiveTextField(){
        return activeField;
    }
    public String getTextInTextField(){
        return activeField.getText();
    }
    public void setTextField(String command){
        activeField.setText(activeField.getText() + command);
    }

    int i=0;
    public void createPanels(){
        if(activeCaret==0 && !mixedDataLinkedList.isEmpty()){
            return;
        }

        JPanel panel = new JPanel();
        JTextField textField = new JTextField("" + i);
        JLabel label = new JLabel("" + i);
        textField.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, HEIGHT));
        label.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(label, BorderLayout.EAST);

        if(mixedDataLinkedList.isEmpty()){
            panelBorderSouth.add(panel);
            mixedDataLinkedList.add(new MixedData(panel, textField, label));
        }
        else{
            expandBorder();
            mixedDataLinkedList = modifiedPanelList(mixedDataLinkedList, activeField, panel, textField, label);
        }

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                activeField = textField;
            }
        });
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    createPanels();
                    repaint();
                    revalidate();
                    System.out.println("Works");
                }
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE && activeCaret==0 && mixedDataLinkedList.size()>1){
                    shrinkPanel();
                    for(int i = 0; i< mixedDataLinkedList.size(); i++){
                        if(activeField == mixedDataLinkedList.get(i).getTextField()){
                            mixedDataLinkedList.remove(i);
                        }
                    }
                    changeTextFields();
                }
                if(e.getKeyCode()==KeyEvent.VK_E){
                    Basic_Calculations calculations = new Basic_Calculations(getTextInTextField());
                    for(int i=0; i<mixedDataLinkedList.size(); i++){
                        if(activeField == mixedDataLinkedList.get(i).getTextField()){
                            mixedDataLinkedList.get(i).getLabel().setText(calculations.getCalculations());
                        }
                    }
                    repaint();
                    revalidate();
                }
            }
        });
        textField.addCaretListener(e -> activeCaret = e.getDot());
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeField = (JTextField) e.getSource();
            }
        });
        changeTextFields();
        activeCaret = 0;
        i++;
    }

    public void changeTextFields(){
        for(int i = 0; i< mixedDataLinkedList.size(); i++){
            panelBorderSouth.add(mixedDataLinkedList.get(i).getPanel());
        }
        repaint();
        revalidate();
    }

    public LinkedList<MixedData> modifiedPanelList
            (LinkedList<MixedData> originalList,
             JTextField activeTextField,
             JPanel newPanel,
             JTextField newTextField,
             JLabel newLabel){

        LinkedList<MixedData> newList = new LinkedList<>();

        for(int i = 0; i<originalList.size(); i++){
            newList.add(originalList.get(i));

            if(activeTextField== mixedDataLinkedList.get(i).getTextField()){
                newList.add(new MixedData(newPanel, newTextField, newLabel));
            }
        }
        return newList;
    }

    public void expandBorder(){
        sizeableHEIGHT += HEIGHT;
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, sizeableHEIGHT));
        System.out.println(sizeableHEIGHT);
    }

    public void shrinkPanel(){
        panelBorderSouth.removeAll();
        repaint();
        revalidate();
        sizeableHEIGHT -= HEIGHT;
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, sizeableHEIGHT));
    }

    public TopPanel(){
        this.setBackground(Color.decode("#F2F3F4"));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(panelBorderSouth, BorderLayout.SOUTH);

        panelBorderSouth.setLayout(new GridLayout(0,1));
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        createPanels();


    }
}
