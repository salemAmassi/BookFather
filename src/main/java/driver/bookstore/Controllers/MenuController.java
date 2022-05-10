package driver.bookstore.Controllers;

import driver.bookstore.FxStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class MenuController {

    @FXML
    public void homeHandle(ActionEvent event) {

    }

    @FXML
    public void addHandle(ActionEvent event) {
        File dashboard  = new File("src/main/resources/AddBook.fxml");
        Pane root = null;
        try {
            root = new FXMLLoader(dashboard.toURI().toURL()).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene mainScene = new Scene(root);
        FxStore.primaryStage.setScene(mainScene);
    }

    @FXML
    public void sellHandle(ActionEvent event) {

    }

}
