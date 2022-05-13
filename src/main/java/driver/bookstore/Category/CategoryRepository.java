package driver.bookstore.Category;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**Crud operations on Category*/
public class CategoryRepository {
    public EntityManager manager;

   public CategoryRepository() {
        manager = Persistence.createEntityManagerFactory("book_unit").createEntityManager();
    }


}
