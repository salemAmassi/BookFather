package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Controllers.DashboardController;
import driver.bookstore.Controllers.SearchController;
import driver.bookstore.CrudLogger;
import driver.bookstore.FxStore;
import driver.bookstore.Publisher.Publisher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class BooksController implements Initializable {
    @FXML
    private TableView<Book> bookTableView;
    @FXML
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
    private TableColumn<Book, Publisher> publisherCol;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> searchCriteria;

    BookRepository repository;
    public static Book selectedBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Link columns with database
        titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categories"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        // Create repositories and selector
        repository = new BookRepository();
        selectedBook = new Book();

        // Fill search criteria
        searchCriteria.getItems().addAll("Title", "Author", "Price");
        searchCriteria.getSelectionModel().selectFirst();

        // Create listener for book selector
        bookTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                selectedBook = repository.findEntity(newValue.getName());
            }

        });

        listBooks();
    }

    @FXML
    void btnBooksSearchOnAction(ActionEvent event) {
        // Refresh TableView with books returned from search operation
        bookTableView.getItems().setAll(new SearchController(repository)
                .search(searchCriteria.getSelectionModel().getSelectedItem(), searchField.getText()));
    }

    @FXML
    void btnAddBookOnClick(ActionEvent event) {
        // Load AddBook page onto Main Dashboard
        StackPane dashContent = DashboardController.getDashContent();
        File file = new File("src/main/resources/Books/AddBook.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane root = null;
        try {
            root = fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        FxStore.primaryStage.setTitle(file.getName().substring(0, file.getName().lastIndexOf(".")));
    }

    @FXML
    public void listBooks() {
        List<Book> books = repository.getAllBooks();
        bookTableView.getItems().addAll(books);
        addActionButtonsToTable();
    }


    // Universal input validation class
    // TODO: Implement in CRUD Controllers
    static boolean areProductInputsValid(String fieldAddBookTitle, String fieldAddBookIsbn, String fieldAddBookAuthor,String fieldAddBookPublisher) {
        // TODO
        //  Better validate inputs.
        String errorMessage = "";

        // TODO: Validate Categories
        if (fieldAddBookTitle == null || fieldAddBookTitle.length() < 3) {
            errorMessage += "Please enter a valid title!\n";
        }
        // TODO: Properly Validate ISBN
        if (fieldAddBookIsbn != null && fieldAddBookIsbn.length() < 5) {
            errorMessage += "ISBN is not valid!\n";
        }
        if (fieldAddBookAuthor != null && fieldAddBookAuthor.length() < 3) {
            errorMessage += "Please enter a valid author name!\n";
        }
        if (fieldAddBookPublisher != null && fieldAddBookPublisher.length() < 3) {
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

    @FXML
    private void btnEditBook() {
        // Load EditBook page onto Main Dashboard
        StackPane dashContent = DashboardController.getDashContent();
        File file = new File("src/main/resources/Books/EditBook.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();

        AnchorPane root = null;
        try {
            root = fxmlLoader.load(file.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        EditBookController controller = new EditBookController();
        fxmlLoader.setController(controller);

    }

    @FXML
    private void btnViewProduct() {
        // Load ViewBook page onto Main Dashboard
        StackPane dashContent = DashboardController.getDashContent();
        File file = new File("src/main/resources/Books/ViewBook.fxml");

        AnchorPane root = null;
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(file.toURI().toURL());
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();

        }
        dashContent.getChildren().clear();
        dashContent.getChildren().add(root);

        ViewBookController controller = new ViewBookController();
        fxmlLoader.setController(controller);
//      controller.setSelectedBook(book);
        FxStore.primaryStage.setTitle(file.getName().substring(0, file.getName().lastIndexOf(".")));
    }

    //TODO: Use text formatters (double and integer)

    // Creates new column in TableView for action buttons
    @FXML
    private void addActionButtonsToTable() {
        TableColumn colBtnEdit = new TableColumn("Actions");

        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                return new TableCell<Book, Void>() {

                    private final Button viewButton = new Button("View");

                    {
                        viewButton.getStyleClass().add("button");
                        viewButton.getStyleClass().add("xs");
                        viewButton.getStyleClass().add("info");
                        viewButton.setOnAction((ActionEvent event) -> {
                            selectedBook = getTableView().getItems().get(getIndex());

                            btnViewProduct();
                        });

                    }

                    private final Button editButton = new Button("Edit");

                    {
                        editButton.getStyleClass().add("button");
                        editButton.getStyleClass().add("xs");
                        editButton.getStyleClass().add("primary");
                        editButton.setOnAction((ActionEvent event) -> {
                            selectedBook = getTableView().getItems().get(getIndex());
                            System.out.println(selectedBook.getCategories().size());
                            btnEditBook();
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
                                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                repository.deleteEntity(repository.findEntity(bookData.getName()));
                                getTableView().getItems().remove(getIndex());
                                try{
                                    // Log operation success
                                    CrudLogger.getInstance().logSuccess(bookData.getName()+" has been Deleted successfully");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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
    }
}