package driver.bookstore.Controllers;

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
                        .createQuery("SELECT b from Book b where b.name like :name")
                        .setParameter("name","%"+searchValue+"%").getResultList();
            case "author":
               return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.author in (SELECT a from Author a where a.name like :AuthorName)")
                        .setParameter("AuthorName","%"+searchValue+"%").getResultList();
            case "price":
                return bookRepository.manager
                        .createQuery("SELECT b from Book b where b.price = :price")
                        .setParameter("price",Integer.parseInt(searchValue)).getResultList();
            default:
                return null;
        }
    }
}
