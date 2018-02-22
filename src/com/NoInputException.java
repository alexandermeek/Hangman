package com;

/**
 * An exception for when the user doesn't enter anything into a text box.
 * @author Alex Meek
 */
public class NoInputException extends IllegalStateException{
    public NoInputException(String message) {
        super(message);
    }
}
