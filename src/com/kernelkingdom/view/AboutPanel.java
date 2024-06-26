package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import java.util.Objects;

public class AboutPanel extends Panel{
    private Panel about;
    private ImageButton menuButton;

    public ImageButton getMenuButton() {
        return menuButton;
    }

    public AboutPanel () {
        super("about-and-text.gif");
        menuButton = new ImageButton("buttons/menu-button.png");
        menuButton.setBounds(16, 600, 171, 66);
        menuButton.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");
        menuButton.setOpaque(false);


        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/about-and-text.gif")));

        JLabel bgImage = new JLabel();
        bgImage.setBounds(0, 0, 700, 700);
        bgImage.setIcon(background);
        bgImage.add(menuButton);
        this.add(bgImage);
    }

    public static void main(String[] args) {
        AboutPanel m = new AboutPanel();
        Frame frame = new Frame("About Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}