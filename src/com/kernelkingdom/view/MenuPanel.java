package view;

import view.component.Frame;
import  view.component.ImageButton;
import view.component.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends Panel {
    private Panel menu;
    ImageButton startButton;
    ImageButton instructionsButton;
    ImageButton aboutButton;

    public MenuPanel () {
        super("menu.gif");
        // MAIN MENU PANEL
        startButton = new ImageButton("buttons/start.png");
        instructionsButton = new ImageButton("buttons/instructions.png");
        aboutButton = new ImageButton("buttons/about.png");

        startButton.setBounds(74, 250, 187, 72);
        instructionsButton.setBounds(70, 350, 390, 78);
        aboutButton.setBounds(74, 450, 179, 69);

        this.add(startButton);
        this.add(instructionsButton);
        this.add(aboutButton);

    }

    public ImageButton getStartButton() {
        return startButton;
    }

    public ImageButton getInstructionsButton() {
        return instructionsButton;
    }

    public ImageButton getAboutButton() {
        return aboutButton;
    }

    public static void main(String[] args) {
        MenuPanel m = new MenuPanel();
        Frame frame = new Frame("Menu Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}