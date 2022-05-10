package driver.bookstore.Controllers;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.Query;
import java.net.URL;
import java.util.ResourceBundle;

public class SellBookController implements Initializable {
    @FXML
    private Button sellBookButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addBookButton;
    @FXML
    private Button confirmSell;
    @FXML
    private TextField bNameField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<Book> purchasedBooks;
    @FXML
    private TableColumn<Book, String> bNameCol;
    @FXML
    private TableColumn<Book, Author> authCol;
    @FXML
    private TableColumn<Book, Integer> priceCol;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private Button addBook;
    @FXML
    private Button homeButton;

    BookRepository repository;
    MenuController controller;


    @FXML
    void addBookHandle(ActionEvent event) {
            Book resultBook  =  repository.findEntity(bNameField.getText());
      Book book =   Book.builder()
              .name(resultBook.getName())
              .price(resultBook.getPrice())
//              .author
              .quantity(Integer.parseInt(quantityField.getText()))
              .build();
      purchasedBooks.getItems().add(book);//all attributes?
    }
@FXML
public void deleteButtonHandle(ActionEvent event){
        purchasedBooks.getItems().removeAll(purchasedBooks.getSelectionModel().getSelectedItems());
}
   @FXML
   public void confirmSellHandle(ActionEvent event){
//needs work!
        Query updateQuantity = repository.manager
                .createNamedQuery("update book set quantity = quantity - :toSellQuantity where id = :id");
        purchasedBooks.getItems()
                .stream()
                .forEach(book->updateQuantity
                        .setParameter("toSellQuantity",book.getQuantity())
                        .setParameter("id",book.getId())
                        .executeUpdate());// we want to decrease the
   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new BookRepository();
        controller = new MenuController();
        homeButton.setOnAction(event->controller.homeHandle(event));
        addBookButton.setOnAction(event -> controller.addHandle(event));
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    //I want to get quantity value from the textField
    }

}
