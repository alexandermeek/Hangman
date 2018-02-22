package com;

import com.services.ReadDiagrams;
import com.services.ReadWords;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is an version of the game of Hangman that you can interact with
 * via the console.
 * @author Alex Meek
 */
public class HangmanMain {
    private static final String WORDS_FILE_PATH = "words.txt";
    private static final String DIAGRAMS_FILE_PATH = "diagrams.txt";
    private static final int MAX_GUESSES = 10;

    private static final String WELCOME_MSG = "Welcome to hangman.\n - Type a single " +
            "letter to guess a letter, or type a word to try guess the whole thing. \n";
    private static final String PLAY_AGAIN_MSG = "Would you like to play another game? (y/n)";
    private static final String CORRECT_MSG = "Correct guess!";
    private static final String INCORRECT_MSG = "Incorrect guess.";
    private static final String GUESS_MSG = "Guess the following word: ";
    private static final String YOU_WON_MSG = "You won. You spelled out: ";
    private static final String YOU_LOST_MSG = "You lost. The word was actually: ";
    private static final String NO_WORDS_ERROR_MSG = "You have run out of words to play.";
    private static final String DUPLICATE_LETTER_ERROR_MSG = "That letter was already guessed, try again.";
    private static final String NOTHING_ENTERED_ERROR_MSG = "Please enter something.";

    private static ArrayList<String> words = ReadWords.readFile(WORDS_FILE_PATH);
    private static ArrayList<String> oldWords = new ArrayList<>();
    private static ArrayList<String> diagrams = ReadDiagrams.readFile(DIAGRAMS_FILE_PATH);

    /**
     * The main program. Allows you to play games of hangman.
     * @param args Any arguments parsed into the program.
     */
    public static void main(String args[]) {
        boolean play = true;
        System.out.println(WELCOME_MSG);
        while (play) {
            if (oldWords.size() == words.size()) {
                System.out.println(NO_WORDS_ERROR_MSG);
                System.exit(0);
            }
            gameOfHangman();
            System.out.println(PLAY_AGAIN_MSG);
            String response = getUserInput();
            play = response.equalsIgnoreCase("y");
        }
    }

    /**
     * Plays a single game of hangman.
     */
    private static void gameOfHangman() {
        Hangman hangman = createNewHangmanGame();

        while (!hangman.isFinished()) {
            System.out.println(GUESS_MSG + hangman.getFoundWord());
            String temp = getUserInput();
            try {
                boolean correct;
                if (temp.length() == 1) {
                    Character guess = temp.charAt(0);
                    correct = hangman.guess(guess);
                } else {
                    correct = hangman.guess(temp);
                }
                if (correct) {
                    System.out.println(CORRECT_MSG);
                } else {
                    System.out.println(INCORRECT_MSG);
                    System.out.println(diagrams.get(hangman.getIncorrectGuesses()-1));
                }
            } catch (LetterGuessedAlreadyException e) {
                System.out.println(DUPLICATE_LETTER_ERROR_MSG);
            } catch (NoInputException e) {
                System.out.println(NOTHING_ENTERED_ERROR_MSG);
            }
        }
        if (hangman.isLost()) {
            System.out.println(YOU_LOST_MSG + hangman.getWord());
        } else if (hangman.isWon()) {
            System.out.println(YOU_WON_MSG + hangman.getWord());
        }
    }

    /**
     * Creates a new hangman game.
     * @return The new game of hangman.
     */
    private static Hangman createNewHangmanGame() {
        Random rand = new Random();
        Hangman hangman = null;
        boolean oldWord;
        do {
            int randInt = rand.nextInt(words.size());
            String newWord = words.get(randInt);
            if (oldWords.contains(newWord)) {
                oldWord = true;
            } else {
                hangman = new Hangman(words.get(randInt), MAX_GUESSES);
                oldWords.add(newWord);
                oldWord = false;
            }
        } while (oldWord);
        return hangman;
    }

    /**
     * Gets the input from the user.
     * @return The input from the user.
     */
    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
