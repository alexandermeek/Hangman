package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class of the Hangman GUI implementation. Loads everything.
 * @author Alex Meek
 */
public class Main extends Application {
    private static final String WINDOW_TITLE = "Hangman";

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
