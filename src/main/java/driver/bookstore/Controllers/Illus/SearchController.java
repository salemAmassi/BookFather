package driver.bookstore.Controllers.Illus;

import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;

import java.util.List;
import java.util.Locale;

public class SearchController {
    BookRepository bookRepository;

    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public List<Book> search(String criteria,String searchValue){
//        List<Book> booksResult;
        criteria = criteria.toLowerCase(Locale.ROOT);
        switch (criteria){
            case "title":
            return
                        bookRepository.manager
                        .createQuery("SELECT b from Book b where b.name = :name")
                        .setParameter("name",searchValue).getResultList();
            case "author":
               return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.author.name = :AuthorName")
                        .setParameter("AuthorName",searchValue).getResultList();
            case "price":
                return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.price = :price")
                        .setParameter("price",Integer.parseInt(searchValue)).getResultList();
            default:
                return null;
        }
    }
}
