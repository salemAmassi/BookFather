package driver.bookstore.Controllers;

import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryComponentFactory;
import driver.bookstore.Publisher.Publisher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import static driver.bookstore.Controllers.Illus.products.BooksController.formatDoubleField;
import static driver.bookstore.Controllers.Illus.products.BooksController.formatIntField;

public class AddBookController implements Initializable {
//    @FXML
//    private Slider partNumField;
//    @FXML
//    private ComboBox<String> paperSizeField;
//    @FXML
//    private ComboBox<String> sourceField;
//    @FXML
//    private TextField locationField;
//    @FXML
//    private TextField authNameField;
//    @FXML
//    private ComboBox<String> coverField;
//    @FXML
//    private TextField bookNameField;
//    @FXML
//    private TextField priceField;
//    @FXML
//    private ComboBox<String> printColorField;
//    @FXML
//    private FlowPane categoriesCheckBox;
//    @FXML
//    private Button confirmButton;
//    @FXML
//    private TextField pageNumField;
//    @FXML
//    private TextField bNameConfirm;
//    @FXML
//    private TextField authConfirm;
//    @FXML
//    private TextField priceConfirm;
//    @FXML
//    private TextField partNumConfirm;
//    @FXML
//    private TextField sourceConfirm;
//    @FXML
//    private TextField pageNumConfirm;
//    @FXML
//    private TextField coverConfirm;
//    @FXML
//    private TextField categoryConfirm;
//    @FXML
//    private TextField colorConfirm;
//    @FXML
//    private TextField quantityField;
//    @FXML
//    private TextField sizeConfirm;
//    @FXML
//    private TextField isbnConfirm;
//    @FXML
//    private Tab confirmAddTab;
//    @FXML
//    private TextField quantityConfirm;
//    private ArrayList<CheckBox> categoriesChoices;
    BookRepository repository;
    @FXML
    public ComboBox<Category> fieldAddBookCategory;
    public Text viewProductResponse;
    public TextField fieldAddBookTitle;
    public TextField fieldAddBookIsbn;
    public TextField fieldAddBookAuthor;
    public TextField fieldAddBookPrice;
    public TextField fieldAddBookPart;
    // TODO: Create size, cover, source entities for combobox
    public ComboBox fieldAddBookSize;
    public TextField fieldAddBookPages;
    public CheckBox fieldAddBookColor;
    public ComboBox fieldAddBookCover;
    public ComboBox fieldAddBookSource;
    public TextField fieldAddBookPublisher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new BookRepository();
//        categoriesChoices = new ArrayList<>();
//        try {
//            getCategories();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        confirmAddTab.setOnSelectionChanged(event -> getBookValues());
        repository = new BookRepository();
        
        
        //fieldAddProductCategoryId.setItems(FXCollections.observableArrayList(Datasource.getInstance().getProductCategories(Datasource.ORDER_BY_ASC)));
        // TODO: Format all fields
        TextFormatter<Double> textFormatterDouble = formatDoubleField();
        TextFormatter<Integer> textFormatterInt = formatIntField();
        fieldAddBookPrice.setTextFormatter(textFormatterDouble);
        fieldAddBookPart.setTextFormatter(textFormatterInt);
        fieldAddBookPages.setTextFormatter(textFormatterInt);

    }

    private void getCategories() throws IOException {
        CategoryComponentFactory factory = new CategoryComponentFactory();
        CheckBox choice = factory.createCategoryCheckBox("أدب");
        //categoriesChoices.add(choice);
    }
    /**add checkboxes according to the categories*/
    private void getBookValues(){
    }

    /**adds (persists) a new book to database!*/
        public void addBookAction(ActionEvent event) {
            Book book =
                    Book.builder()
                    .name(fieldAddBookTitle.getText())
                            .author(new Author(fieldAddBookAuthor.getText()))
                            .price(getValue(fieldAddBookPrice.getText()))
                            .pagNo(getValue(fieldAddBookPages.getText()))
                            .partNo(getValue(fieldAddBookPart.getText()))
                            .cover(fieldAddBookCover.getSelectionModel().getSelectedItem().toString())
                            .paintColor(fieldAddBookColor.isSelected())//needs to set 0 and 1
                            .isbn(fieldAddBookIsbn.getText()) //isbn needs control
                            .size(fieldAddBookSize.getSelectionModel().getSelectedItem().toString())
                            .categories(new ArrayList<Category>((Collection<? extends Category>) new Category(fieldAddBookCategory.getSelectionModel().getSelectedItem().toString())))
                            .publisher(new Publisher(fieldAddBookPublisher.getText()))
                            .build();
            repository.addEntity(book);
        }


private int getValue(String input){
     try{
            return Integer.parseInt(input);
     }catch (NumberFormatException ex){
         return 0;
     }

}

// ========= NEW CODE ======================================================

    @FXML
    private void btnAddProductOnAction() {
        //TODO: retrieve entities on initialize, Category, Size, Cover, Source
        Category category = fieldAddBookCategory.getSelectionModel().getSelectedItem();
        int cat_id = 0;
        if (category != null) {
            //cat_id = category.id();
        }

        assert category != null;
        //TODO: Fill validation with all items
//        if (areProductInputsValid(fieldAddBookTitle.getText(), fieldAddBookAuthor.getText(),
//                fieldAddBookPrice.getText(), fieldAddBookPart.getText(), cat_id)) {
//
//            // TODO: Sync database items with entity and include all fields
//            String booktitle = fieldAddBookTitle.getText();
//            String productDescription = fieldAddProductDescription.getText();
//            double productPrice = Double.parseDouble(fieldAddProductPrice.getText());
//            int productQuantity = Integer.parseInt(fieldAddProductQuantity.getText());
//            int productCategoryId = category.getId();
//
//            Task<Boolean> addProductTask = new Task<Boolean>() {
//                @Override
//                protected Boolean call() {
//                    return Datasource.getInstance().insertNewProduct(productName, productDescription, productPrice, productQuantity, productCategoryId);
//                }
//            };
//
//            addProductTask.setOnSucceeded(e -> {
//                if (addProductTask.valueProperty().get()) {
//                    viewProductResponse.setVisible(true);
//                    System.out.println("Product added!");
//                }
//            });
//
//            new Thread(addProductTask).start();
    }
}
