/**
 * The program allows the user to change the color of the text at the top of the window by sliding the bar on the 3 scroll bars.
 * In doing so, you change the value the bar represents and, inturn, the rgb value and color.
 * The forth scrollbar determines the opacity of the text
 */

package com.example.lelesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ScrollBar;

import java.awt.*;

import static javafx.scene.paint.Color.*;

public class ColorScroll extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Override the start method in the Application class
    @Override
    public void start(Stage primaryStage) {

        // Create a VBox for the scrollbars, position center, padding
        Pane pane = new Pane();
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(30, 15, 30, 15));

        // Set the position and style for the text
        Text text = new Text();
        text.setText("COLOR CHANGING TEXT");
        text.setFont(Font.font("Comic Sans MS", 20));
        text.setFill(BLACK);
        text.setY(100);
        text.setX(130);
        pane.getChildren().add(text);  // Add message to pane

        // Create a scrollbar with the proper measurements for ach color
        HBox redBox = new HBox(35);  // HBox for each label and scrollbar
        ScrollBar redBar = new ScrollBar();
        redBar.setMin(0);  // rgb starts at 0
        redBar.setMax(255);  // ends at 255
        redBar.setValue(0);  // Set the start value to 0
        redBar.setBlockIncrement(5);  // With each click on the scroll pane, the value increases by 5
        redBar.setMinWidth(300);  // Height of the scrollbar
        redBar.setMinHeight(30);  // Width of the scrollbar

        // Colored label for each scrollbar
        Label redLabel = new Label("Red");
        redLabel.setTextFill(RED);
        redBox.getChildren().addAll(redLabel, redBar);  // Add label and scrollbar to the HBox

        HBox greenBox = new HBox(29);
        ScrollBar greenBar = new ScrollBar();
        greenBar.setMin(0);
        greenBar.setMax(255);
        greenBar.setValue(0);
        greenBar.setBlockIncrement(5);
        greenBar.setMinHeight(30);
        greenBar.setMinWidth(300);

        Label greenLabel = new Label("Green");
        greenLabel.setTextFill(GREEN);
        greenLabel.setLabelFor(greenBar);
        greenBox.getChildren().addAll(greenLabel, greenBar);

        HBox blueBox = new HBox(35);
        ScrollBar blueBar = new ScrollBar();
        blueBar.setMin(0);
        blueBar.setMax(255);
        blueBar.setValue(0);
        blueBar.setBlockIncrement(5);
        blueBar.setMinHeight(30);
        blueBar.setMinWidth(300);

        Label blueLabel = new Label("Blue");
        blueLabel.setTextFill(BLUE);
        blueBox.getChildren().addAll(blueLabel, blueBar);

        HBox opacityBox = new HBox(20);
        ScrollBar opacityBar = new ScrollBar();
        opacityBar.setMin(0.0);  // Opacity goes from 0.0 to 1.0
        opacityBar.setMax(1.0);
        opacityBar.setValue(1);  // Starting at 1 so text is fully visible
        opacityBar.setBlockIncrement(0.01);  // Increase by 0.01 instead or 1
        opacityBar.setMinHeight(30);
        opacityBar.setMinWidth(300);

        Label opacityLabel = new Label("Opacity");
        opacityLabel.setTextFill(rgb(86, 86, 86, 0.34));
        opacityBox.getChildren().addAll(opacityLabel, opacityBar);

        // Add a listener for each scrollbar. Changes the text to the desired rgb value (color)
        redBar.valueProperty().addListener((observable, oldValue, newValue) ->
                text.setFill(Color.rgb(newValue.intValue(), (int) greenBar.getValue(), (int) blueBar.getValue(), opacityBar.getValue())));
        greenBar.valueProperty().addListener((observable, oldValue, newValue) ->
                text.setFill(Color.rgb((int) redBar.getValue(), newValue.intValue(), (int) blueBar.getValue(), opacityBar.getValue())));
        blueBar.valueProperty().addListener((observable, oldValue, newValue) ->
                text.setFill(Color.rgb((int) redBar.getValue(), (int) greenBar.getValue(), newValue.intValue(), opacityBar.getValue())));
        opacityBar.valueProperty().addListener((observable, oldValue, newValue) ->
                text.setFill(Color.rgb((int) redBar.getValue(), (int) greenBar.getValue(), (int) blueBar.getValue(), newValue.doubleValue())));

        vBox.getChildren().addAll(redBox, greenBox, blueBox, opacityBox);  // Adding the HBoxes to the vBox
        vBox.setLayoutY(150);  // Setting its position
        vBox.setLayoutX(50);
        pane.getChildren().add(vBox);  // Adding the vBox to the pane

        // Add the text and scrollbars to the pane. Add the pane to the scene, the scene to the stage, and display it
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
