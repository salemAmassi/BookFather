package driver.bookstore.Publisher;
import driver.bookstore.Book.Book;
import javax.persistence.*;
import java.util.List;

/**Publisher Entity*/
@Entity
@Table(name = "publisher")

public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", length = 200)
    private String name;
//    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)
//    private List<Book> books;

}
