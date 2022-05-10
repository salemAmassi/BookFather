package driver.bookstore.Controllers;

import driver.bookstore.Category.CategoryComponentFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

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
    private ComboBox<?> searchCriteria;

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
