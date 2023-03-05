package view;

import view.component.Frame;
import view.component.Panel;

public class AboutPanel extends Panel{
    private Panel about;

    public AboutPanel () {
        super("about-and-text.gif");
    }

    public static void main(String[] args) {
        AboutPanel m = new AboutPanel();
        Frame frame = new Frame("About Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}