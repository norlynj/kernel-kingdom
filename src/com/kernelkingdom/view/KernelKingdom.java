package view;

import model.Game;
import  view.component.ImageButton;
import  view.component.Frame;

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

    public KernelKingdom() {
        frame = new Frame("Kernel Kingdom");
        game = new Game();
        game.newGame();

        // create Panels
        menuPanel = new MenuPanel();
        instructionsPanel = new InstructionsPanel();
        aboutPanel = new AboutPanel();
        mainGamePanel = new MainGamePanel();

        // set up the content pane and card layout
        contentPane = new Panel();
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

        frame.add(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    private void listenToMenu() {
        menuPanel.getStartButton().addActionListener(e -> cardLayout.show(contentPane, "mainGamePanel" ));
        menuPanel.getInstructionsButton().addActionListener(e -> cardLayout.show(contentPane, "instructionsPanel" ));
        menuPanel.getAboutButton().addActionListener(e -> cardLayout.show(contentPane, "aboutPanel" ));
    }

    private void listenToInstructions() {
        instructionsPanel.getMenuButton().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));
    }

    private void listenToAbout() {
        aboutPanel.getMenuButton().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));
    }

    private void listenToMainGame(){
        mainGamePanel.getRestartButtonS().addActionListener(e -> restartGame());
        mainGamePanel.getRestartButtonGO().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));
        mainGamePanel.getMenuButtonS().addActionListener(e -> restartGame());
        mainGamePanel.getMenuButtonGO().addActionListener(e -> cardLayout.show(contentPane, "menuPanel" ));

        ImageButton[] letterButtons = mainGamePanel.getLetterButtons();
        for (int i = 0; i < 26; i++){
            char buttonValue = (Character.toString((char) ('A' + i)) + ".png").charAt(0);
            int finalI = i;
            letterButtons[i].addActionListener(e ->  guess(Character.toLowerCase(buttonValue), finalI));
        }

    }

    private void guess(char letter, int button){
        updateBlankSpaces(game.getCurrentGuess());

        if (game.alive()) {
            System.out.println(letter);
            if (game.guess(letter)) {
                correct();
            } else {
                incorrect();
            }
            updateBlankSpaces(game.getCurrentGuess());
        } else {
            mainGamePanel.setGameOverVisibility();
            System.out.println("Game over!");
        }
    }
    private void correct() {
        updateMaps(game.getCurrentGuess());
        if (game.success()) {
            mainGamePanel.setSuccessVisibility();

        }
    }

    private void incorrect() {
        shake(frame);
        if (game.getMemory() > 0) {
            updateBackground();
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

    private void updateMaps(StringBuilder currentGuess) {
    }

    private void restartGame() {
        game.newGame();
        mainGamePanel.restart();
        updateBlankSpaces(game.getCurrentGuess());
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

    private void switchPanel(JPanel one, JPanel two){
        one.setVisible(false);
        two.setVisible(true);
    }
}
