package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;
import view.component.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class AboutPanel extends Panel{
    private Panel about;
    private ImageButton menuButton;

    public ImageButton getMenuButton() {
        return menuButton;
    }

    public AboutPanel () {
        super("about-blank.gif");
        menuButton = new ImageButton("buttons/menu-button.png");
        menuButton.setBounds(16, 600, 171, 66);
        menuButton.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");
        menuButton.setOpaque(false);


        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/about-blank.gif")));

        JLabel bgImage = new JLabel();
        bgImage.setBounds(0, 0, 700, 700);
        bgImage.setIcon(background);
        bgImage.add(menuButton);


        JLabel text = new Label("<html>In the heart of Tacloban City is a trio of brilliant young women who came " +
                "together with a shared passion for technology. As part of their CMSC 125 group " +
                "project, Norlyn, Charissa, and Jannah poured their hearts and souls into " +
                "creating Kernel Kingdom - a vibrant digital realm that promises endless " +
                "adventure and excitement. <br>" +
                "<br>" +
                "With their boundless creativity and technical know-how, these three third-" +
                "year students from UP Visayas Tacloban College have brought Kernel Kingdom to " +
                "life. <br>" +
                "<br>" +
                "Now, with the project complete, Norlyn, Charissa, and Jannah can't wait to see" +
                "how your knowledge on Operating Systems can help you win the game. Are you " +
                "ready to enter the Kingdom?</html>", 25,true, SwingConstants.CENTER);
        text.setBounds(0, 0, 700, 700);
        bgImage.add(text);

        this.add(bgImage);
    }

    public static void main(String[] args) {
        AboutPanel m = new AboutPanel();
        Frame frame = new Frame("About Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}