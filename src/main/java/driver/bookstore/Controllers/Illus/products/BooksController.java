package driver.bookstore.Controllers.Illus.products;

//import app.utils.HelperMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
<<<<<<< Updated upstream
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
//import model.Datasource;
//import model.Product;
=======
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
>>>>>>> Stashed changes

import java.io.IOException;
<<<<<<< Updated upstream
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
=======
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
>>>>>>> Stashed changes

/**
 * This class handles the admin products page.
 * @author      Sajmir Doko
 */
public class BooksController {

    @FXML
    public TextField fieldProductsSearch;
    @FXML
<<<<<<< Updated upstream
    public Text viewProductResponse;
    @FXML
    public GridPane formEditProductView;
    @FXML
    private StackPane productsContent;
//    @FXML
//    private TableView<Book> tableProductsPage;

    /**
     * This method lists all the product to the view table.
     * It starts a new Task, gets all the products from the database then bind the results to the view.
     * @since                   1.0.0
     */
    @FXML
    public void listBooks() {
//
//        Task<ObservableList<Product>> getAllProductsTask = new Task<ObservableList<Product>>() {
//            @Override
//            protected ObservableList<Product> call() {
//                return FXCollections.observableArrayList(Datasource.getInstance().getAllProducts(Datasource.ORDER_BY_NONE));
//            }
//        };
//
//        tableProductsPage.itemsProperty().bind(getAllProductsTask.valueProperty());
//        addActionButtonsToTable();
//        new Thread(getAllProductsTask).start();

    }

    /**
     * This private method adds the action buttons to the table rows.
     * @since                   1.0.0
     */
    @FXML
    private void addActionButtonsToTable() {
//        TableColumn colBtnEdit = new TableColumn("Actions");
//
//        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<TableColumn<Product, Void>, TableCell<Product, Void>>() {
//            @Override
//            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
//                return new TableCell<Product, Void>() {
//
//                    private final Button viewButton = new Button("View");
//
//                    {
//                        viewButton.getStyleClass().add("button");
//                        viewButton.getStyleClass().add("xs");
//                        viewButton.getStyleClass().add("info");
//                        viewButton.setOnAction((ActionEvent event) -> {
//                            Product productData = getTableView().getItems().get(getIndex());
//                            btnViewProduct(productData.getId());
//                        });
//                    }
//
//                    private final Button editButton = new Button("Edit");
//
//                    {
//                        editButton.getStyleClass().add("button");
//                        editButton.getStyleClass().add("xs");
//                        editButton.getStyleClass().add("primary");
//                        editButton.setOnAction((ActionEvent event) -> {
//                            Product productData = getTableView().getItems().get(getIndex());
//                            btnEditProduct(productData.getId());
//                        });
//                    }
//
//                    private final Button deleteButton = new Button("Delete");
//
//                    {
//                        deleteButton.getStyleClass().add("button");
//                        deleteButton.getStyleClass().add("xs");
//                        deleteButton.getStyleClass().add("danger");
//                        deleteButton.setOnAction((ActionEvent event) -> {
//                            Product productData = getTableView().getItems().get(getIndex());
//
//                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                            alert.setHeaderText("Are you sure that you want to delete " + productData.getName() + " ?");
//                            alert.setTitle("Delete " + productData.getName() + " ?");
//                            Optional<ButtonType> deleteConfirmation = alert.showAndWait();
//
//                            if (deleteConfirmation.get() == ButtonType.OK) {
//                                System.out.println("Delete Product");
//                                System.out.println("product id: " + productData.getId());
//                                System.out.println("product name: " + productData.getName());
//                                if (Datasource.getInstance().deleteSingleProduct(productData.getId())) {
//                                    getTableView().getItems().remove(getIndex());
//                                }
//                            }
//                        });
//                    }
//
//                    private final HBox buttonsPane = new HBox();
//
//                    {
//                        buttonsPane.setSpacing(10);
//                        buttonsPane.getChildren().add(viewButton);
//                        buttonsPane.getChildren().add(editButton);
//                        buttonsPane.getChildren().add(deleteButton);
//                    }
//
//                    @Override
//                    public void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(buttonsPane);
//                        }
//                    }
//                };
//            }
//        };
//
//        colBtnEdit.setCellFactory(cellFactory);
//
//        tableProductsPage.getColumns().add(colBtnEdit);

    }

    /**
     * This private method handles the products search functionality.
     * It creates a new task, gets the search results from the database and binds them to the view table.
     * @since                   1.0.0
     */
    @FXML
    private void btnBooksSearchOnAction() {
//        Task<ObservableList<Product>> searchProductsTask = new Task<ObservableList<Product>>() {
//            @Override
//            protected ObservableList<Product> call() {
//                return FXCollections.observableArrayList(
//                        Datasource.getInstance().searchProducts(fieldProductsSearch.getText().toLowerCase(), Datasource.ORDER_BY_NONE));
//            }
//        };
//        tableProductsPage.itemsProperty().bind(searchProductsTask.valueProperty());
//
//        new Thread(searchProductsTask).start();
    }

    /**
     * This private method loads the add product view page.
     * @since                   1.0.0
     */
    @FXML
    private void btnAddProductOnClick() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/add-book.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane root = fxmlLoader.getRoot();
        productsContent.getChildren().clear();
        productsContent.getChildren().add(root);

    }

    /**
     * This private method loads the edit product view page.
     * @param book_id        Product id.
     * @since                   1.0.0
     */
    @FXML
    private void btnEditBook(int book_id) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/edit-book.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        AnchorPane root = fxmlLoader.getRoot();
        productsContent.getChildren().clear();
        productsContent.getChildren().add(root);

        EditBookController controller = fxmlLoader.getController();
        controller.fillEditingBookFields(book_id);

=======
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Author> authorCol;
    @FXML
    private StackPane productsContent;
    @FXML
    private TableColumn<Book, List<String>> categoryCol;
    @FXML
    private Button addBook;
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
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        repository  = new BookRepository();
        searchCriteria.getItems().addAll("Title","Author","Price");
        searchCriteria.getSelectionModel().selectFirst();
        listBooks();
    }
    @FXML
    void btnBooksSearchOnAction(ActionEvent event) {
       bookTableView.getItems().setAll(new SearchController(repository)
               .search(searchCriteria.getSelectionModel().getSelectedItem(),searchField.getText()));
>>>>>>> Stashed changes
    }

    /**
     * This private method loads single add product view page.
     * @param book_id        Product id.
     * @since                   1.0.0
     */
    @FXML
    private void btnViewBook(int book_id) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/admin/pages/products/view-book.fxml").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< Updated upstream
        AnchorPane root = fxmlLoader.getRoot();
        productsContent.getChildren().clear();
        productsContent.getChildren().add(root);

        ViewBookController controller = fxmlLoader.getController();
        controller.fillViewingBookFields(book_id);
    }

    /**
     * This private method validates the user input fields for the product.
     * @return boolean          Returns true or false.
     * @since                   1.0.0
     */
    @FXML
    boolean areProductInputsValid(String fieldAddProductName, String fieldAddProductDescription, String fieldAddProductPrice, String fieldAddProductQuantity, int productCategoryId) {
        // TODO
        //  Better validate inputs.
        System.out.println("TODO: Better validate inputs.");
        String errorMessage = "";

        if (productCategoryId == 0) {
            errorMessage += "Not valid category id!\n";
        }
        if (fieldAddProductName == null || fieldAddProductName.length() < 3) {
            errorMessage += "please enter a valid name!\n";
        }
        if (fieldAddProductDescription == null || fieldAddProductDescription.length() < 5) {
            errorMessage += "Description is not valid!\n";
        }
//        if (!HelperMethods.validateProductPrice(fieldAddProductPrice)) {
//            errorMessage += "Price is not valid!\n";
//        }

//        if (!HelperMethods.validateProductQuantity(fieldAddProductQuantity)) {
//            errorMessage += "Not valid quantity!\n";
//        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }

    /**
     * This method returns the TextFormatter for validating as double text input fields.
     * @return TextFormatter
     * @since               1.0.0
     */
    public static TextFormatter<Double> formatDoubleField() {
//        Pattern validEditingState = Pattern.compile("^[0-9]+(|\\.)[0-9]+$");
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Double> converter = new StringConverter<Double>() {
            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }
            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        return new TextFormatter<>(converter, 0.0, filter);
    }

    /**
     * This method returns the TextFormatter for validating as int text input fields.
     * @return TextFormatter
     * @since               1.0.0
     */
    public static TextFormatter<Integer> formatIntField() {
//        Pattern validEditingState = Pattern.compile("-?(0|[1-9]\\d*)");
        Pattern validEditingState = Pattern.compile("^[0-9]+$");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };
        StringConverter<Integer> converter = new StringConverter<Integer>() {
            @Override
            public Integer fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0 ;
                } else {
                    return Integer.valueOf(s);
                }
            }
            @Override
            public String toString(Integer d) {
                return d.toString();
            }
        };

        return new TextFormatter<>(converter, 0, filter);
=======
    @FXML
    public void listBooks() {
        List<Book> books = repository.getAllBooks();
        bookTableView.getItems().addAll(books);
        addActionButtonsToTable();
    }

    @FXML
    private void btnEditBook(int book_id) {
        StackPane dashContent = DashboardController.getDashContent();
        File file  = new File("src/main/resources/Illus/products/edit-book.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = null;
        try {
            root = fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        EditBookController controller = fxmlLoader.getController();
        controller.fillEditingBookFields(book_id);

    }

    @FXML
    private void btnViewProduct(int product_id) {
        StackPane dashContent = DashboardController.getDashContent();
        File file  = new File("src/main/resources/Illus/products/view-book.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = null;
        try {
            root = fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        ViewBookController controller = fxmlLoader.getController();
        controller.fillViewingBookFields(product_id);
    }

    // TODO: Use product Validation
    boolean areProductInputsValid(String fieldAddBookTitle, String fieldAddBookIsbn, String fieldAddBookAuthor, String fieldAddBookPrice, String fieldAddBookPart, String fieldAddBookPages, String fieldAddBookPublisher, String fieldAddBookQuantity, String fieldAddBookLocation) {
        // TODO
        //  Better validate inputs.
        String errorMessage = "";

//        if (productCategoryId == 0) {
//            errorMessage += "Not valid category id!\n";
//        }// TODO: Validate Categories
        if (fieldAddBookTitle == null || fieldAddBookTitle.length() < 3) {
            errorMessage += "Please enter a valid title!\n";
        }
        // TODO: Properly Validate ISBN
        if (fieldAddBookIsbn != null && fieldAddBookIsbn.length() < 5) {
            errorMessage += "ISBN is not valid!\n";
        }
        if (fieldAddBookAuthor != null || fieldAddBookAuthor.length() < 3) {
            errorMessage += "Please enter a valid author name!\n";
        }
        //TODO: Properly Validate Price
//        if (fieldAddBookPrice) {
//            errorMessage += "Price is not valid!\n";
//        }
        //TODO: Properly Validate Part
//        if (fieldAddBookPart)) {
//            errorMessage += "Not valid Part!\n";
//        }
        //TODO: Properly Validate Quantity
//        if (fieldAddBookQuantity)) {
//            errorMessage += "Not valid Quantity!\n";
//        }
        //TODO: Properly Validate Quantity
//        if (fieldAddBookPages)) {
//            errorMessage += "Not valid Page!\n";
//        }
        //TODO: Properly Validate Location
//        if (fieldAddBookLocation)) {
//            errorMessage += "Not valid Location!\n";
//        }
        if (fieldAddBookPublisher != null || fieldAddBookPublisher.length() < 3) {
            errorMessage += "Please enter a valid publisher name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }

    //TODO: Use text formatters (double and integer)

    @FXML
    private void addActionButtonsToTable() {
        TableColumn colBtnEdit = new TableColumn("Actions");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<TableColumn<Book, Void>, TableCell<Book, Void>>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<Book, Void>() {

                    private final Button viewButton = new Button("View");

                    {
                        viewButton.getStyleClass().add("button");
                        viewButton.getStyleClass().add("xs");
                        viewButton.getStyleClass().add("info");
                        viewButton.setOnAction((ActionEvent event) -> {
                            Book bookData = getTableView().getItems().get(getIndex());
                            btnViewProduct(bookData.getId());
                        });
                    }

                    private final Button editButton = new Button("Edit");

                    {
                        editButton.getStyleClass().add("button");
                        editButton.getStyleClass().add("xs");
                        editButton.getStyleClass().add("primary");
                        editButton.setOnAction((ActionEvent event) -> {
                            Book bookData = getTableView().getItems().get(getIndex());
                            btnEditBook(bookData.getId());
                        });
                    }

                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.getStyleClass().add("button");
                        deleteButton.getStyleClass().add("xs");
                        deleteButton.getStyleClass().add("danger");
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Book bookData = getTableView().getItems().get(getIndex());

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("Are you sure that you want to delete " + bookData.getName() + " ?");
                            alert.setTitle("Delete " + bookData.getName() + " ?");
                            Optional<ButtonType> deleteConfirmation = alert.showAndWait();

                            if (deleteConfirmation.get() == ButtonType.OK) {
                                System.out.println("Delete Product");
                                System.out.println("book id: " + bookData.getId());
                                System.out.println("book name: " + bookData.getName());
                                // TODO: fix delete operation
                                repository.deleteEntity(repository.findEntity((long) bookData.getId()));
                                getTableView().getItems().remove(getIndex());
                            }
                        });
                    }

                    private final HBox buttonsPane = new HBox();

                    {
                        buttonsPane.setSpacing(10);
                        buttonsPane.getChildren().add(viewButton);
                        buttonsPane.getChildren().add(editButton);
                        buttonsPane.getChildren().add(deleteButton);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(buttonsPane);
                        }
                    }
                };
            }
        };

        colBtnEdit.setCellFactory(cellFactory);

        bookTableView.getColumns().add(colBtnEdit);

>>>>>>> Stashed changes
    }
}