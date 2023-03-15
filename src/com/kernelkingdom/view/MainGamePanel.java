package view;

import view.component.ImageButton;
import view.component.Frame;
import view.component.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGamePanel extends Panel {
    private Panel mainGame;
    private Panel success;
    private Panel gameOver;
    private Panel mapsPanel;

    private Panel letterButtonsPanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;
    private ImageButton[] letterButtons;
    private ImageButton restartButtonS;
    private ImageButton menuButtonS;
    private ImageButton restartButtonGO;
    private ImageButton menuButtonGO;
    private ImageButton musicStop;
    private ImageButton musicOn;
    private ImageButton restart;
    private JLabel blankLetters;
    private JLabel score;

    public Panel getMapsPanel() {
        return mapsPanel;
    }

    public Panel getLetterButtonsPanel() {
        return letterButtonsPanel;
    }

    public ImageButton[] getLetterButtons() {
        return letterButtons;
    }

    public ImageButton getRestartButtonS() {
        return restartButtonS;
    }

    public ImageButton getMenuButtonS() {
        return menuButtonS;
    }

    public ImageButton getRestartButtonGO() {
        return restartButtonGO;
    }

    public ImageButton getMenuButtonGO() {
        return menuButtonGO;
    }

    public void setSuccessGameOverVisibility(boolean a, boolean b) {
        this.success.setVisible(a);
        this.gameOver.setVisible(b);
    }

    public void setMaps(String image) {
        mapsPanel.setImage(image);
    }

    public void setBlankLettersFont(Font font) {
        blankLetters.setFont(font);
    }

    public void setBlankLettersText(String text) {
        blankLetters.setText(text);
    }

    public void restart() {
        this.gameOver.setVisible(false);
        this.success.setVisible(false);
        score.setText("score: 0");
        // update keyboard
        for (int i = 0; i < 26; i++) {
            this.letterButtons[i].setEnabled(true);
        }
    }

    public MainGamePanel () {
        super("main-game-panel-5.png");
        //Blanks for the word
        blankLetters = new JLabel();
        blankLetters.setBounds(10, 87, 700, 71);
        blankLetters.setFont(new Font("Source Sans Pro", Font.BOLD, 35));
        blankLetters.setForeground(Color.WHITE);
        blankLetters.setHorizontalAlignment(JLabel.CENTER);

        //score
        score = new JLabel();
        score.setBounds(47, 140, 700, 71);
        score.setFont(new Font("Source Sans Pro", Font.BOLD, 20));
        score.setForeground(Color.WHITE);
        score.setText("score: 0");

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
            letterButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    letterButtons[finalI].setEnabled(false);
                }
            });

            letterButtons[finalI].hover("alphabet/selected/" + buttonValue + ".png", "alphabet/normal/" + buttonValue + ".png");

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
        restartButtonS = new ImageButton("buttons/restart.png");
        menuButtonS = new ImageButton("buttons/menu-button.png");

        restartButtonS.setBounds(66, 400, 254, 91);
        menuButtonS.setBounds(59, 500, 171, 66);


        gameOver = new Panel(false, "game-over-panel.png");
        restartButtonGO = new ImageButton("buttons/restart.png");
        menuButtonGO = new ImageButton("buttons/menu-button.png");
        musicStop = new ImageButton("buttons/volume-off.png");
        musicOn = new ImageButton("buttons/volume-on.png");
        restart = new ImageButton("buttons/loop.png");

        restartButtonGO.setBounds(66, 400, 254, 91);
        menuButtonGO.setBounds(59, 500, 171, 66);
        musicStop.setBounds(630,630,40,40);
        musicOn.setBounds(630,630,40,40);
        restart.setBounds(560,630,40,40);

        setListeners();

        musicStop.setVisible(false);
        musicOn.setVisible(true);

        success.add(restartButtonS);
        success.add(menuButtonS);
        gameOver.add(restartButtonGO);
        gameOver.add(menuButtonGO);
        this.add(success);
        this.add(gameOver);
        this.add(blankLetters);
        this.add(score);
        this.add(mapsPanel);
        this.add(letterButtonsPanel);
        this.add(musicStop);
        this.add(musicOn);
        this.add(restart);

    }

    public JLabel getScore() {
        return score;
    }

    public ImageButton getMusicStop() {
        return musicStop;
    }

    public ImageButton getMusicOn() {
        return musicOn;
    }

    private void setListeners() {
        restartButtonS.hover("buttons/restart-hover.png", "buttons/restart.png");
        menuButtonS.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");
        restartButtonGO.hover("buttons/restart-hover.png", "buttons/restart.png");
        menuButtonGO.hover("buttons/menu-button-hover.png", "buttons/menu-button.png");
        musicStop.hover("buttons/volume-on-hover.png", "buttons/volume-off.png");
        musicOn.hover("buttons/volume-off-hover.png", "buttons/volume-on.png");
        restart.hover("buttons/loop-hover.png", "buttons/loop.png");
    }

    public ImageButton getRestart() {
        return restart;
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
        MainGamePanel m = new MainGamePanel();
        Frame frame = new Frame("Main Game Panel");
        frame.add(m);
        frame.setVisible(true);
    }

}