package CustomButtonDesign;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    String colorGradient1;
    String colorGradient2;

    private String getColorGradient1(){
        return colorGradient1;
    }
    private String getColorGradient2(){
        return colorGradient2;
    }

    public GradientButton(String text, String fontColor, String colorGradient1, String colorGradient2){
        super(text);
        setContentAreaFilled(false); //Allows custom painting
        setForeground(Color.decode(fontColor)); //text color
        setFont(new Font("Arial", Font.BOLD,15));
        setFocusPainted(false);
        setBorderPainted(false);

        this.colorGradient1 = colorGradient1;
        this.colorGradient2 = colorGradient2;
    }

    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create(); //cast to graphics2d

        //Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = 30;

        //Monomorphic shadows
        Color shadowLight = new Color(255,255,255,80); //top left
        Color shadowDark = new Color(0,0,0,60); //bottom right

        //Shadow pass
        g2.setPaint(shadowLight);
        g2.fillRoundRect(-6,-6,width,height,arc,arc);

        g2.setPaint(shadowDark);
        g2.fillRoundRect(6,6,width,height,arc,arc);

        //Create gradient paint (from top to bottom)
        GradientPaint gp = new GradientPaint(
                0,0,Color.decode(getColorGradient1()), //Top color "#31383d"
                0, getHeight(), Color.decode(getColorGradient2()) //Bottom color "#121317"
        );

        //Fill with gradient
        g2.setPaint(gp);
        g2.fillRoundRect(0,0,getWidth(),getHeight(),10,10);

        g2.dispose(); //Clean up

        //Now paint the button text and borders
        super.paintComponent(g);
    }
}
