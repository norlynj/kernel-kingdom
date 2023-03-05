package view;

import view.component.Frame;
import view.component.Panel;

public class InstructionsPanel extends Panel {
    private Panel instructions;

    public InstructionsPanel () {
        super("instructions-and-text.gif");
    }

    public static void main(String[] args) {
        InstructionsPanel m = new InstructionsPanel();
        Frame frame = new Frame("Instructions Panel");
        frame.add(m);
        frame.setVisible(true);
    }


}