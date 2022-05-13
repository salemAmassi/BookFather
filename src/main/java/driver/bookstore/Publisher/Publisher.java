package driver.bookstore.Publisher;
import driver.bookstore.Book.Book;
import javax.persistence.*;
import java.util.List;

/**Publisher Entity*/
@Entity
@Table(name = "publisher")

public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name", length = 200, unique=true)
    private String name;

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {

    }
//    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)
//    private List<Book> books;

    @Override
    public String toString() {
        return  name;
    }
}
