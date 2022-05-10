package driver.bookstore.Category;

import driver.bookstore.Book.Book;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**Category Entity*/
@Entity
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", length = 100)
    private String name;
    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private List<Book> books;

    public Category(String name){
        this.name = name;
    }
}
