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
    // Find book entity by id
    public Book findEntity(int id) {
        return manager.find(Book.class,id);
    }

    //needs refactoring
    @Override
    public Entity updateEntity(Entity entity) {
        Book temp = (Book) entity;
        Book toUpdateBook =  findEntity((temp.getName()));
        manager.getTransaction().begin();
        toUpdateBook.setName(temp.getName());
        toUpdateBook.setAuthor(temp.getAuthor());
        toUpdateBook.setCategories(temp.getCategories());
        toUpdateBook.setPageNo(temp.getPageNo());
        toUpdateBook.setPrice(temp.getPrice());
        toUpdateBook.setCover(temp.getCover());
        toUpdateBook.setIsbn(temp.getIsbn());
        toUpdateBook.setLocation(temp.getLocation());
        toUpdateBook.setPaintColor(temp.getPaintColor());
        toUpdateBook.setPublisher(temp.getPublisher());
        toUpdateBook.setQuantity(temp.getQuantity());
        toUpdateBook.setPartNo(temp.getPartNo());
        manager.getTransaction().commit();
        return toUpdateBook;
    }

    @Override
    public boolean deleteEntity(Entity entity) {
        Book book= ((Book) entity);
        manager.getTransaction().begin();
        manager.remove(findEntity( book.getName()));
        manager.getTransaction().commit();
        return true;
    }

    @Override
    public void addEntity(Entity entity) {
    manager.getTransaction().begin();
    manager.persist(entity);//add
    manager.getTransaction().commit();
    }

    //TODO: casting Object to Book
//    public List<Book> getBookByCategory(String categoryName){
//            TypedQuery<Book> query = manager.createQuery("SELECT " +
//                    "   distinct b " +
//                    "FROM " +
//                    "Book b join b.categories  c " +
//                    " where c.name = :categoryName ",Book.class).setParameter("categoryName",categoryName);
//    List<Book> books =  query.getResultList();
//    return books;
//
//    }

    public List<Book> getAllBooks(){
        return  manager.createQuery("SELECT b from Book b",Book.class).getResultList();
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
        List<Book> books =
                manager .createQuery("SELECT b from Book b where b.name = :name")
                        .setParameter("name",name).getResultList();
        if(!books.isEmpty())
            return (Book) books.get(0);
        return null;
    }
}
