package driver.bookstore.Category;

import driver.bookstore.Book.Book;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**Crud operations on Category*/
public class CategoryRepository {
    public EntityManager manager;

   public CategoryRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }
    public void addEntity(Entity entity) {
       if(findEntity(entity.name())!=null){
        manager.getTransaction().begin();
        manager.persist(entity);//add
        manager.getTransaction().commit();
       }
    }
    public Category findEntity(String name) {
        return manager.find(Category.class,name);
    }

}
