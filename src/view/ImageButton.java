import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

//class for making an image button
public class ImageButton extends JButton {
    private String imageName;


    public ImageButton(String imageName) {
        this(imageName, 300, 70);
        this.imageName = imageName;

    }

    public ImageButton(String imageName, int width, int height) {
        this.imageName = imageName;
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        this.setIcon(imageName);
        setVisible(true);

        addActionListener(e -> {
            new AudioPlayer("click.wav").play(); //click sound
            System.out.println("click");
        });

    }

    public void setIcon(String imageName) {
        setIcon(new ImageIcon(Objects.requireNonNull("src/resources/images/" + imageName))); //img bg
    }

    public void hover(String hover, String unhover) {
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setIcon("buttons/" + hover);
            }
            public void mouseExited(MouseEvent e) {
                setIcon("buttons/" + unhover);
            }
        });
    }

}
