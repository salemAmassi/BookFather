package driver.bookstore.Category;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class CategoryFactory {
    public void createCategory(String type, FlowPane categoriesCheckBox) throws IOException {
        categoriesCheckBox.getChildren().add(new CheckBox(type));
    }
}
