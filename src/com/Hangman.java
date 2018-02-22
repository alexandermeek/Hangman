package com;

import java.util.ArrayList;

/**
 * This class represents a game of hangman.
 * @author Alex Meek
 */
public class Hangman {
    private String word;
    private ArrayList<Character> guessedLetters;
    private int lengthOfWord;
    private int incorrectGuesses; //Number of incorrect guesses.
    private int maxGuesses; //Number of guesses allowed.
    private int totalGuesses; //Number of guesses the user has taken.
    private int totalWordGuessed; //Number of letters from the word that have been guessed.

    /**
     * Creates a new com.Hangman game.
     * @param word The word to be guessed.
     * @param maxGuesses The maximum number of guesses allowed.
     */
    public Hangman(String word, int maxGuesses) {
        if (maxGuesses <= 0) {
            throw new IllegalArgumentException("Cannot have negative or 0 guesses.");
        }
        this.word = word;
        this.maxGuesses = maxGuesses;
        this.guessedLetters = new ArrayList<>();
        this.lengthOfWord = getLengthOfWord();
        this.incorrectGuesses = 0;
        this.totalGuesses = 0;
        this.totalWordGuessed = 0;
    }

    /**
     * Gets the word.
     * @return The word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the already guessed letters.
     * @return The list of letters already guessed.
     */
    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * Gets the number of incorrect guesses.
     * @return Number of incorrect guesses.
     */
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public int getGuessesLeft() {
        return maxGuesses - incorrectGuesses;
    }

    /**
     * Gets the total number of guesses.
     * @return Total number of guesses.
     */
    public int getTotalGuesses() {
        return totalGuesses;
    }

    /**
     * A guess in the game of hangman.
     * @param letter The guessed letter.
     * @return True if the guess was correct, false otherwise.
     */
    public boolean guess(Character letter) {
        letter = charToUpper(letter);
        checkValidGuess(letter);
        ++totalGuesses;
        guessedLetters.add(letter);

        int occurrences = findOccurrencesOf(letter);
        totalWordGuessed = totalWordGuessed + occurrences;
        if (occurrences > 0) {
            return true;
        } else {
            incorrectGuesses++;
            return false;
        }
    }

    /**
     * A guess of the whole word in the game of hangman.
     * @param word The word guessed.
     * @return True if the guess was correct, false otherwise.
     * @throws NoInputException When nothing was entered.
     */
    public boolean guess(String word) {
        if (word.equals("")) {
            throw new NoInputException("Nothing was entered.");
        }
        if (this.word.equalsIgnoreCase(word)) {
            totalWordGuessed = lengthOfWord;
            return true;
        } else {
            ++incorrectGuesses;
            return false;
        }
    }

    /**
     * Checks if the game has been finished.
     * @return True if the game is finished, false otherwise.
     */
    public boolean isFinished() {
        return (isWon() || isLost());
    }

    /**
     * Checks if all the letters in the word have been guessed.
     * @return True if the word has been guessed, false otherwise.
     */
    public boolean isWon() {
        return (lengthOfWord == totalWordGuessed);
    }

    /**
     * Checks if the game has been lost.
     * @return True if the game has been lost, false otherwise.
     */
    public boolean isLost() {
        return (incorrectGuesses >= maxGuesses);
    }

    /**
     * Checks if the letter entered is valid.
     * @param letter The guessed letter.
     * @throws LetterGuessedAlreadyException When the letter has already been guessed.
     * @throws NoGuessesLeftException When there are no guesses left.
     * @throws NoInputException When nothing was entered.
     */
    private void checkValidGuess(Character letter) {
        if (letter == ' ') {
            throw new NoInputException("Nothing was entered.");
        }
        if (guessedLetters.contains(letter)) {
            throw new LetterGuessedAlreadyException("Letter was already guessed.");
        }
        if (isLost()) {
            throw new NoGuessesLeftException("There are no guesses left.");
        }
        if (isWon()) {
            throw new NoGuessesLeftException("All the letters in the word are already guessed.");
        }
    }

    /**
     * Finds out how many times the letter appears in the word.
     * @param letter The letter guessed.
     * @return The number of times the letter occurred in the word.
     */
    private int findOccurrencesOf(char letter) {
        int numberFound = 0;
        for (int i = 0; i < word.length(); i++) {
            if (charToUpper(word.charAt(i)) == letter) {
                numberFound++;
            }
        }
        return numberFound;
    }

    /**
     * Converts a single character to it's uppercase counterpart.
     * @param character The character to convert.
     * @return The uppercase counterpart of the character.
     */
    private Character charToUpper(Character character) {
        String temp = character.toString();
        temp = temp.toUpperCase();
        return temp.charAt(0);
    }

    /**
     * Gets the length of the word. Minus whitespace.
     * @return The length of the word.
     */
    private int getLengthOfWord() {
        int length = word.length();
        length = length - findOccurrencesOf(' ');
        return length;
    }

    /**
     * Gets the string of the word you have found.
     * @return The string of the word you have found.
     */
    public String getFoundWord() {
        StringBuilder foundWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            Character letter = charToUpper(word.charAt(i));
            if (guessedLetters.contains(letter)) {
                foundWord.append(letter);
            } else if (letter == ' ') {
                foundWord.append(" ");
            } else {
                foundWord.append("_");
            }
            foundWord.append(" ");
        }
        return foundWord.toString();
    }
}
