package driver.bookstore.Controllers.Books;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.swing.text.View;
import java.util.List;
import java.util.stream.Collectors;
//import model.Categories;
//import model.Datasource;
//import model.Product;

/**
 * {@inheritDoc}
 */
public class ViewBookController extends BooksController {

    @FXML
    public ComboBox<Category> fieldAddBookCategory;
    public Text viewBookResponse;
    public TextField fieldAddBookTitle;
    public TextField fieldAddBookIsbn;
    public TextField fieldAddBookAuthor;
    public TextField fieldAddBookPrice;
    public TextField fieldAddBookPart;
    // TODO: Create size, cover, source entities
    public ComboBox fieldAddBookSize;
    public TextField fieldAddBookPages;
    public CheckBox fieldAddBookColor;
    public ComboBox fieldAddBookCover;
    public ComboBox fieldAddBookSource;
    public TextField fieldAddBookPublisher;
    public TextField fieldAddBookQuantity;
    public TextField fieldAddBookLocation;
    public ListView<String> categoriesList;
    private BookRepository repository;
    public ViewBookController(){
        repository = new BookRepository();
}

    @FXML
    private void initialize() {
        fillViewingBookFields();
    }


    public void fillViewingBookFields() {
        Task<ObservableList<Book>> fillBookTask = new Task<ObservableList<Book>>() {
            @Override
            protected ObservableList<Book> call() {
                return FXCollections.observableArrayList(
                       selectedBook);
            }
        };
        fillBookTask.setOnSucceeded(e -> {
            viewBookResponse.setText("Viewing: " + fillBookTask.valueProperty().getValue().get(0).getName());
            fieldAddBookTitle.setText(fillBookTask.valueProperty().getValue().get(0).getName());
            fieldAddBookPrice.setText(String.valueOf(fillBookTask.valueProperty().getValue().get(0).getPrice()));
            fieldAddBookQuantity.setText(String.valueOf(fillBookTask.valueProperty().getValue().get(0).getQuantity()));

            List<String> categories = fillBookTask.valueProperty()
                    .getValue().get(0).getCategories()
                    .stream().map(category -> category.getName()).collect(Collectors.toList());
            categoriesList.getItems().addAll(categories);
        });

        new Thread(fillBookTask).start();
    }
}
