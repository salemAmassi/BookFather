package driver.bookstore.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**Crud operations on Publisher*/
public class PublisherRepository {
    public EntityManager manager;

    public PublisherRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }
    public Publisher addEntity(Publisher entity) {
        // If no publisher with inserted name exists yet, create new entity.
        if(findEntity(entity.getName()).size()==0){
            manager.getTransaction().begin();
            manager.persist(entity);//add
            manager.getTransaction().commit();
            return entity;
        }else {
            // Otherwise, return existing publisher entity
            return (Publisher) findEntity(entity.getName()).get(0);
        }
    }

    // Returns list of entities with queried name. Only one publisher should
    // return if no external input has been made on database
    public List findEntity(String name) {
        return manager.createQuery("SELECT p from Publisher p where p.name=:name").setParameter("name", name).getResultList();
    }

}
