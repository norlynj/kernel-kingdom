import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
    private JLabel blankLetters;
    private Panel mapsPanel;

    private Panel letterButtonsPanel;

    private Panel mainGamePanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;
    private ImageButton[] letterButtons;
    private Game game;
    public KernelKingdom() {
        frame = new Frame("Kernel Kingdom");

        // MAIN MENU PANEL
        menuPanel = new Panel("menu.gif");
        JButton startButton = new Button("start");
        JButton instructionsButton = new Button("instructions");
        JButton aboutButton = new Button("about");

        startButton.setBounds(74, 300, 200, 70);
        instructionsButton.setBounds(74, 400, 200, 70);
        aboutButton.setBounds(74, 500, 200, 70);

        startButton.addActionListener(e-> {
            switchPanel(menuPanel, mainGamePanel);
            game = new Game();
            updateBlankSpaces(game.getCurrentGuess());
        });
        instructionsButton.addActionListener(e-> switchPanel(menuPanel, instructionsPanel));
        aboutButton.addActionListener(e-> switchPanel(menuPanel, aboutPanel));


        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(aboutButton);

        // MAIN GAME PANEL
        mainGamePanel = new Panel(false, "main-game-panel.png");
        //Blanks for the word
        blankLetters = new JLabel();
        blankLetters.setBounds(10, 87, 700, 71);
        blankLetters.setFont(new Font("Source Sans Pro", Font.BOLD, 35));
        blankLetters.setHorizontalAlignment(JLabel.CENTER);
        // Maps panel
        mapsPanel = new Panel(true, "new-state.png", 47, 200, 338, 294);
        // Keybroard buttons
        int buttonBoundsX = 17;
        int buttonBoundsY = 22;
        int buttonDimensions = 43;
        int incrementer = 55;
        letterButtonsPanel = new Panel(405, 170, 300, 365);
        letterButtons = new ImageButton[26];
        for (int i = 0; i < 26; i++) {

            letterButtons[i] = new ImageButton("normal/" + Character.toString((char) ('A' + i)) + ".png");
//            letterButtons[i] = new ImageButton(Character.toString((char) ('A' + i)));
            int finalI = i;
            char buttonValue = (Character.toString((char) ('a' + i)) + ".png").charAt(0);
            letterButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guess(buttonValue, finalI);
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

        mainGamePanel.add(blankLetters);
        mainGamePanel.add(mapsPanel);
        mainGamePanel.add(letterButtonsPanel);



        // INSTRUCTIONS PANEL
        instructionsPanel = new Panel(false, "instructions-panel.png");
        JButton backInstMenuButton = new Button("back");
        backInstMenuButton.setBounds(300, 538, 100, 70);
        backInstMenuButton.addActionListener(e -> switchPanel(menuPanel, menuPanel));

        instructionsPanel.add(backInstMenuButton);

        // ABOUT BUTTON
        aboutPanel = new Panel(false, "settings-panel.png");
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
        if (game.alive()) {
            System.out.println(letter);
            if (game.guess(letter)) {
                updateMaps(game.getCurrentGuess());
                letterButtons[button].setIcon("selected/" + Character.toString((char) ('A' + button)) + ".png");
            } else {
                shake(frame);
                changeBackground();
                letterButtons[button].setIcon("incorrect/" + Character.toString((char) ('A' + button)) + ".png");

            }
//            letterButtons[button].setEnabled(false);
            updateBlankSpaces(game.getCurrentGuess());
        } else {
            System.out.println("Game over!");
            System.exit(0);
        }
    }

    private void updateBlankSpaces(StringBuilder guesses) {
        // print spaces between blanks
        String letters = guesses.toString().replace("", " ").trim();
        System.out.println("letters" + letters);
        blankLetters.setText(letters);
    }

    private void changeBackground() {
    }


    private void updateMaps(StringBuilder guesses) {
    }

}
