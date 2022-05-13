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
//@NamedQueries(
//        {
//                @NamedQuery(name = "Book.findAll", query = "SELECT b from Book b"),
//                @NamedQuery(name = "Book.findByCategory",
//                        query = "SELECT b " +
//                                "from Book b join b.categories Category c " +
//                                " where c.name = :categoryName")
//        })
//        @NamedNativeQueries(
//                @NamedNativeQuery(name = "Book.findByCategory",
//                        query = "SELECT b.name FROM book b " +
//                                "WHERE b.id EXISTS(" +
//                                "    SELECT bc.book_id " +
//                                "    FROM" +
//                                "        book_category bc" +
//                                "    JOIN category c ON" +
//                                "        bc.category_id = c.ID" +
//                                "    WHERE\n" +
//                                "        c.name = ?categoryName;\n" +
//                                "); ")
//        )

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

//
//    @JoinTable(
//            name = "book_author",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id")
//    )
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;
//
//    //needs work!
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;

    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
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
