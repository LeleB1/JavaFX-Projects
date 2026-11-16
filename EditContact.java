package com.example.lelesfx;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.RandomAccessFile;

public class EditContact {
    private static final int NAME_LENGTH = 32;
    private static final int STREET_LENGTH = 32;
    private static final int CITY_LENGTH = 20;
    private static final int STATE_LENGTH = 2;
    private static final int ZIP_LENGTH = 5;

    // Method for writing data to a file
    public static void writeValues(String s, int length, RandomAccessFile file) throws IOException {
        // Creating a string of the data entered by the user and writing it to a RandomAccessFile
        StringBuilder writeContact = new StringBuilder(s);
        if (writeContact.length() > length) {  // If the input string is too long,
            writeContact.setLength(length);    // shorten it (by setting it to a fixed length)
        } else {
            while (writeContact.length() < length) {  // If the string is less than the desired length,
                writeContact.append(" ");             // Add empty spaces
            }
        }
        file.writeBytes(writeContact.toString());
    }
    // Method for writing the contact file into a file (using the writeValues method)
    public static void writeContact(
            RandomAccessFile file,
            TextField nameField,
            TextField streetField,
            TextField cityField,
            TextField stateField,
            TextField zipField
    )throws IOException {
        writeValues(nameField.getText(), NAME_LENGTH, file);
        writeValues(streetField.getText(), STREET_LENGTH, file);
        writeValues(cityField.getText(), CITY_LENGTH, file);
        writeValues(stateField.getText(), STATE_LENGTH, file);
        writeValues(zipField.getText(), ZIP_LENGTH, file);
    }
}
