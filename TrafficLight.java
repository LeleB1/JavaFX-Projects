package com.example.lelesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TrafficLight extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Override th start method from the Application class
    @Override
    public void start(Stage primaryStage) {

        // Create a VBox and set its properties
        Pane pane = new Pane();
        VBox vBox = new VBox(4);
        vBox.setBorder(Border.stroke(Color.BLACK));
        vBox.setPadding(new Insets(5, 3, 5, 3));
        vBox.setLayoutX(200);  // Starts at x = 200
        vBox.setLayoutY(50);  // Starts at y = 50

        // Create 3 equal circles, start fill is white
        Circle redCircle = new Circle(250, 100, 45, Color.WHITE);
        redCircle.setStroke(Color.BLACK);
        Circle yellowCircle = new Circle(250, 200, 45, Color.WHITE);
        yellowCircle.setStroke(Color.BLACK);
        Circle greenCircle = new Circle(250, 300, 45, Color.WHITE);
        greenCircle.setStroke(Color.BLACK);
        // Add the circles to the VBox
        vBox.getChildren().addAll(redCircle, yellowCircle, greenCircle);

        // Create HBox for radio buttons, create radio buttons
        HBox hBox = new HBox();
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");
        // Adding the radio buttons to the hBox
        hBox.getChildren().addAll(rbRed, rbYellow, rbGreen);
        hBox.setLayoutX(150);  // Start positions
        hBox.setLayoutY(400);

        // Style radio buttons
        rbRed.setScaleX(1.5);
        rbRed.setScaleY(1.5);
        rbRed.setPadding(new Insets(0, 15, 0, 0));  // Space out the radio buttons
        rbYellow.setScaleX(1.5);
        rbYellow.setScaleY(1.5);
        rbYellow.setPadding(new Insets(0, 15, 0, 15));
        rbGreen.setScaleX(1.5);
        rbGreen.setScaleY(1.5);
        rbGreen.setPadding(new Insets(0, 0, 0, 15));

        rbRed.setFont(Font.font("Comic Sans MS"));
        rbYellow.setFont(Font.font("Comic Sans MS"));
        rbGreen.setFont(Font.font("Comic Sans MS"));

        rbRed.setTextFill(Color.RED);
        rbYellow.setTextFill(Color.rgb(244, 194, 5));  // Visible yellow
        rbGreen.setTextFill(Color.GREEN);

        // Create a toggle group for the radio buttons (select one at a time)
        ToggleGroup buttonGroup = new ToggleGroup();
        rbRed.setToggleGroup(buttonGroup);
        rbYellow.setToggleGroup(buttonGroup);
        rbGreen.setToggleGroup(buttonGroup);

        // When a radio button is clicked, the correct color in the traffic light 'turns on'
        rbRed.setOnAction(e -> {
            if (rbRed.isSelected()) {
                yellowCircle.setFill(Color.WHITE);
                greenCircle.setFill(Color.WHITE);
                redCircle.setFill(Color.RED);
            }
        });
        rbYellow.setOnAction(e -> {
            if (rbYellow.isSelected()) {
                redCircle.setFill(Color.WHITE);
                greenCircle.setFill(Color.WHITE);
                yellowCircle.setFill(Color.YELLOW);
            }
        });
        rbGreen.setOnAction(e -> {
            if (rbGreen.isSelected()) {
                redCircle.setFill(Color.WHITE);
                yellowCircle.setFill(Color.WHITE);
                greenCircle.setFill(Color.GREEN);
            }
        });

        // Adding the traffic light-VBox, and radio buttons-HBox, to the pane
        pane.getChildren().addAll(vBox, hBox);

        // Creating a scene, setting the scene, and displaying it
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
