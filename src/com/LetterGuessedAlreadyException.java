package com;

/**
 * This class represents an exception raised when a letter has
 * already been guessed.
 * @author Alex Meek
 */
public class LetterGuessedAlreadyException extends IllegalStateException{
    public LetterGuessedAlreadyException(String message) {
        super(message);
    }
}

