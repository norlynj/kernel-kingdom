import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static final int MAX_MEMORY = 6;
    private static ArrayList<String> words;
    private static Random random;
    private static String wordToGuess;
    private static StringBuilder currentGuess;
    private static int memory;

    public Game() {
        loadWordsFromFile("src/resources/OSwords.txt");
        this.wordToGuess = this.getRandomWord();
        this.currentGuess = this.setBlanks();
        System.out.println("wordToGuess: " + wordToGuess);
        System.out.println("currentGuess: " + currentGuess);
        System.out.println("words: " + words);
        memory = MAX_MEMORY;
    }

    public boolean alive() {
        return memory > 0;
    }

    public void guess(char guess) {
        boolean correct = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                currentGuess.setCharAt(i, guess);
                correct = true;
            }
        }
        if (!correct) {
            memory--;
        }
        if (currentGuess.toString().equals(wordToGuess)) {
            System.out.println("Congratulations, you guessed the word: " + wordToGuess);
            return;
        }
    }
    private void loadWordsFromFile(String fileName) {
        this.words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.words.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private StringBuilder setBlanks() {
        currentGuess = new StringBuilder(wordToGuess);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) != ' ') {
                currentGuess.setCharAt(i, '_');
            }

        }
        return currentGuess;
    }
    private String getRandomWord() {
        random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public String getFullWordToGuess() {
        return wordToGuess;
    }

    public StringBuilder getCurrentGuess() {
        return currentGuess;
    }
}
