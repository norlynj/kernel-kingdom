package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class InstructionsPanel extends Panel {
    private Panel instructions;
    private ImageButton menuButton;

    public ImageButton getMenuButton() {
        return menuButton;
    }

    public InstructionsPanel () {
        super("instructions-panel.png");
        menuButton = new ImageButton("buttons/menu-button.png");
        menuButton.setBounds(16, 600, 171, 66);
        menuButton.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");

        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/instructions-and-text.gif")));

        JLabel bgImage = new JLabel();
        bgImage.setBounds(0, 0, 700, 700);
        bgImage.setIcon(background);
        bgImage.add(menuButton);

        this.add(bgImage);
    }

    public static void main(String[] args) {
        InstructionsPanel m = new InstructionsPanel();
        Frame frame = new Frame("Instructions Panel");
        frame.add(m);
        frame.setVisible(true);
    }


}