package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Author.AuthorRepository;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryRepository;
import driver.bookstore.Publisher.Publisher;
import driver.bookstore.Publisher.PublisherRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
//import model.Categories;
//import model.Datasource;
//import model.Product;

/**
 * {@inheritDoc}
 */
public class EditBookController implements Initializable {

    @FXML
    private ComboBox<String> coverEditField;
    @FXML
    private TextField isbnEditField;

    @FXML
    private TextField locationEditField;

    @FXML
    private TextField partEditField;

    @FXML
    private TextField publisherEditField;

    @FXML
    private TextField nameEditField;

    @FXML
    private ComboBox<String> sizeEditField;

    @FXML
    private Text viewBookResponse;

    @FXML
    private TextField pageEditField;

    @FXML
    private TextField authorEditField;

    @FXML
    private TextField quantityEditField;
    @FXML
    private TextField priceEditField;
    @FXML
    private FlowPane fieldEditBookCategory;
    @FXML
    private RadioButton coloredChoice;
    @FXML
    private RadioButton bwChoice;
    private Book selectedBook;
    ToggleGroup colors;
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    CategoryRepository categoryRepository;
    PublisherRepository publisherRepository;

    public EditBookController() {
        selectedBook = BooksController.selectedBook;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookRepository = new BookRepository();
        authorRepository = new AuthorRepository();
        categoryRepository = new CategoryRepository();
        publisherRepository = new PublisherRepository();
        fillEditingBookFields();
    }

private void getSelectedCategories(){
    //creates checkboxes:
    List<Category> categories =
            new CategoryRepository()
                    .manager
                    .createQuery("SELECT c from Category c",
                            Category.class)
                    .getResultList();
    categories.forEach(category ->
            fieldEditBookCategory.getChildren().add(new CheckBox(category.getName())));
    //filter the selected categories:
    List<String> selectedCategories =
            (List<String>) categoryRepository.manager
                     .createQuery("select c from Book b join b.categories c where b.name = :name")
                     .setParameter("name",selectedBook.getName())
                     .getResultList()
                     .stream()
                     .map(category->((Category)category).getName())
                     .collect(Collectors.toList());
    System.out.println(selectedCategories);
    fieldEditBookCategory.getChildren()
            .stream()
            .filter(
                    checkbox->selectedCategories.contains(((CheckBox) checkbox).getText()))
            .forEach(checkbox->((CheckBox)checkbox).setSelected(true));
}
    @FXML
    void btnEditBookOnAction(ActionEvent event) {
       List<Category> chosenCategories = fieldEditBookCategory.getChildren()
               .stream().filter(checkbox->((CheckBox)checkbox).isSelected())
               .map(checkbox->(new Category(((CheckBox) checkbox).getText()))).collect(Collectors.toList());
        String paintColor = "0";
        if(colors.getSelectedToggle() == coloredChoice){
            paintColor = "1";
        }else if(colors.getSelectedToggle() == bwChoice){
            paintColor = "0";
        }
        Author author = new Author(authorEditField.getText());
        Publisher publisher = new Publisher(publisherEditField.getText());
        author = authorRepository.addEntity(author);
        publisher = publisherRepository.addEntity(publisher);
        List<Category> categories = chosenCategories.stream()
                .map(category -> categoryRepository.addEntity(category)).collect(Collectors.toList());
        Book book =
                Book.builder()
                        .name(nameEditField.getText())
                        .author(author)
                        .price(getValue(priceEditField.getText()))
                        .pageNo(getValue(pageEditField.getText()))
                        .partNo(getValue(partEditField.getText()))
                        .cover(coverEditField.getSelectionModel().getSelectedItem())
                        .paintColor(paintColor)
                        .isbn(isbnEditField.getText())
                        .size(sizeEditField.getSelectionModel().getSelectedItem())
                        .categories(categories)
                        .publisher(publisher)
                        .location(getValue(locationEditField.getText()))
                        .build();
        bookRepository.updateEntity(book);
        viewBookResponse.setVisible(true);
    }
    private int getValue(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException ex){
            return 0;
        }
    }
    @FXML
    private void btnEditBookOnAction() {
//        Categories category = fieldEditProductCategoryId.getSelectionModel().getSelectedItem();
//        int cat_id = 0;
//        if (category != null) {
//            cat_id = category.getId();
//        }
//
//        assert category != null;
//        if (areProductInputsValid(fieldEditProductName.getText(), fieldEditProductDescription.getText(), fieldEditProductPrice.getText(), fieldEditProductQuantity.getText(), cat_id)) {
//
//            int productId = Integer.parseInt(fieldEditProductId.getText());
//            String productName = fieldEditProductName.getText();
//            String productDescription = fieldEditProductDescription.getText();
//            double productPrice = Double.parseDouble(fieldEditProductPrice.getText());
//            int productQuantity = Integer.parseInt(fieldEditProductQuantity.getText());
//            int productCategoryId = category.getId();
//
//            Task<Boolean> addProductTask = new Task<Boolean>() {
//                @Override
//                protected Boolean call() {
//                    return Datasource.getInstance().updateOneProduct(productId, productName, productDescription, productPrice, productQuantity, productCategoryId);
//                }
//            };
//
//            addProductTask.setOnSucceeded(e -> {
//                if (addProductTask.valueProperty().get()) {
//                    viewBookResponse.setVisible(true);
//                    System.out.println("Product edited!");
//                }
//            });
//
//            new Thread(addProductTask).start();
//        }
    }

    public void fillEditingBookFields() {
        sizeEditField.getItems().addAll("A4", "B5", "A5");
        sizeEditField.getSelectionModel().select(selectedBook.getSize());
        coverEditField.getItems().addAll("Thick", "normal");
        coverEditField.getSelectionModel().select(selectedBook.getCover());
        nameEditField.setText(selectedBook.getName());
        authorEditField.setText(selectedBook.getAuthor().getName());
        publisherEditField.setText(selectedBook.getPublisher().getName());
        priceEditField.setText(selectedBook.getPrice()+"");
        pageEditField.setText(selectedBook.getPageNo()+"");
        partEditField.setText(selectedBook.getPartNo()+"");
        quantityEditField.setText(selectedBook.getQuantity()+"");
        locationEditField.setText(selectedBook.getLocation()+"");
        isbnEditField.setText(selectedBook.getIsbn());
        colors = new ToggleGroup();
        coloredChoice.setToggleGroup(colors);
        bwChoice.setToggleGroup(colors);
        if(selectedBook.getPaintColor().equalsIgnoreCase(bwChoice.getText()))
            bwChoice.setSelected(true);
        else
            coloredChoice.setSelected(true);
        getSelectedCategories();
    }


}
