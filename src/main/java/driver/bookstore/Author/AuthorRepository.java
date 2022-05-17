package driver.bookstore.Author;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**Crud operations on Author*/
public class AuthorRepository {

    public EntityManager manager;
    public AuthorRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }

    // If no Author with inserted name exists yet, create new entity.
    public Author addEntity(Author entity) {
        if(findEntity(entity.getName()).size()==0){
            manager.getTransaction().begin();
            manager.persist(entity);//add
            manager.getTransaction().commit();
            return entity;
        }
        else
            // Otherwise, return existing author entity
            return (Author) findEntity(entity.getName()).get(0);
    }

    // Returns list of entities with queried name. Only one author should
    // return if no external input has been made on database
    public List findEntity(String name) {
        return manager.createQuery("SELECT a from Author  a where a.name=:name").setParameter("name", name).getResultList();
    }
}
