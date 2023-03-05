package view;

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

    public KernelKingdom() {
        frame = new Frame("Kernel Kingdom");

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
    }




}
