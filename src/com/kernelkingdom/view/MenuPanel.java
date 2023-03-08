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

    private ImageButton musicStop;
    private ImageButton musicOn;

    public MenuPanel () {
        super("menu.png");
        // MAIN MENU PANEL
        startButton = new ImageButton("buttons/start.png");
        instructionsButton = new ImageButton("buttons/instructions.png");
        aboutButton = new ImageButton("buttons/about.png");
        musicStop = new ImageButton("buttons/volume-off.png");
        musicOn = new ImageButton("buttons/volume-on.png");

        startButton.setBounds(74, 250, 187, 72);
        instructionsButton.setBounds(70, 350, 390, 78);
        aboutButton.setBounds(74, 450, 179, 69);
        musicStop.setBounds(630,630,40,40);
        musicOn.setBounds(630,630,40,40);

        musicStop.setVisible(false);
        musicOn.setVisible(true);

        setListeners();

        this.add(startButton);
        this.add(instructionsButton);
        this.add(aboutButton);
        this.add(musicStop);
        this.add(musicOn);
    }

    public ImageButton getMusicStop() {
        return musicStop;
    }

    public ImageButton getMusicOn() {
        return musicOn;
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

    private void setListeners(){
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


        musicStop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                musicStop.setIcon("buttons/volume-on-hover.png");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                musicStop.setIcon("buttons/volume-off.png");
            }
        });

        musicOn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                musicOn.setIcon("buttons/volume-off-hover.png");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                musicOn.setIcon("buttons/volume-on.png");
            }
        });
    }

    public void musicClick() {
        if (musicStop.isVisible()){
            musicOn.setVisible(true);
            musicStop.setVisible(false);
        } else {
            musicOn.setVisible(false);
            musicStop.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MenuPanel m = new MenuPanel();
        Frame frame = new Frame("Menu Panel");
        frame.add(m);
        frame.setVisible(true);
    }
}