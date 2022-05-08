package driver.bookstore.Publisher;
import driver.bookstore.Book.Book;
import javax.persistence.*;
import java.util.List;

/**Publisher Entity*/
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

}
