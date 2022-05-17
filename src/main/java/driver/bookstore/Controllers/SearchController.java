package driver.bookstore.Controllers;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchController {
    BookRepository bookRepository;

    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> search(String criteria,String searchValue){
        // Queries database based on different criteria, defined in UI
        criteria = criteria.toLowerCase(Locale.ROOT);

        switch (criteria){
            // Return books with title that includes searchValue in its string
            case "title":
                return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.name like :name")
                        .setParameter("name","%"+searchValue+"%").getResultList();

            // Return books with author that includes searchValue in its string
            case "author":
                return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.author in (SELECT a from Author a where a.name like :AuthorName)")
                        .setParameter("AuthorName","%"+searchValue+"%").getResultList();

            // Return books with price that matches searchValue
            case "price":
                return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.price = :price")
                        .setParameter("price",Integer.parseInt(searchValue)).getResultList();

            // If no criteria has been selected return empty list
            default:
                return new ArrayList<>();
        }
    }
}
