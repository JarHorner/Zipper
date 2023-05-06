package com.jhorn384;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class PrimaryController {

    @FXML
    void selectFileToZip() throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        // sets title of file explorer and its starting location
        directoryChooser.setTitle("Select Directory to zip");
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

                fos.close();
            } catch (Exception e) {
                throw new IOException("Failed to zip this file at " + directoryPath);
            }
        }
    }

    @FXML
    void selectFileToUnzip() throws IOException {

        FileChooser fileChooser = new FileChooser();
        // sets title of file explorer and its starting location
        fileChooser.setTitle("Select Directory to unzip");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Set the file extension filter to show only zip files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIP files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);

        // Opens the window
        File selectedFile = fileChooser.showOpenDialog(null);

        // if directory is selected gets its absolute path, and starts to zip it
        if (selectedFile != null) {
            String directoryPath = selectedFile.getAbsolutePath();

            System.out.println("The file at " + directoryPath + " is being unzipped!");
            System.out.println("----------------------------------");

            int beginIndex = directoryPath.lastIndexOf('\\');
            String path = directoryPath.substring(0, beginIndex + 1);

            // trys to zip file, if the file exists
            try {
                File pathToUnzip = new File(path);
                ZipInputStream zipIn = new ZipInputStream(new FileInputStream(directoryPath));

                UnzipFile.unzipFile(pathToUnzip, zipIn);

                // this opens the newly unzipped folder
                String command = "explorer.exe " + directoryPath;
                Runtime.getRuntime().exec(command);

                System.out.println("----------------------------------");
                System.out.println("The file is zipped!");
                System.out.println("\n");

            } catch (Exception e) {
                throw new IOException("Failed to unzip this file at " + directoryPath);
            }
        }
    }
}
