package driver.bookstore.Controllers.Illus;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label booksCount;
    @FXML
    private Label categoriesCount;
    private BookRepository bookRepo;
    private CategoryRepository categoryRepo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         bookRepo = new BookRepository();
        categoryRepo = new CategoryRepository();
         getBooksCount();
        getCategoriesCount();
    }
    private void getBooksCount(){
       booksCount.setText(bookRepo.manager.createQuery("SELECT count(b.id) FROM Book b", Book.class).getResultList().get(0)+"");

    }
    private void getCategoriesCount(){
        categoriesCount.setText(categoryRepo.manager.createQuery("SELECT count(c.id) FROM Category c ", Category.class).getResultList().get(0)+"");
    }

}
