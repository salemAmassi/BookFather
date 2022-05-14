package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Controllers.DashboardController;
import driver.bookstore.Controllers.SearchController;
import driver.bookstore.FxStore;
import driver.bookstore.Publisher.Publisher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class BooksController implements Initializable {
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> sizeCol;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, Boolean> printColorCol;

    @FXML
    private TableColumn<Book, String> coverCol;

    @FXML
    private TableColumn<Book, Author> authorCol;


    @FXML
    private StackPane productsContent;

    @FXML
    private TableColumn<Book, List<String>> categoryCol;

    @FXML
    private Button addBook;

    @FXML
    private TableColumn<Book, Integer> pageNoCol;

    @FXML
    private TableColumn<Book, Integer> priceCol;


    @FXML
    private TableColumn<Book, Integer> partNoCol;

    @FXML
    private TableColumn<Book, Publisher> publisherCol;

    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> searchCriteria;

    BookRepository repository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categories"));
        partNoCol.setCellValueFactory(new PropertyValueFactory<>("partNo"));
        pageNoCol.setCellValueFactory(new PropertyValueFactory<>("pageNo"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        printColorCol.setCellValueFactory(new PropertyValueFactory<>("paintColor"));
        coverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        repository  = new BookRepository();
        searchCriteria.getItems().addAll("Title","Author","Price");
        searchCriteria.getSelectionModel().selectFirst();
        listBooks();
    }
    @FXML
    void btnBooksSearchOnAction(ActionEvent event) {
       bookTableView.getItems().setAll(new SearchController(repository)
               .search(searchCriteria.getSelectionModel().getSelectedItem(),searchField.getText()));
    }

    @FXML
    void btnAddBookOnClick(ActionEvent event) {
        StackPane dashContent = DashboardController.getDashContent();
        File file  = new File("src/main/resources/Illus/products/AddBook.fxml");
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
    }



    @FXML
    public void listBooks() {
        List<Book> books = repository.getAllBooks();
        bookTableView.getItems().addAll(books);
    }
//
//    /**
//     * This private method adds the action buttons to the table rows.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void addActionButtonsToTable() {
////        TableColumn colBtnEdit = new TableColumn("Actions");
////
////        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
////            @Override
////            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
////                return new TableCell<Product, Void>() {
////
////                    private final Button viewButton = new Button("View");
////
////                    {
////                        viewButton.getStyleClass().add("button");
////                        viewButton.getStyleClass().add("xs");
////                        viewButton.getStyleClass().add("info");
////                        viewButton.setOnAction((ActionEvent event) -> {
////                            Product productData = getTableView().getItems().get(getIndex());
////                            btnViewProduct(productData.getId());
////                        });
////                    }
////
////                    private final Button editButton = new Button("Edit");
////
////                    {
////                        editButton.getStyleClass().add("button");
////                        editButton.getStyleClass().add("xs");
////                        editButton.getStyleClass().add("primary");
////                        editButton.setOnAction((ActionEvent event) -> {
////                            Product productData = getTableView().getItems().get(getIndex());
////                            btnEditProduct(productData.getId());
////                        });
////                    }
////
////                    private final Button deleteButton = new Button("Delete");
////
////                    {
////                        deleteButton.getStyleClass().add("button");
////                        deleteButton.getStyleClass().add("xs");
////                        deleteButton.getStyleClass().add("danger");
////                        deleteButton.setOnAction((ActionEvent event) -> {
////                            Product productData = getTableView().getItems().get(getIndex());
////
////                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////                            alert.setHeaderText("Are you sure that you want to delete " + productData.getName() + " ?");
////                            alert.setTitle("Delete " + productData.getName() + " ?");
////                            Optional<ButtonType> deleteConfirmation = alert.showAndWait();
////
////                            if (deleteConfirmation.get() == ButtonType.OK) {
////                                System.out.println("Delete Product");
////                                System.out.println("product id: " + productData.getId());
////                                System.out.println("product name: " + productData.getName());
////                                if (Datasource.getInstance().deleteSingleProduct(productData.getId())) {
////                                    getTableView().getItems().remove(getIndex());
////                                }
////                            }
////                        });
////                    }
////
////                    private final HBox buttonsPane = new HBox();
////
////                    {
////                        buttonsPane.setSpacing(10);
////                        buttonsPane.getChildren().add(viewButton);
////                        buttonsPane.getChildren().add(editButton);
////                        buttonsPane.getChildren().add(deleteButton);
////                    }
////
////                    @Override
////                    public void updateItem(Void item, boolean empty) {
////                        super.updateItem(item, empty);
////
////                        if (empty) {
////                            setGraphic(null);
////                        } else {
////                            setGraphic(buttonsPane);
////                        }
////                    }
////                };
////            }
////        };
////
////        colBtnEdit.setCellFactory(cellFactory);
////
////        tableProductsPage.getColumns().add(colBtnEdit);
//
//    }
//
//    /**
//     * This private method handles the products search functionality.
//     * It creates a new task, gets the search results from the database and binds them to the view table.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void btnBooksSearchOnAction() {
////        Task<ObservableList<Product>> searchProductsTask = new Task<ObservableList<Product>>() {
////            @Override
////            protected ObservableList<Product> call() {
////                return FXCollections.observableArrayList(
////                        Datasource.getInstance().searchProducts(fieldProductsSearch.getText().toLowerCase(), Datasource.ORDER_BY_NONE));
////            }
////        };
////        tableProductsPage.itemsProperty().bind(searchProductsTask.valueProperty());
////
////        new Thread(searchProductsTask).start();
//    }
//
//    /**
//     * This private method loads the add product view page.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void btnAddBookOnClick() {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        try {
//            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/AddBook.fxml").openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AnchorPane root = fxmlLoader.getRoot();
//        productsContent.getChildren().clear();
//        productsContent.getChildren().add(root);
//
//    }
//
//    /**
//     * This private method loads the edit product view page.
//     * @param book_id        Product id.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void btnEditBook(int book_id) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        try {
//            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/EditBook.fxml").openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AnchorPane root = fxmlLoader.getRoot();
//        productsContent.getChildren().clear();
//        productsContent.getChildren().add(root);
//
//        EditBookController controller = fxmlLoader.getController();
//        controller.fillEditingBookFields(book_id);
//
//    }
//
//    /**
//     * This private method loads single add product view page.
//     * @param book_id        Product id.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void btnViewBook(int book_id) {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        try {
//            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/ViewBook.fxml").openStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AnchorPane root = fxmlLoader.getRoot();
//        productsContent.getChildren().clear();
//        productsContent.getChildren().add(root);
//
//        ViewBookController controller = fxmlLoader.getController();
//        controller.fillViewingBookFields(book_id);
//    }
//
//    /**
//     * This private method validates the user input fields for the product.
//     * @return boolean          Returns true or false.
//     * @since                   1.0.0
//     */
//    @FXML
//    boolean areProductInputsValid(String fieldAddProductName, String fieldAddProductDescription, String fieldAddProductPrice, String fieldAddProductQuantity, int productCategoryId) {
//        // TODO
//        //  Better validate inputs.
//        System.out.println("TODO: Better validate inputs.");
//        String errorMessage = "";
//
//        if (productCategoryId == 0) {
//            errorMessage += "Not valid category id!\n";
//        }
//        if (fieldAddProductName == null || fieldAddProductName.length() < 3) {
//            errorMessage += "please enter a valid name!\n";
//        }
//        if (fieldAddProductDescription == null || fieldAddProductDescription.length() < 5) {
//            errorMessage += "Description is not valid!\n";
//        }
////        if (!HelperMethods.validateProductPrice(fieldAddProductPrice)) {
////            errorMessage += "Price is not valid!\n";
////        }
//
////        if (!HelperMethods.validateProductQuantity(fieldAddProductQuantity)) {
////            errorMessage += "Not valid quantity!\n";
////        }
//
//        if (errorMessage.length() == 0) {
//            return true;
//        } else {
//            // Show the error message.
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Invalid Fields");
//            alert.setHeaderText("Please correct invalid fields");
//            alert.setContentText(errorMessage);
//
//            alert.showAndWait();
//
//            return false;
//        }
//
//    }
//
//    /**
//     * This method returns the TextFormatter for validating as double text input fields.
//     * @return TextFormatter
//     * @since               1.0.0
//     */
//    public static TextFormatter<Double> formatDoubleField() {
////        Pattern validEditingState = Pattern.compile("^[0-9]+(|\\.)[0-9]+$");
//        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
//        UnaryOperator<TextFormatter.Change> filter = c -> {
//            String text = c.getControlNewText();
//            if (validEditingState.matcher(text).matches()) {
//                return c ;
//            } else {
//                return null ;
//            }
//        };
//        StringConverter<Double> converter = new StringConverter<Double>() {
//            @Override
//            public Double fromString(String s) {
//                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
//                    return 0.0 ;
//                } else {
//                    return Double.valueOf(s);
//                }
//            }
//            @Override
//            public String toString(Double d) {
//                return d.toString();
//            }
//        };
//
//        return new TextFormatter<>(converter, 0.0, filter);
//    }
//
//    /**
//     * This method returns the TextFormatter for validating as int text input fields.
//     * @return TextFormatter
//     * @since               1.0.0
//     */
//    public static TextFormatter<Integer> formatIntField() {
////        Pattern validEditingState = Pattern.compile("-?(0|[1-9]\\d*)");
//        Pattern validEditingState = Pattern.compile("^[0-9]+$");
//        UnaryOperator<TextFormatter.Change> filter = c -> {
//            String text = c.getControlNewText();
//            if (validEditingState.matcher(text).matches()) {
//                return c ;
//            } else {
//                return null ;
//            }
//        };
//        StringConverter<Integer> converter = new StringConverter<Integer>() {
//            @Override
//            public Integer fromString(String s) {
//                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
//                    return 0 ;
//                } else {
//                    return Integer.valueOf(s);
//                }
//            }
//            @Override
//            public String toString(Integer d) {
//                return d.toString();
//            }
//        };
//
//        return new TextFormatter<>(converter, 0, filter);
//    }


}