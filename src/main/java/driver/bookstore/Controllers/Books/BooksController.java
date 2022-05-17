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
        titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("categories"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        repository  = new BookRepository();
        selectedBook = new Book();
        searchCriteria.getItems().addAll("Title","Author","Price");
        searchCriteria.getSelectionModel().selectFirst();
        bookTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
            selectedBook = repository.findEntity(newValue.getName());
            }

            });

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
        File file  = new File("src/main/resources/Books/AddBook.fxml");
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
        addActionButtonsToTable();
    }



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

    @FXML
    private void btnEditBook() {
        StackPane dashContent = DashboardController.getDashContent();
        File file  = new File("src/main/resources/Books/EditBook.fxml");
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
        StackPane dashContent = DashboardController.getDashContent();
        File file  = new File("src/main/resources/Books/ViewBook.fxml");
        AnchorPane root = null;
        FXMLLoader fxmlLoader =null;
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
//        controller.setSelectedBook(book);
        FxStore.primaryStage.setTitle(file.getName().substring(0,file.getName().lastIndexOf(".")));
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
//                                System.out.println("Delete Product");
//                                System.out.println("book id: " + bookData.getId());
//                                System.out.println("book name: " + bookData.getName());
                                // TODO: fix delete operation

                                   Alert msg = new Alert(Alert.AlertType.INFORMATION);
                                   repository.deleteEntity(repository.findEntity(bookData.getName()));
//                               if( repository.findEntity(bookData.getName())==null){
//                                msg.setContentText("Delete operation done successfully");
//                               }else{
//                                   msg = new Alert(Alert.AlertType.ERROR);
//                                   msg.setContentText("Something wrong has occurred");
//                               }
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
    }
}