package driver.bookstore.Controllers;

import driver.bookstore.Controllers.Books.BooksController;
import driver.bookstore.Controllers.Books.SellBookController;
import driver.bookstore.FxStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button sellBookButton;
    @FXML
    private Button addBookButton;
    @FXML
    public Button btnHome;
    @FXML
    public Button btnBooks;
    @FXML
    public AnchorPane dashHead;
    @FXML
    private StackPane dashContent;
    private static StackPane dashStackPane ;

    public static  StackPane getDashContent(){
        return dashStackPane;
    }

    public static void setDashStackPane(StackPane pane){
        dashStackPane = pane;
    }

    // Assigns button to fxml page and loads respective controller
    public void btnHomeOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Home.fxml");
        DashboardController homeController = fxmlLoader.getController();
    }

    // Assigns button to fxml page and loads respective controller
    public void btnBooksOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Books/Books.fxml");
        BooksController controller = fxmlLoader.getController();
    }

    // Assigns button to fxml page and loads respective controller
    public void btnAddOnClick(ActionEvent event){
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Books/AddBook.fxml");
        BooksController controller = fxmlLoader.getController();
    }

    // Assigns button to fxml page and loads respective controller
    @FXML
    void btnSellOnClick(ActionEvent event) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Books/SellBook.fxml");
        SellBookController controller = fxmlLoader.getController();
    }

    // Loads fxml page directly onto main dashboard
    private FXMLLoader loadFxmlPage(String view_path) {
        File file  = new File(view_path);
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = null;
        try {
            root = fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);
        FxStore.primaryStage.setTitle(file.getName().substring(0,file.getName().lastIndexOf(".")));
        dashStackPane = dashContent;
        return fxmlLoader;
    }

    // Displays home page by default
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Home.fxml");
        HomeController homeController = fxmlLoader.getController();
    }
}
