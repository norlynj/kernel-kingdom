import javax.swing.*;
import java.awt.*;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
    private Panel mainGamePanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;
    private JButton[] letterButtons;
    private static final Color BUTTON_COLOR = new Color(245, 245, 245);
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
//            newGame();
        });
        instructionsButton.addActionListener(e-> switchPanel(menuPanel, instructionsPanel));
        aboutButton.addActionListener(e-> switchPanel(menuPanel, aboutPanel));


        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(aboutButton);

        // MAIN GAME PANEL
        mainGamePanel = new Panel(false, "main-game-panel.png");
        int buttonBoundsX = 17;
        int buttonBoundsY = 22;
        int buttonDimensions = 43;
        int incrementer = 55;

        Panel letterButtonsPanel = new Panel(405, 150, 300, 365);
        letterButtons = new ImageButton[26];
        for (int i = 0; i < 26; i++) {

            letterButtons[i] = new ImageButton("a.png");
            letterButtons[i].setBackground(BUTTON_COLOR);
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

    private void switchPanel(JPanel one, JPanel two){
        one.setVisible(false);
        two.setVisible(true);
    }

    private void newGame(){
        Game game = new Game();
        while(game.alive()) {
            showMaps();
            changeBackground();
            updateKeyboards();
            updateBlankSpaces();
        }
    }

    private void updateBlankSpaces() {
    }

    private void updateKeyboards() {
    }

    private void changeBackground() {
    }

    private void showMaps() {
    }

}
