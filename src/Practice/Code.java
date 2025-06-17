package Practice;

import javax.swing.*;
import java.awt.*;

public class Code extends JFrame {
    Code(){
        this.setLayout(new GridLayout(0,1));
        this.setSize(400,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setSize(400, 100);
        panel.setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300,100));
        panel.add(textField, BorderLayout.CENTER);

        JLabel label = new JLabel("Hello");
        label.setPreferredSize(new Dimension(100,100));
        panel.add(label, BorderLayout.EAST);

        this.add(panel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
