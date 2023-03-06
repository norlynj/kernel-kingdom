package view;

import view.component.Frame;
import  view.component.ImageButton;
import view.component.Panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends Panel {
    private Panel menu;
    private ImageButton startButton;
    private ImageButton instructionsButton;
    private ImageButton aboutButton;
    private ImageButton music;

    public MenuPanel () {
        super("menu.gif");
        // MAIN MENU PANEL
        startButton = new ImageButton("buttons/start.png");
        instructionsButton = new ImageButton("buttons/instructions.png");
        aboutButton = new ImageButton("buttons/about.png");

        startButton.setBounds(74, 250, 187, 72);
        instructionsButton.setBounds(70, 350, 390, 78);
        aboutButton.setBounds(74, 450, 179, 69);


        // on hover listeners
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon("buttons/start-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                startButton.setIcon("buttons/start.png");
            }
        });

        instructionsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                instructionsButton.setIcon("buttons/instructions-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                instructionsButton.setIcon("buttons/instructions.png");
            }
        });

        aboutButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                aboutButton.setIcon("buttons/about-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                aboutButton.setIcon("buttons/about.png");
            }
        });

        music = new ImageButton("buttons/volume-on.png");
        music.setBounds(630,630,40,40);
        music.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                music.setIcon("buttons/volume-on-hover.png");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                music.setIcon("buttons/volume-on.png");
            }
        });

        this.add(startButton);
        this.add(instructionsButton);
        this.add(aboutButton);
        this.add(music);
    }
    public ImageButton getMusic(){
        return music;
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