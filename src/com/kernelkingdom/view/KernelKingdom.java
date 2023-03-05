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

        menuPanel.getStartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof ImageButton) {
                    cardLayout.show(contentPane, "mainGamePanel" );
                }
            }
        });

        frame.add(contentPane);
        frame.pack();
        frame.setVisible(true);


    }


}
