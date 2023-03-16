package view.component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Label extends JLabel {
    boolean multiLine;
    boolean center;

    public Label() {
        this("");
    }

    public Label(String text) {
        this(text, 20, false, SwingConstants.CENTER);
    }

    public Label(String text, boolean multiLine, int alignment) {
        this(text, 20, true, alignment);
    }

    public Label(String text, int fontSize, boolean multiLine, int alignment) {
        GraphicsEnvironment ge = null;
        try{
            ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/Merchant.ttf")));

        } catch(FontFormatException e){} catch (IOException e){}

        setFont(new Font("Merchant Copy", Font.PLAIN, fontSize));
        setForeground(Color.white);

        center = false;
        if (alignment == SwingConstants.CENTER) {
            center = true;
        }
        this.multiLine = multiLine;

        //sets the horizontal alignment of the label
        setHorizontalAlignment(alignment);
        setVerticalAlignment(SwingConstants.CENTER);
        setText(text);
    }

    @Override
    public void setText(String text) {
        if (multiLine) {
            if (center) {
                text = "<html><center>" + text + "</center></html>"; //align in the center
            } else {
                text = "<html>" + text + "</html>";
            }
        }
        super.setText(text);
    }
}
