package driver.bookstore.Book;

import driver.bookstore.Author.Author;
import driver.bookstore.Publisher.Publisher;

import javax.persistence.*;
import java.util.List;

/**Book Entity*/
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ManyToMany(mappedBy= "books")
    private List<Author> authors;
    @ManyToOne
    private Publisher publisher;

    public Book() {
    }
}
