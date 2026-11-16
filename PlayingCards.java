/**
 * The program displays three random playing cards
 * **/

package com.example.lelesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

public class PlayingCards extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Override default start method in Application
    @Override
    public void start(Stage primaryStage) {

        // An HBox pane for the images
        Pane pane = new HBox(20);
        pane.setPadding(new Insets(30, 30, 30, 30));  // Padding for the cards

        // An ArrayList for images. Limiting capacity to 52
        ArrayList<Image> cards = new ArrayList<>(52);

        /**
         * The i in the for loop starts at 1 for the first card (1.png) and goes to 52
         * It adds the new number to the image source name and adds each image to the cards ArrayList
         * **/
        for(int i = 1; i < 53; i++){
            cards.add(new Image(getClass().getResourceAsStream("/image/card/" + i + ".png")));
        }

        // Shuffling the cards to randomize the selection
        Collections.shuffle(cards);

        // Adding three ImageViews, for three cards, with a width and preserved ratio
        ImageView card1 = new ImageView(cards.get(0)); // First index is 0
        card1.setFitWidth(288);
        card1.setPreserveRatio(true);
        ImageView card2 = new ImageView(cards.get(1));
        card2.setFitWidth(288);
        card2.setPreserveRatio(true);
        ImageView card3 = new ImageView(cards.get(2));
        card3.setFitWidth(288);
        card3.setPreserveRatio(true);

        // Add three random cards to the pane
        pane.getChildren().addAll(card1, card2, card3);

        // Name the stage
        primaryStage.setTitle("Three Playing Cards");

        // Create a scene. Add the scene to the stage. Display/show stage
        Scene scene = new Scene(pane, 980, 450, Color.LIGHTSLATEGRAY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}