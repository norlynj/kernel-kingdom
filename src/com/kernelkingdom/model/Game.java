package model;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private static final int MAX_MEMORY = 5;
    private final URL textFile = getClass().getResource("/resources/OSwords.txt");
    private static ArrayList<String> words;
    private static Random random;
    private static String wordToGuess;
    private static StringBuilder currentGuess;
    private static int memory;
    private static int processFinished;

    public Game() {
        assert textFile != null;
        try {
            loadWordsFromFile(textFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void newGame() {
        wordToGuess = this.getRandomWord();
        currentGuess = this.setBlanks();
        System.out.println("wordToGuess: " + wordToGuess);
        System.out.println("currentGuess: " + currentGuess);
        System.out.println("words: " + words);
        memory = MAX_MEMORY;
    }

    public void nextWord() {
        wordToGuess = this.getRandomWord();
        currentGuess = this.setBlanks();
        System.out.println("wordToGuess: " + wordToGuess);
        System.out.println("currentGuess: " + currentGuess);
        System.out.println("words: " + words);
    }

    public boolean alive() {
        System.out.println(memory);
        return memory > 0;
    }

    public boolean guess(char guess) {
        boolean correct = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                currentGuess.setCharAt(i, guess);
                correct = true;
            }
        }
        if (!correct) {
            correct = false;
            memory--;
        }
        return correct;
    }
    private void loadWordsFromFile(URL fileName) throws IOException {
        words = new ArrayList<>();
        InputStream input = fileName.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(input, "ISO8859_7"));
        String line;
        while ((line = br.readLine()) != null) {
            words.add(line.trim());
        }

    }

    public static int getMemory() {
        return memory;
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

    public boolean success() {
        if (currentGuess.toString().equals(wordToGuess)) {
            System.out.println("success");
            System.out.println("Congratulations, you guessed the word: " + wordToGuess);
            words.remove(wordToGuess); // avoid repetition
            processFinished++;
            return true;
        }
        return false;
    }

    public StringBuilder getFullWordToGuess() {
        return new StringBuilder(wordToGuess);
    }

    public StringBuilder getCurrentGuess() {
        return currentGuess;
    }

    public double progress() {
        String word = wordToGuess.toString();
        Set<Character> uniqueChars = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            uniqueChars.add(word.charAt(i));
        }

        int totalUniqueChars = uniqueChars.size();
        int correctlyGuessedUniqueChars = 0;

        String guess = currentGuess.toString();
        Set<Character> guessedChars = new HashSet<>();
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) != '_')
                guessedChars.add(guess.charAt(i));
        }

        for (char c : guessedChars) {
            if (uniqueChars.contains(c)) {
                correctlyGuessedUniqueChars++;
            }
        }
        System.out.println("uniqueChars:" + uniqueChars);
        System.out.println("guessedChars:" + guessedChars);

        return ((double) correctlyGuessedUniqueChars / totalUniqueChars) * 100;
    }
}
