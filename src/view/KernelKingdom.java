import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
    private JLabel blankLetters;
    private Panel mapsPanel;
    private Panel successsPanel;
    private Panel gameOverPanel;

    private Panel letterButtonsPanel;

    private Panel mainGamePanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;
    private ImageButton[] letterButtons;
    private ImageButton restartButton;
    private ImageButton menuButton;
    private Game game;
    public KernelKingdom() {
        frame = new Frame("Kernel Kingdom");

        // MAIN MENU PANEL
        menuPanel = new Panel("menu.gif");
        ImageButton startButton = new ImageButton("buttons/start.png");
        ImageButton instructionsButton = new ImageButton("buttons/instructions.png");
        ImageButton aboutButton = new ImageButton("buttons/about.png");

        startButton.setBounds(74, 250, 187, 72);
        instructionsButton.setBounds(70, 350, 390, 78);
        aboutButton.setBounds(74, 450, 179, 69);

        // on click action listeners
        startButton.addActionListener(e-> {
            switchPanel(menuPanel, mainGamePanel);
            game = new Game();
            game.newGame();
            updateBlankSpaces(game.getCurrentGuess());
        });
        instructionsButton.addActionListener(e-> switchPanel(menuPanel, instructionsPanel));
        aboutButton.addActionListener(e-> switchPanel(menuPanel, aboutPanel));

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


        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(aboutButton);

        // MAIN GAME PANEL
        mainGamePanel = new Panel(false, "main-game-panel-5.png");

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
            letterButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guess(Character.toLowerCase(buttonValue), finalI);
                }
            });
            letterButtons[i].addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    letterButtons[finalI].setIcon("alphabet/selected/" + buttonValue + ".png");
                }
                public void mouseExited(MouseEvent e) {
                    letterButtons[finalI].setIcon("alphabet/normal/" + buttonValue + ".png");
                }
            });
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

        successsPanel = new Panel(false, "success-panel.png");
        restartButton = new ImageButton("buttons/restart.png");
        menuButton = new ImageButton("buttons/menu-button.png");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                restartGame();
            }
        });
        menuButton.addActionListener(e-> switchPanel(mainGamePanel, menuPanel));

        restartButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                restartButton.setIcon("buttons/restart-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                restartButton.setIcon("buttons/restart.png");
            }
        });

        menuButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                menuButton.setIcon("buttons/menu-button-hover.png");
            }
            public void mouseExited(MouseEvent e) {
                menuButton.setIcon("buttons/menu-button.png");
            }
        });


        restartButton.setBounds(66, 400, 254, 91);
        menuButton.setBounds(59, 500, 171, 66);

        gameOverPanel = new Panel(false, "game-over-panel.png");

        successsPanel.add(restartButton);
        successsPanel.add(menuButton);
        mainGamePanel.add(successsPanel);
        mainGamePanel.add(gameOverPanel);
        mainGamePanel.add(blankLetters);
        mainGamePanel.add(mapsPanel);
        mainGamePanel.add(letterButtonsPanel);

        // INSTRUCTIONS PANEL
        instructionsPanel = new Panel(false, "instructions-and-text.gif");
        JButton backInstMenuButton = new Button("back");
        backInstMenuButton.setBounds(300, 538, 100, 70);
        backInstMenuButton.addActionListener(e -> switchPanel(menuPanel, menuPanel));

        instructionsPanel.add(backInstMenuButton);

        // ABOUT BUTTON
        aboutPanel = new Panel(false, "about-and-text.gif");
        JButton backAboutMenuButton = new Button("back");
        backAboutMenuButton.setBounds(300, 538, 100, 70);
        backAboutMenuButton.addActionListener(e -> switchPanel(aboutPanel, menuPanel));
        aboutPanel.add(backAboutMenuButton);
        
        frame.add(menuPanel);
        frame.add(mainGamePanel);
        frame.add(instructionsPanel);
        frame.add(aboutPanel);
        frame.setVisible(true);
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

    private void guess(char letter, int button){
        letterButtons[button].setEnabled(false); // disable button so that the user can't click on it.
        if (game.alive()) {
            System.out.println(letter);
            if (game.guess(letter)) {
                updateMaps(game.getCurrentGuess());
//                letterButtons[button].setIcon("alphabet/selected/" + Character.toString((char) ('A' + button)) + ".png");
                if (game.success()) {
                    successsPanel.setVisible(true);


                }
            } else {
                shake(frame);
                if (game.getMemory() > 0) {
                    changeBackground();
                }
//                letterButtons[button].setIcon("alphabet/incorrect/" + Character.toString((char) ('A' + button)) + ".png");

            }
//            letterButtons[button].setEnabled(false);
            updateBlankSpaces(game.getCurrentGuess());
        } else {
            gameOverPanel.setVisible(true);
            System.out.println("Game over!");
        }
    }

    private void updateBlankSpaces(StringBuilder guesses) {
        // print spaces between blanks
        String letters = guesses.toString().replace("", " ").trim();
        if (letters.length() > 20) {
            blankLetters.setFont(new Font("Source Sans Pro", Font.BOLD, 25));
        }

        System.out.println("letters" + letters);
        blankLetters.setText(letters);
    }

    private void changeBackground() {
        mainGamePanel.setImage("main-game-panel-" + game.getMemory() + ".png");
    }


    private void updateMaps(StringBuilder guesses) {
    }

    private void restartGame() {
        successsPanel.setVisible(false);
        gameOverPanel.setVisible(false);
        game.newGame();
        updateBlankSpaces(game.getCurrentGuess());
        updateKeyboard();
    }

    public void updateKeyboard() {
        for (int i = 0; i < 26; i++) {
            letterButtons[i].setEnabled(true);
        }
    }

}
