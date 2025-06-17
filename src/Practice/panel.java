package Practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

public class panel extends JFrame {
    final int WIDTH = 500;
    final int HEIGHT = 50;
    int sizeableHEIGHT = 0;
    final int LABEL_WIDTH = 50;
    int sizeableLabelWIDTH = LABEL_WIDTH;
    JPanel topContentPane = new JPanel();
    JPanel bottomContentPane = new JPanel();
    JPanel panelBorderSouth = new JPanel();
    ArrayList<JPanel> panelList = new ArrayList<>();
    ArrayList<JTextField> textFieldList = new ArrayList<>();
    ArrayList<JLabel> labelList = new ArrayList<>();
    JTextField activeField = null;

    ArrayList<MixedData> mixedDataArrayList = new ArrayList<>();

    int i = 0;
    public void createPanels(){
        JPanel panel = new JPanel();
        JTextField textField = new JTextField("" + i);
        JLabel label = new JLabel("" + i);
        label.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));

        panel.setLayout(new BorderLayout());

        if(panelList.isEmpty()){
            panelBorderSouth.add(panel);
            mixedDataArrayList.add(panel, textField, label);
        }
        else{
            expandBorder();
            panelList = modifiedPanelList(panelList, activeField, panel);
        }

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    System.out.println("Works");
                }
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                activeField = (JTextField) e.getSource();
            }
        });
        i++;
    }

    public ArrayList<JPanel> modifiedPanelList
            (ArrayList<JPanel> originalList ,JTextField activeTextField, JPanel newPanel){
        ArrayList<JPanel> newList = new ArrayList<>();
        for(int i = 0; i<originalList.size(); i++){
            newList.add(originalList.get(i));
            if(activeTextField==textFieldList.get(i)){
                newList.add(newPanel);
            }
        }
        return newList;
    }

    public void expandBorder(){
        sizeableHEIGHT += HEIGHT;
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, sizeableHEIGHT));
        System.out.println(sizeableHEIGHT);
    }

    panel(){
        this.setLayout(new GridLayout(2,1));
        this.setSize(400,500);

        topContentPane.setLayout(new BorderLayout());
        bottomContentPane.setBackground(Color.blue);
        this.add(topContentPane);
        this.add(bottomContentPane);
        panelBorderSouth.setLayout(new GridLayout(0,1));
        topContentPane.add(panelBorderSouth, BorderLayout.SOUTH);
        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        createPanels();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
