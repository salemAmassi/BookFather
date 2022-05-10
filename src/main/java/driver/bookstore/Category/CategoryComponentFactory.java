package driver.bookstore.Category;
import driver.bookstore.FxStore;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class CategoryComponentFactory {
    public CheckBox createCategoryCheckBox(String type) {
        return  new CheckBox(type);
    }
    public HBox createCategoryHBox(String type){
        Button goToCategory = new Button(type);
        goToCategory.setOnAction(event -> {
            File dashboard  = new File("src/main/resources/category.fxml");
            Pane root = null;
            try {
                root = new FXMLLoader(dashboard.toURI().toURL()).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene mainScene = new Scene(root);
            FxStore.primaryStage.setScene(mainScene);
        });
        HBox categoryBox = new HBox(goToCategory);
        categoryBox.setAlignment(Pos.CENTER);
        return categoryBox;
    }
}
