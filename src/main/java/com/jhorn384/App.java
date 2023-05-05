package com.jhorn384;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // vars as these values are used in multiple elements
        double buttonHeight = 100;
        double buttonWidth = 300;

        // Create an instance of PrimaryController to operate event listeners of buttons
        PrimaryController controller = new PrimaryController();

        // grid to properly place each element
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10.0);
        grid.setVgap(20.0);
        grid.setId("grid");

        // title of app
        Text title = new Text("Zipper");
        title.setId("title");

        // put the title in an Horizontal box, so the text can be center aligned
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);

        // button to zip a file
        Button zipButton = new Button("Zip File");
        zipButton.setId("zipButton");

        zipButton.setOnAction(event -> {
            // Call the selectFileToZip() method of PrimaryController
            try {
                controller.selectFileToZip();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // button to unzip a file
        Button unzipButton = new Button("Unzip File");
        unzipButton.setId("unzipButton");

        unzipButton.setOnAction(event -> {
            // Call the handleButtonClick() method of PrimaryController
            try {
                controller.selectFileToUnzip();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // adds each element to the grid
        grid.add(titleBox, 0, 0);
        grid.add(zipButton, 0, 1);
        grid.add(unzipButton, 0, 2);

        // sets the prefered width and height of each element
        titleBox.setPrefWidth(buttonWidth);
        titleBox.setPrefHeight(buttonHeight);

        zipButton.setPrefWidth(buttonWidth);
        zipButton.setPrefHeight(buttonHeight);

        unzipButton.setPrefWidth(buttonWidth);
        unzipButton.setPrefHeight(buttonHeight);

        // creates the displayed scene at a specified size
        scene = new Scene(grid, 440, 360);

        // this ensures a CSS file is loaded for styling
        URL url = this.getClass().getResource("Stylesheet.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        // sets title of the app and shows the scene
        stage.setTitle("Zipper");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}