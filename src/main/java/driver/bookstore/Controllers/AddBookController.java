package driver.bookstore.Controllers;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryComponentFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
private TextField sizeConfirm;
@FXML
private TextField isbnConfirm;
@FXML
private Tab confirmAddTab;

    @FXML
    private TextField quantityConfirm;
    private ArrayList<CheckBox> categoriesChoices;
    BookRepository repository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         repository = new BookRepository();
        categoriesChoices = new ArrayList<>();
        try {
            getCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        confirmAddTab.setOnSelectionChanged(event -> getBookValues());
        repository = new BookRepository();

    }

private void getCategories() throws IOException {
    CategoryComponentFactory factory = new CategoryComponentFactory();
    CheckBox choice = factory.createCategoryCheckBox("أدب");
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
    this.partNumConfirm.setText((int)(this.partNumField.getValue())+"");
    this.colorConfirm.setText(this.printColorField.getSelectionModel().getSelectedItem());
    this.coverConfirm.setText(this.coverField.getSelectionModel().getSelectedItem());
    this.sourceConfirm.setText(this.sourceField.getSelectionModel().getSelectedItem());
    this.quantityConfirm.setText(this.quantityField.getText());
}

/**adds (persists) a new book to database!*/
    public void addBookAction(ActionEvent event) {
ArrayList<Category> categories = new ArrayList<>();
        Arrays.stream(categoryConfirm.getText().split(","))
                .forEach(category-> categories.add(new Category(category)) );
        Book book =
                Book.builder()
                .name(bNameConfirm.getText())
                        .author(new Author(authConfirm.getText()))
                        .price(getValue(priceConfirm.getText()))
                        .pagNo(getValue(pageNumConfirm.getText()))
                        .partNo(getValue(partNumConfirm.getText()))
                        .cover(coverConfirm.getText())
//                        .paintColor(colorConfirm.getText())//needs to set 0 and 1
                        .isbn(isbnConfirm.getText()) //isbn needs control
                        .size(sizeConfirm.getText())
                        .categories(categories)
                .build();
        repository.addEntity(book);
    }


private int getValue(String input){
        if(input.isBlank())
            return 0;
        else
            return Integer.parseInt(input);
}
}
