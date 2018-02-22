package com.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads words for a game of hangman from a file.
 * @author Alex Meek
 */
public class ReadWords {
    private static final String FILEPATH = "data/";

    private static final String NO_FILE_ERROR_MSG = "Cannot open ";

    /**
     * Reads the file.
     * @param filename The name of the file to read.
     * @return A list of all the words in the file.
     */
    public static ArrayList<String> readFile(String filename) {
        File inputFile = getOrMakeFile(filename);
        Scanner in = null;
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.out.println(NO_FILE_ERROR_MSG + filename);
            System.exit(0);
        }

        return readData(in);
    }

    /**
     * Gets the file itself.
     * @param filename The name of the file.
     * @return The file.
     */
    private static File getOrMakeFile(String filename) {
        File inputFile = new File(FILEPATH + filename);
        File pdir = inputFile.getParentFile();

        // Checks if the parent dir exists if it doesn't create it.
        if (! pdir.exists()){
            pdir.mkdirs();
        }
        return inputFile;
    }

    /**
     * Reads the data from the file.
     * @param in The scanner containing the file.
     * @return A list of all the words.
     */
    private static ArrayList<String> readData(Scanner in) {
        ArrayList<String> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            lines.add(in.nextLine());
        }
        in.close();

        return lines;
    }
}
