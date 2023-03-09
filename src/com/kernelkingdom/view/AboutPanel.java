package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AboutPanel extends Panel{
    private Panel about;
    private ImageButton menuButton;

    public ImageButton getMenuButton() {
        return menuButton;
    }

    public AboutPanel () {
        super("about-panel.png");
        menuButton = new ImageButton("buttons/menu-button.png");
        menuButton.setBounds(16, 600, 171, 66);
        menuButton.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");
        this.add(menuButton);
    }

    public static void main(String[] args) {
        AboutPanel m = new AboutPanel();
        Frame frame = new Frame("About Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}