package view;

import view.component.Frame;
import view.component.ImageButton;
import view.component.Panel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InstructionsPanel extends Panel {
    private Panel instructions;
    private ImageButton menuButton;

    public InstructionsPanel () {
        super("instructions-and-text.gif");
        menuButton = new ImageButton("buttons/menu-button.png");
        menuButton.setBounds(16, 600, 171, 66);
        menuButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menuButton.setIcon("buttons/menu-button-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                menuButton.setIcon("buttons/menu-button.png");
            }
        });
        this.add(menuButton);
    }

    public static void main(String[] args) {
        InstructionsPanel m = new InstructionsPanel();
        Frame frame = new Frame("Instructions Panel");
        frame.add(m);
        frame.setVisible(true);
    }


}