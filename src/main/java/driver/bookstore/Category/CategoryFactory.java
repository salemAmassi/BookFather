package driver.bookstore.Category;
import javafx.scene.control.CheckBox;
public class CategoryFactory {
    public CheckBox createCategory(String type) {
        return  new CheckBox(type);
    }
}
