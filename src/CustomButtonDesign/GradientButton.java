package CustomButtonDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.CookieHandler;

public class GradientButton extends JButton {
    private boolean isHovered = true;
    private boolean isPressed = false;
    String colorGradient1;
    String colorGradient2;
    String colorHover1;
    String colorHover2;
    String colorPressed1;
    String colorPressed2;

    private String getColorGradient1(){
        return colorGradient1;
    }
    private String getColorGradient2(){
        return colorGradient2;
    }
    private String getColorHover1(){
        return colorHover1;
    }
    private String getColorHover2(){
        return colorHover2;
    }
    private String getColorPressed1(){
        return colorPressed1;
    }
    private String getColorPressed2(){
        return colorPressed2;
    }

    public GradientButton
            (String text,
             String fontColor,
             String colorGradient1, String colorGradient2,
             String colorHover1, String colorHover2,
             String colorPressed1, String colorPressed2){
        super(text);
        setContentAreaFilled(false); //Allows custom painting
        setFocusPainted(false);
        setBorderPainted(false);

        setForeground(Color.decode(fontColor)); //text color
        setFont(new Font("Arial", Font.BOLD,15));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });

        this.colorGradient1 = colorGradient1;
        this.colorGradient2 = colorGradient2;
        this.colorHover1 = colorHover1;
        this.colorHover2 = colorHover2;
        this.colorPressed1 = colorPressed1;
        this.colorPressed2 = colorPressed2;
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
        GradientPaint gradient;

        if(isPressed){
            gradient = new GradientPaint(
            0,0, Color.decode(getColorPressed1()),
            0, height, Color.decode(getColorPressed2()));
        }
        else if(isHovered){
            gradient = new GradientPaint(
            0,0,Color.decode(getColorGradient1()), //Top color
            0, height, Color.decode(getColorGradient2())); //Bottom color
        }
        else{
            gradient = new GradientPaint(
            0,0, Color.decode(getColorHover1()),
            0, height, Color.decode(getColorHover2()));
        }

        //Fill with gradient
        g2.setPaint(gradient);
        g2.fillRoundRect(0,0,width,height,10,10);

        g2.dispose(); //Clean up

        //Now paint the button text and borders
        super.paintComponent(g);
    }
}