package driver.bookstore.Controllers.Illus.products;

import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

/**
 * {@inheritDoc}
 */
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
        TextFormatter<Integer> textFormatterInt = formatIntField();
        fieldAddBookPrice.setTextFormatter(textFormatterDouble);
        fieldAddBookPart.setTextFormatter(textFormatterInt);
        fieldAddBookPages.setTextFormatter(textFormatterInt);
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
<<<<<<< Updated upstream
=======
        String size = "B5";
        System.out.println(fieldAddBookSize.getSelectionModel().getSelectedItem());
        Author author = new Author(fieldAddBookAuthor.getText());
        Publisher publisher = new Publisher(fieldAddBookPublisher.getText());
        author = authorRepository.addEntity(author);
        publisher = publisherRepository.addEntity(publisher);
        Book book =
                Book.builder()
            .name(fieldAddBookTitle.getText())
            .author(author)
            .price(getValue(fieldAddBookPrice.getText()))
            .pageNo(getValue(fieldAddBookPages.getText()))
            .partNo(getValue(fieldAddBookPart.getText()))
            .cover(fieldAddBookCover.getSelectionModel().getSelectedItem())
            .paintColor(paintColor)//needs to set 0 and 1
            .isbn(fieldAddBookIsbn.getText()) //isbn needs control
            .size(fieldAddBookSize.getSelectionModel().getSelectedItem())
            .categories(chosenCategories)
            .publisher(publisher)
            .location(getValue(fieldAddBookLocation.getText()))
            .build();
            bookRepository.addEntity(book);

            viewProductResponse.setVisible(true);
    }
        private int getValue(String input){
            try{
                return Integer.parseInt(input);
            }catch (NumberFormatException ex){
                return 0;
            }
    }
>>>>>>> Stashed changes

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
