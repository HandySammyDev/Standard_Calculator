import Calculations.Basic_Calculations;
import Record.MixedData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Objects;

public class TopPanel extends JPanel {
    final int WIDTH = 500;
    final int HEIGHT = 70;
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

    public void createPanels(){
        if(activeCaret==0 && !mixedDataLinkedList.isEmpty()){
            return;
        }

        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        JLabel label = new JLabel();

        textField.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, HEIGHT));
        textField.setFont(new Font(null, Font.PLAIN, 25));
        textField.setBackground(Color.decode("#f0edec"));
        textField.setBorder(null);

        label.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        label.setFont(new Font(null, Font.PLAIN, 25));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBackground(Color.decode("#f0edec"));

        panel.setBorder(new LineBorder(Color.decode("#1d2226")));
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
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    Basic_Calculations calculations = new Basic_Calculations(getTextInTextField());
                    for(int i=0; i<mixedDataLinkedList.size(); i++){
                        if(activeField == mixedDataLinkedList.get(i).getTextField()){
                            mixedDataLinkedList.get(i).getLabel().setIcon(null);
                            mixedDataLinkedList.get(i).getLabel().setText(null);

                            if(calculations.getCalculations().equals("Error")){
                                ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/error1.png")));
                                Image scaledImage = originalIcon.getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING);
                                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                                mixedDataLinkedList.get(i).getLabel().setIcon(scaledIcon);
                            }
                            else{
                                mixedDataLinkedList.get(i).getLabel().setText(calculations.getCalculations());
                            }
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
        this.setBackground(Color.decode("#31383d"));
        this.setOpaque(true);
        this.setLayout(new BorderLayout());
        this.add(panelBorderSouth, BorderLayout.SOUTH);

        panelBorderSouth.setLayout(new GridLayout(0,1));
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        createPanels();
    }
}
