package driver.bookstore.Author;

import driver.bookstore.Book.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**Author Entity*/
@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", length = 100)
    private String name;
    @OneToMany(mappedBy= "author",cascade = CascadeType.ALL)
    private List<Book> books;
    public Author(String name){
        this.name = name;
    }
}
