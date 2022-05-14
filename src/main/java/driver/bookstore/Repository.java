package driver.bookstore;


import javax.persistence.Entity;

public interface Repository {
    Entity findEntity(int id);
    Entity updateEntity(Entity entity);
    void deleteEntity(Entity id);
    void addEntity(Entity entity);
    void close();
    Entity findEntity(String name);
}
