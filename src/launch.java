package Test9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class launch extends JFrame implements ActionListener {
    JPanel topPanel = new JPanel();

    final int PANEL_HEIGHT = 83;
    final int G_WIDTH = 400;
    int totalHeight = PANEL_HEIGHT;

    JPanel bottomPanel = new JPanel();
    JPanel panel1 = new JPanel(); //for numbers and extras
    JPanel calcPanel = new JPanel(); //for +, -, *, /

    //-----------------------------------------------------------------------
    static final int NUM_NUMBER_BUTTONS = 10;

    JButton[] buttonsList = new JButton[NUM_NUMBER_BUTTONS];

    public void createNumberButtons(){
        for(int i=0; i<NUM_NUMBER_BUTTONS; i++){
            JButton button = new JButton("" + i);
            button.addActionListener(this);
            button.setActionCommand("" + i);

            buttonsList[i] = button;
            panel1.add(button);
        }
    }

    String[] extraButtonString = {".", "ans"};

    public void createExtraButtons(){
        for (String s : extraButtonString) {
            JButton extraButton = new JButton(s);
            extraButton.addActionListener(this);
            extraButton.setActionCommand(s);
            panel1.add(extraButton);
        }
    }

    String[] calcButtonString = {"+","-","*","/"};

    public void createCalcButtons(){
        for(String s : calcButtonString){
            JButton calcButton = new JButton(s);
            calcButton.addActionListener(this);
            calcButton.setActionCommand(s);
            calcPanel.add(calcButton);
        }
    }

    //-----------------------------------------------------------------------
    JTextField activeField = null;

    LinkedList<JTextField> textFieldLinkedList = new LinkedList<>();

    JPanel growthPanel = new JPanel(); //THIS PANEL GROWS
    JPanel fillerPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(topPanel);

    public void createPermanentTextPanel(){
        growthPanel.setLayout(new GridLayout(0,1));
        growthPanel.setPreferredSize(new Dimension(G_WIDTH, totalHeight));
        growthPanel.setBackground(Color.red);

        fillerPanel.setPreferredSize(new Dimension(G_WIDTH, totalHeight*2));
        fillerPanel.setBackground(Color.red);

        //getContentPane().add(scrollPane);
        topPanel.add(fillerPanel);
        topPanel.add(growthPanel);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        repaint();
        revalidate();
    }

    public void permanentJTextField(){
        JTextField PTF = new JTextField();

        PTF.setSize(G_WIDTH,PANEL_HEIGHT);
        PTF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    createTextFields(); //creates TF and extends panel
                    growthPanel.revalidate();
                    topPanel.revalidate();
                }
            }
        });
        PTF.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                activeField = PTF;
            }
        });

        textFieldLinkedList.add(PTF); //index 0
        growthPanel.add(PTF);
    }

    public void createTextFields(){
        extendPanel();

        JTextField textField = new JTextField();
        growthPanel.add(textField);

        String replace = textFieldLinkedList.getFirst().getText();
        textField.setText(replace);
        textFieldLinkedList.getFirst().setText(null);
    }

    public void deleteTextFields(JTextField TF){
        shrinkPanel();
    }

    public void extendPanel(){
        totalHeight += PANEL_HEIGHT;
        growthPanel.setPreferredSize(new Dimension(G_WIDTH,totalHeight));
    }

    public void shrinkPanel(){
        totalHeight -= PANEL_HEIGHT;
        growthPanel.setPreferredSize(new Dimension(G_WIDTH,totalHeight));
    }

    //-----------------------------------------------------------------------

    launch(){
        final int HEIGHT = 500;
        final int WIDTH = 400;

        this.setLayout(new GridLayout(2,1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.white);

        panel1.setLayout(new GridLayout(4,3));
        //panel1.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        calcPanel.setLayout(new GridLayout(4,1));
        calcPanel.setPreferredSize(new Dimension(WIDTH/4, HEIGHT));

        createNumberButtons();
        createExtraButtons();
        createCalcButtons();

        //------------------------------------------------------------

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        createPermanentTextPanel();
        permanentJTextField();
        this.add(topPanel);

        //----------------------------------------------------------

        //NEED LAYOUT MANAGERS
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(panel1, BorderLayout.CENTER);
        bottomPanel.add(calcPanel, BorderLayout.EAST);

        add(bottomPanel);
        //pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(activeField != null){
            activeField.setText(activeField.getText() + command);
        }
    }
}
