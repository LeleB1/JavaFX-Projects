// The program allows the user to add, view, and edit their contacts

package com.example.lelesfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class AddressBook extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    long filePointer;
    private static final int CONTACT_SIZE = 91;
    private RandomAccessFile contactFile;

    // Method for resetting the message label
    private static void resetMessage(Label m) {
        m.setText("");
    }

    private static void exceptionHandling(Exception e, Label m){
        System.out.println(e.getMessage());
        m.setText("Something went wrong, oops!");
    }
    @Override
    public void start(Stage primaryStage) {

        // Boxes to align items
        HBox nameBox = new HBox(5);
        HBox streetBox = new HBox(5);
        HBox addressBox = new HBox(5);
        HBox buttonsBox =  new HBox(5);
        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(0, 20, 0, 20));

        // Add labeled text fields and add them to an HBox
        Label name = new Label("Name");
        TextField nameField = new TextField();
        nameField.setPrefColumnCount(25);  // Set the width
        nameBox.getChildren().addAll(name, nameField);

        Label street = new Label("Street");
        TextField streetField = new TextField();
        streetField.setPrefColumnCount(25);
        streetBox.getChildren().addAll(street, streetField);

        Label city = new Label("City   ");
        TextField cityField = new TextField();
        cityField.setPrefColumnCount(10);

        Label state = new Label("State ");
        TextField stateField = new TextField();
        stateField.setPrefColumnCount(2);

        Label zip = new Label("Zip ");
        TextField zipField = new TextField();
        zipField.setPrefColumnCount(4);
        addressBox.getChildren().addAll(city, cityField, state, stateField, zip, zipField);

        // Buttons with unique functions, add to an HBox
        Button add = new Button("Add");
        Button first = new Button("First");
        Button next = new Button("Next");
        Button previous = new Button("Previous");
        Button last = new Button("Last");
        Button update = new Button("Update");
        buttonsBox.getChildren().addAll(add, first, next, previous, last, update);
        buttonsBox.setPadding(new Insets(5, 10, 5, 35));

        // Message label for success and error messages
        Label message = new Label("");
        vBox.getChildren().addAll(nameBox, streetBox, addressBox, buttonsBox, message);
        vBox.setAlignment(Pos.CENTER);

        // Create a scene, add scene to stage, display stage with title
        Scene scene = new Scene(vBox, 400, 175);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Address Book");
        primaryStage.show();


        // Try creating a RandomAccessFile. Catch exception if occurs and display a message to user
        try{
            contactFile = new RandomAccessFile("ContactFile.dat", "rw");
        } catch(FileNotFoundException e) {
            exceptionHandling(e, message);
        }
        // Add an action event for the Add button
        add.setOnAction(e-> {
            resetMessage(message);
            try {
                contactFile.seek(contactFile.length());
                EditContact.writeContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                message.setText("Contact added successfully");
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
            nameField.clear();
            streetField.clear();
            cityField.clear();
            stateField.clear();
            zipField.clear();
        });

        // Action event for the button First
        first.setOnAction(e-> {
            resetMessage(message);
            filePointer = 0;
            try {
                if(contactFile.length() == 0) {
                    message.setText("No contacts found");
                } else {
                    contactFile.seek(filePointer);
                    DisplayContact.displayContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                    filePointer = CONTACT_SIZE;
                    message.setText("First contact");
                }
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
        });

        // Action event for next button
        next.setOnAction(e-> {
            resetMessage(message);
            try {
                if (filePointer < contactFile.length()){
                    filePointer += CONTACT_SIZE;
                    contactFile.seek(filePointer);
                    DisplayContact.displayContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                } else {
                    message.setText("Last contact");
                }
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
        });

        // Action event for previous button
        previous.setOnAction(e-> {
            resetMessage(message);
            try {
                if(filePointer >= CONTACT_SIZE && contactFile.length() > CONTACT_SIZE) {
                    filePointer -= CONTACT_SIZE;
                    contactFile.seek(filePointer);
                    DisplayContact.displayContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                } else {
                    message.setText("No previous contact");
                }
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
        });

        // Action event for last button
        last.setOnAction(e-> {
            resetMessage(message);
            try{
                if(contactFile.length() == 0) {
                    message.setText("No contacts found");
                } else {
                    filePointer = contactFile.length() - CONTACT_SIZE;
                    contactFile.seek(filePointer);
                    DisplayContact.displayContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                    filePointer = contactFile.length();
                    message.setText("Last contact");
                }
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
        });

        // Action event for update button
        update.setOnAction(e-> {
            resetMessage(message);
            // If no contact was selected, display a message
            if (filePointer < CONTACT_SIZE) {
                message.setText("Select a contact first!");
                return;
            }
            try{
                contactFile.seek(filePointer - CONTACT_SIZE);
                EditContact.writeContact(contactFile, nameField, streetField, cityField, stateField, zipField);
                message.setText("Contact updated successfully!");
            } catch (IOException ex) {
                exceptionHandling(ex, message);
            }
        });

        // Window event for closing the window-ensuring the contact file is closed properly
        primaryStage.setOnCloseRequest(e-> {
            if (contactFile != null) {
                try {
                    contactFile.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
