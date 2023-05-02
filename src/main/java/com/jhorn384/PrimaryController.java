package com.jhorn384;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void selectFileToZip() throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        // sets title of file explorer and its starting location
        directoryChooser.setTitle("Select Directory");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // Opens the window
        File selectedDirectory = directoryChooser.showDialog(null);
        // if directory is selected gets its absolute path, and starts to zip it
        if (selectedDirectory != null) {
            String directoryPath = selectedDirectory.getAbsolutePath();
            System.out.println("The file at " + directoryPath + " is being zipped!");
            System.out.println("----------------------------------");

            int beginIndex = directoryPath.lastIndexOf('\\');
            String fileName = directoryPath.substring(beginIndex + 1);
            System.out.println(fileName);

            // trys to zip file, if the file exists
            try {
                FileOutputStream fos = new FileOutputStream(directoryPath + ".zip");
                ZipOutputStream zipOut = new ZipOutputStream(fos);

                File fileToZip = new File(directoryPath);

                ZipFile.zipFile(fileToZip, fileName, zipOut);

                System.out.println("----------------------------------");
                System.out.println("The file is zipped!");
                System.out.println("\n");

                // fos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
