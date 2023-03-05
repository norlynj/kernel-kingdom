package view;

import view.component.ImageButton;
import view.component.Frame;

import view.component.Panel;



import javax.swing.*;
import java.awt.*;

public class MainGamePanel extends Panel {
    private Panel mainGame;
    private Panel success;
    private Panel gameOver;
    private Panel mapsPanel;

    private Panel letterButtonsPanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;
    private ImageButton[] letterButtons;
    private ImageButton restartButton;
    private ImageButton menuButton;
    private JLabel blankLetters;
    

    public MainGamePanel () {
        super("main-game-panel-5.png");
        //Blanks for the word
        blankLetters = new JLabel();
        blankLetters.setBounds(10, 87, 700, 71);
        blankLetters.setFont(new Font("Source Sans Pro", Font.BOLD, 35));
        blankLetters.setHorizontalAlignment(JLabel.CENTER);
        // Maps panel
        mapsPanel = new Panel(true, "maps/new-state.png", 47, 200, 338, 294);
        // Keybroard buttons
        int buttonBoundsX = 17;
        int buttonBoundsY = 22;
        int buttonDimensions = 43;
        int incrementer = 55;
        letterButtonsPanel = new Panel(405, 170, 300, 365);
        letterButtons = new ImageButton[26];
        for (int i = 0; i < 26; i++) {

            letterButtons[i] = new ImageButton("alphabet/normal/" + Character.toString((char) ('A' + i)) + ".png");
//            letterButtons[i] = new ImageButton(Character.toString((char) ('A' + i)));
            int finalI = i;
            char buttonValue = (Character.toString((char) ('A' + i)) + ".png").charAt(0);

            if ((i+1) % 5 == 0) {
                letterButtons[i].setBounds(buttonBoundsX, buttonBoundsY, buttonDimensions, buttonDimensions);
                buttonBoundsX = 17;
                buttonBoundsY += incrementer;
            } else {
                letterButtons[i].setBounds(buttonBoundsX, buttonBoundsY, buttonDimensions, buttonDimensions);
                buttonBoundsX += incrementer;

            }

            letterButtonsPanel.add(letterButtons[i]);

        }
        letterButtonsPanel.setVisible(true);

        success = new Panel(false, "success-panel.png");
        restartButton = new ImageButton("buttons/restart.png");
        menuButton = new ImageButton("buttons/menu-button.png");

        restartButton.setBounds(66, 400, 254, 91);
        menuButton.setBounds(59, 500, 171, 66);

        gameOver = new Panel(false, "game-over-panel.png");

        success.add(restartButton);
        success.add(menuButton);
        this.add(success);
        this.add(gameOver);
        this.add(blankLetters);
        this.add(mapsPanel);
        this.add(letterButtonsPanel);


    }

    public static void main(String[] args) {
        MainGamePanel m = new MainGamePanel();
        Frame frame = new Frame("Main Game Panel");
        frame.add(m);
        frame.setVisible(true);
    }


}