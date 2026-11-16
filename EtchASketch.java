package com.example.lelesfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class EtchASketch extends Application {
    // Setting starting points for the line
    static int x1 = 100;
    static int y1 = 100;
    static int x2 = 100;
    static int y2 =100;
    int delta = 2;  // Setting the increment at which the line is drawn with each click of an arrow button

    private static final Pane pane = new Pane();
    private static final Line line = new Line(x1, y1, x2, y2);
    private static void setLine(){
        Line newLine = new Line(x1, y1, x2, y2);  // Setting the new coordinates
        pane.getChildren().add(newLine);  // Adding the new line to the pane
    }

    // Override start method from the Application class
    @Override
    public void start(Stage primaryStage) {

        // Crate a pane and line, add line measurements, add it to the pane, add the pane to the scene
        pane.getChildren().add(line);
        Scene scene = new Scene(pane, 500, 500, Color.LIGHTGOLDENRODYELLOW);

        // Key event. Draws a line when an arrow key is pressed in its direction
        scene.setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.UP) {
                y2 -= delta;  // The top corner is 0, 0 so to go up you have to reduce from the y (as apposed to adding)
                setLine();
                y1 = y2; // Setting the new y starting point
            } else if (e.getCode() == KeyCode.DOWN) {
                y2 += delta;
                setLine();
                y1 =y2;
            } else if (e.getCode() == KeyCode.RIGHT) {
                x2 += delta;
                setLine();
                x1 =x2;
            } else if (e.getCode() == KeyCode.LEFT) {
                x2 -= delta;
                setLine();
                x1 = x2;
            }
        });
        // Set scene in stage and display the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Launch the application
    public static void main (String[]args){
        launch(args);
    }
}