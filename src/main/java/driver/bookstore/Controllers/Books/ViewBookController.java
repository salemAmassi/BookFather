package driver.bookstore.Controllers.Books;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Setter
@Getter
public class ViewBookController implements Initializable {

    @FXML
    public ListView<String> fieldAddBookCategory;
    public Text viewBookResponse;
    public TextField fieldAddBookTitle;
    public TextField fieldAddBookIsbn;
    public TextField fieldAddBookAuthor;
    public TextField fieldAddBookPrice;
    public TextField fieldAddBookPart;
    // TODO: Create size, cover, source entities
    public TextField fieldAddBookSize;
    public TextField fieldAddBookPages;
    public TextField fieldAddBookColor;
    public TextField fieldAddBookCover;
    public TextField fieldAddBookSource;
    public TextField fieldAddBookPublisher;
    public TextField fieldAddBookQuantity;
    public TextField fieldAddBookLocation;
    public ListView<String> categoriesList;
    private BookRepository repository;
    private Book selectedBook;
    public ViewBookController(){
        repository = new BookRepository();
        selectedBook = BooksController.selectedBook;
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillViewingBookFields();
    }


    public void fillViewingBookFields() {


            fieldAddBookTitle.setText(selectedBook.getName());
            fieldAddBookQuantity.setText(selectedBook.getQuantity()+"");
            fieldAddBookPrice.setText(selectedBook.getPrice()+"");
            List<String> categories = selectedBook.getCategories()
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toList());
            categoriesList.getItems().addAll(categories);
            System.out.println(categoriesList.getItems().size());//0 why?
            fieldAddBookSize.setText(selectedBook.getSize());
            fieldAddBookAuthor.setText(selectedBook.getAuthor().getName());
            fieldAddBookPublisher.setText(selectedBook.getPublisher().getName());
            fieldAddBookColor.setText(selectedBook.getPaintColor());
            fieldAddBookIsbn.setText(selectedBook.getIsbn());
            fieldAddBookLocation.setText(selectedBook.getLocation()+"");
            fieldAddBookPages.setText(selectedBook.getPageNo()+"");
            fieldAddBookPart.setText(selectedBook.getPartNo()+"");
        }



}
