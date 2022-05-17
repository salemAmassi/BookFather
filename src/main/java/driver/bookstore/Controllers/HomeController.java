package driver.bookstore.Controllers;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryRepository;
import driver.bookstore.Publisher.Publisher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label booksCount;
    @FXML
    private Label categoriesCount;
    @FXML
    private Label authorsCount;
    @FXML
    private Label publishersCount;
    private BookRepository bookRepo;
    private CategoryRepository categoryRepo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookRepo = new BookRepository();
        categoryRepo = new CategoryRepository();
        getFigures();
    }

    // Displays statistics about number of entities in database
    private void getFigures() {
        booksCount.setText(bookRepo.manager.createQuery("SELECT count(b.id) FROM Book b", Book.class).getResultList().get(0) + "");
        categoriesCount.setText(bookRepo.manager.createQuery("SELECT count(c.id) FROM Category c ", Category.class).getResultList().get(0) + "");
        authorsCount.setText(bookRepo.manager.createQuery("SELECT count(a.id) FROM Author a ", Author.class).getResultList().get(0) + "");
        publishersCount.setText(bookRepo.manager.createQuery("SELECT count(p.id) FROM Publisher p ", Publisher.class).getResultList().get(0) + "");
    }


}
