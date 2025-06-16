package Test2.Demo;

import javax.swing.*;
import java.awt.*;

public class panel extends JFrame {
    final int WIDTH = 500;
    final int HEIGHT = 50;
    int sizeableHEIGHT = HEIGHT;
    JPanel topContentPane = new JPanel();
    JPanel bottomContentPane = new JPanel();
    JPanel panelBorderSouth = new JPanel();

    int i = 0;
    public void createPanels(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        JTextField textField = new JTextField("" + i);
        JLabel label = new JLabel("" + i);

        panel.add(textField);
        panel.add(label);
        panelBorderSouth.add(panel);

        panelBorderSouth.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        i++;
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

        createPanels();

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
