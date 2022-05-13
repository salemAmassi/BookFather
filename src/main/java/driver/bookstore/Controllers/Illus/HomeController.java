package driver.bookstore.Controllers.Illus;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
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
    private BookRepository repo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         repo = new BookRepository();
        booksCount.setText(getBooksCount()+"");
        categoriesCount.setText(getCategoriesCount()+"");
    }
    private int getBooksCount(){
       return repo.manager.createNativeQuery("SELECT count(id) FROM Book;", Book.class).getFirstResult();
    }
    private int getCategoriesCount(){
        EntityManager manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
        return manager.createNativeQuery("SELECT max(c.id) FROM Book c ;", Category.class).getFirstResult();
    }

}
