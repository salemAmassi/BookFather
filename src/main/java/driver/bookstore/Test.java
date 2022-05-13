package driver.bookstore;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        Persistence.createEntityManagerFactory("book_unit").createEntityManager();
//        BookRepository repo = new BookRepository();
//        List<Book> books = new BookRepository().getBookByCategory("رواية");
//        for (Book b: books) {
//            System.out.println(b.name());
//        }
//        ArrayList<String> n = new ArrayList<>();
//        n.add("فلسفة");
//        n.add("رواية");
//        System.out.println(repo.getBookByCategories(n).get(0));
//        System.out.println(
//                repo.manager.
//                createNativeQuery("SELECT count(id) FROM Category;", Category.class)
//                .getFirstResult());
        EntityManager manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
        System.out.println(
                manager.
                        createNativeQuery("SELECT max(c.id) FROM Book c ;", Category.class)
                        .getFirstResult());
    }
}
