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
    public Author addEntity(Author entity) {
        if(findEntity(entity.getName()).size()==0){
            manager.getTransaction().begin();
            manager.persist(entity);//add
            manager.getTransaction().commit();
            return entity;
        }
        else
            return (Author) findEntity(entity.getName()).get(0);
    }
    //TODO: You have provided an instance of an incorrect PK class for this find operation.  Class expected : class java.lang.Integer, Class received : class java.lang.String.
    public List findEntity(String name) {
        return manager.createQuery("SELECT a from Author  a where a.name=:name").setParameter("name", name).getResultList();
    }
}
