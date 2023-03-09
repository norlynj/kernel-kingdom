package view;

import view.component.Frame;
import  view.component.ImageButton;
import view.component.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

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

        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/images/menu.gif")));

        JLabel bgImage = new JLabel();
        bgImage.setBounds(0, 0, 700, 700);
        bgImage.setIcon(background);
        bgImage.add(startButton);
        bgImage.add(instructionsButton);
        bgImage.add(aboutButton);
        bgImage.add(musicStop);
        bgImage.add(musicOn);

        this.add(bgImage);
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
        startButton.hover("buttons/start-hover.png", "buttons/start.png");
        instructionsButton.hover("buttons/instructions-hover.png", "buttons/instructions.png");
        aboutButton.hover("buttons/about-hover.png", "buttons/about.png");
        musicStop.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        musicOn.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");

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