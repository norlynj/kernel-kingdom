import javax.swing.*;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
    private Panel mainGamePanel;
    private Panel instructionsPanel;
    private Panel settingsPanel;

    public KernelKingdom() {
        frame = new Frame("Kernel Kingdom");

        // MAIN MENU PANEL
        menuPanel = new Panel("menu.png");
        JButton startButton = new Button("start");
        JButton instructionsButton = new Button("instructions");
        JButton settingsButton = new Button("settings");

        startButton.setBounds(74, 300, 200, 70);
        instructionsButton.setBounds(74, 400, 200, 70);
        settingsButton.setBounds(74, 500, 200, 70);

        startButton.addActionListener(e-> switchPanel(menuPanel, mainGamePanel));
        instructionsButton.addActionListener(e-> switchPanel(menuPanel, instructionsPanel));
        settingsButton.addActionListener(e-> switchPanel(menuPanel, settingsPanel));


        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(settingsButton);


        // MAIN GAME PANEL
        mainGamePanel = new Panel("main-game-panel.png");



        // INSTRUCTIONS PANEL
        instructionsPanel = new Panel(false, "instructions-panel.png");


        // SETTINGS BUTTON
        settingsPanel = new Panel(false, "settings-panel.png");

        frame.add(menuPanel);
        frame.add(mainGamePanel);
        frame.add(instructionsPanel);
        frame.add(settingsPanel);
        frame.setVisible(true);
    }

    private void switchPanel(JPanel one, JPanel two){
        one.setVisible(false);
        two.setVisible(true);
    }
}
