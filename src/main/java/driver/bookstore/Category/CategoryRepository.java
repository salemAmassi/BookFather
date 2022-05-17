package driver.bookstore.Category;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**Crud operations on Category*/
public class CategoryRepository {
    public EntityManager manager;

   public CategoryRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
   }

    public Category addEntity(Category entity) {
        // If no category with inserted name exists yet, create new entity.
        if(findEntity(entity.getName()).size()==0){
            manager.getTransaction().begin();
            manager.persist(entity);//add
            manager.getTransaction().commit();
            return entity;
        }
        else
            // Otherwise, return existing category entity
            return  findEntity(entity.getName()).get(0);
    }

    public List<Category> findEntity(String name) {
        return  manager
                .createQuery("SELECT c from Category c where c.name = :name")
                .setParameter("name",name).getResultList();
    }

}
