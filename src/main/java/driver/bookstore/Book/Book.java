package driver.bookstore.Book;

import driver.bookstore.Author.Author;
import driver.bookstore.Category.Category;
import driver.bookstore.Publisher.Publisher;
import lombok.*;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.List;
//                    "(Book b left join book_category on Book.id = book_category.bookId) " +
//            @NamedQuery(name = "Book.findByAuthor",
//            query = "SELECT b from Book b " +
//                    "where b.AuthorId"

/**Book Entity*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "book")
public class Book implements Entity{
    //TODO: CREATE ALL QUERIES FOR BOOK, CATEGORY.
    @Id @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
     int id;
    @Column(name = "name", nullable = false, length = 200,  unique=true)
     String name;

    @Column(name = "price", nullable = false, length = 3)
     int price;

    @Column(name = "partNo", length = 3)
     int partNo;
    @Column(name = "size",  length = 15)
     String size;
    @Column(name = "pageNo", length = 4)
     int pageNo;
    @Column(name = "paintColor")
     String paintColor;
    @Column(name = "cover", length = 15)
    String cover;
    @Column(name = "isbn", length = 15)
     String isbn;
    @Column(name = "quantity", length = 3)
     int quantity;
    @Column(name = "location", length = 3)
     int location;


    @ManyToOne
    private Author author;
//
//    //needs work!
    @ManyToOne
    private Publisher publisher;

    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ManyToMany()
        private List<Category> categories;

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

}
