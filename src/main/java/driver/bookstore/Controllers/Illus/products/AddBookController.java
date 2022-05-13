package driver.bookstore.Controllers.Illus.products;

import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


public class AddBookController extends BooksController {

    BookRepository repository;
    @FXML
    public ComboBox<Category> fieldAddBookCategory;
    public Text viewProductResponse;
    public TextField fieldAddBookTitle;
    public TextField fieldAddBookIsbn;
    public TextField fieldAddBookAuthor;
    public TextField fieldAddBookPrice;
    public TextField fieldAddBookPart;
    // TODO: Create size, cover, source entities
    public ComboBox fieldAddBookSize;
    public TextField fieldAddBookPages;
    public CheckBox fieldAddBookColor;
    public ComboBox fieldAddBookCover;
    public ComboBox fieldAddBookSource;
    public TextField fieldAddBookPublisher;
    public TextField fieldAddBookQuantity;
    public TextField fieldAddBookLocation;


    @FXML
    private void initialize() {
        //fieldAddProductCategoryId.setItems(FXCollections.observableArrayList(Datasource.getInstance().getProductCategories(Datasource.ORDER_BY_ASC)));
        // TODO: Format all fields
        TextFormatter<Double> textFormatterDouble = formatDoubleField();
        TextFormatter<Integer> textFormatterInt_page = formatIntField();
        TextFormatter<Integer> textFormatterInt_part = formatIntField();
        fieldAddBookPrice.setTextFormatter(textFormatterDouble);
        fieldAddBookPart.setTextFormatter(textFormatterInt_part);
        fieldAddBookPages.setTextFormatter(textFormatterInt_page);
    }

    /**
     * This private method handles the add product button functionality.
     * It validates user input fields and adds the values to the database.
     * @since                   1.0.0
     */
    @FXML
    private void btnAddBookOnAction() {
        //TODO: retrieve entities on initialize, Category, Size, Cover, Source
        Category category = fieldAddBookCategory.getSelectionModel().getSelectedItem();
        int cat_id = 0;
        if (category != null) {
            //cat_id = category.id();
        }

        assert category != null;
        //TODO: Fill validation with all items
        if (areProductInputsValid(fieldAddBookTitle.getText(), fieldAddBookAuthor.getText(),
                fieldAddBookPrice.getText(), fieldAddBookPart.getText(), cat_id)) {

            // TODO: Sync database items with entity and include all fields
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
}
