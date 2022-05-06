package driver.bookstore.Controllers;

import driver.bookstore.Category.CategoryFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    @FXML
    private Slider partNumField;

    @FXML
    private ComboBox<String> paperSizeField;

    @FXML
    private ComboBox<String> sourceField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField authNameField;

    @FXML
    private ComboBox<String> coverField;

    @FXML
    private TextField bookNameField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> printColorField;

    @FXML
    private FlowPane categoriesCheckBox;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField pageNumField;

    @FXML
    private TextField bNameConfirm;
    @FXML
    private TextField authConfirm;
    @FXML
    private TextField priceConfirm;

    @FXML
    private TextField partNumConfirm;

    @FXML
    private TextField sourceConfirm;

    @FXML
    private TextField pageNumConfirm;

    @FXML
    private TextField coverConfirm;

    @FXML
    private TextField categoryConfirm;
    @FXML
    private TextField colorConfirm;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField quantityConfirm;
    private ArrayList<CheckBox> categoriesChoices;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriesChoices = new ArrayList<>();
        try {
            getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private void getCategories() throws IOException {
    CategoryFactory factory = new CategoryFactory();
    CheckBox choice = factory.createCategory("أدب");
    categoriesChoices.add(choice);
}
/**add checkboxes according to the categories*/
private void getBookValues(){
    this.bNameConfirm.setText(this.bookNameField.getText());
    this.authConfirm.setText(this.authNameField.getText());
    this.priceConfirm.setText(this.priceField.getText());
    categoriesChoices.stream()
            .filter(choice-> choice.isSelected())
            .forEach(choice->categoryConfirm.appendText(choice.getText()+","));
    this.pageNumConfirm.setText(this.pageNumField.getText());
    this.partNumConfirm.setText(this.partNumField.getValue()+"");
    this.colorConfirm.setText(this.printColorField.getSelectionModel().getSelectedItem());
    this.coverConfirm.setText(this.coverField.getSelectionModel().getSelectedItem());
    this.sourceConfirm.setText(this.sourceField.getSelectionModel().getSelectedItem());
    this.quantityConfirm.setText(this.quantityField.getText());

}

/**adds (persists) a new book to database!*/
    public void addBookAction(ActionEvent event) {
    }
}
