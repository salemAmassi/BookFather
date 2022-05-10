package driver.bookstore.Controllers;
import driver.bookstore.Author.Author;
import driver.bookstore.Book.Book;
import driver.bookstore.Book.BookRepository;
import driver.bookstore.Category.Category;
import driver.bookstore.Publisher.Publisher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private Button searchButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addBookButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchCriteria;
    @FXML
    private Button homeButton;
    @FXML
    private Button sellBookButton;
    @FXML
    private TableView<Book> booksTable;
    @FXML //needs work!
    private TableColumn<Book, Category> categCol;
    @FXML
    private TableColumn<Book, Integer> locationCol;
    @FXML
    private TableColumn<Book, Author> authNCol;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private TableColumn<Book, Boolean> colorCol;
    @FXML
    private TableColumn<Book, Publisher> publisherCol;
    @FXML
    private Button updateButton;
    @FXML
    private TableColumn<Book, Integer> priceCol;
    @FXML
    private TableColumn<Book, String> bNameCol;

        BookRepository repository;



    @FXML
   public void searchHandle(ActionEvent event) {
        //needs work
//        repository.manager
//                .createNamedQuery("Book.findByCategory")
//                .setParameter("categoryName",searchField.getText());
        new SearchController().search();
    }

    @FXML
   public void deleteHandle(ActionEvent event) {

    }

    @FXML
   public void updateHandle(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        //needs work!
        authNCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        //needs work!
        categCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        try {
            Arrays.stream(Class.forName("driver.bookstore.Book.Book").getFields())
                    .forEach(field -> searchCriteria.getItems().add(field.getName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        MenuController controller = new MenuController();
        homeButton.setOnAction(event->controller.homeHandle(event));
        addBookButton.setOnAction(event -> controller.addHandle(event));
        sellBookButton.setOnAction(event -> controller.sellHandle(event));
        repository = new BookRepository();
        //now we need to load data by category!
//        repository.manager.createNamedQuery("Book.findByCategory").setParameter("category");

    }
}
