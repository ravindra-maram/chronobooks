package com.example.chronobooks.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadHelper {

    // Method to download an audio file and save it locally in the app's private storage
    public static File downloadAudioFile(Context context, String audioUrl, String filename) {
        try {
            // Create a URL object from the provided audio URL string
            URL url = new URL(audioUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();  // Establish the connection

            // Create a file in the app's private storage directory to save the downloaded audio
            File outputFile = new File(context.getFilesDir(), filename);
            FileOutputStream outputStream = new FileOutputStream(outputFile); // Output stream to write the data

            // Get the input stream to read data from the URL connection
            InputStream inputStream = connection.getInputStream();

            // Buffer to store chunks of data while downloading
            byte[] buffer = new byte[4096];
            int len;

            // Read the data from the input stream and write it to the output file
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len); // Write data to the file
            }

            // Close the input and output streams after the download is complete
            outputStream.close();
            inputStream.close();

            // Return the downloaded file
            return outputFile;
        } catch (Exception e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
            return null; // Return null if download fails
        }
    }
}
