import javax.swing.*;
import java.awt.*;

public class  Frame extends JFrame {
    JWindow window = new JWindow();
    public Frame(String name) {
//        loadImage("src/resources/images/splashscreen.gif");  //SplashScreen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(name);
        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(700, 700));
        pack();
        setLocationRelativeTo(null);
    }

    private void loadImage(String imageName) {

        //splash screen
        window.getContentPane().add(
                new JLabel("", new ImageIcon(imageName), SwingConstants.CENTER));

        window.setSize(700, 700);
        window.setLocationRelativeTo(null); //set position to the center screen

        window.setVisible(true);
        try {
            Thread.sleep(10000); //show the splash screen for 10 seconds
        } catch (InterruptedException e) {
            e.getMessage();
        }

        window.dispose(); //disappears then after
    }
}
