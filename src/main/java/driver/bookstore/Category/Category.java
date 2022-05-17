package driver.bookstore.Category;

import driver.bookstore.Book.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.List;

/**Category Entity*/
@Entity
@Table(name = "category")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Category.findAll",query = "SELECT c from Category c")
})
@Setter
@Getter
public class Category implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name", length = 100, unique=true)
    private String name;
    @ManyToMany(mappedBy = "categories",cascade = CascadeType.PERSIST)
    private List<Book> books;

    public Category(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
