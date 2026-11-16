package com.example.lelesfx;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DisplayContact {
    private static final int NAME_LENGTH = 32;
    private static final int STREET_LENGTH = 32;
    private static final int CITY_LENGTH = 20;
    private static final int STATE_LENGTH = 2;
    private static final int ZIP_LENGTH = 5;

    // Method for reading the user input from the RandomAccessFile
    private static String readString(RandomAccessFile file, int length) throws IOException {
        byte[] bytes = new  byte[length];
        file.readFully(bytes);
        return new String(bytes).trim();
    }
    // Method to display a contact
    public static void displayContact(
            RandomAccessFile file,
            TextField nameField,
            TextField streetField,
            TextField cityField,
            TextField stateField,
            TextField zipField
    ) throws IOException{
        nameField.setText(readString(file, NAME_LENGTH));
        streetField.setText(readString(file, STREET_LENGTH));
        cityField.setText(readString(file, CITY_LENGTH));
        stateField.setText(readString(file, STATE_LENGTH));
        zipField.setText(readString(file, ZIP_LENGTH));
    }
}
