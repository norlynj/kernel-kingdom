import javax.swing.*;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
    private Panel mainGamePanel;
    private Panel instructionsPanel;
    private Panel aboutPanel;

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

        startButton.addActionListener(e-> switchPanel(menuPanel, mainGamePanel));
        instructionsButton.addActionListener(e-> switchPanel(menuPanel, instructionsPanel));
        aboutButton.addActionListener(e-> switchPanel(menuPanel, aboutPanel));


        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(aboutButton);

        // MAIN GAME PANEL
        mainGamePanel = new Panel(false, "main-game-panel.png");

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
}
