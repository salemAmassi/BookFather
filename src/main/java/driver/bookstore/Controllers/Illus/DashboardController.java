package driver.bookstore.Controllers.Illus;

import driver.bookstore.Controllers.Illus.products.BooksController;
import driver.bookstore.FxStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
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
    private FlowPane categoriesContainer;
    @FXML
    public Button btnHome;
    @FXML
    public Button btnProducts;


    @FXML
    public AnchorPane dashHead;
    @FXML
    private StackPane dashContent;

    public void btnHomeOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Illus/main-dashboard.fxml");
        DashboardController homeController = fxmlLoader.getController();
//        homeController.getDashboardProdCount();
//        homeController.getDashboardCostCount();
    }

    public void btnBooksOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Illus/products/books.fxml");
        BooksController controller = fxmlLoader.getController();
//        controller.listBooks();
    }
    public void btnAddOnClick(ActionEvent event){
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Illus/products/add-book.fxml");
        BooksController controller = fxmlLoader.getController();
    }
    public void btnSellOnClick(ActionEvent event){
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/SellBook.fxml");
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
        try {
          fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StackPane root = fxmlLoader.getRoot();
//        dashContent.getChildren().clear();
        dashContent = root;
//        FxStore.primaryStage.setTitle(dashboard.getName());
        return fxmlLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO: Move two lines to books view
//        CategoryComponentFactory factory= new CategoryComponentFactory();
//        categoriesContainer.getChildren().add(factory.createCategoryHBox("سياسة"));
        FXMLLoader fxmlLoader = loadFxmlPage("src/main/resources/Illus/home.fxml");
        driver.bookstore.Controllers.Illus.HomeController homeController = fxmlLoader.getController();
//        homeController.getDashboardProdCount();
//        homeController.getDashboardCostCount();
    }
}
