package driver.bookstore.Controllers;

import driver.bookstore.Controllers.Illus.CustomersController;
import driver.bookstore.Controllers.Illus.OrdersController;
import driver.bookstore.Controllers.Illus.products.BooksController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
//    @FXML
//    private Button sellBookButton;
//    @FXML
//    private Button searchButton;
//    @FXML
//    private Button addBookButton;
//    @FXML
//    private TextField searchField;
//    @FXML
//    private ComboBox<String> searchCriteria;
//    @FXML
//    private Button homeButton;
//    @FXML
//    private FlowPane categoriesContainer;
    @FXML
    public Button btnHome;
    @FXML
    public Button btnProducts;
    @FXML
    public Button btnCustomers; //TODO: Morphs into categories or authors
    @FXML
    public Button btnOrders; //TODO: Morphs into authors or categories
    @FXML
    public Button btnSettings;
    @FXML
    public AnchorPane dashHead;
    @FXML
    private StackPane dashContent;
    @FXML
    private Label lblUsrName;

    public void btnHomeOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("Illus/home.fxml");
        driver.bookstore.Controllers.Illus.HomeController homeController = fxmlLoader.getController();
        homeController.getDashboardProdCount();
        homeController.getDashboardCostCount();
    }

    public void btnProductsOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/products/books.fxml");
        BooksController controller = fxmlLoader.getController();
        controller.listBooks();
    }

    public void btnOrdersOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/orders/orders.fxml");
        OrdersController orders = fxmlLoader.getController();
        orders.listOrders();
    }

    public void btnCustomersOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/customers/customers.fxml");
        CustomersController controller = fxmlLoader.getController();
        controller.listCustomers();
    }

    public void btnSettingsOnClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/settings/settings.fxml");
    }

    //TODO: Remove Logout from fxml

    private FXMLLoader loadFxmlPage(String view_path) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource(view_path).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnchorPane root = fxmlLoader.getRoot();
        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        return fxmlLoader;
    }


//    @FXML
//    void sellHandle(ActionEvent event) {
//        File dashboard  = new File("src/main/resources/SellBook.fxml");
//        Pane root = null;
//        try {
//            root = new FXMLLoader(dashboard.toURI().toURL()).load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene mainScene = new Scene(root);
//        FxStore.primaryStage.setScene(mainScene);
//    }
//
//    @FXML
//    void searchHandle(ActionEvent event) {
//        new SearchController().search();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TODO: Move two lines to books view
//        CategoryComponentFactory factory= new CategoryComponentFactory();
//        categoriesContainer.getChildren().add(factory.createCategoryHBox("سياسة"));

        FXMLLoader fxmlLoader = loadFxmlPage("/view/admin/pages/home/home.fxml");
        driver.bookstore.Controllers.Illus.HomeController homeController = fxmlLoader.getController();
        homeController.getDashboardProdCount();
        homeController.getDashboardCostCount();
    }
}
