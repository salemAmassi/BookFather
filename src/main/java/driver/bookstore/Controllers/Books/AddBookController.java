package driver.bookstore.Controllers.Books;

import driver.bookstore.Author.Author;
import driver.bookstore.Author.AuthorRepository;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Category.CategoryRepository;
import driver.bookstore.CrudLogger;
import driver.bookstore.Publisher.Publisher;
import driver.bookstore.Publisher.PublisherRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static driver.bookstore.Controllers.Books.BooksController.areProductInputsValid;


public class AddBookController implements Initializable {

    BookRepository repository;
    @FXML
    public FlowPane fieldAddBookCategory;
    public Text viewBookResponse;
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
        List<Category> categories = new CategoryRepository().manager.createQuery("SELECT c from Category c", Category.class).getResultList();
        categories.stream().forEach(category -> fieldAddBookCategory.getChildren().add(new CheckBox(category.getName())));
        fieldAddBookSize.getItems().addAll("A4", "B5", "A5");
        fieldAddBookCover.getItems().addAll("Thick", "normal");
        fieldAddBookSource.getItems().addAll("Original", "other");
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
        if(!areProductInputsValid(fieldAddBookTitle.getText(), fieldAddBookIsbn.getText(), fieldAddBookAuthor.getText(), fieldAddBookPublisher.getText())){
            try{
                CrudLogger.getInstance().logWarning("Failure in adding book, Invalid Input");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // Creates list with checkboxes representing categories
        chosenCategories = fieldAddBookCategory.getChildren().stream().filter(checkbox -> ((CheckBox) checkbox).isSelected()).map(checkbox -> (new Category(((CheckBox) checkbox).getText()))).collect(Collectors.toList());

        // Extracts printColor from toggle group to either 0 or 1
        String paintColor = "0";
        if (colors.getSelectedToggle() == coloredChoice) {
            paintColor = "1";
        } else if (colors.getSelectedToggle() == bwChoice) {
            paintColor = "0";
        }

        System.out.println(fieldAddBookSize.getSelectionModel().getSelectedItem());
        // Creates author entity if no author with inserted name exists yet
        Author author = new Author(fieldAddBookAuthor.getText());
        author = authorRepository.addEntity(author);

        // Creates publisher entity if no author with inserted name exists yet
        Publisher publisher;
        if (fieldAddBookPublisher.getText().isEmpty()){
            publisher = new Publisher("Default");
        }else{
            publisher = new Publisher(fieldAddBookPublisher.getText());
        }
        publisher = publisherRepository.addEntity(publisher);


        int quantity;
        if(fieldAddBookQuantity.getText().isEmpty()){
            quantity = 1;
        }
        else{
            quantity = getValue(fieldAddBookQuantity.getText());
        }

        // Builds book entity with inserted values
        // 13 Columns
        Book book =
                Book.builder()
                        .name(fieldAddBookTitle.getText())                              //1
                        .author(author)                                                 //2
                        .price(getValue(fieldAddBookPrice.getText()))                   //3
                        .pageNo(getValue(fieldAddBookPages.getText()))                  //4
                        .partNo(getValue(fieldAddBookPart.getText()))                   //5
                        .cover(fieldAddBookCover.getSelectionModel().getSelectedItem()) //6
                        .paintColor(paintColor)//needs to set 0 and 1                   //7
                        .isbn(fieldAddBookIsbn.getText()) //isbn needs control          //8
                        .size(fieldAddBookSize.getSelectionModel().getSelectedItem())   //9
                        .categories(chosenCategories)                                   //10
                        .publisher(publisher)                                           //11
                        .location(getValue(fieldAddBookLocation.getText()))             //12
                        .quantity(quantity)                                             //13
                        .build();
        bookRepository.addEntity(book);
        viewBookResponse.setVisible(true);
        clearFields();
        try{
            // Log operation success
            CrudLogger.getInstance().logSuccess(book.getName()+"Has been added successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getValue(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    //clear all fields
    public void clearFields(){
        fieldAddBookTitle.setText("");
        fieldAddBookAuthor.setText("");
        fieldAddBookPages.setText("");
        fieldAddBookPrice.setText("");
        fieldAddBookPart.setText("");
        fieldAddBookLocation.setText("");
        fieldAddBookQuantity.setText("");
        fieldAddBookPublisher.setText("");
        coloredChoice.setSelected(false);
        bwChoice.setSelected(false);
        fieldAddBookCategory.getChildren()
                .stream()
                .filter(checkbox-> ((CheckBox) checkbox).isSelected())
                .forEach(checkbox-> ((CheckBox) checkbox).setSelected(false));
    }
}
