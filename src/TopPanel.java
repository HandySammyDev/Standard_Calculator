import Calculations.Basic_Calculations;
import Record.MixedData;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class TopPanel extends JPanel {
    private final int WIDTH = 500;
    private final int HEIGHT = 70;
    private final int LABEL_WIDTH = 180;

    private int sizeableHEIGHT = HEIGHT;
    private int sizeableLabelWIDTH = LABEL_WIDTH;

    private JPanel panelBorderSouth = new JPanel();
    private JTextField activeField = null;

    private LinkedList<MixedData> mixedDataLinkedList = new LinkedList<>();

    private String ans = null;
    private int positionOfCaret;
    private int indexOfActiveField;

    public void createPanels(){
        if(getPositionOfCaret()==0 && !mixedDataLinkedList.isEmpty()){
            return;
        }

        JPanel panel = new JPanel();
        JTextField textField = new JTextField();
        JLabel label = new JLabel();

        textField.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
            setActiveTextField(mixedDataLinkedList.getFirst().getTextField());
            highlightActiveTextField(getActiveTextField());
        }
        else{
            expandBorder();
            mixedDataLinkedList = modifiedPanelList(mixedDataLinkedList, getActiveTextField(), panel, textField, label);
            setActiveTextField(mixedDataLinkedList.get(indexOfNextTextField +1).getTextField());
            clearHighlightActiveTextField();
            highlightActiveTextField(activeField);
        }

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int pos = textField.getCaretPosition();
                setPositionOfCaret(pos);

                setActiveTextField(textField);
                clearHighlightActiveTextField();
                highlightActiveTextField(getActiveTextField());

                toggleClearButton();
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    createPanels();
                    repaint();
                    revalidate();

                    setPositionOfCaret(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE && getPositionOfCaret()==0 && mixedDataLinkedList.size()>1){
                    removeTextField();
                }
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeLabel();
                toggleClearButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeLabel();
                toggleClearButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeLabel();
                toggleClearButton();
            }
        });

        textField.addCaretListener(e -> {
            setPositionOfCaret(e.getDot()); //fix the backspace instant delete
            System.out.println("Position of caret: " + getPositionOfCaret());
        });

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setActiveTextField((JTextField) e.getSource());
                toggleClearButton();
            }
        });

        changeTextFields();
        setPositionOfCaret(0);
    }

    public void changeTextFields(){
        for(int i = 0; i< mixedDataLinkedList.size(); i++){
            panelBorderSouth.add(mixedDataLinkedList.get(i).getPanel());
        }
        repaint();
        revalidate();
    }

    private int indexOfNextTextField;
    public LinkedList<MixedData> modifiedPanelList
            (LinkedList<MixedData> originalList,
             JTextField activeTextField,
             JPanel newPanel,
             JTextField newTextField,
             JLabel newLabel){

        LinkedList<MixedData> newList = new LinkedList<>();

        for(int i = 0; i<originalList.size(); i++){
            newList.add(originalList.get(i));

            if(activeTextField == mixedDataLinkedList.get(i).getTextField()){
                newList.add(new MixedData(newPanel, newTextField, newLabel));
                indexOfNextTextField = i;
            }
        }
        return newList;
    }
    public void removeTextField(){
        shrinkBorder();
        if(getActiveTextField()==mixedDataLinkedList.getFirst().getTextField()){
            mixedDataLinkedList.removeFirst();
            setActiveTextField(mixedDataLinkedList.getFirst().getTextField());
            highlightActiveTextField(getActiveTextField());
            changeTextFields();
            return;
        }

        for(int i = 0; i< mixedDataLinkedList.size(); i++){
            if(getActiveTextField() == mixedDataLinkedList.get(i).getTextField()){
                mixedDataLinkedList.remove(i);
                setActiveTextField(mixedDataLinkedList.get(i-1).getTextField());
                highlightActiveTextField(getActiveTextField());
                changeTextFields();
            }
        }
    }

    public void changeLabel(){
        for(int i=0; i<mixedDataLinkedList.size(); i++){
            if(activeField == mixedDataLinkedList.get(i).getTextField()){
                Basic_Calculations calculations = new Basic_Calculations(getTextInTextField());

                errorIndex = i;
                ans = calculations.getCalculations();

                clearError();

                if(ans.equals("Error")){
                    if(getTextInTextField().isEmpty()){
                        mixedDataLinkedList.get(i).getLabel().setText(null);
                        return;
                    }
                    errorImageDelay();
                }
                else{
                    mixedDataLinkedList.get(i).getLabel().setText(ans);
                }
            }
        }
        repaint();
        revalidate();
    }

    private Timer errorTimer;
    private int errorIndex;
    public void errorImageDelay() {
        if(errorTimer!=null){
            errorTimer.cancel(); //cancel previous timer if still running
        }

        errorTimer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 1;
            @Override
            public void run() {
                count--;
                if(count<0){
                    errorTimer.cancel();
                    mixedDataLinkedList.get(errorIndex).getLabel().setIcon(errorImage());
                }
            }
        };

        errorTimer.scheduleAtFixedRate(task, 0, 750);
    }
    public void clearError(){
        if(errorTimer!=null){
            errorTimer.cancel();
        }
        mixedDataLinkedList.get(errorIndex).getLabel().setIcon(null);
        mixedDataLinkedList.get(errorIndex).getLabel().setText(null);
    }
    public ImageIcon errorImage(){
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/error1.png")));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50,50,Image.SCALE_AREA_AVERAGING);

        return new ImageIcon(scaledImage);
    }

    public void clearTextField(){
        getActiveTextField().setText("");
    }
    public void clearAllTextFields(){
        panelBorderSouth.removeAll();
        mixedDataLinkedList.clear();
        sizeableHEIGHT = HEIGHT;
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        repaint();
        revalidate();
    }

    public void toggleClearButton(){
        String clearButton = "clear";
        String CEButton = "CE";
        int index = Window.bottomPanel.indexOfClear;
        if(!getActiveTextField().getText().isEmpty() || mixedDataLinkedList.size()==1){
            Window.bottomPanel.buttonLists[index].setText(clearButton);
            Window.bottomPanel.buttonLists[index].setActionCommand(clearButton);
            //System.out.println(clearButton);
        }
        else if(getActiveTextField().getText().isEmpty()){
            Window.bottomPanel.buttonLists[index].setText(CEButton);
            Window.bottomPanel.buttonLists[index].setActionCommand(CEButton);
            //System.out.println(CEButton);
        }
    }

    private int mixedDataTemp;
    public void highlightActiveTextField(JTextField activeTextField){
        for(int i=0; i<mixedDataLinkedList.size(); i++){
            if(mixedDataLinkedList.get(i).getTextField()==activeTextField){
                mixedDataLinkedList.get(i).getPanel().setBorder(new LineBorder(Color.decode("#1e8d8f"), 4));
                toggleClearButton();

                setIndexOfChangedTextField(i);
                saveTextFieldData();

                mixedDataTemp = i; //we need this
            }
        }
    }
    public void clearHighlightActiveTextField(){
        try{
            mixedDataLinkedList.get(mixedDataTemp).getPanel().setBorder(new LineBorder(Color.decode("#1d2226")));
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Outta bounds");
        }
    }

    public void backspaceDelete(){

    }

    private int indexOfChangedTextField;
    private int getIndexOfChangedTextField(){
        return indexOfChangedTextField;
    }
    private void setIndexOfChangedTextField(int index){
        this.indexOfChangedTextField = index;
    }
    //if we clicked Enter
    public void saveTextFieldData(){
        Stack<LinkedList> stack = new Stack<>();

        //We need the index of the curr CHANGED jtextfield
        stack.push(mixedDataLinkedList);

        // Getting slices of text
        // Creating textfields
        // deleting textfields
    }

    public void expandBorder(){
        sizeableHEIGHT += HEIGHT;
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, sizeableHEIGHT));
    }
    public void shrinkBorder(){
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

    public JTextField getActiveTextField(){
        return activeField;
    }
    public void setActiveTextField(JTextField textField){
        this.activeField = textField;
    }
    public String getTextInTextField(){
        return getActiveTextField().getText();
    }
    public void setTextInTextField(String command){
        String currentText = getActiveTextField().getText();
        String newText = "";
        if(!currentText.isEmpty()){
            newText = currentText.substring(0,getPositionOfCaret()) + command + currentText.substring(getPositionOfCaret());
        }

        try {
            getActiveTextField().getDocument().insertString(getPositionOfCaret(), command, null);
            setPositionOfCaret(getPositionOfCaret() + 1);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        System.out.println("Output: " + newText);
    }
    public String getAns(){
        return ans;
    }
    public void setPositionOfCaret(int pos){
        this.positionOfCaret = pos;
    }
    public int getPositionOfCaret(){
        return positionOfCaret;
    }

    //Call this before the highlight method
    public void setIndexOfActiveField(){
        for(int i=0; i<mixedDataLinkedList.size(); i++){
            if(mixedDataLinkedList.get(i).getTextField() == getActiveTextField()){
                this.indexOfActiveField = i;
            }
        }
        this.indexOfActiveField = 0;
    }
    public int getIndexOfActiveField(){
        return indexOfActiveField;
    }
}