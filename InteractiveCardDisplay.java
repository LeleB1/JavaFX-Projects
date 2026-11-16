/******
 * The program displays four random playing cards and
 * features a refresh button allowing the user to change/refresh the displayed cards
 ******/

package com.example.lelesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;

public class InteractiveCardDisplay extends Application {

    private HBox hBox = new HBox(20);                           // For the cards
    private StackPane stackPane = new StackPane();                      // For the button
    private BorderPane borderPane = new BorderPane();                   // For the cards and button
    private ArrayList<Image> cards = new ArrayList<>(52);   // For the images

    // Override default start method in Application class
    @Override
    public void start(Stage primaryStage) {

        // The i starts at 1 and ends at 52, changing the image source name each time to add all 52 card images
        for (int i = 1; i < 53; i++) {
        cards.add(new Image(getClass().getResourceAsStream("/image/card/" + i + ".png")));
        }
        // Shuffling the cards to randomize selection
        Collections.shuffle(cards);

        // Adding four ImageViews, for four cards, with a width and preserved ratio
        ImageView card1 = new ImageView(cards.get(0));
        card1.setFitWidth(250);
        card1.setPreserveRatio(true);
        ImageView card2 = new ImageView(cards.get(1));
        card2.setFitWidth(250);
        card2.setPreserveRatio(true);
        ImageView card3 = new ImageView(cards.get(2));
        card3.setFitWidth(250);
        card3.setPreserveRatio(true);
        ImageView card4 = new ImageView(cards.get(3));
        card4.setFitWidth(250);
        card4.setPreserveRatio(true);

        // Add the four random cards to the HBox and set padding
        hBox.getChildren().addAll(card1, card2, card3, card4);
        hBox.setPadding(new Insets(30, 30, 30, 30));

        // The refresh button allows the user to refresh the four displayed cards
        Button refBtn = new Button("Refresh");
        // The lambda function, action event, handles the refresh button action
        refBtn.setOnAction((e) -> {
            Collections.shuffle(cards);   // Shuffling the cards again for new random cards
            card1.setImage(cards.get(0)); // Displaying the new cards
            card2.setImage(cards.get(1));
            card3.setImage(cards.get(2));
            card4.setImage(cards.get(3));
        });
        stackPane.getChildren().add(refBtn);

        // Adding the HBox and button to a BorderPane
        borderPane.setCenter(hBox);
        borderPane.setBottom(stackPane);
        BorderPane.setAlignment(stackPane, Pos.CENTER);
        borderPane.setPadding(new Insets(0, 0, 20, 0)); // Adding padding to the bottom of the button
        borderPane.setBackground(Background.fill(Color.DARKRED));

        // Name the stage. Add pane to scene. Add scene to stage and display it.
        primaryStage.setTitle("Interactive Playing Cards");
        Scene scene = new Scene(borderPane, 1120, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}