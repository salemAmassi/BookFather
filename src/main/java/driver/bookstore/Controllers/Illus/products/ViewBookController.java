package driver.bookstore.Controllers.Illus.products;

import driver.bookstore.Category.Category;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
//import model.Categories;
//import model.Datasource;
//import model.Product;

/**
 * {@inheritDoc}
 */
public class ViewBookController extends BooksController {

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
        //fieldViewProductCategoryId.setItems(FXCollections.observableArrayList(Datasource.getInstance().getProductCategories(Datasource.ORDER_BY_ASC)));
    }

    /**
     * This method gets the data for one product from the database and binds the values to viewing fields.
     * @param product_id        Product id.
     * @since                   1.0.0
     */
    //TODO: Fix viewing controller
    public void fillViewingBookFields(int product_id) {
//        Task<ObservableList<Product>> fillProductTask = new Task<ObservableList<Product>>() {
//            @Override
//            protected ObservableList<Product> call() {
//                return FXCollections.observableArrayList(
//                        Datasource.getInstance().getOneProduct(product_id));
//            }
//        };
//        fillProductTask.setOnSucceeded(e -> {
//            viewProductName.setText("Viewing: " + fillProductTask.valueProperty().getValue().get(0).getName());
//            fieldViewProductName.setText(fillProductTask.valueProperty().getValue().get(0).getName());
//            fieldViewProductPrice.setText(String.valueOf(fillProductTask.valueProperty().getValue().get(0).getPrice()));
//            fieldViewProductQuantity.setText(String.valueOf(fillProductTask.valueProperty().getValue().get(0).getQuantity()));
//            fieldViewProductDescription.setText(fillProductTask.valueProperty().getValue().get(0).getDescription());
//
//            Categories category = new Categories();
//            category.setId(fillProductTask.valueProperty().getValue().get(0).getCategory_id());
//            category.setName(fillProductTask.valueProperty().getValue().get(0).getCategory_name());
//            fieldViewProductCategoryId.getSelectionModel().select(category);
//        });
//
//        new Thread(fillProductTask).start();
    }
}
