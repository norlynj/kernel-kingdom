import java.io.*;
import javax.sound.sampled.*;

public class AudioPlayer {
    private Clip clip;
    private String soundName;

    public AudioPlayer(String soundName) {
        this.soundName = "src/resources/sounds/" + soundName;
    }

    public void play() {
        try {
            File file = new File(soundName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}