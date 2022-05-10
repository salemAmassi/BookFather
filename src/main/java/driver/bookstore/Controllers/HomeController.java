package driver.bookstore.Controllers;

import driver.bookstore.Category.CategoryComponentFactory;
import driver.bookstore.FxStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    @FXML
    private Button sellBookButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button addBookButton;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> searchCriteria;

    @FXML
    private Button homeButton;
    @FXML
    private FlowPane categoriesContainer;

    @FXML
    void homeHandle(ActionEvent event) {

    }

    @FXML
    void addHandle(ActionEvent event) {

    }

    @FXML
    void sellHandle(ActionEvent event) {
        File dashboard  = new File("src/main/resources/SellBook.fxml");
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
    void searchHandle(ActionEvent event) {
        new SearchController().search();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CategoryComponentFactory factory= new CategoryComponentFactory();
        categoriesContainer.getChildren().add(factory.createCategoryHBox("سياسة"));

    }
}
