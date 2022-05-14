package driver.bookstore.Controllers;

import driver.bookstore.Controllers.Books.BooksController;
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
//    @FXML
//    private Button searchButton;
    @FXML
    private Button addBookButton;
//    @FXML
//    private TextField searchField;
//    @FXML
//    private ComboBox<String> searchCriteria;
//    @FXML
//    private FlowPane categoriesContainer;
    @FXML
    public Button btnHome;
    @FXML
    public Button btnProducts;


    @FXML
    public AnchorPane dashHead;
    @FXML
    private StackPane dashContent;
    private static StackPane dashStackPane ;
public static  StackPane getDashContent(){
    return dashStackPane;
}
    public void btnHomeOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Home.fxml");
        DashboardController homeController = fxmlLoader.getController();
//        homeController.getDashboardProdCount();
//        homeController.getDashboardCostCount();
    }

    public void btnBooksOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Books/Books.fxml");
        BooksController controller = fxmlLoader.getController();
//        controller.listBooks();
    }
    public void btnAddOnClick(ActionEvent event){
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Books/AddBook.fxml");
        BooksController controller = fxmlLoader.getController();
    }

//    public void btnOrdersOnClick(ActionEvent actionEvent) {
//        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/orders/orders.fxml");
////        OrdersController orders = fxmlLoader.getController();
////        orders.listOrders();
//    }

//    public void btnCustomersOnClick(ActionEvent actionEvent) {
//        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/customers/customers.fxml");
////        CustomersController controller = fxmlLoader.getController();
////        controller.listCustomers();
//    }
//
//    public void btnSettingsOnClick(ActionEvent actionEvent) {
//        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Illus/settings/settings.fxml");
//    }

    //TODO: Remove Logout from fxml

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO: Move two lines to books view
//        CategoryComponentFactory factory= new CategoryComponentFactory();
//        categoriesContainer.getChildren().add(factory.createCategoryHBox("سياسة"));
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Home.fxml");
        HomeController homeController = fxmlLoader.getController();
//        homeController.getDashboardProdCount();
//        homeController.getDashboardCostCount();
    }
}