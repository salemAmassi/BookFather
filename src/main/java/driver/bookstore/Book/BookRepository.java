package driver.bookstore.Book;

import driver.bookstore.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**Crud operations on Book*/
public class BookRepository implements Repository {
    public EntityManager manager;
    public BookRepository(){
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }

    @Override
    public Book findEntity(Long id) {
        return manager.find(Book.class,id);
    }

    //needs refactoring
    @Override
    public Entity updateEntity(Entity entity) {
        Book temp = (Book) entity;
        Book toUpdateBook =  findEntity(((long) temp.getId()));
        manager.getTransaction().begin();
        toUpdateBook.setName(temp.getName());
//        toUpdateBook.setAuthors(temp.getAuthors());
//        toUpdateBook.setCategories(temp.getCategories());
        toUpdateBook.setPagNo(temp.getPagNo());
        toUpdateBook.setPrice(temp.getPrice());
        toUpdateBook.setCover(temp.getCover());
        toUpdateBook.setIsbn(temp.getIsbn());
        toUpdateBook.setLocation(temp.getLocation());
        toUpdateBook.setPaintColor(temp.isPaintColor());
//        toUpdateBook.setPublisher(temp.getPublisher());
        toUpdateBook.setQuantity(temp.getQuantity());
        temp.setPartNo(temp.getPartNo());
        manager.getTransaction().commit();
        return toUpdateBook;
    }

    @Override
    public void deleteEntity(Entity entity) {
        Book book= ((Book) entity);
        manager.getTransaction().begin();
        manager.remove(findEntity((long) book.getId()));
    }

    @Override
    public void addEntity(Entity entity) {
    manager.getTransaction().begin();
    manager.persist(entity);//add
    manager.getTransaction().commit();
    }
    //TODO: casting Object to Book
public List<Book> getBookByCategory(String categoryName){
        TypedQuery<Book> query = manager.createQuery("SELECT " +
                "   distinct b " +
                "FROM " +
                "Book b join b.categories  c " +
                " where c.name = :categoryName ",Book.class).setParameter("categoryName",categoryName);
List<Book> books =  query.getResultList();
return books;

}
public List<Book> getBookByCategories(ArrayList<String> categories){
    TypedQuery<Book> query = manager.createQuery("SELECT " +
            "   distinct b " +
            "FROM " +
            "        Book b join b.categories  c " +
            " where c.name in :categories ",Book.class).setParameter("categories",categories);
    return query.getResultList();
}
    @Override
    public void close() {
        manager.close();
    }

    @Override
    public Book findEntity(String name) {
        return manager.find(Book.class,name);
    }
}
