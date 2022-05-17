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
        // Creates list with selected checkboxes representing categories
        List<Category> chosenCategories = fieldEditBookCategory.getChildren()
               .stream().filter(checkbox->((CheckBox)checkbox).isSelected())
               .map(checkbox->(new Category(((CheckBox) checkbox).getText()))).collect(Collectors.toList());
        List<Category> categories = chosenCategories.stream()
                .map(category -> categoryRepository.addEntity(category)).collect(Collectors.toList());

        // Extracts printColor from toggle group to either 0 or 1
        String paintColor = "0";
        if(colors.getSelectedToggle() == coloredChoice){
               paintColor = "1";
           }else if(colors.getSelectedToggle() == bwChoice){
               paintColor = "0";
           }

        // Creates author entity if no author with inserted name exists yet
        Author author = new Author(authorEditField.getText());
        author = authorRepository.addEntity(author);

        // Creates publisher entity if no author with inserted name exists yet
        Publisher publisher = new Publisher(publisherEditField.getText());
        publisher = publisherRepository.addEntity(publisher);

        // Builds book entity with inserted values
        // 13 Columns
        Book book =
                Book.builder()
                        .name(nameEditField.getText())                                   //1
                        .author(author)                                                  //2
                        .price(getValue(priceEditField.getText()))                       //3
                        .pageNo(getValue(pageEditField.getText()))                       //4
                        .partNo(getValue(partEditField.getText()))                       //5
                        .cover(coverEditField.getSelectionModel().getSelectedItem())     //6
                        .paintColor(paintColor)                                          //7
                        .isbn(isbnEditField.getText())                                   //8
                        .size(sizeEditField.getSelectionModel().getSelectedItem())       //9
                        .categories(categories)                                          //10
                        .publisher(publisher)                                            //11
                        .location(getValue(locationEditField.getText()))                 //12
                        .quantity(getValue(quantityEditField.getText()))         //13
                        .build();
        bookRepository.updateEntity(book);  // Updates entity with new entity values
        viewBookResponse.setVisible(true);  // Notify interface with success
    }

    private int getValue(String input){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException ex){
            return 0;
        }
    }

    // Fill fields with existing book values
    public void fillEditingBookFields() {
        sizeEditField.getItems().addAll("A4", "B5", "A5");
        sizeEditField.getSelectionModel().select(selectedBook.getSize());               //1
        coverEditField.getItems().addAll("Thick", "normal");
        coverEditField.getSelectionModel().select(selectedBook.getCover());             //2
        nameEditField.setText(selectedBook.getName());                                  //3
        authorEditField.setText(selectedBook.getAuthor().getName());                    //4
        publisherEditField.setText(selectedBook.getPublisher().getName());              //5
        priceEditField.setText(selectedBook.getPrice()+"");                             //6
        pageEditField.setText(selectedBook.getPageNo()+"");                             //7
        partEditField.setText(selectedBook.getPartNo()+"");                             //8
        quantityEditField.setText(selectedBook.getQuantity()+"");                       //9
        locationEditField.setText(selectedBook.getLocation()+"");                       //10
        isbnEditField.setText(selectedBook.getIsbn());                                  //11
        colors = new ToggleGroup();
        coloredChoice.setToggleGroup(colors);
        bwChoice.setToggleGroup(colors);                                                //12
        if(selectedBook.getPaintColor().equalsIgnoreCase(bwChoice.getText()))
            bwChoice.setSelected(true);
        else
            coloredChoice.setSelected(true);
        getSelectedCategories();                                                        //13
    }
}
