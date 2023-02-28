import javax.swing.*;

public class KernelKingdom {
    private final Frame frame;
    private Panel menuPanel;
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

        menuPanel.add(startButton);
        menuPanel.add(instructionsButton);
        menuPanel.add(settingsButton);

        frame.add(menuPanel);
        frame.setVisible(true);
    }
}
