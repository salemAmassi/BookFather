package driver.bookstore.Author;

import driver.bookstore.Book.Book;

import javax.persistence.*;
import java.util.List;

/**Author Entity*/
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToMany(mappedBy= "authors")
    private List<Book> books;
}