package driver.bookstore.Publisher;

import driver.bookstore.Author.Author;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**Crud operations on Publisher*/
public class PublisherRepository {
    public EntityManager manager;

    public PublisherRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }
    public void addEntity(Publisher entity) {
        if(findEntity(entity.getName())!=null){
            manager.getTransaction().begin();
            manager.persist(entity);//add
            manager.getTransaction().commit();
        }
    }
    public Author findEntity(String name) {
        return manager.find(Author.class,name);
    }

}
