package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Author.AuthorRepository;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryRepository;
import driver.bookstore.Publisher.Publisher;
import driver.bookstore.Publisher.PublisherRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class AddBookController implements Initializable {

    BookRepository repository;
    @FXML
    public FlowPane fieldAddBookCategory;
    public Text viewProductResponse;
    public TextField fieldAddBookTitle;
    public TextField fieldAddBookIsbn;
    public TextField fieldAddBookAuthor;
    public TextField fieldAddBookPrice;
    public TextField fieldAddBookPart;
    public ComboBox<String> fieldAddBookSize;
    public TextField fieldAddBookPages;
    public ComboBox<String> fieldAddBookCover;
    public ComboBox<String> fieldAddBookSource;
    public TextField fieldAddBookPublisher;
    public TextField fieldAddBookQuantity;
    public TextField fieldAddBookLocation;
    @FXML
    private RadioButton coloredChoice;
    @FXML
    private RadioButton bwChoice;

    @FXML
    private Button btnAddBook;
        List<Category> chosenCategories;
        ToggleGroup colors;
        BookRepository bookRepository;
        AuthorRepository authorRepository;
        CategoryRepository categoryRepository;
        PublisherRepository publisherRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Category> categories = new CategoryRepository().manager.createQuery("SELECT c from Category c",Category.class).getResultList();
        categories.stream().forEach(category -> fieldAddBookCategory.getChildren().add(new CheckBox(category.getName())));
        fieldAddBookSize.getItems().addAll("A4","B5","A5");
        fieldAddBookCover.getItems().addAll("Thick","normal");
        fieldAddBookSource.getItems().addAll("Original","other");
        fieldAddBookCover.getSelectionModel().selectFirst();
        fieldAddBookSize.getSelectionModel().selectFirst();
        fieldAddBookSource.getSelectionModel().selectFirst();
        colors = new ToggleGroup();
        coloredChoice.setToggleGroup(colors);
        bwChoice.setToggleGroup(colors);
        bookRepository = new BookRepository();
        authorRepository = new AuthorRepository();
        categoryRepository = new CategoryRepository();
        publisherRepository = new PublisherRepository();
    }

    @FXML
    private void btnAddBookOnAction(){
        chosenCategories = fieldAddBookCategory.getChildren().stream().filter(checkbox->((CheckBox)checkbox).isSelected()).map(checkbox->(new Category(((CheckBox) checkbox).getText()))).collect(Collectors.toList());
        String paintColor = "0";
        if(colors.getSelectedToggle() == coloredChoice){
            paintColor = "1";
        }else if(colors.getSelectedToggle() == bwChoice){
            paintColor = "0";
        }
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
        }
        private int getValue(String input){
            try{
                return Integer.parseInt(input);
            }catch (NumberFormatException ex){
                return 0;
            }
    }

//
//    @FXML
//    private void initialize() {
//        //fieldAddProductCategoryId.setItems(FXCollections.observableArrayList(Datasource.getInstance().getProductCategories(Datasource.ORDER_BY_ASC)));
//        // TODO: Format all fields
//        TextFormatter<Double> textFormatterDouble = formatDoubleField();
//        TextFormatter<Integer> textFormatterInt_page = formatIntField();
//        TextFormatter<Integer> textFormatterInt_part = formatIntField();
//        fieldAddBookPrice.setTextFormatter(textFormatterDouble);
//        fieldAddBookPart.setTextFormatter(textFormatterInt_part);
//        fieldAddBookPages.setTextFormatter(textFormatterInt_page);
//    }
//
//    /**
//     * This private method handles the add product button functionality.
//     * It validates user input fields and adds the values to the database.
//     * @since                   1.0.0
//     */
//    @FXML
//    private void btnAddBookOnAction() {
//        //TODO: retrieve entities on initialize, Category, Size, Cover, Source
//        Category category = fieldAddBookCategory.getSelectionModel().getSelectedItem();
//        int cat_id = 0;
//        if (category != null) {
//            //cat_id = category.id();
//        }
//
//        assert category != null;
//        //TODO: Fill validation with all items
//        if (areProductInputsValid(fieldAddBookTitle.getText(), fieldAddBookAuthor.getText(),
//                fieldAddBookPrice.getText(), fieldAddBookPart.getText(), cat_id)) {
//
//            // TODO: Sync database items with entity and include all fields
////            String booktitle = fieldAddBookTitle.getText();
////            String productDescription = fieldAddProductDescription.getText();
////            double productPrice = Double.parseDouble(fieldAddProductPrice.getText());
////            int productQuantity = Integer.parseInt(fieldAddProductQuantity.getText());
////            int productCategoryId = category.getId();
////
////            Task<Boolean> addProductTask = new Task<Boolean>() {
////                @Override
////                protected Boolean call() {
////                    return Datasource.getInstance().insertNewProduct(productName, productDescription, productPrice, productQuantity, productCategoryId);
////                }
////            };
////
////            addProductTask.setOnSucceeded(e -> {
////                if (addProductTask.valueProperty().get()) {
////                    viewProductResponse.setVisible(true);
////                    System.out.println("Product added!");
////                }
////            });
////
////            new Thread(addProductTask).start();
//        }
//    }
}
