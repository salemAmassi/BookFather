package driver.bookstore.Controllers;

import driver.bookstore.Category.CategoryFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    private FlowPane categoriesCheckBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CategoryFactory factory = new CategoryFactory();
            factory.createCategory("أدب",categoriesCheckBox);
            factory.createCategory("سياسة",categoriesCheckBox);
            factory.createCategory("تنمية",categoriesCheckBox);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
