package view;

import model.Game;
import view.component.AudioPlayer;
import  view.component.ImageButton;
import  view.component.Frame;
import  view.component.Panel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KernelKingdom {
    private MenuPanel menuPanel;
    private InstructionsPanel instructionsPanel;
    private AboutPanel aboutPanel;
    private MainGamePanel mainGamePanel;
    private Frame frame;
    private Panel contentPane;
    private CardLayout cardLayout;
    private Game game;

    private AudioPlayer audio;
    private boolean allowedToGuess = false;

    public KernelKingdom() {
        audio = new AudioPlayer("bgm.wav");
        audio.play();
        audio.loop();
        frame = new Frame("Kernel Kingdom");
        game = new Game();
        game.newGame();

        // create Panels
        menuPanel = new MenuPanel();
        instructionsPanel = new InstructionsPanel();
        aboutPanel = new AboutPanel();
        mainGamePanel = new MainGamePanel();
        updateBlankSpaces(game.getCurrentGuess());

        // set up the content pane and card layout
        contentPane = new Panel(true, "menu.png");
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        // add the panels to the content pane
        contentPane.add(menuPanel, "menuPanel");
        contentPane.add(mainGamePanel, "mainGamePanel");

        contentPane.add(instructionsPanel, "instructionsPanel");
        contentPane.add(aboutPanel, "aboutPanel");

        listenToMenu();
        listenToAbout();
        listenToInstructions();
        listenToMainGame();
        listenToMusic();

        frame.add(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    private void listenToMenu() {
        menuPanel.getStartButton().addActionListener(e -> {
            cardLayout.show(contentPane, "mainGamePanel" );
            allowedToGuess = true;
        });
        menuPanel.getInstructionsButton().addActionListener(e -> {
            cardLayout.show(contentPane, "instructionsPanel" );
            allowedToGuess = false;
        });
        menuPanel.getAboutButton().addActionListener(e -> {
            cardLayout.show(contentPane, "aboutPanel" );
            allowedToGuess = false;
        });
    }

    private void listenToInstructions() {
        instructionsPanel.getMenuButton().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));
    }

    private void listenToAbout() {
        aboutPanel.getMenuButton().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));
    }

    private void listenToMainGame(){
        mainGamePanel.getRestartButtonR().addActionListener(e -> {
            restartGame();
            mainGamePanel.getRestartPanel().setVisible(false);
        });
        mainGamePanel.getContinueButtonR().addActionListener(e -> mainGamePanel.getRestartPanel().setVisible(false));
        mainGamePanel.getRestartButtonS().addActionListener(e -> restartGame());
        mainGamePanel.getRestartButtonGO().addActionListener(e -> restartGame());
        mainGamePanel.getRestart().addActionListener(e -> mainGamePanel.getRestartPanel().setVisible(true));
        mainGamePanel.getMenuButtonS().addActionListener(e -> {
            cardLayout.show(contentPane, "menuPanel" );
            restartGame();
            allowedToGuess = false;
        });
        mainGamePanel.getMenuButtonGO().addActionListener(e -> {
            cardLayout.show(contentPane, "menuPanel" );
            restartGame();
            allowedToGuess = false;

        });

        mainGamePanel.getMenuButtonR().addActionListener(e -> {
            mainGamePanel.getRestartPanel().setVisible(false);
            cardLayout.show(contentPane, "menuPanel" );
            restartGame();
            allowedToGuess = false;
        });
        mainGamePanel.getMenuButtonR().addActionListener(e -> {
            cardLayout.show(contentPane, "menuPanel" );
            restartGame();
            allowedToGuess = false;

        });

        ImageButton[] letterButtons = mainGamePanel.getLetterButtons();
        for (int i = 0; i < 26; i++){
            char buttonValue = (Character.toString((char) ('A' + i)) + ".png").charAt(0);
            int finalI = i;
            letterButtons[i].addActionListener(e ->  guess(Character.toLowerCase(buttonValue), finalI));
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int letterIndex = keyCode - KeyEvent.VK_A; // convert key code to letter index (0-25)
                if (letterIndex >= 0 && letterIndex <= 25 && allowedToGuess) { // check if the pressed key is a letter from a to z
                    guess(e.getKeyChar(), e.getKeyChar() - 'A');
                    mainGamePanel.getLetterButtons()[letterIndex].setEnabled(false);
                }
            }
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    private void listenToMusic(){
        menuPanel.getMusicStop().addActionListener(e -> soundClick());
        menuPanel.getMusicOn().addActionListener(e -> soundClick());
        mainGamePanel.getMusicStop().addActionListener(e -> soundClick());
        mainGamePanel.getMusicOn().addActionListener(e -> soundClick());
    }

    public void soundClick() {
        menuPanel.musicClick();
        mainGamePanel.musicClick();
        if (audio.isPlaying()) {
            audio.stop();
        } else {
            audio.play();
        }
    }

    private void guess(char letter, int button){

        if (game.alive()) {
            System.out.println(letter);
            if (allowedToGuess) {
                if (game.guess(letter)) {
                    correct();
                } else {
                    incorrect();
                }
                updateBlankSpaces(game.getCurrentGuess());
            }
        } else {
            mainGamePanel.setSuccessGameOverVisibility(false, true);
            disableAllBUttons();
            updateBlankSpaces(game.getFullWordToGuess());
            System.out.println("Game over!");
        }
    }
    private void correct() {
        if (game.success() && game.alive()) {
            new AudioPlayer("correct.wav").play();
            next();
        }
    }

    private void incorrect() {
        new AudioPlayer("wrong.wav").play();
        shake(frame);
        if (game.getMemory() > 0) {
            updateBackground();
        }
    }

    private void next() {
        if (game.hasNextWord() && game.getScore() < 6) {
            allowedToGuess = false;
            mainGamePanel.getSuccessGIF().setVisible(true);
            disableAllBUttons();
            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainGamePanel.getSuccessGIF().setVisible(false);
                    enableAllBUttons();
                    allowedToGuess = true;
                }
            });
            timer.setRepeats(false);
            timer.start();

            game.nextWord();
            updateBlankSpaces(game.getCurrentGuess());
            updateMaps();
            mainGamePanel.getScore().setText("score: " + game.getScore());

        } else {
            mainGamePanel.setSuccessGameOverVisibility(true, false);
            disableAllBUttons();
            System.out.println("success");
        }

    }
    private void updateBlankSpaces(StringBuilder guesses) {
        // print spaces between blanks
        String letters = guesses.toString().replace("", " ").trim();
        if (letters.length() > 20) {
            mainGamePanel.setBlankLettersFont(new Font("Source Sans Pro", Font.BOLD, 25));
        }
        System.out.println("letters" + letters);
        mainGamePanel.setBlankLettersText(letters);
    }

    private void updateBackground() {
        mainGamePanel.setImage("main-game-panel-" + game.getMemory() + ".png");
    }

    private void updateMaps() {
        System.out.println("game progress:" + game.getScore());
        int progress = game.getScore();

        if (progress == 0) {
            mainGamePanel.setMaps("maps/new-state.png");
        } else if (progress == 2) {
            mainGamePanel.setMaps("maps/ready-state.png");
        } else if (progress == 4) {
            mainGamePanel.setMaps("maps/running-state.png");
        } else if (progress == 6) {
            mainGamePanel.setMaps("maps/terminate-state.png");
        }
    }

    private void disableAllBUttons() {
        ImageButton[] letterButtons = mainGamePanel.getLetterButtons();
        for (int i = 0; i < 26; i++){
            letterButtons[i].setEnabled(false);
        }
    }

    private void enableAllBUttons() {
        ImageButton[] letterButtons = mainGamePanel.getLetterButtons();
        for (int i = 0; i < 26; i++){
            letterButtons[i].setEnabled(true);
        }
    }

    private void restartGame() {
        game.newGame();
        mainGamePanel.restart();
        updateBlankSpaces(game.getCurrentGuess());
        updateMaps();
        updateBackground();
    }

    private void shake(Frame mainWindow) {
        int vibrationLength = 7;
        int vibrationSpeed = 2;
        try {
            final int originalX = mainWindow.getLocationOnScreen().x;
            final int originalY = mainWindow.getLocationOnScreen().y;
            for (int i = 0; i < vibrationLength; i++) {
                Thread.sleep(10);
                mainWindow.setLocation(originalX, originalY + vibrationSpeed);
                Thread.sleep(10);
                mainWindow.setLocation(originalX, originalY - vibrationSpeed);
                Thread.sleep(10);
                mainWindow.setLocation(originalX + vibrationSpeed, originalY);
                Thread.sleep(10);
                mainWindow.setLocation(originalX, originalY);
            }
        }

        catch (Exception err) {
            err.printStackTrace();
        }
    }
}
