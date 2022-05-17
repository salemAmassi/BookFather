package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Book.OrderedBook;
import driver.bookstore.CrudLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javax.persistence.Query;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;

// TODO: Rework sell controller and UI
public class SellBookController implements Initializable {
    @FXML
    private TableView<OrderedBook> purchasedBooksList;
    @FXML
    private Button deleteButton;
    @FXML
    private Button confirmSell;
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private Button addBook;
    @FXML
    private TableColumn<Book, String> bNameCol;
    @FXML
    private TableColumn<Book, Author> authCol;
    @FXML
    private TextField quantityField;
    @FXML
    private TableColumn<Book, Integer> purchasePriceCol;
    @FXML
    private Text totalField;
    @FXML
    private TableColumn<Book, String> purchaseTitleCol;
    @FXML
    private TableColumn<Book, Integer> purchaseQuantityCol;
    @FXML
    private TableColumn<Book, Integer> purchaseTotalCol;
    @FXML
    private TableColumn<Book, Integer> priceCol;
    BookRepository repository;
    Book selectedBook;
    private Set<OrderedBook> books;

    @FXML
    void addBookHandle(ActionEvent event) {
        int quantity = AddBookController.getValue(quantityField.getText());
        OrderedBook book = new OrderedBook();
        if (quantity > selectedBook.getQuantity()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There is not enough quantity!");
            alert.show();
            quantityField.setText("");
        } else {
            book.setName(selectedBook.getName());
            book.setId(selectedBook.getId());
            book.setPrice(selectedBook.getPrice());
            book.setQuantity(quantity);
            books.add(book);
        }
        purchasedBooksList.getItems().setAll(books);
        int total = AddBookController.getValue(totalField.getText());
        totalField.setText(total + book.getTotal() + "");
    }

    @FXML
    public void deleteButtonHandle(ActionEvent event) {
        purchasedBooksList.getItems().remove(purchasedBooksList.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void confirmSellHandle(ActionEvent event) {
        Query updateQuantity = repository.manager
                .createNativeQuery("update book set quantity = quantity - :toSellQuantity where id = :id");
        purchasedBooksList.getItems()
                .stream()
                .forEach(book -> {
                            updateQuantity
                                    .setParameter("toSellQuantity", book.getQuantity())
                                    .setParameter("id", book.getId())
                                    .executeUpdate();
                            CrudLogger.getInstance()
                                    .getLogger()
                                    .log(new LogRecord(Level.FINE,
                                            "update book set quantity = quantity - "
                                                    + book.getQuantity() + "where id = " + book.getId()));
                        }
                );// we want to decrease the
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new BookRepository();
        books = new HashSet<>();
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        booksTable.getItems().setAll(repository.getAllBooks());
        CrudLogger.getInstance().getLogger().log(Level.FINE, "SELECT b from Book b");
        purchaseTitleCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        purchasePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        purchaseQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchaseTotalCol.setCellValueFactory(new PropertyValueFactory<>("totalField"));
        repository = new BookRepository();
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        booksTable.getSelectionModel()
                .selectedItemProperty().addListener(
                        event ->
                                selectedBook = booksTable.getSelectionModel().getSelectedItem()
                );
    }

}
