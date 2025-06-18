package Record;

import javax.swing.*;

public class MixedData {
    JPanel panel;
    JTextField textField;
    JLabel label;

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JPanel getPanel() {
        return panel;
    }

    public MixedData(JPanel panel, JTextField textField, JLabel label){
        this.panel = panel;
        this.textField = textField;
        this.label = label;
    }
}
