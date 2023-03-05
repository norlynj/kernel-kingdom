package view.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Panel extends JPanel {
    private BufferedImage background;

    public Panel(String imageName) {
        this(true, imageName, 0, 0, 700, 700); //panel with bg photo
    }

    public Panel(boolean visible, String imageName) {
        this(visible, imageName, 0, 0, 700, 700); //panel with bg photo
    }


    public Panel(int x, int y, int width, int height) {
        this(true, "", x, y, width, height); //Panel with dimensions and position
    }

    public Panel(boolean visible, String imageName, int x, int y, int width, int height) {
        setOpaque(false);
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(visible);

        if (!imageName.equals("")) { //Checks that the specified object reference is not null. This method is designed primarily for doing parameter validation in methods and constructors,
            URL url =Objects.requireNonNull(getClass().getResource("/resources/images/" + imageName));
            try {
                background = ImageIO.read(url); //upload an image file
            } catch (IOException e) {
                System.out.println("Image can't be read");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }

    //allows one to add a bg image to the panel
    public void setImage(String imageName) {
        URL url =Objects.requireNonNull(getClass().getResource("/resources/images/" + imageName));
        try {
            background = ImageIO.read(url); //upload an image file
        } catch (IOException e) {
            System.out.println("Image can't be read");
        }
        repaint();
    }
}
