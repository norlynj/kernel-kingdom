import javax.swing.*;
import java.awt.*;
import java.util.Objects;

//class for making an image button
public class ImageButton extends JButton {
    private String imageName;

    public String getImageName() {
        return imageName;
    }

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
        setIcon(new ImageIcon(Objects.requireNonNull("src/resources/images/buttons/keyboard/" + imageName))); //img bg
        setVisible(true);

        addActionListener(e -> {
            new AudioPlayer("click.wav").play(); //click sound
            System.out.println("click");
        });

    }

}
